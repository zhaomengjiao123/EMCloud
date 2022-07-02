<template>
  <div class="ai-wrapper">
    <div class="block-title">异常信息统计</div>
    <div class="block-left">
      <div class="block-picker">
        <el-date-picker
          v-model="form.exceptionDate"
          class="block-date-picker"
          :editable="false"
          type="daterange"
          :picker-options="pickerOptions"
          range-separator="~"
          start-placeholder="开始时间"
          end-placeholder="结束时间"
          format="yyyy/MM/dd"
          value-format="yyyy-MM-dd"
        />
        <el-select v-model="proType.proTypeData"
                   @change="getOption"
                   placeholder="请选择产品类型">
          <el-option
            v-for="item in optionData"
            :key="item.product_type_id"
            :label="item.product_type_name"
            :value="item.product_type_id">
          </el-option>
        </el-select>


        <el-button-group class="block-button-picker">
          <el-button
            type="primary"
            size="medium"
            @click='getExceptionInfo("day")'
          >
            按天查询
          </el-button>
          <el-button
            type="primary"
            size="medium"
            @click='getExceptionInfo("mouth")'
          >
            按月查询
          </el-button>
        </el-button-group>

        <el-button-group>
          <el-button
            type="primary"
            size="medium"
            @click='getExceptionInfoDefault("day",7)'
          >
            近七天异常数据
          </el-button>
          <el-button
            type="primary"
            size="medium"
            @click='getExceptionInfoDefault("day",30)'
          >
            近30天异常数据
          </el-button>
          <el-button
            type="primary"
            size="medium"
            @click='getExceptionInfoDefault("mouth",180)'
          >
            近半年异常数据
          </el-button>
        </el-button-group>

        <div>
          <el-row class="el-row-report">
            <el-col class="el-col-report">
              <div id="chartLine" class="chartline"></div>
            </el-col>
            <el-col class="el-col-report">
              <div id="chartBar" class="chartbar"></div>
            </el-col>
          </el-row>
          <el-row class="el-row-report">
            <el-col class="el-col-report">
              <div id="chartAll" class="chartall"></div>
            </el-col>
          </el-row>
        </div>

      </div>
    </div>
  </div>


</template>

<script>


import {
  getExceptionByDay,
  getExceptionByMouth,
  getExceptionTypeByDay,
  getExceptionTypeByMouth, getProExceptionByDay, getProExceptionByMouth,
  getProType
} from "../../api";

export default {
  name: "ExceptionAnalysis",

  data(){
    return {
      pickerOptions: {//禁用今天之后的日期
        disabledDate(time) {
          return time.getTime() > Date.now();//禁用今天之后的日期
        }
      },
      startTime: '',
      endTime: '',
      provalue:'',//类型id
      prolabel:'',//类型名称
      form: {
        exceptionDate: '',
      },
      proType: {
        proTypeData: '',
      },
      optionData:[],
      optionname:[],
      title:'',
      color: '#000',
      color1: ['#3AA1FF', '#64BAFF', '#48a5ff', '#3b88e1', '#336fc2', '#325ea1'],
      color2: ['#3fa1ff', '#78e7fc', '#23d8bc', '#7af0c8', '#77ca45', '#addf83', '#ffd05a', '#e88657', '#e55757', '#f7a798', '#a566fb'],
      // 以下是用来存储接口返回的数据
      legendData: [],
      xAxisData: [],
      yAxisData: [],
      itemData: [],
      chartLine:null,
      chartBar:null,
      chartAll:null,

    }
  },
  mounted() {
    //初始化
    this.chartLine = this.$echarts.init(document.getElementById('chartLine'))
    this.chartLine.setOption(this.optionLine)

    this.chartBar = this.$echarts.init(document.getElementById('chartBar'))
    this.chartBar.setOption(this.optionBar)

    this.chartAll = this.$echarts.init(document.getElementById('chartAll'))
    this.chartAll.setOption(this.optionAll)

    this.getOptionInfo()
    this.proType.proTypeData=0
    this.getExceptionInfoDefault("day",7)

  },

  methods: {

    getDate(days){   //获取最近七天的起始数据
      var etime = new Date();
      var month =etime.getMonth() < 9 ? "0" + (etime.getMonth() + 1) : etime.getMonth() + 1;
      var date = etime.getDate() <= 9 ? "0" + etime.getDate() : etime.getDate();
      this.endTime = etime.getFullYear() + "-" + month + "-" + date;
      console.log(this.endTime)
      var stime = new Date(etime - days*24*60*60*1000);
      var month =stime.getMonth() < 9 ? "0" + (stime.getMonth() + 1) : stime.getMonth() + 1;
      var date = stime.getDate() <= 9 ? "0" + stime.getDate() : stime.getDate();
      this.startTime = stime.getFullYear() + "-" + month + "-" + date;
      console.log(this.startTime)
    },

    getExceptionInfoDefault(type,days){//默认
      // this.provalue=this.proType.proTypeData
      // console.log(this.provalue)
      this.getDate(days)
      this.getException(type)
      this.getExceptionType(type)
    },

    getExceptionInfo(type){
      this.startTime=this.form.exceptionDate[0]
      this.endTime=this.form.exceptionDate[1]
      this.getException(type)
      this.getExceptionType(type)
    },

    getOptionInfo(){
      getProType()
        .then(res => {
          this.optionData=res;
          this.optionData.push(
            {
              product_type_id:0,
              product_type_name:"全部产品"
            }
          )
        })
    },

    getOption (id) {
      this.prolabel = this.optionData.find(item => item.product_type_id === id).product_type_name

},

    getException(type) {
      let params = new URLSearchParams()
      params.append('starttime',this.startTime )
      params.append('endtime', this.endTime)
      this.xAxisData = [];
      this.yAxisData = [];
      this.provalue=this.proType.proTypeData
      console.log(this.provalue)
      if(this.provalue==0){  //默认显示所有产品的异常数量
        this.title="所有产品"
        if (type==='day'){  //按天获取异常
          this.xAxisData = this.getYearAndMonthAndDay(this.startTime, this.endTime)//获取x轴数据
          getExceptionByDay(params)
            .then(res => {
              if (res) {
                let time = []
                for (let i = 0; i < res.list.length; i++) {//不同类型
                  time[i] = res.list[i].time//拿到所有的时间
                }
                ;
                for (let i = 0; i < this.xAxisData.length; i++) {//不同类型
                  if (time.indexOf(this.xAxisData[i]) !== -1) {//判断该坐标轴数据是否在后端数据的时间里，如果没有，该坐标对应的数据为0
                    this.yAxisData[i] = res.list[time.indexOf(this.xAxisData[i])].count;//如果在里面，就让y轴的值等于x轴数据在time位置那个数据的count
                  } else {
                    this.yAxisData[i] = 0;
                  }
                }
                ;
                this.chartLine.setOption(this.optionLine)
                this.chartBar.setOption(this.optionBar)
                window.addEventListener("resize", function () {
                  this.chartLine.resize();
                }),
                  window.addEventListener("resize", function () {
                    this.chartBar.resize();
                  })
              }
            })
        }else if(type==='mouth'){ //按月获取异常
          this.xAxisData = this.getYearAndMonth(this.startTime, this.endTime)
          getExceptionByMouth(params)
            .then(res => {
              if (res) {
                let time = []
                for (let i = 0; i < res.list.length; i++) {//不同类型
                  time[i] = res.list[i].time
                };
                console.log(time)
                for (let i = 0; i < this.xAxisData.length; i++) {//不同类型
                  if (time.indexOf(this.xAxisData[i]) !== -1) {
                    this.yAxisData[i] = res.list[time.indexOf(this.xAxisData[i])].count;
                    console.log(this.yAxisData[i])
                  } else {
                    this.yAxisData[i] = 0;
                  }
                };

                this.chartLine.setOption(this.optionLine)
                this.chartBar.setOption(this.optionBar)
                window.addEventListener("resize", function () {
                  this.chartLine.resize();
                }),
                  window.addEventListener("resize", function () {
                    this.chartBar.resize();
                  })

              }
            })
        }
      }else{ //选择某个产品类型
        this.title=this.prolabel;
        params.append('product_type_id', this.provalue)
        if (type==='day'){  //按天获取异常
          this.xAxisData = this.getYearAndMonthAndDay(this.startTime, this.endTime)//获取x轴数据
          getProExceptionByDay(params)
            .then(res => {
              if (res) {
                let time = []
                for (let i = 0; i < res.list.length; i++) {//不同类型
                  time[i] = res.list[i].time//拿到所有的时间
                }
                ;
                for (let i = 0; i < this.xAxisData.length; i++) {//不同类型
                  if (time.indexOf(this.xAxisData[i]) !== -1) {//判断该坐标轴数据是否在后端数据的时间里，如果没有，该坐标对应的数据为0
                    this.yAxisData[i] = res.list[time.indexOf(this.xAxisData[i])].count;//如果在里面，就让y轴的值等于x轴数据在time位置那个数据的count
                  } else {
                    this.yAxisData[i] = 0;
                  }
                }
                ;

                this.chartLine.setOption(this.optionLine)
                this.chartBar.setOption(this.optionBar)
                window.addEventListener("resize", function () {
                  this.chartLine.resize();
                }),
                  window.addEventListener("resize", function () {
                    this.chartBar.resize();
                  })
              }
            })
        }else if(type==='mouth'){ //按月获取异常
          this.xAxisData = this.getYearAndMonth(this.startTime, this.endTime)
          getProExceptionByMouth(params)
            .then(res => {
              if (res) {
                let time = []
                for (let i = 0; i < res.list.length; i++) {//不同类型
                  time[i] = res.list[i].time
                };
                console.log(time)
                for (let i = 0; i < this.xAxisData.length; i++) {//不同类型
                  if (time.indexOf(this.xAxisData[i]) !== -1) {
                    this.yAxisData[i] = res.list[time.indexOf(this.xAxisData[i])].count;
                    console.log(this.yAxisData[i])
                  } else {
                    this.yAxisData[i] = 0;
                  }
                };

                this.chartLine.setOption(this.optionLine)
                this.chartBar.setOption(this.optionBar)
                window.addEventListener("resize", function () {
                  this.chartLine.resize();
                }),
                  window.addEventListener("resize", function () {
                    this.chartBar.resize();
                  })

              }
            })
        }

      }



    },


    getExceptionType(type) {
      let params = new URLSearchParams()
      params.append('starttime', this.startTime)
      params.append('endtime', this.endTime)
      this.legendData = [];
      this.xAxisData1 = [];
      //this.yAxisData = [];
      this.itemData = [];
      if(type==='day') {//获取不同类型的异常；按天获取
        this.xAxisData1 = this.getYearAndMonthAndDay(this.startTime, this.endTime)
        getExceptionTypeByDay(params)
          .then(res => {
            if (res) {
              console.log(res)
              for (let i = 0; i < res.list.length; i++) {
                this.legendData.push(res.list[i].name)//res.list[i].name是产品的类型
                let dataList = res.list[i].data;//res.list[i].data是每个产品类型的相关数据，包括时间和异常数量
                console.log(dataList)
                let time = [];
                for (let j = 0; j < dataList.length; j++) {
                  time[j] = dataList[j].time  //获取时间列表
                }
                let num = []
                for (let x = 0; x < this.xAxisData1.length; x++) {//这个地方参考上面一条折线的那个方法
                  if (time.indexOf(this.xAxisData1[x]) !== -1) {
                    num[x] = dataList[time.indexOf(this.xAxisData1[x])].count;
                  } else {
                    num[x] = 0;
                  }
                }
                let pointObj = {
                  name: res.list[i].name,
                  type: 'line',
                  data: num,
                  symbol: 'circle',
                  symbolSize: 4,
                };
                this.itemData.push(pointObj)
              }
              ;
              this.chartAll.setOption(this.optionAll)
              window.addEventListener("resize", function () {
                this.chartAll.resize();
              })
            }
          })
      }else if(type==='mouth'){ //获取不同类型的异常；按月获取
        this.xAxisData1 = this.getYearAndMonth(this.startTime, this.endTime)
        getExceptionTypeByMouth(params)
          .then(res => {
            if (res) {
              console.log(res)
              for (let i=0;i<res.list.length;i++){
                this.legendData.push(res.list[i].name)
                let dataList = res.list[i].data;
                console.log(dataList)
                let time=[];
                for (let j=0;j<dataList.length;j++) {
                  time[j]=dataList[j].time
                }
                let num=[]
                for (let x = 0; x < this.xAxisData1.length; x++) {//不同类型
                  if(time.indexOf(this.xAxisData1[x])!==-1){
                    num[x] = dataList[time.indexOf(this.xAxisData1[x])].count;
                  }else{
                    num[x] = 0;
                  }
                }
                let pointObj = {
                  name: res.list[i].name,
                  type: 'line',
                  data: num,
                  symbol: 'circle',
                  symbolSize: 4,
                };
                this.itemData.push(pointObj)
              };
              this.chartAll.setOption(this.optionAll)
              window.addEventListener("resize",function() {
                this.chartAll.resize();
              })
            }
          })
      }


    },

    getYearAndMonth(start, end) {  //获得一段时间的年，月，格式：yyyy-mm
      let result = [];
      const starts = start.split('-');
      const ends = end.split('-');
      let staYear = parseInt(starts[0]);
      let staMon = parseInt(starts[1]);
      const endYear = parseInt(ends[0]);
      const endMon = parseInt(ends[1]);
      result.push(staYear + "-" + starts[1]);
      // result.push(endYear+"-"+ends[1]);
      while (staYear <= endYear) {
        if (staYear === endYear) {
          let staMonStr = '';
          while (staMon < endMon) {
            staMon++;
            if (staMon < 10) {
              staMonStr = "0"+staMon;
            }else {
              staMonStr = staMon;
            }
            result.push(staYear + "-" + staMonStr);
          }
          staYear++;
        } else {
          staMon++;
          var staMonStr = "";
          if (staMon > 12) {
            staMon = 1;
            staYear++;
          }
          if (staMon < 10 ) {
            staMonStr = "0"+staMon;
          }else {
            staMonStr = staMon;
          }
          result.push(staYear+"-"+staMonStr);
        }
      }
      console.log(result)
      return result;
    },


    getYearAndMonthAndDay(begin, end){   //获得一段时间的年，月，格式：yyyy-mm-hh
      let arr1 = begin.split("-");   //这里可以换成/  就2020/01/1 这种
      let arr2 = end.split("-");
      let arr1_ = new Date();
      let arrTime = [];
      arr1_.setUTCFullYear(arr1[0], arr1[1] - 1, arr1[2]);
      let arr2_ = new Date();
      arr2_.setUTCFullYear(arr2[0], arr2[1] - 1, arr2[2]);
      let unixDb = arr1_.getTime();
      let unixDe = arr2_.getTime();
      for (let k = unixDb; k <= unixDe;) {
        arrTime.push(this.datetimeparse(k, 'YY-MM-DD'));
        k = k + 24 * 60 * 60 * 1000;
      }
      console.log(arrTime)
      return arrTime;
    },
    // 时间格式处理
    datetimeparse(timestamp, format, prefix) {
      if (typeof timestamp == 'string') {
        timestamp = Number(timestamp)
      };
      //转换时区
      let currentZoneTime = new Date(timestamp);
      let currentTimestamp = currentZoneTime.getTime();
      let offsetZone = currentZoneTime.getTimezoneOffset() / 60; //如果offsetZone>0是西区，西区晚
      let offset = null;
      //客户端时间与服务器时间保持一致，固定北京时间东八区。
      offset = offsetZone + 8;
      currentTimestamp = currentTimestamp + offset * 3600 * 1000

      let newtimestamp = null;
      if (currentTimestamp) {
        if (currentTimestamp.toString().length === 13) {
          newtimestamp = currentTimestamp.toString()
        } else if (currentTimestamp.toString().length === 10) {
          newtimestamp = currentTimestamp + '000'
        } else {
          newtimestamp = null
        }
      } else {
        newtimestamp = null
      };
      let dateobj = newtimestamp ? new Date(parseInt(newtimestamp)) : new Date()
      let YYYY = dateobj.getFullYear()
      let MM = dateobj.getMonth() > 8 ? dateobj.getMonth() + 1 : '0' + (dateobj.getMonth() + 1)
      let DD = dateobj.getDate() > 9 ? dateobj.getDate() : '0' + dateobj.getDate()
      let HH = dateobj.getHours() > 9 ? dateobj.getHours() : '0' + dateobj.getHours()
      let mm = dateobj.getMinutes() > 9 ? dateobj.getMinutes() : '0' + dateobj.getMinutes()
      let ss = dateobj.getSeconds() > 9 ? dateobj.getSeconds() : '0' + dateobj.getSeconds()
      let output = '';
      let separator = '/'
      if (format) {
        separator = format.match(/-/) ? '-' : '/'
        output += format.match(/yy/i) ? YYYY : ''
        output += format.match(/MM/) ? (output.length ? separator : '') + MM : ''
        output += format.match(/dd/i) ? (output.length ? separator : '') + DD : ''
        output += format.match(/hh/i) ? (output.length ? ' ' : '') + HH : ''
        output += format.match(/mm/) ? (output.length ? ':' : '') + mm : ''
        output += format.match(/ss/i) ? (output.length ? ':' : '') + ss : ''
      } else {
        output += YYYY + separator + MM + separator + DD
      }
      output = prefix ? (prefix + output) : output

      return newtimestamp ? output : ''
    },

  },
  computed:{
    optionLine() {   //线形图的option
      return{
        title: {
          text:this.title+ '异常数量',
          textStyle: {
            color: '#1f2d3d'
          },
          left: 'center'
        },
        grid: {
          top: '20%',
          left: '6%',
          right: '8%',
          bottom: '3%',
          containLabel: true
        },
        xAxis: {
          type: 'category',
          data: this.xAxisData,
          boundaryGap: false,
          axisLine: {
            lineStyle: {
              color: '#8595a8'
            }
          },
          axisTick: {
            //去掉x轴刻度
            //show: false
          },
          name: '日期'
        },
        yAxis: {
          type: 'value',
          name: '数量',
          axisLine: {
            lineStyle: {
              color: '#8595a8'
            }
          },
          axisTick: {
            show: false
          },
          //去除网格线
          splitLine: {
            show: false
          },
          //最小刻度设置为1
          minInterval: 1
        },
        series: [
          {
            data: this.yAxisData,
            type: 'line',
            lineStyle: {
              color: '#004C63'
            },
            itemStyle: {
              color: '#004C63'
            },
            //标记的图形（拐点）
            symbol: 'circle',
            //大小
            symbolSize: 5
          }
        ],
        tooltip: {
          trigger: 'axis'
        }

      };
    },

    optionBar() {     //柱状图的option
      return{
        title: {
          text: this.title+'异常数量',
          textStyle: {
            color: '#1f2d3d'
          },
          left: 'center'
        },
        grid: {
          top: '20%',
          left: '6%',
          right: '8%',
          bottom: '3%',
          containLabel: true
        },
        xAxis: {
          type: 'category',
          data: this.xAxisData,
          // boundaryGap: false,
          axisLine: {
            lineStyle: {
              color: '#8595a8'
            }
          },
          axisTick: {
            //去掉x轴刻度
            show: false
          },
          name: '日期'
        },
        yAxis: {
          type: 'value',
          name: '数量',
          axisLine: {
            lineStyle: {
              color: '#8595a8'
            }
          },
          axisTick: {
            show: false
          },
          //去除网格线
          splitLine: {
            show: false
          },
          //最小刻度设置为1
          minInterval: 1
        },
        series: [
          {
            data: this.yAxisData,
            type: 'bar',
            lineStyle: {
              color: '#004C63'
            },
            itemStyle: {
              color: '#a73ef6'
            },
            //标记的图形（拐点）
            symbol: 'circle',
            //大小
            symbolSize: 5
          }
        ],
        tooltip: {
          trigger: 'axis'
        }

      };
    },
    optionAll() {    //不同类型线形图的option
      return{
        title: {
          text: '不同类型产品异常数量',
          textStyle: {
            color: '#1f2d3d'
          },
          left: 'center'
        },
        grid: {
          top: '20%',
          left: '6%',
          right: '8%',
          bottom: '3%',
          containLabel: true
        },
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            type: 'shadow'
          }
        },
        legend: {
          // orient: 'vertical',
          left: 'left',
          data: this.legendData,
          // icon: 'rect',
          // itemWidth: 10,
          // itemHeight: 10,
          // itemGap: 20,
          // left: 30
        },
        xAxis: [{
          name:'日期',
          type: 'category',
          axisLine: {
            show: false
          },
          axisTick: {
            show: false
          },
          axisLabel: {
            color: '#9AA1A9'
          },
          boundaryGap: false,
          data: this.xAxisData1
        }],
        yAxis: [{
          name: '数量',
          type: 'value',
          axisLine: {
            show: false
          },
          axisTick: {
            show: false
          },
          axisLabel: {
            color: '#9AA1A9'
          }
        }],
        dataZoom: [{
          show: true,
          height: 15,
          xAxisIndex: [0],
          bottom: 30,
          handleIcon: 'path://M306.1,413c0,2.2-1.8,4-4,4h-59.8c-2.2,0-4-1.8-4-4V200.8c0-2.2,1.8-4,4-4h59.8c2.2,0,4,1.8,4,4V413z',
          handleSize: '110%',
          handleStyle: {
            color: "#e3dbf5",
          },
          textStyle:{
            color:"rgba(204,187,225,0.5)",
          },
          fillerColor:"rgba(221,218,245,0.4)",
          borderColor: "rgba(204,187,225,0.5)",

        }, {
          type: "inside",
          show: true,
          height: 15,
          start: 1,
          end: 35
        }],
        series: this.itemData
      };



    },
  }


}
</script>

<style scoped>
.chartbar {
  border:2px solid #ee0909;
  width: 100%;
  height: 100%;
}
.chartline {
  border:2px solid #ee0909;
  width: 100%;
  height: 100%;
}
.chartall {
  border:2px solid #d427cf;
  width: 100%;
  height: 100%;
}
.el-row-report {
  width: 100%;
  height: auto;
  display: flex;
}
.el-col-report {
  height: 250px;
  margin-top: 10px;
  margin-right: 20px;
  flex: 1;
}

</style>


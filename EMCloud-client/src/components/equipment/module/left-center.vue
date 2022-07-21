<template>
  <div class="content">
<!--    <dv-active-ring-chart :config="configPie" :style="{ height: '100%', width: '100%' }"/>-->
        <div id="left-center" :style="{ height: '90%', width: '90%' }"/>

  </div>

</template>

<script>

import {getAbnormalEquipmentNum, getAllExceptionCount, getOnlineAndNotOnlineEquipmentNum} from "../../../api";

export default {
  name:'left-center',
  components: {},
  data() {
    return {
      chart:null,
      count:0,
      options:{},
      configPie:{},
      countUserNumData: {
        abnormalEquipmentCount: 0,
        onlineNum: 0,
        offlineNum: 0,
        totalNum: 0
      },
      timer: null
    };
  },
  created() {
    this.getData();
  },
  mounted() {
   // this.timer = setInterval(this.getData, 3000);
  },

  beforeDestroy() {
    this.clearData()

  },
  methods: {
    clearData() {
      if (this.timer) {
        clearInterval(this.timer)
        this.timer = null
      }
    },
    getData() {
      this.count++;
      console.log("count:",this.count)
      this.pageflag = true
      // this.pageflag =false
      let _this=this
      getOnlineAndNotOnlineEquipmentNum().then(res=>{
        console.log("res:::",res)
        for(let i=0;i<res.length;i++){
          if(res[i].equipstate==1){
            _this.countUserNumData.onlineNum=res[i].count
          }else if(res[i].equipstate==0){
            _this.countUserNumData.offlineNum=res[i].count
          }
        }

        this.init();

      });

      getAbnormalEquipmentNum().then(res=>{
        console.log("HHHH:",res)
        _this.countUserNumData.abnormalEquipmentCount=res.abnormalEquipmentCount
        this.init()
      });

     this.switper();

    },
    //轮询
    switper() {
      if (this.timer) {
        return
      }
      let looper = (a) => {
        this.getData()
      };
      //setInterval 循环执行
      this.timer = setInterval(looper, 10000);

      this.chart = this.chart.on('mouseover', params => {
         this.clearData()
       });
      this.chart.on('mouseout', params => {
        this.timer = setInterval(looper, 3000);
      });
      this.chart.setOption(this.options)
    },
    init() {
      let total = this.countUserNumData.offlineNum+this.countUserNumData.onlineNum+this.countUserNumData.abnormalEquipmentCount;
      //let total = 100;
      this.chart = this.$echarts.init(document.getElementById('left-center'));

      let colors = ["#ECA444", "#33A1DB", "#56B557"];
      let piedata = {
        name: "用户总览",
        type: "pie",
        radius: ["42%", "65%"],
        avoidLabelOverlap: false,
        itemStyle: {
          borderRadius: 4,
          borderColor: "rgba(206,124,124,0)",
          borderWidth: 2,
        },
        data: [
          {
            value: this.countUserNumData.abnormalEquipmentCount,
            name: "异常",
            label: {
              shadowColor: colors[0],
            },
          },
          {
            value: this.countUserNumData.onlineNum,
            name: "在线",
            label: {
              shadowColor: colors[1],
            },
          },
          {
            value: this.countUserNumData.offlineNum,
            name: "离线",
            label: {
              shadowColor: colors[2],
            },
          },
        ],
      };
      this.options = {
        title: {
          // zlevel: 0,
          text: ["{value|" + total + "}", "{name|总数}"].join("\n"),
          top: "center",
          left: "center",
          textStyle: {
            rich: {
              value: {
                color: "#ffffff",
                fontSize: 24,
                fontWeight: "bold",
                lineHeight: 20,
              },
              name: {
                color: "#ffffff",
                lineHeight: 20,
              },
            },
          },
        },
        tooltip: {
          trigger: "item",
          backgroundColor: "rgba(0,0,0,.6)",
          borderColor: "rgba(147, 235, 248, .8)",
          textStyle: {
            color: "#FFF",
          },
        },
        legend: {
          show: false,
          top: "5%",
          left: "center",
        },
        series: [
          //展示圆点
          {
            ...piedata,
            tooltip: { show: true },
            label: {
              formatter: "{b|{b}}\n {per|{d}%}",
              //   position: "outside",
              rich: {
                b: {
                  color: "#fff",
                  fontSize: 12,
                  lineHeight: 26,
                },
                c: {
                  color: "#31ABE3",
                  fontSize: 12,
                },
                per: {
                  color: "#31ABE3",
                  fontSize: 12,
                },
              },
            },
            labelLine: {
              length: 10, // 第一段线 长度
              length2: 15, // 第二段线 长度
              show: true,
              emphasis: {
                show: true,
              },
            },
          },
          {
            ...piedata,
            tooltip: { show: true },
            itemStyle: {},
            label: {
              backgroundColor: "auto", //圆点颜色，auto：映射的系列色
              height: 0,
              width: 0,
              lineHeight: 0,
              borderRadius: 2.5,
              shadowBlur: 8,
              shadowColor: "auto",
              padding: [2.5, -2.5, 2.5, -2.5],
            },
            labelLine: {
              length: 20, // 第一段线 长度
              length2: 36, // 第二段线 长度
              show: false,
            },
          },
        ],
      };




        this.configPie = {
        lineWidth: 20,
        color:colors,
        data: [
          {
            value: this.countUserNumData.abnormalEquipmentCount,
            name: "异常"
          },
          {
            value: this.countUserNumData.onlineNum,
            name: "在线"
          },
          {
            value: this.countUserNumData.offlineNum,
            name: "离线"
          },
        ],

      };
        this.chart.setOption(this.options)
    },

  },
};
</script>
<style lang='scss' scoped>
.content{
  height: 100%;
  width: 100%;
  //margin-bottom: 20px;
  //position: center;

}
</style>

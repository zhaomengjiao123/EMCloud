<template>

  <div class="content" >
<!--    <div class="info">-->
      <dv-border-box8 class="info">
<<<<<<< HEAD
        <div class="header" >
          {{company.company_name}}公司
        </div>
        <div class="up">
          <div
            class="item-warning" @click="toInfoList">
            <p class="colorBlue fw-b" style="text-align: center;color: blue">
              预警数
            </p>
            <span>{{warningNum}}</span>
=======
        <div class="up">
          <div
            class="bg-color-black item"
            v-for="item in titleItem"
            :key="item.title" @click="test">
            <p class="colorBlue fw-b" style="text-align: center;color: white">
              {{ item.title }}
            </p>
            <span>{{item.number}}</span>
            <div>
              <dv-digital-flop
                :config="item.number"
                style="width:100%; height: 100%; text-align: center" />
              <!-- padding:0rem 0.9rem 0rem 0.9rem; -->
>>>>>>> b8a4ca62808d71934c6fae2f8a46e691c26817b4
            </div>
          <div
            class="item-erro" @click="toInfoList">
            <p class="colorBlue fw-b" style="text-align: center;color: IndianRed">
             报警数
            </p>
            <span>{{erroNum}}</span>
          </div>
<<<<<<< HEAD
          <div
            class="item-emergency" @click="toInfoList">
            <p class="colorBlue fw-b" style="text-align: center;color: red">
              紧急警告数
            </p>
            <span>{{emergencyNum}}</span>
          </div>
          </div>
        <el-button class="moreBtn" type="primary" size="mini">查看更多>></el-button>
=======
        </div>>
>>>>>>> b8a4ca62808d71934c6fae2f8a46e691c26817b4

      </dv-border-box8>
<!--    </div>-->
    <div id="bmap" class="bmap" :style="{ height: '100%', width: '100%' }">城市地图</div>

  </div>


</template>

<script>

import {loadBMap} from "../../libs/loadBMap.js"
import {getEmergencyNumByCid, getEquipmentCountOfCompany, getErroNumByCid, getWarningNumByCid} from "../../api";
import cityData from "../../assets/map/china_city.json";
import china from "../../assets/map/china.json"


export default {
  name: "equipment_cityMap",
  data() {
    return {
<<<<<<< HEAD
      company:{
        company_name:' ',
        company_id:' '
      },
      tempObj:{
        company_name:'nna',
        company_id:'2022'
      },
      cityMap: null,
      option: {},
      warningNum:'0',
      erroNum:'0',
      emergencyNum:'0',
      companyEquipmentData:[],
=======
      cityMap: null,
      option: {},
      titleItem: [
        {
          title: "报警警告",
          number: {
            number: [120],
            toFixed: 1,
            content: "{nt}",
            style: {
              fontSize: 15,
              fill: "#ffffff",
            },
          },
        },
        {
          title: "预警警告",
          number: {
            number: [18],
            toFixed: 1,
            content: "{nt}",
            style: {
              fontSize: 15,
              fill: "#3de7c9",
            },
          },
        },
        {
          title: "紧急警告",
          number: {
            number: [2],
            toFixed: 1,
            content: "{nt}",
            style: {
              fontSize: 15,
              fill: "#3de7c9",
            },
          },
        },

      ],
      config: {
        header: ["主办单位", "数量"],
        data: [
          ['<span style="color:#9fe6b8;">海淀区政府</span>', "23"],
          ['<span style="color:#9fe6b8;">市发展改革委</span>', "26"],
          ['<span style="color:#9fe6b8;">市民政局</span>', "35"],
          ['<span style="color:#9fe6b8;">市城市管理委</span>', "39"],
          ['<span style="color:#9fe6b8;">市规划自然资源委</span>', "39"],
          ['<span style="color:#9fe6b8;">市住房城乡建设委</span>', "45"],
          ['<span style="color:#9fe6b8;">市公安局</span>', "53"],
          ['<span style="color:#9fe6b8;">市教委</span>', "55"],
          ['<span style="color:#9fe6b8;">市卫生健康委</span>', "60"],
          ['<span style="color:#9fe6b8;">市交通委</span>', "153"],
        ],
        rowNum: 5, //表格行数
        headerHeight: 35,
        headerBGC: "#0f1325", //表头
        oddRowBGC: "#0f1325", //奇数行
        evenRowBGC: "#171c33", //偶数行
        index: true,
        columnWidth: [30,150,50],
        align: ["center"],
        waitTime: 3000,
        carousel: "page",
      },


>>>>>>> b8a4ca62808d71934c6fae2f8a46e691c26817b4
    }

  },
  created() {
    this.getCompanyData()
  },
  mounted() {
    this.$nextTick(() => {
      loadBMap("XMpRSRWH7DbM5A5gL8ShHhWWOG2XAuIg").then(() => {
        this.initEcharts()
      });
        this.cityMap.on('click', this.cityMapClick);
    })

  },
<<<<<<< HEAD
  watch:{

  },
  computed:{

  },
=======
>>>>>>> b8a4ca62808d71934c6fae2f8a46e691c26817b4
  methods: {
    // 初次加载绘制地图
    initEcharts() {
      //地图容器
      this.cityMap = this.$echarts.init(document.getElementById('bmap'));
      this.option = {
        title: {
          text: '',
          left: 'center',
          textStyle: {
            color: '#fff'
          }
        },
        tooltip: {
          trigger: 'item',
          formatter: function (res) {
            console.log("RESRESRES::",res)
            return res.data.company_name + '公司总设备数为：' + res.value[2]
          }
        },
        bmap: {
          center: [104.114129, 37.550339],
          zoom: 6,
          roam: true
        },
        series: [
          {
            name: '测试',
            type: 'effectScatter',
            coordinateSystem: 'bmap',
            data: this.getPointData(this.companyEquipmentData),
            symbolSize: function (val) {
              return val[2]*2
            },
            rippleEffect: {
              brushType: 'stroke'
            },
            itemStyle: {
              normal: {
                color: '#ff0000',
                shadowBlur: 10,
                shadowColor: '#333'
              }
            }
          }
        ]
      }
      this.cityMap.setOption(this.option)
      // 点击每个站点可以进行相关操作
      let _this=this
      this.cityMap.on('click', function (e) {
<<<<<<< HEAD
        console.log("EEE:",e.data)
        console.log("信息:",e.data.company_name) //  每个标识点的信息
        console.log("Company_name:",_this.company.company_name) //  每个标识点的信息
        _this.company.company_name=e.data.company_name
        _this.company.company_id=e.data.value[3]
        console.log("ID:",_this.company.company_id) //  每个标识点的信息

        _this.getCompanyWarningNum()
        _this.getCompanyErroNum()
        _this.getCompanyEmergencyNum()


=======
        console.log(e) //  每个标识点的信息
>>>>>>> b8a4ca62808d71934c6fae2f8a46e691c26817b4
      })

    },
    cityMapClick(params){
      //console.log("点击的点是；",)
      console.log("KKKK:",params.name)
    },
    test(params){
      console.log("click:",params.target.innerText)
<<<<<<< HEAD
      let type = params.target.innerText
      if(type== "预警数"||type=="报警数"||type=="紧急警告数") {
        if (this.company.company_id == ' ') {
          this.$message({
            message: '请先选择一个公司查看',
            type: 'warning'
          });
        } else {
          if (type == "预警数") {
            this.$router.push({path: "/equipment/warning", query: {company_id: this.company.company_id}})
          } else if (type == "报警数") {
            this.$router.push({path: "/equipment/erro", query: {company_id: this.company.company_id}})
          } else if (type == "紧急警告数") {
            this.$router.push({path: "/equipment/emergency", query: {company_id: this.company.company_id}})
          }
          //
        }
      }

    },

    //查询某所有公司及其拥有的设备数量
    getCompanyData(){
      getEquipmentCountOfCompany().then(res=>{
        console.log("所有公司的设备：",res)
        this.companyEquipmentData = res
        console.log(this.companyEquipmentData)
        this.initEcharts()
      })
    },

    //查询某公司设备预警数量
    getCompanyWarningNum(){
      let params = new URLSearchParams();
      params.append("company_id",this.company.company_id)
      getWarningNumByCid(params).then(res=>{
        console.log("此公司的预警数量：",res)
        this.warningNum = res
      })
    },
    //查询某公司设备报警数量
    getCompanyErroNum(){
      let params = new URLSearchParams();
      params.append("company_id",this.company.company_id)
      getErroNumByCid(params).then(res=>{
        console.log("此公司的报警数量：",res)
        this.erroNum = res
      })
    },
    //查询某公司设备紧急告警数量
    getCompanyEmergencyNum(){
      let params = new URLSearchParams();
      params.append("company_id",this.company.company_id)
      getEmergencyNumByCid(params).then(res=>{
        console.log("此公司的紧急预警数量：",res)
        this.emergencyNum = res
      })
    },



    getPointData(numData){ 				//通过该方法获取自己数据中各地区的经纬度
      numData = this.companyEquipmentData
      let geoCoordMap = {}
      let mapFeatures = this.$echarts.getMap('china').geoJson.features;	//获取全国地区的经纬度（只包含了一级城市、省份经纬度）
      mapFeatures.forEach(function (v) { //获取一级城市、省份经纬度
        let name = v.properties.name; // 地区名称
        let value = v.properties.cp;
        geoCoordMap[name] = value; // 地区经纬度
      });

      let tempRes = [];
      for (let i = 0; i < numData.length; i++) {
        let geoCoord = geoCoordMap[numData[i].company_local_city] || [];
        if (geoCoord) {
          tempRes.push({
            name:numData[i].company_local_city,
            value: geoCoord.concat(numData[i].count,numData[i].company_id) || [],
            company_name: numData[i].company_name,
            company_id: numData[i].company_id
          });
        }
        console.log("temRes:",tempRes)
      }
      for (let i = 0; i < tempRes.length; i++) {
        let tempGeoCoord = cityData.rows.filter(t => { return t.name.includes(tempRes[i].name) })
        if (tempGeoCoord) {
          tempRes[i].value.unshift(tempGeoCoord[0].lat)
          tempRes[i].value.unshift(tempGeoCoord[0].lng)
        }
      }
      return tempRes;
=======
>>>>>>> b8a4ca62808d71934c6fae2f8a46e691c26817b4
    }

  }

}
</script>

<style lang="scss" scoped>
.content{
  height: 100%;
  width: 100%;
  .info {
    position: relative;
    float: left;
    width: 200px;
    height: 400px;
    //background-color: gray;
    background: rgba(255, 255, 255, 0.7);
    z-index: 5;

<<<<<<< HEAD
    .header {
      color: darkorange;
      text-align: center;
      margin-top: 15px;
      margin-bottom: 10px;
      font-size: 20px;
    }

=======
>>>>>>> b8a4ca62808d71934c6fae2f8a46e691c26817b4
    .up {
      width: 100%;
      display: flex;
      //flex-wrap: wrap;
      //justify-content: space-around;

      .item-warning {
        border-radius: 5px;
        padding-top: 5px;
        margin-top: 5px;
        width: 100%;
        height: 40px;
        font-size: 12px;
        text-align: center;
        color: blue;
      }
      .item-erro {
        border-radius: 5px;
        padding-top: 5px;
        margin-top: 5px;
        width: 100%;
        height: 40px;
        font-size: 12px;
        text-align: center;
        color: IndianRed;
      }
      .item-emergency {
        border-radius: 5px;
        padding-top: 5px;
        margin-top: 5px;
<<<<<<< HEAD
        width: 100%;
        height: 40px;
        font-size: 12px;
        text-align: center;
        color: red;
=======
        width: 100px;
        height: 20px;
        font-size: 10px;
>>>>>>> b8a4ca62808d71934c6fae2f8a46e691c26817b4
      }
    }
  }
  .bmap{
    position: absolute;
    z-index: 1;

  }

}


</style>

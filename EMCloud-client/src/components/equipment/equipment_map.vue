<template>
  <div class="contents" id="bigScreen">

    <div class="content_left">
      <div class="content_left_1">
        <TitleWrap title="设备总览">
          <LeftTop></LeftTop>
        </TitleWrap>
      </div>
      <div class="content_left_2">
        <TitleWrap title="状态总览">
          <LeftCenter></LeftCenter>
        </TitleWrap>
      </div>
    </div>

    <div class="content_center">
        <TitleWrap title="分布地图">
        <el-button type="text" size="large" class="back" @click="back" v-if="deepTree.length > 1">返回</el-button>
        <div id="map" class="echart-map" :style="{ height: '90%', width: '100%' }"></div>
        </TitleWrap>
    </div>

    <div class="content_right">
      <div class="content_right_1">
        <TitleWrap title="设备提醒">
          <RightTop></RightTop>
        </TitleWrap>
      </div>
      <div class="content_right_2">
        <TitleWrap title="告警提醒">
          <RightCenter></RightCenter>
        </TitleWrap>
      </div>
    </div>
  </div>
</template>

<script>


import {mapOption} from "../../assets/map/mapOption";
import china from "../../assets/map/china.json"
import cityData from "../../assets/map/china_city.json"
import "../../libs/utils"
import axios from "axios";
import resize from "../../libs/resize";
import LeftTop from "./module/left-top";
import LeftCenter from './module/left-center'
import {formatTime} from "../../libs/utils";
import RightTop from "./module/right-top"
import TitleWrap from "./module/title-wrap"
import RightCenter from "./module/right-center"

export default {
  name: "equipment_map",
  mixins:[resize],
  components: {LeftCenter, LeftTop, RightTop, TitleWrap, RightCenter},
  props: {
    areaCode: {
      type: String,
      default: '000000000000'
    },
    areaLevel: {
      type: [String, Number],
      default: 0
    },
    areaName: {
      type: String,
      default: 'china'
    },
    // 当前地图上的地区名字
    mapNameList: {
      type: Array,
      default() {
        return []
      }
    },
    // 当前地图上的地区Code
    mapCodeList: {
      type: Array,
      default() {
        return []
      }
    },
    // 地区统计数据
    areaStatistic: {
      type: Array,
      default() {
        return []
      }
    }
  },
  data() {
    return {
      chart: null, // 实例化echarts
      mapDataList: [], // 当前地图上的地区
      option: {...mapOption.basicOption}, // map的相关配置
      deepTree: [],// 点击地图时push，点返回时pop
      areaStatisticMapValue: {}, // 地图数据value, 只是amounts
      areaStatisticMapData: {}, // 地图数据data,包含所有数据
      areaLevelMap: {
        'country': 0,
        'china': 0,
        'province': 1,
        'city': 2,
        'district': 3,
      },
      tooltipAutoplay: null, // 提示框自动播放
      tooltipAutoplayIndex: 0, // 提示框自动播放index
<<<<<<< HEAD
      tempData :[],
      numData:[],
=======
      tempData :[			//模拟数据
        {name:'北京',value:'20'},
        {name:'济南',value:'170'},
        {name:'枣庄',value:'10'},
        {name:'深圳',value:'50'},
        {name:'齐齐哈尔',value:'90'},
        {name: '潍坊',value: '70'}
      ],
>>>>>>> b8a4ca62808d71934c6fae2f8a46e691c26817b4
      paramsMap:{
        areaCode:'',
        areaLevel:'',
        areaName:''
      },
      timing: null,
      loading: true,
      dateDay: null,
      dateYear: null,
      dateWeek: null,
      weekday: ["周日", "周一", "周二", "周三", "周四", "周五", "周六"],


    }
  },
  filters: {
    numsFilter(msg) {
      return msg || 0;
    },
  },
  created() {
<<<<<<< HEAD
=======

>>>>>>> b8a4ca62808d71934c6fae2f8a46e691c26817b4
  },
  beforeDestroy() {
    if (!this.chart) {
      return
    }
    this.chart.dispose()
    this.chart = null
  },
  mounted() {
    this.$nextTick(() => {
<<<<<<< HEAD
      this.getCityEquipmentNum();
=======
>>>>>>> b8a4ca62808d71934c6fae2f8a46e691c26817b4
      this.initEcharts();
      this.chart.on('click', this.echartsMapClick);
      this.chart.on('mouseover', this.echartsMapMouseover);
      this.chart.on('mouseout', this.echartsMapMouseout);
    });
    this.timeFn();
  },
  watch: {
    areaStatistic: {
      handler(val) {
        var objValue = {}, objData = {}
        for (var i = 0; i < val.length; i++) {
          objValue[val[i]['areaCode'].substr(0, 6)] = val[i].amounts * 1
          objData[val[i]['areaCode'].substr(0, 6)] = val[i]
        }
        this.areaStatisticMapValue = objValue
        this.areaStatisticMapData = objData
<<<<<<< HEAD
        this.getCityEquipmentNum()
=======
        this.initEcharts()
>>>>>>> b8a4ca62808d71934c6fae2f8a46e691c26817b4
      },
      deep: true,
    }
  },
  methods: {

    //点击全屏事件
    enlarge() {
      let element = document.getElementById("mapCodeID"); //需要全屏容器的id
      // 浏览器兼容
      if (this.FullScreen) {
        if (document.exitFullscreen) {
          document.exitFullscreen();
        } else if (document.webkitCancelFullScreen) {
          document.webkitCancelFullScreen();
        } else if (document.mozCancelFullScreen) {
          document.mozCancelFullScreen();
        } else if (document.msExitFullscreen) {
          document.msExitFullscreen();
        }
      } else {
        if (element.requestFullscreen) {
          element.requestFullscreen();
        } else if (element.webkitRequestFullScreen) {
          element.webkitRequestFullScreen();
        } else if (element.mozRequestFullScreen) {
          element.mozRequestFullScreen();
        } else if (element.msRequestFullscreen) {
          element.msRequestFullscreen();
        }
      }
      this.FullScreen = !this.FullScreen;
    },

    timeFn() {
      this.timing = setInterval(() => {
        this.dateDay = formatTime(new Date(), "HH: mm: ss");
        this.dateYear = formatTime(new Date(), "yyyy-MM-dd");
        this.dateWeek = this.weekday[new Date().getDay()];
      }, 1000);
    },

    // 方法
//     initEchartsM () {
//   // 新建一个promise对象
//   let newPromise = new Promise((resolve) => {
//     resolve()
//   });
//
//   //然后异步执行echarts的初始化函数
//   newPromise.then(() => {
//     //	此dom为echarts图标展示dom
//     this.chart = this.$echarts.init(document.getElementById('map'));
//   });
//
// },


// 初次加载绘制地图
    initEcharts() {
      //地图容器
      this.chart = this.$echarts.init(document.getElementById('map'));
      if (this.areaLevel === 0) {
        this.requestGetChinaJson();
      }else if (this.areaLevel === 1) {
        this.requestGetProvinceJSON({name: this.areaName, level: 'province', adcode: this.areaCode.substr(0, 6)})
      } else if (this.areaLevel === 2) {
        this.requestGetCityJSON({name: this.areaName, level: 'city', adcode: this.areaCode.substr(0, 6)})
      } else {
        return false
      }
    },
<<<<<<< HEAD
    //查询城市设备数量
    getCityEquipmentNum(){
      axios.get("http://121.5.74.11:8080/equipment/getAllEquipmentNumAndCity").then(res=>{
        this.numData = res.data
        this.initEcharts()
      })
    },
=======
>>>>>>> b8a4ca62808d71934c6fae2f8a46e691c26817b4
    // 地图点击
    echartsMapClick(params) {
      console.log("点击拉尔电联寄lack那上次男盗女娼进度才能")
      console.log("MAP:",params)
      china.features.forEach(item => {
        if (item.properties.name === params.name) {
          this.areaLevel=item.properties.level
          this.areaCode=item.properties.adcode
          this.areaName=params.name
          // this.$emit('update:areaCode', item.properties.adcode)
          // this.$emit('update:areaName', params.name)
          // this.$emit('update:areaLevel', this.areaLevelMap[item.properties.level])
        }
      })


      if (this.areaLevel === 'province') {
        this.requestGetProvinceJSON(this.paramsMap);
      } else if (params.data.level === 'city') {
        this.requestGetCityJSON(params.data)
      } else if (params.data.level === 'district' && this.mapDataList.length > 1) {
        this.requestGetDistrictJSON(params.data)
      } else {
        return false
      }
    },
    //绘制全国地图areaStatistic
    requestGetChinaJson() {
        this.setJsonData(china,'china')
      console.log(china)
    },
    // 加载省级地图
    requestGetProvinceJSON(params) {
      console.log("URL:","https://geo.datav.aliyun.com/areas_v3/bound/"+this.areaCode+"_full.json")
      axios.get("https://geo.datav.aliyun.com/areas_v3/bound/"+this.areaCode+"_full.json").then(res => {
         console.log('province--->', res)
        this.$emit('update:areaLevel', 1)
        this.setJsonData(res.data, params)
      });
    },
    // 加载市级地图
    requestGetCityJSON(params) {
        axios.get("https://geo.datav.aliyun.com/areas_v3/bound/"+this.areaCode+"_full.json").then(res => {
        console.log('city--->', res)
        this.$emit('update:areaLevel', 2)
        this.setJsonData(res.data, params)
      })
    },

    // 设置数据
    setJsonData(res, params) {
      var mapDataList = [];
      var mapNameList = [];
      var mapCodeList = [];
      for (var i = 0; i < res.features.length; i++) {
        console.log("当前：",res.features[i].properties)
        var obj = {
          ...res.features[i].properties,
           value: (Math.random() * 1000).toFixed(0) * 1,
           valueData: (Math.random() * 1000).toFixed(0) * 1,
        };
        mapDataList.unshift(obj)
        mapNameList.unshift(res.features[i].properties.name)
        mapCodeList.unshift(res.features[i].properties.adcode + '000000')
      }
      this.mapDataList = mapDataList;
      console.log("!!!!!!!!:",res)
      this.$emit('update:mapNameList', mapNameList)
      this.$emit('update:mapCodeList', mapCodeList)
      this.setMapData(res, params)
    },
    // 设置地图信息
    setMapData(res, params) {
      if (this.areaName === 'china') {
        this.deepTree.push({
          mapDataList: this.mapDataList,
          params: {name: 'china', level: 'country', adcode: '100000'}
        });
        //注册地图
        this.$echarts.registerMap(params, res);
        //绘制地图
        this.renderMap('china', this.mapDataList);
      } else {
        this.deepTree.push({mapDataList: this.mapDataList, params: params});
        this.$echarts.registerMap(params, res);
        this.renderMap(params, this.mapDataList);
      }
    },
    // 渲染地图
    renderMap(map, data) {
      var mapDataList = data.map(item => {
        return {
          name: item.name,
          value: item.value
        }
      })
      mapDataList = mapDataList.sort(function (a, b) {
        return b.value - a.value
      });
      // 设置左下角数量范围值
      //this.option.visualMap.min = mapDataList.length > 1 ? mapDataList[mapDataList.length - 2].value : 0
      //this.option.visualMap.max = mapDataList.length > 0 ? mapDataList[0].value : 0
      // 设置左上角当前位置
      this.option.title[0].text = this.areaName
      console.log("map:::",map)
      this.option.geo = {
        show: false,
        map: map,
        zoom: 1.2, //当前视角的缩放比例
        roam: true, //是否开启平游或缩放
        center: undefined,
      }
      this.option.series = [
        {
          name: map,
          mapType: map,
          zoom: 1, //当前视角的缩放比例
          roam: false, //是否开启平游或缩放
          center: undefined,
          scaleLimit: { //滚轮缩放的极限控制
            min: .5,
            max: 10
          },
          ...mapOption.seriesOption,
          data: this.getPointData(this.tempData)
        },
        {
          name: '散点',//series名称
          type: 'effectScatter',//散点类型
          coordinateSystem: 'geo',// series坐标系类型
          rippleEffect: {
            brushType: 'fill'
          },
          normal: {
            show: true,
            // 提示内容
            formatter: params => {
              return params.name;
            },
            position: 'top', // 提示方向
            color: '#fff'
          },
          emphasis: {
            show: true // 点
          },
          itemStyle: {
            normal: {
              color: '#ddb926',
              shadowBlur: 10,
              shadowColor: '#000'
            }
          },
          symbol: "circle", 				//点的样式
          cursor: "pointer", 			//鼠标放上去的效果
          data: this.getPointData(this.tempData),
          symbolSize: function (val) {
<<<<<<< HEAD
             return val[2] ;
=======
             return val[2] / 10;
>>>>>>> b8a4ca62808d71934c6fae2f8a46e691c26817b4
          },
          showEffectOn: 'render', //加载完毕显示特效
        },
      ]
      //渲染地图

      this.chart.setOption(this.option, true)
      window.onresize = function(){
        this.chart.resize();

      }
    },
    //地图鼠标移入事件
    echartsMapMouseover() {
      clearInterval(this.tooltipAutoplay)
    },
    //地图鼠标移出事件
    echartsMapMouseout() {
    },

    // 返回
    back() {
      //目前完成的地图只能是从省返回到全国，因此....
      this.areaName = 'china'
      this.areaCode = '100000'
      if (this.deepTree.length > 1) {
        this.deepTree.pop();
        this.mapDataList = this.deepTree[this.deepTree.length - 1].mapDataList;
        var areaName = this.deepTree[this.deepTree.length - 1].params.name;
        var areaCode = this.deepTree[this.deepTree.length - 1].params.adcode;
        var areaLevel = this.deepTree[this.deepTree.length - 1].params.level;
        var mapNameList = this.mapDataList.map(item => {
          return item.name
        })
        var mapCodeList = this.mapDataList.map(item => {
          return item.adcode + '000000'
        })
        this.$emit('update:areaCode', (areaCode === '100000' ? '000000' : areaCode) + '000000')
        this.$emit('update:areaName', areaName)
        this.$emit('update:areaLevel', this.areaLevelMap[areaLevel])
        this.$emit('update:mapNameList', mapNameList)
        this.$emit('update:mapCodeList', mapCodeList)
        this.renderMap(areaName, this.mapDataList);
      }

    },
    getPointData(tempData){ 				//通过该方法获取自己数据中各地区的经纬度
      let geoCoordMap = {}
      let mapFeatures = this.$echarts.getMap('china').geoJson.features;	//获取全国地区的经纬度（只包含了一级城市、省份经纬度）
<<<<<<< HEAD
=======
      //this.chart.json.hideLoading();			//隐藏loading样式
      console.log("MAPfeatures:",mapFeatures)
>>>>>>> b8a4ca62808d71934c6fae2f8a46e691c26817b4
      mapFeatures.forEach(function (v) { //获取一级城市、省份经纬度
        let name = v.properties.name; // 地区名称
        let value = v.properties.cp;
        geoCoordMap[name] = value; // 地区经纬度
<<<<<<< HEAD
=======
        console.log("name:",name,"LT:",geoCoordMap[name])
>>>>>>> b8a4ca62808d71934c6fae2f8a46e691c26817b4
      });

      let tempRes = [];
      for (let i = 0; i < tempData.length; i++) {
        let geoCoord = geoCoordMap[tempData[i].name] || [];
        console.log("geoCoord:",geoCoord)
        if (geoCoord) {
          tempRes.push({
            name: tempData[i].name,
            value: geoCoord.concat(tempData[i].value) || [],
          });
        }
      }
      for (let i = 0; i < tempRes.length; i++) {
        let tempGeoCoord = cityData.rows.filter(t => { return t.name.includes(tempRes[i].name) })
        if (tempGeoCoord && tempRes[i].value.length == 1) {
          tempRes[i].value.unshift(tempGeoCoord[0].lat)
          tempRes[i].value.unshift(tempGeoCoord[0].lng)
        }
      }
      return tempRes;
    }
  }






}
</script>

<style lang="scss" scoped>

// 内容
.contents {
  background-image: url("../../assets/imgs/pageBg.png");
  background-size: cover;
  height: 100%;
  width: 100%;
  //.header{
  //  width: 100%;
  //  height: 50px;
  //  color: white;
  //  text-align: center;
  //
  //}


  .host-body {
    height: 100%;
    .title_wrap {
      height: 60px;
      background-image: url("../../assets/imgs/top.png");
      background-size: cover;
      background-position: center center;
      position: relative;
      margin-bottom: 4px;
      //.guang {
      //  position: absolute;
      //  bottom: -26px;
      //  background-image: url("../../assets/imgs/guang.png");
      //  background-position: 80px center;
      //  width: 100%;
      //  height: 56px;
      //}

      //.zuojuxing,
      //.youjuxing {
      //  position: absolute;
      //  top: -2px;
      //  width: 140px;
      //  height: 6px;
      //  background-image: url("../../assets/imgs/headers/juxing1.png");
      //}

      .zuojuxing {
        left: 11%;
      }
      .youjuxing {
        right: 11%;
        transform: rotate(180deg);
      }

      .timers {
        position: absolute;
        right: 0;
        top: 30px;
        font-size: 18px;
        display: flex;
        align-items: center;

        //.blq-icon-shezhi02 {
        //  cursor: pointer;
        //}
      }
    }

    .title {
      position: relative;
      // width: 500px;
      text-align: center;
      background-size: cover;
      color: transparent;
      height: 60px;
      line-height: 46px;

      .title-text {
        font-size: 38px;
        font-weight: 900;
        letter-spacing: 6px;
        width: 100%;
        background: linear-gradient(92deg, #0072FF 0%, #00EAFF 48.8525390625%, #01AAFF 100%);
        -webkit-background-clip: text;
        -webkit-text-fill-color: transparent;
      }
    }
  }


  .content_left{
    width: 300px;
    float: left;
    .content_left_1,
    .content_left_2{
      margin-top: 40px;
      height: 210px;
    }
  }

  .content_right{
    width: 300px;
    float: right;
    .content_right_1,
    .content_right_2{
      margin-top: 40px;
      height: 210px;
    }
  }

  .content_center{
    //background-image: url("../../assets/imgs/center_map.png");
    margin: 30px;
    width: 600px;
    height: 400px;
    float: left;
  }
}
.contents{
  background-image: url("../../assets/imgs/pageBg.png");
}



@keyframes rotating {
  0% {
    -webkit-transform: rotate(0) scale(1);
    transform: rotate(0) scale(1);
  }
  50% {
    -webkit-transform: rotate(180deg) scale(1.1);
    transform: rotate(180deg) scale(1.1);
  }
  100% {
    -webkit-transform: rotate(360deg) scale(1);
    transform: rotate(360deg) scale(1);
  }
}


</style>

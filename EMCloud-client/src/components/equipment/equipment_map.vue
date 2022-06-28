<template>
<!--  <dv-full-screen-container>-->
  <div class="contents">
    <div class="header" >设备分布总览</div>
    <div class="content_left">
      <div class="content_left_1">
      <dv-border-box1>
        <Left></Left>
      </dv-border-box1>
      </div>
      <div class="content_left_2">
        <dv-border-box1>
          <LeftCenter></LeftCenter>
        </dv-border-box1>
      </div>

    </div>
    <div class="content_center">
<!--      <dv-border-box1>-->
        <el-button type="text" size="large" class="back" @click="back" v-if="deepTree.length > 1">返回</el-button>
        <div id="map" class="echart-map" :style="{ height: '100%', width: '100%' }" ref="myEchart"></div>
    </div>
  </div>
<!--  </dv-full-screen-container>-->
</template>

<script>


import {mapOption} from "../../assets/map/mapOption";
import china from "../../assets/map/china.json"
import cityData from "../../assets/map/china_city.json"
import "../../libs/utils"
import axios from "axios";
import resize from "../../libs/resize";
import Left from "./module/left";
import LeftCenter from './module/left-center'


export default {
  name: "equipment_map",
  mixins:[resize],
  components: {LeftCenter, Left},
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
      tempData :[			//模拟数据
        // {name:'北京',value:'20'},
        // {name:'济南',value:'170'},
        // {name:'枣庄',value:'10'},
        // {name:'深圳',value:'50'},
        // {name:'齐齐哈尔',value:'90'},
        // {name: '潍坊',value: '70'}
      ],
      numData:[],
      paramsMap:{
        areaCode:'',
        areaLevel:'',
        areaName:''
      }

    }
  },
  filters: {
    numsFilter(msg) {
      return msg || 0;
    },
  },
  created() {
    //this.getCityEquipmentNum()
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
      this.getCityEquipmentNum()
      this.initEcharts();
      this.chart.on('click', this.echartsMapClick);
      this.chart.on('mouseover', this.echartsMapMouseover);
      this.chart.on('mouseout', this.echartsMapMouseout);
    });
  },
  watch: {
    areaStatistic: {
      handler(val) {
        let objValue = {}, objData = {}
        for (var i = 0; i < val.length; i++) {
          objValue[val[i]['areaCode'].substr(0, 6)] = val[i].amounts * 1
          objData[val[i]['areaCode'].substr(0, 6)] = val[i]
        }
        this.areaStatisticMapValue = objValue
        this.areaStatisticMapData = objData
        this.getCityEquipmentNum()
        //this.initEcharts()
      },
      deep: true,
    }
  },
  methods: {
    // 初次加载绘制地图
    initEcharts() {
      //this.getCityEquipmentNum();
      //地图容器
      this.chart = this.$echarts.init(document.getElementById('map'));
      if (this.areaLevel === 0) {
        this.requestGetChinaJson();
      }else if (this.areaLevel === 1) {
        this.requestGetProvinceJSON({name: this.areaName, level: 'province', adcode: this.areaCode.substr(0, 6)})
      }
    },
    //查询城市设备数量
    getCityEquipmentNum(){
      axios.get("http://121.5.74.11:8080/equipment/getAllEquipmentNumAndCity").then(res=>{
        console.log("NUM:",res.data)
        this.numData = res.data
        console.log("CITYDATA:",this.numData.length)
        this.initEcharts()
      })
    },
    // 地图点击
    echartsMapClick(params) {
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
      }
    },
    //绘制全国地图areaStatistic
    requestGetChinaJson() {
        this.setJsonData(china,'china')
      console.log(china)
    },
    // 加载省级地图
    requestGetProvinceJSON(params) {
      let par = new URLSearchParams()
      par.append('adcode', this.areaCode)
      axios.get("http://121.5.74.11:8080/util/getMapJson",{
        params:{
          adcode:this.areaCode
        }
        }).then(res => {
         console.log('province--->', res.data)
        this.$emit('update:areaLevel', 1)
        this.setJsonData(res.data, params)
      });
    },

    // 设置数据
    setJsonData(res, params) {
      let mapDataList = [];
      let mapNameList = [];
      let mapCodeList = [];
      for (let i = 0; i < res.features.length; i++) {
        //console.log("当前：",res.features[i].properties)
        let obj = {
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
      console.log("渲染地图:",this.numData.length)
      let mapDataList = data.map(item => {
        return {
          name: item.name,
          value: item.value
        }
      })
      mapDataList = mapDataList.sort(function (a, b) {
        return b.value - a.value
      });
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
          data: this.getPointData(this.numData)

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
          data: this.getPointData(this.numData),
          symbolSize: function (val) {
             return val[2]*10 ;
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
        let areaName = this.deepTree[this.deepTree.length - 1].params.name;
        let areaCode = this.deepTree[this.deepTree.length - 1].params.adcode;
        let areaLevel = this.deepTree[this.deepTree.length - 1].params.level;
        let mapNameList = this.mapDataList.map(item => {
          return item.name
        })
        let mapCodeList = this.mapDataList.map(item => {
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
    getPointData(numData){ 				//通过该方法获取自己数据中各地区的经纬度
      numData = this.numData
      console.log("HHHHH",this.numData.length)
      let geoCoordMap = {}
      let mapFeatures = this.$echarts.getMap('china').geoJson.features;	//获取全国地区的经纬度（只包含了一级城市、省份经纬度）
      //this.chart.json.hideLoading();			//隐藏loading样式
      //console.log("MAPfeatures:",mapFeatures)
      mapFeatures.forEach(function (v) { //获取一级城市、省份经纬度
        let name = v.properties.name; // 地区名称
        let value = v.properties.cp;
        geoCoordMap[name] = value; // 地区经纬度
        //console.log("name:",name,"LT:",geoCoordMap[name])
      });

      let tempRes = [];
      for (let i = 0; i < numData.length; i++) {
        let geoCoord = geoCoordMap[numData[i].city] || [];
        if (geoCoord) {
          tempRes.push({
            name: numData[i].city,
            value: geoCoord.concat(numData[i].num) || [],
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
      console.log("TEMRES:",tempRes)
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
  .header{
    width: 100%;
    height: 50px;
    color: white;
    text-align: center;

  }

  .content_left{
    width: 220px;
    float: left;
    .content_left_1,
    .content_left_2{
      //margin-top: 40px;
      height: 210px;
    }
  }
  .content_center{
    //background-image: url("../../assets/imgs/center_map.png");
    margin: 20px;
    width: 900px;
    height: 500px;
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

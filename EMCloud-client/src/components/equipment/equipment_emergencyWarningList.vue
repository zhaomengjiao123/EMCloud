<template>
  <div class="contents">
    <div class="handle-box">
      <el-button class="handle-del mr10" type="primary" size="mini" @click="delAll">销售时间</el-button>
      <el-input v-model="select_word" class="handle-input mr10" size="mini" placeholder="年/月/日"></el-input>
      <el-button type="primary" size="mini" @click="centerDialogVisible = true">查询</el-button>
      <el-button type="primary" size="mini" @click="back">返回</el-button>
    </div>
    <div class="table" style="min-height: 500px">
      <el-table :data="warningData" stripe>
        <el-table-column label="设备编号" prop="sc_id" />
        <el-table-column label="产品名称" prop="course_name" />
        <el-table-column label="紧急警告信息" prop="teacher_name" />
        <el-table-column label="紧急警告时间" prop="course_credit" />
        <el-table-column label="销售人" prop="sc_daily_score" />
        <el-table-column label="销售时间" prop="sc_exam_score" />
      </el-table>
    </div>
  </div>
</template>

<script>


import {mapOption} from "../../assets/map/mapOption";
import china from "../../assets/map/china.json"
import cityData from "../../assets/map/china_city.json"
import "../../libs/utils"



export default {
  name: "equipment_emergencyWarningList",
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
        {name:'北京',value:'20'},
        {name:'济南',value:'170'},
        {name:'枣庄',value:'10'},
        {name:'深圳',value:'50'},
        {name:'齐齐哈尔',value:'90'},
        {name: '潍坊',value: '70'}
      ],
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

  },
  beforeDestroy() {
    if (!this.chart) {
      return
    }
    this.chart.dispose()
    this.chart = null
  },
  mounted() {
  },
  watch: {
  },
  methods: {
    back(){
      this.$router.go(-1)
    }

  }




}
</script>

<style lang="scss" scoped>

// 内容
.contents {
  height: 100%;
  width: 100%;
  .handle-box {
    margin: 20px;
  }
  .handle-input {
    width: 300px;
    display: inline-block;
  }

}



</style>

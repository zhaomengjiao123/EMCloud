<template>

  <div class="content" >
<!--    <div class="info">-->
      <dv-border-box8 class="info">
        <div class="header" >
          XXX公司
        </div>
        <div class="up">
          <div
            class="bg-color-black item"
            v-for="item in titleItem"
            :key="item.title" @click="toInfoList">
            <p class="colorBlue fw-b" style="text-align: center;color: white">
              {{ item.title }}
            </p>
            <span>{{item.number}}</span>
            </div>
          </div>
        <el-button class="moreBtn" type="primary" size="mini">查看更多</el-button>

      </dv-border-box8>
    <div id="bmap" class="bmap" :style="{ height: '100%', width: '100%' }">城市地图</div>

  </div>


</template>

<script>

import {loadBMap} from "../../libs/loadBMap.js"

export default {
  name: "equipment_cityMap",
  data() {
    return {
      company_name:'',
      company_name_1:'',
      company_id: '',
      // company:{
      //   company_name:'nna',
      //   company_id:''
      // },
      cityMap: null,
      option: {},
      titleItem: [
        {
          title: "报警警告",
          number: 200,
        },
        {
          title: "预警警告",
          number: 200,
        },
        {
          title: "紧急警告",
          number: 200,
        },

      ],


    }

  },
  mounted() {
    this.$nextTick(() => {
      // this.cityMap.on('click', this.cityMapClick(e));
      loadBMap("XMpRSRWH7DbM5A5gL8ShHhWWOG2XAuIg").then(() => {
        this.initEcharts()
      });

    })

  },
  watch:{
    company_name:{
      handler(val, newval) {
        console.log("company_name改变了！！1:",this.company_name)
        //this.company_name = newval
      },
      immediate: true

    }

  },
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
            return res.name + ':' + res.value[2]
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
            data: [
              { name: '上海', value: [121.480509, 31.23592, 10] },
              { name: '南京', value: [118.804147, 32.070037, 10] }
            ],
            symbolSize: function (val) {
              return val[2]
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
      this.cityMap.on('click', function (e) {
        console.log("信息",e.name) //  每个标识点的信息
        this.company_name = e.name
        console.log("Company_name:",this.company_name) //  每个标识点的信息

        //this.company.company_name=e.name

      })

    },

    toInfoList(params){
      console.log("click:",params.target.innerText)
      let type = params.target.innerText
      if(type=="预警警告"){
        this.$router.push("/equipment/warning")
      }else if(type=="报警警告"){
        this.$router.push("/equipment/erro")
      }else if(type=="紧急警告"){
        this.$router.push("/equipment/emergency")
      }
      //
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
    height: 200px;
    //background-color: gray;
    background: rgba(0, 0, 1, 0.7);
    z-index: 5;

    .header {
      color: white;
      text-align: center;
      margin-top: 15px;
      margin-bottom: 10px;
      font-size: 20px;
    }

    .up {
      width: 100%;
      display: flex;
      //flex-wrap: wrap;
      //justify-content: space-around;

      .item {
        border-radius: 5px;
        padding-top: 5px;
        margin-top: 5px;
        width: 100%;
        height: 40px;
        font-size: 10px;
        text-align: center;
        color: white;
      }
    }

    .moreBtn {
      margin: 60px;
      position: center;
    }
  }
  .bmap{
    position: absolute;
    z-index: 1;

  }

}


</style>

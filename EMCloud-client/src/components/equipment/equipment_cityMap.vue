<template>

  <div class="content" >
<!--    <div class="info">-->
      <dv-border-box8 class="info">
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
            </div>
          </div>
        </div>>

      </dv-border-box8>
<!--    </div>-->
    <div id="bmap" class="bmap" :style="{ height: '100%', width: '100%' }">城市地图</div>

  </div>


</template>

<script>

import {loadBMap} from "../../libs/loadBMap.js"

export default {
  name: "equipment_cityMap",
  data() {
    return {
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


    }

  },
  mounted() {
    this.$nextTick(() => {
      loadBMap("XMpRSRWH7DbM5A5gL8ShHhWWOG2XAuIg").then(() => {
        this.initEcharts()
      });
        this.cityMap.on('click', this.cityMapClick);
    })

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
        console.log(e) //  每个标识点的信息
      })

    },
    cityMapClick(params){
      //console.log("点击的点是；",)
      console.log("KKKK:",params.name)
    },
    test(params){
      console.log("click:",params.target.innerText)
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
    background: rgba(0, 0, 1, 0.7);
    z-index: 5;

    .up {
      width: 100%;
      display: flex;
      //flex-wrap: wrap;
      //justify-content: space-around;

      .item {
        border-radius: 5px;
        padding-top: 5px;
        margin-top: 5px;
        width: 100px;
        height: 20px;
        font-size: 10px;
      }
    }
  }
  .bmap{
    position: absolute;
    z-index: 1;

  }

}


</style>

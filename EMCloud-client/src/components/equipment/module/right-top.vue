<template>
  <div  class="right_top_content">
<!--    :class="{ 'overflow-y-auto': sbtxSwiperFlag }">-->
<!--    <dv-scroll-board :config="config" style="width:100%;height:100%" />-->
<!--    <vue-seamless-scroll :data="list" class="warp">-->
<!--      <ul class="item">-->
<!--        <li v-for="(item, index) in list" :key="index">-->
<!--          <span class="orderNum doudong">{{ index + 1 }}</span>-->
<!--&lt;!&ndash;          <span class="title" v-text="item.title"></span>&ndash;&gt;-->
<!--&lt;!&ndash;          <span class="date" v-text="item.date"></span>&ndash;&gt;-->
<!--          <div class="inner_right">-->
<!--            <div class="bottom_line"></div>-->
<!--            <div class="info id">-->
<!--              <span class="labels">设备ID：</span>-->
<!--              <span class="contents">{{item.id}}</span>-->
<!--              <span class="state" :class="{  typeRed: item.onlineState == 0,typeGreen: item.onlineState == 1,}">-->
<!--               {{ item.onlineState == 1 ? "上线" : "下线" }}-->
<!--              </span>-->
<!--            </div>-->
<!--            <div class="info time">-->
<!--              <span class="labels">时间：</span>-->
<!--              <span class="contents " style="font-size: 12px">-->
<!--                  {{ item.createTime }}</span>-->
<!--            </div>-->
<!--            <div class="info" address>-->
<!--              <span class="labels">地址：</span>-->
<!--              <span class="contents ciyao" style="font-size: 12px">-->
<!--&lt;!&ndash;                {{ addressHandle(item) }}&ndash;&gt;-->
<!--                hhhh-->
<!--              </span>-->
<!--            </div>-->
<!--          </div>-->
<!--        </li>-->
<!--      </ul>-->
<!--    </vue-seamless-scroll>-->

    <vueSeamlessScroll :data="list" :class-option="defaultOption">
      <ul class="right_top">
        <li class="right_top_item" v-for="(item, i) in list" :key="i">
          <span class="orderNum">{{ i + 1 }}</span>
          <div class="inner_right">
            <div class="bottom_line"></div>
            <div class="flex">
              <div class="info">
                <span class="labels">设备ID：</span>
                <span class="contents zhuyao doudong wangguan">{{ item.equipment_id }}</span>
                <span class="labels" >所属公司：</span>
                <span class="contents " >
                  {{ item.company_name }}
                </span>

              </div>
            </div>

            <div class="info addresswrap">
              <span class="labels" >状态：</span>
              <span class="contents " :class="{
                  typeRed: item.equipment_state == 0,
                  typeGreen: item.equipment_state == 1,
                  }">
                  {{ item.equipment_state == 1 ? "上线" : "宕机" }}
              </span>
              <span class="labels" style="margin-left: 65px">地址：</span>
              <span class="contents ciyao" style="font-size: 10px">
                {{ addressHandle(item) }}
              </span>

            </div>
          </div>
        </li>
      </ul>
    </vueSeamlessScroll>
  </div>

</template>

<script>
import vueSeamlessScroll from "vue-seamless-scroll";
import {getBigScreenEquipmentInfo} from "../../../api"; // vue2引入方式
export default {
  components: { vueSeamlessScroll },
  data() {
    return {
      list: [],
      pageflag: true,
      components: vueSeamlessScroll,

      defaultOption: {
        singleHeight: 240,
        limitMoveNum: 5,
        step: 1, // 数值越大速度滚动越快
        hoverStop: true, // 是否开启鼠标悬停stop
        openWatch: true, // 开启数据实时监控刷新dom
        direction: 1, // 0向下 1向上 2向左 3向右
        singleWidth: 0, // 单步运动停止的宽度(默认值0是无缝不停止的滚动) direction => 2/3
        waitTime: 3000 // 单步运动停止的时间(默认值1000ms)
      },
      timer:'',



    };
  },
  computed: {
    sbtxSwiperFlag() {
      let sbtxSwiper = false;
      if (sbtxSwiper) {
        this.components = vueSeamlessScroll;
      } else {
      }
      return sbtxSwiper;
    },


  },
  created() {
    this.getData();
  },
  beforeDestroy() {
    this.clearData();

  },

  mounted() {
  },
  methods: {
    clearData() {
      if (this.timer) {
        clearInterval(this.timer)
        this.timer = null
      }
    },

    //轮询
    switper() {
      if (this.timer) {
        return
      }
      let looper = (a) => {
        this.getData()
      };
      this.timer = setInterval(looper, 5000);
    },

    addressHandle(item) {
      let name = item.company_local_province;
      if (item.company_local_city) {
        name += "/" + item.company_local_city;
        // if (1) {
        //   name += "/" + "中国";
        // }
      }
      return name;
    },
    getData() {
      this.pageflag = true;
      getBigScreenEquipmentInfo().then(res=>{
        console.log("BigScreen:",res)
        this.list=res;
      });
      this.switper();

    },
  },
};
</script>
<style lang='scss' scoped>
//.warp {
//  height: 100%;
//  width: 100%;
//  margin: 0 auto;
//  overflow: hidden;
//  ul {
//    list-style: none;
//    padding: 0;
//    margin: 0 auto;
//    li,
//    a {
//      display: block;
//      height: 30px;
//      line-height: 30px;
//      display: flex;
//      justify-content: space-between;
//      font-size: 10px;
//      color: white;
//    }
//    .inner_right{
//      position: relative;
//      height: 100%;
//      width: 100%;
//      left: 0%;
//      flex-shrink: 0;
//      line-height: 1;
//      display: flex;
//      align-items: center;
//      justify-content: space-between;
//      flex-wrap: wrap;
//      .bottom_line {
//        position: absolute;
//        height: 1px;
//        width: 80%;
//        background-image: url("../../../assets/imgs/zuo_xuxian.png");
//        bottom: -8px;
//        left: -4%;
//        right: -5%;
//        background-size: cover;
//      }
//
//    }
//
//    .info {
//      margin-right: 10px;
//      display: flex;
//      align-items: center;
//      color: #fff;
//
//      .labels {
//        flex-shrink: 0;
//        font-size: 10px;
//        color: rgba(255, 255, 255, 0.6);
//      }
//
//      .zhuyao {
//        //color: $primary-color;
//        font-size: 15px;
//      }
//
//      .ciyao {
//        color: rgba(255, 255, 255, 0.8);
//      }
//
//      .warning {
//        color: #e6a23c;
//        font-size: 15px;
//      }
//    }
//
//  }
//}
.right_top_content {
  overflow: hidden;
  width: 100%;
  height: 90%;
}

.doudong {
  //  vertical-align:middle;
  //position: center;
  overflow: hidden;
  -webkit-backface-visibility: hidden;
  -moz-backface-visibility: hidden;
  -ms-backface-visibility: hidden;
  backface-visibility: hidden;
}

.overflow-y-auto {
  overflow-y: auto;
}

.right_top {
  width: 100%;
  height: 90%;

  .right_top_item {
    display: flex;
    align-items: center;
    justify-content: center;
    padding: 5px;
    font-size: 10px;
    margin: 10px 0;
    .orderNum {
      margin: 0 16px 0 -20px;
    }

    .info {
      margin-right: 0px;
      display: flex;
      align-items: center;
      color: #fff;

      .labels {
        flex-shrink: 0;
        font-size: 10px;
        color: rgba(255, 255, 255, 0.6);
      }

      .zhuyao {
        //color: $primary-color;
        font-size: 12px;
      }

      .ciyao {
        color: rgba(255, 255, 255, 0.8);
      }

      .warning {
        color: #e6a23c;
        font-size: 15px;
      }
    }

    .inner_right {
      position: relative;
      height: 100%;
      width: 100%;
      left: -5%;
      flex-shrink: 0;
      line-height: 1;
      display: flex;
      align-items: center;
      justify-content: space-between;
      flex-wrap: wrap;
      .bottom_line {
        position: absolute;
        height: 1px;
        width: 80%;
        background-image: url("../../../assets/imgs/zuo_xuxian.png");
        bottom: -8px;
        left: -4%;
        right: -5%;
        background-size: cover;
      }
      .addresswrap {
        width: 100%;
        display: flex;
        margin-top: 8px;
      }
    }

    .wangguan {
      color: #1890ff;
      font-weight: 900;
      font-size: 12px;
      width: 80px;
      flex-shrink: 0;
    }


    .time {
      font-size: 12px;
      // color: rgba(211, 210, 210,.8);
      color: #fff;
    }

    .address {
      font-size: 12px;
      cursor: pointer;
      // @include text-overflow(1);
    }

    .types {
      width: 30px;
      flex-shrink: 0;
    }

    .typeRed {
      color: #fc1a1a;
    }

    .typeGreen {
      color: #29fc29;
    }
  }
}
</style>

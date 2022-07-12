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
                <span class="contents zhuyao doudong wangguan">{{ item.id }}</span>
                <span class="labels">时间：</span>
                <span class="contents " >
                  {{ item.createTime }}
                </span>

              </div>
            </div>

            <div class="info addresswrap">
              <span class="labels">地址：</span>
              <span class="contents ciyao" style="font-size: 10px">
<!--                {{ addressHandle(item) }}-->
                hhhh
              </span>
              <span class="labels" style="margin-left: 65px">状态：</span>
              <span class="contents " :class="{
                  typeRed: item.onlineState == 0,
                  typeGreen: item.onlineState == 1,}">
                  {{ item.onlineState == 1 ? "上线" : "下线" }}
                </span>
            </div>
          </div>
        </li>
      </ul>
    </vueSeamlessScroll>
  </div>

</template>

<script>
import vueSeamlessScroll from "vue-seamless-scroll"; // vue2引入方式
export default {
  components: { vueSeamlessScroll },
  data() {
    return {
      list: [
        {
          'id':22,
          'onlineState':1,
          'createTime':'2022-7-1'

        },
        {
          'id':23,
          'onlineState':1,
          'createTime':2022-7-1
        },
        {
          'id':24,
          'onlineState':1,
          'createTime':2022-7-1
        },
        {
          'id':24,
          'onlineState':0,
          'createTime':2022-7-1
        },
        {
          'id':24,
          'onlineState':1,
          'createTime':2022-7-1
        },
        {
          'id':24,
          'onlineState':1,
          'createTime':2022-7-1
        },
        {
          'id':24,
          'onlineState':1,
          'createTime':2022-7-1
        },
        {
          'id':24,
          'onlineState':1,
          'createTime':2022-7-1
        },
      ],
      pageflag: true,
      components: vueSeamlessScroll,
      // defaultOption: {
      //   // ...this.$store.state.setting.defaultOption,
      //   singleHeight: 240,
      //   limitMoveNum: 5,
      //   step: 0,
      // },
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

  mounted() {
  },
  methods: {

    // addressHandle(item) {
    //   let name = item.provinceName;
    //   if (item.cityName) {
    //     name += "/" + item.cityName;
    //     if (item.countyName) {
    //       name += "/" + item.countyName;
    //     }
    //   }
    //   return name;
    // },
    getData() {
      this.pageflag = true;
      // this.pageflag =false
      // currentGET("big3", { limitNum: 20 }).then((res) => {
      //   console.log("设备提醒", res);
      //   if (res.success) {
      //     this.countUserNumData = res.data;
      //     this.list = res.data.list;
      //     let timer = setTimeout(() => {
      //       clearTimeout(timer);
      //       this.defaultOption.step =
      //         this.$store.state.setting.defaultOption.step;
      //     },
      //       this.$store.state.setting.defaultOption.waitTime);
      //   } else {
      //     this.pageflag = false;
      //     // this.$Message({
      //     //   text: res.msg,
      //     //   type: "warning",
      //     // });
      //   }
      // });
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
      margin-right: 5px;
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

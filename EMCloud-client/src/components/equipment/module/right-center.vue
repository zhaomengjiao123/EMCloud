<template>
  <div class="right_center">
    <dv-scroll-ranking-board :config="config" style="width:98%;height:90%" />
<!--    <dv-capsule-chart :config="config" style="width:100%;height:100%" />-->
  </div>
</template>

<script>
import vueSeamlessScroll from 'vue-seamless-scroll'
import {getErroCountOfAllCity} from "../../../api";  // vue2引入方式

export default {
  name: "right-center",
  components: { vueSeamlessScroll },

  data(){
    return{
      config: {
        showValue: true,
        rowNum:4,
        carousel: "single",
        sort: true,
        unit: "次",
        waitTime: 1000,
        data: []
      },
    }
  },
  created() {
    this.getData()

  },
  computed:{
    // config: {
    //   showValue: true,
    //   carousel: 'single',
    //   rowNum: 4,
    //   sort: true,
    //   unit: "次",
    //   waitTime: 2000,
    //   data: [
    //     {
    //       name: 'ss',
    //       value: 22
    //     },
    //     {
    //       name:'ss',
    //       value:80
    //     }
    //     ,
    //     {
    //       name:'ss',
    //       value:90
    //     }
    //     ,
    //     {
    //       name:'ss',
    //       value:10
    //     }
    //     ,
    //     {
    //       name:'ss',
    //       value:50
    //     },
    //     {
    //       name:'yy',
    //       value:50
    //     }
    //     ,
    //     {
    //       name:'ss',
    //       value:50
    //     }
    //   ]
    // },

  },
  mounted() {
    // this.config={
    //   showValue: true,
    //   carousel: 'single',
    //   rowNum: 4,
    //   sort: true,
    //   unit: "次",
    //   waitTime: 200,
    //   data: [
    //     {
    //       name: 'ss',
    //       value: 22
    //     },
    //     {
    //       name:'yy',
    //       value:80
    //     }
    //     ,
    //     {
    //       name:'hh',
    //       value:90
    //     }
    //     ,
    //     {
    //       name:'ii',
    //       value:10
    //     }
    //     ,
    //     {
    //       name:'pp',
    //       value:50
    //     },
    //     {
    //       name:'nn',
    //       value:50
    //     }
    //     ,
    //     {
    //       name:'aa',
    //       value:50
    //     }
    //   ]
    // }
  },
  methods:{

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
      this.timer = setInterval(looper, this.$store.state.setting.echartsAutoTime);
    },
    getData() {
      this.pageflag = true
      // this.pageflag =false
     // currentGET('big7', { gatewayno: this.gatewayno }).then(res => {
      getErroCountOfAllCity().then(res=>{
        console.log("ErroCountCity:",res)
        this.config = {
          ...this.config,
          data:res
        }
      })

        if (!this.timer) {
          //console.log('报警排名', res);
        }
        if (true) {
          // this.config = {
          //   ...this.config,
          //   data: this.config.data
          // }
          this.switper()
        } else {
          this.pageflag = false
          this.srcList = []
          // this.$Message({
          //   text: res.msg,
          //   type: 'warning'
          // })
        }
      //})
    },
  }


}
</script>

<style lang="scss" scoped>

//.list_Wrap {
//  height: 100%;
//  overflow: hidden;
//
//  /deep/ .kong {
//    width: auto;
//  }
//}

//.sbtxSwiperclass {
//  .img_wrap {
//    overflow-x: auto;
//  }
//
//}

.right_center {
  width: 100%;
  height: 100%;
  box-sizing: border-box;
  padding: 0 16px;

  //.searchform {
  //  height: 80px;
  //  display: flex;
  //  align-items: center;
  //  justify-content: center;
  //
  //  .searchform_item {
  //    display: flex;
  //    justify-content: center;
  //    align-items: center;
  //
  //    label {
  //      margin-right: 10px;
  //      color: rgba(255, 255, 255, 0.8);
  //    }
  //
  //    button {
  //      margin-left: 30px;
  //    }
  //
  //    input {}
  //  }
  //}

  //.img_wrap {
  //  display: flex;
  //  // justify-content: space-around;
  //  box-sizing: border-box;
  //  padding: 0 0 20px;
  //  // overflow-x: auto;
  //
  //  li {
  //    width: 105px;
  //    height: 10px;
  //    border-radius: 6px;
  //    overflow: hidden;
  //    cursor: pointer;
  //    // background: #84ccc9;
  //    // border: 1px solid #ffffff;
  //    overflow: hidden;
  //    flex-shrink: 0;
  //    margin: 0 10px;
  //
  //    img {
  //      flex-shrink: 0;
  //    }
  //  }
  //
  //
  //
  //
  //}

  .noData {
    width: 100%;
    line-height: 100px;
    text-align: center;
    color: rgb(129, 128, 128);

  }
}
</style>

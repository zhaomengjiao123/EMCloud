<template>
  <div class="content">
<!--    <div id="leftCenter" :style="{ height: '90%', width: '90%' }"></div>-->
    <dv-active-ring-chart :config="configPie" :style="{ height: '100%', width: '100%' }"/>
<!--    <dv-active-ring-chart :config="configPie"></dv-active-ring-chart>-->
  </div>

</template>

<script>

import {getAllExceptionCount, getOnlineAndNotOnlineEquipmentNum} from "../../../api";

export default {
  name:'left-center',
  components: {},
  data() {
    return {
      configPie:{},
      // data:[],
      countUserNumData: {
        exceptionNum: 0,
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
   // this.init();
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

      });

      getAllExceptionCount().then(res=>{
        console.log("HHHH:",res)
        _this.countUserNumData.exceptionNum=res.exceptionCount
        this.init()
      });

      //currentGET('big1').then(res => {
        //只打印一次
        //if (!this.timer) {
          //console.log("设备总览", res);
        //}
        //if (res.success) {
          //this.countUserNumData = res.data
          //this.$nextTick(() => {
            //this.init()
            //this.switper()
            // })

            //} else {
            //this.pageflag = false
            // this.$Message({
            //   text: res.msg,
            //   type: 'warning'
            // })
            // }
           // })

    },
    //轮询
    switper() {
      if (this.timer) {
        return
      }
      let looper = (a) => {
        this.getData()
      };



      //this.timer = setInterval(looper, this.$store.state.setting.echartsAutoTime);

      // this.chart = this.chart.on('mouseover', params => {
      //    this.clearData()
      //  });
      // this.chart.on('mouseout', params => {
      //   //this.timer = setInterval(looper, this.$store.state.setting.echartsAutoTime);
      // });
      // this.chart.setOption(this.options)
      // window.onresize = function(){
      //   this.chart.resize();
      // }
    },
    init() {
      //let total = this.countUserNumData.totalNum;
      let total = 100;
      let colors = ["#ECA444", "#33A1DB", "#56B557"];
      // let piedata = {
      //   name: "用户总览",
      //   type: "pie",
      //   radius: ["42%", "65%"],
      //   avoidLabelOverlap: false,
      //   itemStyle: {
      //     borderRadius: 4,
      //     borderColor: "rgba(206,124,124,0)",
      //     borderWidth: 2,
      //   },
      // };
      this.configPie = {
        lineWidth: 20,
        color:colors,
        data: [
          {
            value: this.countUserNumData.exceptionNum,
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

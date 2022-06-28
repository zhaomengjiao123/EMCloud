<template>
  <div class="content">
<!--    <div id="leftCenter" :style="{ height: '90%', width: '90%' }"></div>-->
    <dv-active-ring-chart :config="config" :style="{ height: '90%', width: '90%' }"/>
  </div>

</template>

<script>

export default {
  name:'left-center',
  components: {},
  data() {
    return {
      chart:null,
      options: {},
      config:{},
      data:[],
      countUserNumData: {
        erroNum: 0,
        onlineNum: 0,
        offlineNum: 0,
        totalNum: 0
      },
      pageflag: true,
      timer: null
    };
  },
  created() {
    this.getData(),
      this.init()
  },
  mounted() {
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

      //currentGET('big1').then(res => {
        //只打印一次
        if (!this.timer) {
          //console.log("设备总览", res);
        }
        //if (res.success) {
          //this.countUserNumData = res.data
          this.$nextTick(() => {
            this.init()
            this.switper()
            // })

            //} else {
            this.pageflag = false
            // this.$Message({
            //   text: res.msg,
            //   type: 'warning'
            // })
            // }
             })

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
      this.chart = this.$echarts.init(document.getElementById('leftCenter'));
      this.chart = this.chart.on('mouseover', params => {
         this.clearData()
       });
      this.chart.on('mouseout', params => {
        //this.timer = setInterval(looper, this.$store.state.setting.echartsAutoTime);
      });
      this.chart.setOption(this.options)
      window.onresize = function(){
        this.chart.resize();
      }
    },
    init() {
      //let total = this.countUserNumData.totalNum;
      let total = 100;
      let colors = ["#ECA444", "#33A1DB", "#56B557"];
      let piedata = {
        name: "用户总览",
        type: "pie",
        radius: ["42%", "65%"],
        avoidLabelOverlap: false,
        itemStyle: {
          borderRadius: 4,
          borderColor: "rgba(206,124,124,0)",
          borderWidth: 2,
        },
      };
      this.config = {
        lineWidth: 20,
        color:colors,
        data: [
          {
            //value: this.countUserNumData.lockNum,
            value: 20,
            name: "异常"
          },
          {
            //value: this.countUserNumData.onlineNum,
            value: 20,
            name: "在线"
          },
          {
            //value: this.countUserNumData.offlineNum,
            value: 10,
            name: "离线"
          },
        ],

      }
    },
    initDv(){

    }
  },
};
</script>
<style lang='scss' scoped>
.content{
  height: 220px;
  width: 220px;
  position: center;

}
</style>

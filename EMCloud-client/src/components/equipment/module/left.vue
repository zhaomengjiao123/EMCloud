<template xmlns:el-col="http://www.w3.org/1999/html">
  <ul class="user_Overview flex" v-if="pageflag">
    <el-row :gutter="50">
      <el-col :span="11">
        <li class="user_Overview-item" style="color: #00fdfa">
          <div class="user_Overview_nums allnum ">
            <dv-digital-flop :config="config" style="width:100%;height:100%;" />
          </div>
          <p>总设备数</p>
        </li>
      </el-col>
      <el-col :span="11">
        <li class="user_Overview-item" style="color: #07f7a8">
          <div class="user_Overview_nums online">
            <dv-digital-flop :config="onlineconfig" style="width:100%;height:100%;" />
          </div>
          <p>在线数</p>
        </li>
      </el-col>
    </el-row>
    <el-row :gutter="50">
      <el-col :span="11">
        <li class="user_Overview-item" style="color: #f5023d">
          <div class="user_Overview_nums laramnum">
            <dv-digital-flop :config="laramnumconfig" style="width:100%;height:100%;" />
          </div>
          <p>告警次数</p>
        </li>
      </el-col>
      <el-col :span="11">
        <li class="user_Overview-item" style="color: #e3b337">
          <div class="user_Overview_nums offline">
            <dv-digital-flop :config="offlineconfig" style="width:100%;height:100%;" />
          </div>
          <p>掉线数</p>
        </li>
      </el-col>
    </el-row>
  </ul>
  <Reacquire v-else @onclick="getData" line-height="200px">
    重新获取
  </Reacquire>
</template>

<script>
let style = {
  fontSize: 15
}
export default {
  name: "left",
  data() {
    return {
      options: {},
      userOverview: {
        alarmNum: 0,
        offlineNum: 0,
        onlineNum: 0,
        totalNum: 0,
      },
      pageflag: true,
      timer: null,
      config: {
        number: [0],
        content: '{nt}',
        style: {
          ...style,
          // stroke: "#00fdfa",
          fill: "#00fdfa",
        },
      },
      onlineconfig: {
        number: [0],
        content: '{nt}',
        style: {
          ...style,
          // stroke: "#07f7a8",
          fill: "#07f7a8",
        },
      },
      offlineconfig: {
        number: [0],
        content: '{nt}',
        style: {
          ...style,
          // stroke: "#e3b337",
          fill: "#e3b337",
        },
      },
      laramnumconfig: {
        number: [200],
        content: '{nt}',
        style: {
          ...style,
          // stroke: "#f5023d",
          fill: "#f5023d",
        },
      }

    };
  },
  filters: {
    numsFilter(msg) {
      return msg || 0;
    },
  },
  created() {
    this.getData()
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
      this.pageflag = true;
      //currentGET("big2").then((res) => {
        if (!this.timer) {
          //console.log("设备总览", res);
        }

        //if (res.success) {
          this.userOverview = res.data;
          this.onlineconfig = {
            ...this.onlineconfig,
            //number: [res.data.onlineNum]
            number: [200]
          }
          this.config = {
            ...this.config,
            //number: [res.data.totalNum]
            number: [200]
          }
          this.offlineconfig = {
            ...this.offlineconfig,
           //number: [res.data.offlineNum]
            number: [200]
          }
          this.laramnumconfig = {
            ...this.laramnumconfig,
            //number: [res.data.alarmNum]
            number: [200]
          }
          //this.switper()
        //} else {
          this.pageflag = false;
          //this.$Message.warning(res.msg);
        //}
      //});
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
    },
  }
}
</script>

<style lang='scss' scoped>
.user_Overview {
  li {
    flex: 1;
    p {
      text-align: center;
      //height: 16px;
      font-size: 5px;
    }

    .user_Overview_nums {
      width: 50px;
      height: 50px;
      text-align: center;
      line-height: 5px;
      font-size: 4px;
      margin-top: 20px;
      background-size: cover;
      background-position: center center;
      position: relative;
      //
      //&::before {
      //  content: '';
      //  position: absolute;
      //  width: 100%;
      //  height: 100%;
      //  top: 0;
      //  left: 0;
      //}

      &.bgdonghua::before {
        animation: rotating 14s linear infinite;
      }
    }

    .allnum {
     // &::before {
        background-image: url("../../../assets/imgs/left_top_lan.png");
      //}
    }

    .online {
     // &::before {
        background-image: url("../../../assets/imgs/left_top_lv.png");
     // }
    }

    .offline {
     // &::before {
        background-image: url("../../../assets/imgs/left_top_huang.png");
     // }
    }

    .laramnum {
      //&::before {
        background-image: url("../../../assets/imgs/left_top_hong.png");
      //}
    }
  }
}

</style>

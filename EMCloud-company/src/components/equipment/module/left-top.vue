<template xmlns:el-col="http://www.w3.org/1999/html">
  <ul class="user_Overview flex" v-if="pageflag">
    <el-row :gutter="50">
      <el-col :span="10">
        <li class="user_Overview-item" style="color: #00fdfa">
          <div class="user_Overview_nums allnum ">
            <dv-digital-flop :config="totalconfig" style="width:100%;height:100%;" />
          </div>
          <p>总设备数</p>
        </li>
      </el-col>
      <el-col :span="10">
        <li class="user_Overview-item" style="color: #07f7a8">
          <div class="user_Overview_nums online">
            <dv-digital-flop :config="onlineconfig" style="width:100%;height:100%;" />
          </div>
          <p>在线数</p>
        </li>
      </el-col>
    </el-row>
    <el-row :gutter="50">
      <el-col :span="10">
        <li class="user_Overview-item" style="color: #f5023d">
          <div class="user_Overview_nums laramnum">
            <dv-digital-flop :config="laramnumconfig" style="width:100%;height:100%;" />
          </div>
          <p>告警次数</p>
        </li>
      </el-col>
      <el-col :span="10">
        <li class="user_Overview-item" style="color: #e3b337">
          <div class="user_Overview_nums offline">
            <dv-digital-flop :config="offlineconfig" style="width:100%;height:100%;" />
          </div>
          <p>掉线数</p>
        </li>
      </el-col>
    </el-row>
  </ul>
</template>

<script>
import {getAllEquipmentCount, getAllExceptionCount, getOnlineAndNotOnlineEquipmentNum} from "../../../api";

let style = {
  fontSize: 15
}
export default {
  name: "left-top",
  data() {
    return {
      count:0,
      options: {},
      // userOverview: {
      //   alarmNum: 0,
      //   offlineNum: 0,
      //   onlineNum: 0,
      //   totalNum: 0,
      // },
      pageflag: true,
      timer: null,
      totalconfig: {
        number: [0],
        content: '{nt}',
        style: {
          ...style,
          fill: "#00fdfa",
        },
      },
      onlineconfig: {
        number: [0],
        content: '{nt}',
        style: {
          ...style,
          fill: "#07f7a8",
        },
      },
      offlineconfig: {
        number: [0],
        content: '{nt}',
        style: {
          ...style,
          fill: "#e3b337",
        },
      },
      laramnumconfig: {
        number: [0],
        content: '{nt}',
        style: {
          ...style,
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
      this.count++;
      console.log("count:",this.count);
      //this.pageflag = true;
      //查询在线数与不在线数
      getOnlineAndNotOnlineEquipmentNum().then(res=>{
        console.log("res:::",res)
        for(let i=0;i<res.length;i++){
          console.log("RRR:",res[i].equipstate,res[i].count)
          if(res[i].equipstate==1){
            console.log("YYYY")
            this.onlineconfig = {
              ...this.onlineconfig,
              number: [res[i].count]
            }
          }else if(res[i].equipstate==0){
            console.log("NNNNN")
            this.offlineconfig = {
              ...this.offlineconfig,
              number: [res[i].count]
            }
          }
        }
      });

      getAllEquipmentCount().then(res=>{
        console.log("EquipemnetCount:",res);
        this.totalconfig = {
          ...this.totalconfig,
          number: [res]
        }
      });

      getAllExceptionCount().then(res=>{
        console.log(res)
        this.laramnumconfig = {
          ...this.laramnumconfig,
          number: [res.exceptionCount]
        }
      });


         // this.userOverview = res.data;
          // this.onlineconfig = {
          //   ...this.onlineconfig,
          //   //number: [res.data.onlineNum]
          //   number: [200]
          // }
          // this.totalconfig = {
          //   ...this.totalconfig,
          //   //number: [res.data.totalNum]
          //   number: [200]
          // }
          // this.offlineconfig = {
          //   ...this.offlineconfig,
          //  //number: [res.data.offlineNum]
          //   number: [200]
          // }
          // this.laramnumconfig = {
          //   ...this.laramnumconfig,
          //   //number: [res.data.alarmNum]
          //   number: [200]
          // }
          this.switper();
        //} else {
          //this.pageflag = false;
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
      this.timer = setInterval(looper, 3000);
    },
  }
}
</script>

<style lang='scss' scoped>
.user_Overview {
  li {
    flex: 0.5;
    p {
      text-align: center;
      //height: 16px;
      font-size: 4px;
    }

    .user_Overview_nums {
      width: 50px;
      height: 50px;
      text-align: center;
      line-height: 5px;
      font-size: 3px;
      //margin-top: 20px;
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

      //&.bgdonghua::before {
      //  animation: rotating 14s linear infinite;
      //}
    }

    .allnum {
      //&::before {
        background-image: url("../../../assets/imgs/left_top_lan.png");
      //}
    }

    .online {
      //&::before {
        background-image: url("../../../assets/imgs/left_top_lv.png");
      //}
    }

    .offline {
      //&::before {
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

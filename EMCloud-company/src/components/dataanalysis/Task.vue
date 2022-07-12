<template>
  <div>
    <div>
      <el-form>
        任务信息统计
      </el-form>
    </div>
    <div>
      <el-row class="el-row-report">
        <el-col class="el-col-report">
          <div id="chartEquipState" class="chartEquipStatesy"></div>
        </el-col>
        <el-col class="el-col-report">
          <div id="chartEquipType" class="chartEquipTypesy"></div>
        </el-col>
      </el-row>
    </div>
  </div>




</template>

<script>


import {getEquipState, getEquipTaskType} from "../../api";

export default {
  name: "Task",

  data() {
    return {
      loading: false,
      timer: null,
      online:0,
      notonline: 0,
      color:['#5470c6', '#91cc75', '#fac858', '#ee6666', '#73c0de', '#3ba272', '#fc8452', '#9a60b4', '#ea7ccc'],
      // 视图
      chartEquipState: null,
      EquipStateData: [],
      // 查询表单
      chartEquipTaskType: null,
      EquipTaskTypeData: [],


    }
  },
  mounted() {
    // 初始化
    // 设备是否在线
    this.chartEquipState = this.$echarts.init(document.getElementById('chartEquipState'))
    this.chartEquipState.setOption(this.optionEquipState)
    this.getEquipState()

    //设备种类异常
    this.chartEquipTaskType = this.$echarts.init(document.getElementById('chartEquipType'))
    this.chartEquipTaskType.setOption(this.optionEquipTaskType)
    this.getEquipTaskType()
  },
  methods: {
    // 设备是否在线饼图
    getEquipState() {
      let params = new URLSearchParams()
      params.append('company_id',sessionStorage.getItem("company_id") )
      this.loading = true
      getEquipState(params).then((res) => {
        if (res) {
          console.log(res)
          this.loading = false
          for (let i = 0; i < res.length; i++) {
            if(res[i].equipstate==1){//如果equipstate==1，说明表示在线的数量
              this.online=res[i].count
            }else if(res[i].equipstate==0){
              this.notonline=res[i].count
            }
          }
          this.chartEquipState.setOption(this.optionEquipState)
        }
      })


    },

    getEquipTaskType() {
      this.loading = true
      let params = new URLSearchParams()
      params.append('company_id',sessionStorage.getItem("company_id") )
      getEquipTaskType(params).then((res) => {
        if (res) {
          this.loading = false
          console.log(res)
          this.EquipTaskTypeData = [];
          for (let i = 0; i < res.length; i++) {//有多少类型，循环多少次
            if (res[i].product_type && res[i].task_num) {
              this.EquipTaskTypeData [i] = {//饼图的series数据
                name: res[i].product_type,
                value: res[i].task_num,
                itemStyle: {color: this.color[i]}
              }

            }
          }
          this.chartEquipTaskType.setOption(this.optionEquipTaskType)
        }
      })
    },


  },
  computed: {
    // 年
    optionEquipState() {
      return {
        title: {
          text: '设备信息',
          // subtext: 'Fake Data',
          left: 'center'
        },
        tooltip: {
          trigger: 'item'
        },
        legend: {
          orient: 'vertical',
          left: 'left'
        },
        color:['#5470c6', '#91cc75', '#fac858', '#ee6666', '#73c0de', '#3ba272', '#fc8452', '#9a60b4', '#ea7ccc'],
        series: [
          {
            name: '设备状态',
            type: 'pie',
            radius: '50%',
            data: [
              { value: this.online, name: '在线' },
              { value: this.notonline, name: '离线' }
            ],
            emphasis: {
              itemStyle: {
                shadowBlur: 10,
                shadowOffsetX: 0,
                shadowColor: 'rgba(0, 0, 0, 0.5)'
              }
            }
          }
        ]
      }
    },
    optionEquipTaskType() {
      return {
        title: {
          text: '任务数量',
          subtext: '按照设备类型',
          left: 'center'
        },
        tooltip: {
          trigger: 'item',
          formatter: '{a} <br/>{b} : {c} ({d}%)'
        },
        legend: {
          orient: 'vertical',
          left: 'left'
        },
        series: [
          {
            name: '任务数量',
            type: 'pie',
            radius: '50%',
            data: this.EquipTaskTypeData,
            emphasis: {
              itemStyle: {
                shadowBlur: 10,
                shadowOffsetX: 0,
                shadowColor: 'rgba(0, 0, 0, 0.5)'
              }
            }
          }
        ]
      }
    }

  },

  destroyed() {
    clearInterval(this.timer)
  }


}
</script>

<style scoped>

.chartEquipStatesy {
  border:2px solid #ee0909;
  width: 100%;
  height: 100%;
}
.chartEquipTypesy {
  border:2px solid #ee0909;
  width: 100%;
  height: 100%;
}

.el-row-report {
  width: 100%;
  height: auto;
  display: flex;
}
.el-col-report {
  height: 450px;
  margin-top: 50px;
  margin-right: 20px;
  flex: 1;
}


</style>




<template>
  <div class="contents">
    <el-breadcrumb separator-class="el-icon-arrow-right">
<!--      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>-->
      <el-breadcrumb-item>实时监控</el-breadcrumb-item>
      <el-breadcrumb-item>设备状态监控</el-breadcrumb-item>
    </el-breadcrumb>
    <el-tabs type="border-card" class="contents-table" v-model="activeName">
      <el-tab-pane label="实时设备数据列表" name="equipment-list">
        <div class="handle-box">
          <el-select v-model="queryInfo.queryCompanyId"
                     size="small"
                     @change=""
                     placeholder="根据公司查询">
            <el-option
              v-for="item in companySelectList"
              :key="item.company_id"
              :label="item.company_name"
              :value="item.company_id">
            </el-option>
          </el-select>
          <el-button type="primary" size="small" @click="getDataByCid">查询</el-button>
        </div>
        <div class="table" >
          <el-table :data="equipmentUpdateData" border stripe height="450px">
            <el-table-column label="设备ID" prop="equipment_id" />
            <el-table-column label="设备编号" prop="equipment_number" />
            <el-table-column label="所属产品名称" prop="product_name" />
            <el-table-column label="所属产品类型" prop="product_type_name" />
            <el-table-column label="更新时间" prop="state_update_time" />
            <el-table-column label="所属公司" prop="company_name" />
            <el-table-column label="操作"
                             width="200px">
              <template slot-scope="scope">
                <el-button type="primary" size="small"
                  @click="checkStateInfo(scope.row)">查看实时数据</el-button>
              </template>
            </el-table-column>

          </el-table>
          <el-pagination
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :current-page="queryInfo.pagenum"
            :page-sizes="[5, 8, 10,15]"
            :page-size="queryInfo.pagesize"
            layout="total, sizes, prev, pager, next, jumper"
            :total="total" background>
          </el-pagination>
        </div>
      </el-tab-pane>
      <el-tab-pane label="实时设备数据查看" name="state-info" disabled>
        <el-descriptions border column="2">
          <el-descriptions-item label="设备ID">{{ stateInfo.equipment_id }}</el-descriptions-item>
          <el-descriptions-item label="设备编号">{{ stateInfo.equipment_number}}</el-descriptions-item>
          <el-descriptions-item label="所属产品名称">{{stateInfo.product_name}}</el-descriptions-item>
          <el-descriptions-item label="所属产品类型">{{stateInfo.product_type_name}}</el-descriptions-item>
          <el-descriptions-item label="开机时间">{{stateInfo.power_on_time}}</el-descriptions-item>
          <el-descriptions-item label="所属公司">{{stateInfo.company_name}}</el-descriptions-item>
          <el-descriptions-item label="剩余电量">{{ stateInfo.battery }}</el-descriptions-item>
          <el-descriptions-item label="执行的cap信息">{{ stateInfo.exec_cap }}</el-descriptions-item>
          <el-descriptions-item label="执行的cap信息">{{ stateInfo.exec_cap }}</el-descriptions-item>
          <el-descriptions-item label="总里程">{{ stateInfo.total_odom }}</el-descriptions-item>
          <el-descriptions-item label="开机里程">{{ stateInfo.today_odom }}</el-descriptions-item>
          <el-descriptions-item label="实时位置">( {{stateInfo.xpos}} , {{stateInfo.ypos}} )</el-descriptions-item>
          <el-descriptions-item label="更新时间">{{stateInfo.state_update_time}}</el-descriptions-item>
        </el-descriptions>
      </el-tab-pane>
    </el-tabs>

  </div>
</template>

<script>


import {mapOption} from "../../assets/map/mapOption";
import china from "../../assets/map/china_old.json"
import cityData from "../../assets/map/china_city.json"
import "../../libs/utils"
import {
  getCompany,
  getEquipmentListOfUpdateTime, getEquipmentListOfUpdateTimeByCid, getStateInfoByEidAndTime,
} from "../../api";



export default {
  name: "EquipmentStateMonitor",
  data() {
    return {
      activeName:'',
      company_id:'',
      equipmentUpdateData:[],
      total:0,
      queryInfo:{
        queryType:'',
        queryCompanyId:'',
        pagenum: 1,
        pagesize: 5,
      },
      companySelectList:{},
      stateInfo:{
    battery:'',
    board_disconnect: '',
    company_name:'',
    equipment_id:'',
    exec_cap: '',
    external_sts: '',
    manual_auto: '',
    mode_running: '',
    motor_error: '',
    path_running: '',
    power_on_time: '',
    product_name: '',
    product_type_name: '',
    radar_disconnect: '',
    radar_error: '',
    robot_ready: '',
    sendtime: '',
    state_update_time: '',
    target_cap: '',
    task_complete: '',
    theta: '',
    today_odom: '',
    total_odom: '',
    vel_angle: '',
    vel_line: '',
    warning_code: '',
    xpos: '',
    ypos: '',
      },
      timer:'',

    }
  },
  filters: {
    numsFilter(msg) {
      return msg || 0;
    },
  },
  created() {
    this.getUpdateData();
    this.showtabs();
    this.getCompanyList();
  },
  mounted() {
  },
  watch: {
  },
  beforeDestroy() {
    this.clearData()

  },
  methods: {
    // 监听当前页数变化的事件
    handleSizeChange(newSize){
      this.queryInfo.pagesize = newSize;
      this.queryInfo.pagenum = 1;
      this.getUpdateData()
    },
    // 监听当前页码变化的事件
    handleCurrentChange(newPage){
      this.queryInfo.pagenum = newPage
      this.getUpdateData()
    },
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
        this.getUpdateData();
      };
      this.timer = setInterval(this.getUpdateData, 5000);
    },

    getUpdateData(){
        //查询全部设备的最新更新时间
        getEquipmentListOfUpdateTime().then(res=>{
          this.equipmentUpdateData=res;
          this.total=res.length;
          console.log("Update",res);
        });
    //this.switper();
    },

    getCompanyList(){
      getCompany().then(res=>{
        this.companySelectList=res;
        this.companySelectList.push(
          {
            company_id:0,
            company_name:"全部公司"
          })
      });
    },

    getDataByCid(){
      clearInterval(this.timer);
      this.timer=null;
      let params = new URLSearchParams();
      params.append("company_id",this.queryInfo.queryCompanyId);
      if(this.queryInfo.queryCompanyId===0){
        this.getUpdateData();
      }else{
        //查询某公司设备最近更新时间
        getEquipmentListOfUpdateTimeByCid(params).then(res=>{
          this.equipmentUpdateData=res;
          if(res.length === 0){
            this.$message.warning("抱歉，没有查找到数据")
          }
        });
      }

    },


    checkStateInfo(row){
      this.activeName='state-info';
      let params = new URLSearchParams();
      params.append("equipment_id",row.equipment_id);
      params.append("state_update_time",row.state_update_time)
      getStateInfoByEidAndTime(params).then(res=>{
        this.stateInfo=res[0];
        console.log(res);
      })

    },

    showtabs(){
      if(this.$route.query.activeName!=null){
        this.activeName = this.$route.query.activeName;
      }else{
        this.activeName='equipment-list'
      }
    }

  }




}
</script>

<style lang="scss" scoped>

// 内容
.contents {
  height: 100%;
  width: 100%;
  .contents-table{
    margin-top: 40px;
    height: 100%;
  }
  .handle-box {
    margin: 40px;
    float: right;
  }
  .handle-input {
    width: 300px;
    display: inline-block;
  }

}



</style>

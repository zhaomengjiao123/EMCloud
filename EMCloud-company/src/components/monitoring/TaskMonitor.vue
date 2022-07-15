<template>
  <div class="contents">
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>任务监控</el-breadcrumb-item>
      <el-breadcrumb-item>任务监控</el-breadcrumb-item>
    </el-breadcrumb>
    <el-tabs type="border-card" class="contents-table" v-model="activeName">
      <el-tab-pane label="实时任务数据列表" name="equipment-list">
        <div class="handle-box">
          <el-select v-model="queryInfo.queryEquipmentNumber"
                     size="small"
                     @change=""
                     placeholder="根据设备编号查询">
            <el-option
              v-for="item in equipmentNumberSelectList"
              :key="item.equipment_number"
              :label="item.equipment_number"
              :value="item.equipment_number">
            </el-option>
          </el-select>
          <el-button type="primary" size="small" @click="getDataByCidAndEn">查询</el-button>
        </div>
        <div class="table" style="min-height: 500px">
          <el-table :data="equipmentUpdateData" border stripe>
            <el-table-column label="任务ID" prop="taskID" />
            <el-table-column label="设备ID" prop="agvid" />
            <el-table-column label="开始时间" prop="beginTime" />
            <el-table-column label="结束时间" prop="endTime" />
            <el-table-column label="操作"
                             width="200px">
              <template slot-scope="scope">
                <el-button type="primary" size="small"
                           @click="checkStateInfo(scope.row)">查看实时数据</el-button>
              </template>
            </el-table-column>

          </el-table>
          <el-pagination
            class="page"
            background
            layout="prev,pager, next, jumper"
            :total="total>5000?5000:total"
            :page-sizes="[10, 20, 30, 40]"
            :page-size="10"
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange">
          </el-pagination>
        </div>
      </el-tab-pane>
      <el-tab-pane label="实时设备数据查看" name="state-info" disabled>
        <el-descriptions border column="2">
          <el-descriptions-item label="任务ID">{{ stateInfo.taskID }}</el-descriptions-item>
          <el-descriptions-item label="设备ID">{{ stateInfo.agvid}}</el-descriptions-item>
          <el-descriptions-item label="运行模式">{{ stateInfo.runModel}}</el-descriptions-item>
          <el-descriptions-item label="运行模式名称">{{stateInfo.runModelName}}</el-descriptions-item>
          <el-descriptions-item label="开始时间">{{ stateInfo.beginTime }}</el-descriptions-item>
          <el-descriptions-item label="结束时间">{{ stateInfo.endTime }}</el-descriptions-item>
          <el-descriptions-item label="花费时间">{{ stateInfo.totalMinute }}</el-descriptions-item>
          <el-descriptions-item label="消耗电量">{{ stateInfo.costbattery }}</el-descriptions-item>
          <el-descriptions-item label="开始电量">{{stateInfo.beginbattery}}</el-descriptions-item>
          <el-descriptions-item label="结束电量">{{stateInfo.endbattery}}</el-descriptions-item>
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
  getEquipmentListOfUpdateTime,
  getEquipmentListOfUpdateTimeByCid, getEquipmentListOfUpdateTimeByCidAndEn,
  getEquipmentNumberListByCid,
  getStateInfoByEidAndTime, getTaskByCompanyId, getTaskByEquip, getTaskById,
} from "../../api";



export default {
  name: "TaskMonitor",
  data() {
    return {
      activeName:'',
      company_id:'',
      equipmentUpdateData:[],
      total:0,
      queryInfo:{
        queryType:'',
        queryEquipmentNumber:'',
      },
      equipmentNumberSelectList:{},
      stateInfo:{
        taskID:'',
        runModel: '',
        runModelName:'',
        beginTime:'',
        endTime: '',
        totalMinute: '',
        agvid: '',
        costbattery: '',
        beginbattery: '',
        endbattery: '',
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
    this.company_id = sessionStorage.getItem("company_id");

    this.getUpdateData();
    this.showtabs();
    this.getCompanyList();
    //this.switper();
  },
  mounted() {
  },
  watch: {
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
      console.log("company_id:",this.company_id)
      //alert(this.company_id)
      let params = new URLSearchParams();
      params.append("company_id",this.company_id);
      //查询公司内全部设备的编号
      getEquipmentNumberListByCid(params).then(res=>{
        this.equipmentNumberSelectList=res;
        console.log("NNN",res)
        this.equipmentNumberSelectList.push(
          {
            equipment_id:0,
            equipment_number:"全部设备"
          })
      });
      getTaskByCompanyId(params).then(res=>{
        this.equipmentUpdateData=res;
      });
      this.switper();

    },


    getDataByCidAndEn(){
      clearInterval(this.timer);
      this.timer=null;
      if(this.queryInfo.queryEquipmentNumber === "全部设备"){
        this.getUpdateData();
      }else{
        let params = new URLSearchParams();
        params.append("equipment_number",this.queryInfo.queryEquipmentNumber);
        params.append("company_id",this.company_id);

        //查询某公司设备最近更新时间
        getTaskByEquip(params).then(res=>{
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
      params.append("taskID",row.taskID);
      getTaskById(params).then(res=>{
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

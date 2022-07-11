<template>
  <div class="contents">
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>异常监控</el-breadcrumb-item>
      <el-breadcrumb-item>紧急警告</el-breadcrumb-item>
    </el-breadcrumb>
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
      <!--      <el-button class="handle-del mr10" type="primary" size="mini">销售时间</el-button>-->
      <!--      <el-input v-model="select_word" class="handle-input mr10" size="mini" placeholder="年/月/日"></el-input>-->
      <el-button type="primary" size="small" @click="getCompanyEmergency">查询</el-button>
      <!--      <el-button class="el-button-back"  type="primary" size="mini" @click="back">返回</el-button>-->
    </div>
    <div class="table" style="min-height: 500px">
      <el-table :data="emergencyData" border stripe>
        <el-table-column label="设备ID" prop="equipment_id" />
        <el-table-column label="设备编号" prop="equipment_number" />
        <el-table-column label="紧急警告信息" prop="emergency_warning_content" />
        <el-table-column label="紧急警告时间" prop="emergency_warning_time" />
        <el-table-column label="销售人" prop="salesman_name" />
        <el-table-column label="销售时间" prop="sell_time" />
        <el-table-column label="所属公司" prop="company_name" />

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
  </div>
</template>

<script>


import {mapOption} from "../../assets/map/mapOption";
import china from "../../assets/map/china_old.json"
import cityData from "../../assets/map/china_city.json"
import "../../libs/utils"
import {getAllEmergencyInfo, getAllWarningInfo, getCompany, getEmergencyByCid, getWarningByCid} from "../../api";



export default {
  name: "EmergencyWarningList",
  props: {},
  data() {
    return {
      company_id:'',
      emergencyData:[],
      total:0,
      queryInfo:{
        queryType:'',
        queryCompanyId:'',
      },
      companySelectList:{},

    }
  },
  filters: {
    numsFilter(msg) {
      return msg || 0;
    },
  },
  created() {
    this.company_id = this.$route.query.company_id;
    this.getData();


  },
  mounted() {
  },
  watch: {
  },
  methods: {
    back(){
      this.$router.go(-1)
    },
    getData(){
      //查询全部的公司
      getCompany().then(res=>{
        this.companySelectList=res;
        this.companySelectList.push(
          {
            company_id:0,
            company_name:"全部公司"
          })
      });
      if(this.company_id==null){
        // this.$message.info("companyId:"+this.company_id);
        getAllEmergencyInfo().then(res=>{
          this.emergencyData=res
          console.log(res)
        });
      }else{
        let params = new URLSearchParams();
        params.append("company_id",this.company_id);
        console.log("Company_id:",this.company_id)
        getEmergencyByCid(params).then(res=>{
          console.log(res)
          this.emergencyData = res;
        });
      }

    },
    getCompanyEmergency(){
      if(this.queryInfo.queryCompanyId===0){
        this.getData();
      }else{
        let params = new URLSearchParams();
        params.append("company_id",this.queryInfo.queryCompanyId);
        //查询某个公司的报警信息
        getEmergencyByCid(params).then(res=>{
          console.log(res)
          this.emergencyData = res;
        });
      }

    },

  }




}
</script>

<style lang="scss" scoped>

// 内容
.contents {
  height: 100%;
  width: 100%;
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

<template>
  <div class="contents">
    <div class="handle-box">
      <el-form :inline="true" :model="queryInfo" class="demo-form-inline">
        <el-form-item label="">
          <el-select v-model="queryInfo.queryCompanyId"
                     @change=""
                     placeholder="根据公司查询">
            <el-option
              v-for="item in companySelectList"
              :key="item.company_id"
              :label="item.company_name"
              :value="item.company_id">
            </el-option>
          </el-select>
        </el-form-item>

        <el-form-item>
          <el-button type="primary" @click="getCompanyException">查询</el-button>
        </el-form-item>
      </el-form>

    </div>
    <div class="table" style="min-height: 500px">
      <el-table :data="exceptionData" border stripe>
        <el-table-column label="设备ID" prop="equipment_id" />
        <el-table-column label="设备编号" prop="equipment_number" />
        <el-table-column label="异常信息" prop="abnormal_content" />
        <el-table-column label="异常时间" prop="adnormal_time" />
        <el-table-column label="设备所属公司" prop="company_name" />
        <el-table-column label="设备备注" prop="equipment_comment" />
      </el-table>
    </div>
  </div>
</template>

<script>


import {mapOption} from "../../assets/map/mapOption";
import china from "../../assets/map/china_old.json"
import cityData from "../../assets/map/china_city.json"
import "../../libs/utils"
import {getAllExceptionInfo, getAllExceptionInfoByCid, getCompany, getErroByCid, getWarningByCid} from "../../api";



export default {
  name: "equipment_exceptionList",
  props: {},
  data() {
    return {
      exceptionData: [],
      company_id: '',
      companySelectList:{},
      queryInfo:{
        queryType:'',
        queryCompanyId:'',
      }
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
    getData(){
      //查询全部的异常信息
        getAllExceptionInfo().then(res=>{
          console.log(res)
          this.exceptionData = res;
        });

        //查询全部的公司
      getCompany().then(res=>{
        this.companySelectList=res;
        this.companySelectList.push(
          {
            company_id:0,
            company_name:"全部公司"
          })
      });
    },

    //查询某公司的异常信息
    getCompanyException(){
      let params = new URLSearchParams();
      params.append("company_id",this.queryInfo.queryCompanyId)
      if(this.queryInfo.queryCompanyId===0){
        getAllExceptionInfo().then(res=>{
          console.log(res)
          this.exceptionData = res;
        });
      }else{
        getAllExceptionInfoByCid(params).then(res=>{
          this.exceptionData=res;
        });
      }

    },
    back(){
      this.$router.go(-1)
    }

  }




}
</script>

<style lang="scss" scoped>

// 内容
.contents {
  height: 100%;
  width: 100%;
  .handle-box {
    margin: 20px;
    //
    //.el-button-back{
    //  float: right;
    //}
  }
  .handle-input {
    width: 300px;
    display: inline-block;
  }

}



</style>

<template>
  <div class="contents">
    <el-breadcrumb separator-class="el-icon-arrow-right">
<!--      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>-->
      <el-breadcrumb-item>异常监控</el-breadcrumb-item>
      <el-breadcrumb-item>设备预警</el-breadcrumb-item>
    </el-breadcrumb>
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
      <el-button type="primary" size="small" @click="getCompanyWarning">查询</el-button>
    </div>
    <div class="table">
      <el-table :data="warningData" border stripe height="450px">
        <el-table-column label="设备ID" prop="equipment_id" />
        <el-table-column label="设备编号" prop="equipment_number" />
        <el-table-column label="预警信息" prop="warning_content" />
        <el-table-column label="预警时间" prop="warning_time" />
        <el-table-column label="销售人" prop="salesman_name" />
        <el-table-column label="销售时间" prop="sell_time" />
        <el-table-column label="所属公司" prop="company_name" />

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
  </div>
</template>

<script>


import {mapOption} from "../../assets/map/mapOption";
import china from "../../assets/map/china_old.json"
import cityData from "../../assets/map/china_city.json"
import "../../libs/utils"
import {
  getAllWarningInfo,
  getCompany,
  getEquipmentNumberListByCid,
  getErroByCid,
  getWarningByCid,
  getWarningByEquipmentNumberAndCid
} from "../../api";



export default {
  name: "WarningList",
  props: {},
  data() {
    return {
      company_id:'',
      warningData:[],
      total:0,
      queryInfo:{
        queryType:'',
        queryEquipmentNumber:'',
        pagenum: 1,
        pagesize: 8,
      },
      equipmentNumberSelectList:{},

    }
  },
  filters: {
    numsFilter(msg) {
      return msg || 0;
    },
  },
  created() {
    this.company_id = sessionStorage.getItem("company_id");
    this.getData();

  },
  mounted() {
  },
  watch: {
  },
  methods: {
    // 监听当前页数变化的事件
    handleSizeChange(newSize){
      this.queryInfo.pagesize = newSize;
      this.queryInfo.pagenum = 1;
      this.getData();
    },
    // 监听当前页码变化的事件
    handleCurrentChange(newPage){
      this.queryInfo.pagenum = newPage
      this.getData();
    },
    back(){
      this.$router.go(-1)
    },
    getData(){
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

        getWarningByCid(params).then(res=>{
          console.log("WWWW",res)
          this.warningData = res;
          this.total=res.length;
        });
      },

    getCompanyWarning(){
      if(this.queryInfo.queryEquipmentNumber==="全部设备"){
        this.getData();
      }else{
        let params = new URLSearchParams();
        params.append("equipment_number",this.queryInfo.queryEquipmentNumber);
        params.append("company_id",this.company_id);
        console.log(this.queryInfo.queryEquipmentNumber," vfdbkdbfkjd ",this.company_id);
        getWarningByEquipmentNumberAndCid(params).then(res=>{
          console.log(res)
          if(res.length === 0){
            this.$message.warning("抱歉，没有查找到数据")
          }
          this.warningData = res;
          this.total=res.length;
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

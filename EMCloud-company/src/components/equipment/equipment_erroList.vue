<template>
  <div class="contents">
    <div class="handle-box">
      <el-button class="handle-del mr10" type="primary" size="mini">销售时间</el-button>
      <el-input v-model="select_word" class="handle-input mr10" size="mini" placeholder="年/月/日"></el-input>
      <el-button type="primary" size="mini" @click="centerDialogVisible = true">查询</el-button>
      <el-button class="el-button-back"  type="primary" size="mini" @click="back">返回</el-button>

    </div>
    <div class="table" style="min-height: 500px">
      <el-table :data="erroData" stripe>
        <el-table-column label="设备编号" prop="equipment_number" />
        <el-table-column label="产品名称" prop="product_name" />
        <el-table-column label="报警信息" prop="erro_content" />
        <el-table-column label="报警时间" prop="erro_time" />
        <el-table-column label="销售人" prop="salesman_name" />
        <el-table-column label="销售时间" prop="sell_time" />
      </el-table>
    </div>
  </div>
</template>

<script>


import {mapOption} from "../../assets/map/mapOption";
import china from "../../assets/map/china_old.json"
import cityData from "../../assets/map/china_city.json"
import "../../libs/utils"
import {getErroByCid, getWarningByCid} from "../../api";



export default {
  name: "equipment_erroList",
  props: {},
  data() {
    return {
      erroData: [],
      company_id: '',
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
        let params = new URLSearchParams();
        params.append("company_id",this.company_id);
        getErroByCid(params).then(res=>{
          console.log(res)
          this.erroData = res;
        })

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

<template>
  <div>
    <el-container>
      <el-header style="text-align: right; font-size: 12px">
      </el-header>

      <el-main>
        <el-table :data="tableData" style="width: 100%">
          <el-table-column label="产品类型ID" prop="product_type_id"></el-table-column>
          <el-table-column label="产品类型编号" prop="product_type_number"></el-table-column>
          <el-table-column label="产品类型名称" prop="product_type_name"></el-table-column>
          <el-table-column label="更新时间" prop="product_type_update_time"></el-table-column>
          <el-table-column label="备注" prop="product_type_comment"></el-table-column>
          <el-table-column align="right">
            <template slot="header" slot-scope="scope">
              <el-input v-model="search" size="mini" placeholder="输入关键字搜索"/>
            </template>
            <template slot-scope="scope">
              <el-button size="mini" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
            </template>
          </el-table-column>
        </el-table>
        <el-button style="margin: 10px 0px;"  @click="saveUserInfo" type="success" size="mini" >添加</el-button>
        <transition name="el-zoom-in-center">
          <div v-show="show" class="transition-box">
            <el-form ref="form" :model="form" label-width="80px">
              <el-form-item label="产品类型ID">
                <el-input v-model="form.product_type_id"></el-input>
              </el-form-item>
              <el-form-item label="产品类型编号">
                <el-input v-model="form.product_type_number"></el-input>
              </el-form-item>
              <el-form-item label="产品类型名称">
                <el-input v-model="form.product_type_name"></el-input>
              </el-form-item>
              <el-form-item label="更新时间">
                <el-input v-model="form.product_type_update_time"></el-input>
              </el-form-item>
              <el-form-item label="备注">
                <el-input v-model="form.product_type_comment"></el-input>
              </el-form-item>
              <el-form-item>
                <el-button type="primary" @click="onSubmit">立即创建</el-button>
                <el-button type="primary" @click="onEdit">编辑</el-button>
                <el-button type="primary" @click="saveUserInfo">重置</el-button>
              </el-form-item>
            </el-form>
          </div>
        </transition>
      </el-main>
    </el-container>

  </div>
</template>

<script>
export default {
  name: "Order",
  data() {
    return {
      show:false,
      search:"",
      tableData: [],
      form: {
        product_type_id:'',
        product_type_number: '',
        product_type_name: '',
        product_type_update_time: '',
        product_type_comment: '',
      }
    }
  },
  methods: {
    saveUserInfo(){
      this.show=!this.show;
      this.form={};
    },
    handleEdit(index, row) {
      console.log(index, row);
      this.show=!this.show;
      this.form=row;
    },
    findAllTableData(){
      this.$http.get("http://121.5.74.11:8080/productType/getAllProductType").then(res=>{
        this.tableData=res.data;
      });
    },
    onSubmit() {
      console.log(this.form.product_name);
      this.$http.post("http://121.5.74.11:8080/productType/addProductType",this.form).then(res=>{
        if(res.data.status){
          this.$message({
            message: '恭喜你，'+res.data.msg,
            type: 'success'
          });
          this.findAllTableData();
          //清空表单信息
          this.form={};
          //隐藏表单
          this.show=false;
        }else{
          this.$message.error(res.data.msg);
        }
      });
    },
    onEdit(){
      console.log(this.form.product_name);
      this.$http.post("http://121.5.74.11:8080/productType/updateProductType",this.form).then(res=>{
        console.log(res.data);
        if(res.data.status){
          this.$message({
            message: '恭喜你，'+res.data.msg,
            type: 'success'
          });
          //清空表单信息
          this.form={};
          //隐藏表单
          this.show=false;
          this.findAllTableData();
        }else{
          this.$message.error(res.data.msg);
        }
      });
    },
  },
  created(){
    this.findAllTableData();
  }
}
</script>

<style scoped>
.el-header {
  background-color: #B3C0D1;
  color: #333;
  line-height: 60px;
}
.demo-table-expand {
  font-size: 0;
}
.demo-table-expand label {
  width: 90px;
  color: #99a9bf;
}
.demo-table-expand .el-form-item {
  margin-right: 0;
  margin-bottom: 0;
  width: 50%;
}
</style>

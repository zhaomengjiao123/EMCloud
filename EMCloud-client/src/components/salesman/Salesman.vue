<template>
  <div>
    <el-container>
      <el-header style="text-align: right; font-size: 12px">
      </el-header>

      <el-main>
        <el-table :data="tableData" style="width: 100%">
          <el-table-column label="售货员id" prop="salesman_id"></el-table-column>
          <el-table-column label="售货员姓名" prop="salesman_name"></el-table-column>
          <el-table-column align="right">
            <template slot="header" slot-scope="scope">
              <el-input v-model="search" size="mini" placeholder="输入关键字搜索"/>
            </template>
            <template slot-scope="scope">
              <el-button size="mini" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
              <el-popconfirm confirm-button-text='好的' cancel-button-text='不用了' icon="el-icon-info" icon-color="red" title="这是一段内容确定删除吗？" @confirm="handleDelete(scope.$index, scope.row)">
                <el-button slot="reference" size="mini" type="danger">删除</el-button>
              </el-popconfirm>
            </template>
          </el-table-column>
        </el-table>
        <el-button style="margin: 10px 0px;"  @click="saveUserInfo" type="success" size="mini">添加</el-button>
        <transition name="el-zoom-in-center">
          <div v-show="show" class="transition-box">
            <el-form ref="form" :model="form" label-width="80px">
              <el-form-item label="售货员id">
                <el-input v-model="form.salesman_id"></el-input>
              </el-form-item>
              <el-form-item label="售货员姓名">
                <el-input v-model="form.salesman_name"></el-input>
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
        salesman_id:'',
        salesman_name:'',
      }
    }
  },
  methods: {
    saveUserInfo(){
      this.show=!this.show;
      this.form={};
    },
    handleDelete(index, row) {
      // console.log(index, row);
      console.log(row.salesman_name);
      // this.$http.get("http://121.5.74.11:8080/productTypeAttribute/deleteProductTypeAttribute/detail", {params:{"product_attribute_id":row.product_attribute_id}}).then(res=>{
      //   if(res.data.status){
      //     this.$message({
      //       message: res.data.msg,
      //       type: 'success'
      //     });
      //     this.findAllTableData();
      //   }else{
      //     this.$message.error(res.data.msg);
      //   }
      // });
    },
    handleEdit(index, row) {
      console.log(index, row);
      this.show=!this.show;
      this.form=row;
    },
    findAllTableData(){
      this.$http.get("http://121.5.74.11:8080/salesman/getAll").then(res=>{
        this.tableData=res.data;
      });
    },
    onSubmit() {
      console.log(this.form.salesman_name);
      this.$http.post("http://121.5.74.11:8080/salesman/addSalesman",this.form).then(res=>{
        if(res.data.status){
          this.$message({
            message: '恭喜你，'+res.data.msg,
            type: 'success'
          });
          //清空表单信息
          this.form={};
          //隐藏表单
          this.show=!this.show;
          this.findAllTableData();
        }else{
          this.$message.error(res.data.msg);
        }
      });
    },
    onEdit(){
      console.log(this.form.salesman_name);
      this.$http.post("http://121.5.74.11:8080/productTypeAttribute/updateProductTypeAttribute",this.form).then(res=>{
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

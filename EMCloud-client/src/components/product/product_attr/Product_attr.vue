<template>
  <div>
    <el-container>
      <el-header style="text-align: right; font-size: 12px">
      </el-header>

      <el-main>
        <el-table :data="tableData" style="width: 100%">
          <el-table-column type="expand">
            <template slot-scope="props">
              <el-form label-position="left" inline class="demo-table-expand">
                <el-form-item label="产品属性ID">
                  <span>{{ props.row.product_attribute_id }}</span>
                </el-form-item>
                <el-form-item label="产品属性编号">
                  <span>{{ props.row.product_attribute_number }}</span>
                </el-form-item>
                <el-form-item label="产品属性名称">
                  <span>{{ props.row.product_attribute_name }}</span>
                </el-form-item>
                <el-form-item label="产品属性值">
                  <span>{{ props.row.product_attribute_value }}</span>
                </el-form-item>
                <el-form-item label="更新时间">
                  <span>{{ props.row.product_attribute_update_time}}</span>
                </el-form-item>
                <el-form-item label="产品维修值">
                  <span>{{ props.row.product_attribute_repair}}</span>
                </el-form-item>
                <el-form-item label="产品预警值">
                  <span>{{ props.row.product_attribute_warning}}</span>
                </el-form-item>
                <el-form-item label="产品报警值">
                  <span>{{ props.row.product_attribute_erro}}</span>
                </el-form-item>
                <el-form-item label="产品属性是否监控">
                  <span>{{ props.row.product_attribute_monitor}}</span>
                </el-form-item>
                <el-form-item label="产品属性对应的产品类型ID">
                  <span>{{ props.row.product_type_id}}</span>
                </el-form-item>
                <el-form-item label="产品备注">
                  <span>{{ props.row.product_attribute_comment}}</span>
                </el-form-item>
              </el-form>
            </template>
          </el-table-column>
          <el-table-column label="产品属性名称" prop="product_attribute_name"></el-table-column>
          <el-table-column label="产品属性值" prop="product_attribute_value"></el-table-column>
          <el-table-column label="产品维修值" prop="product_attribute_repair"></el-table-column>
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
              <el-form-item label="产品属性ID">
                <el-input v-model="form.product_attribute_id"></el-input>
              </el-form-item>
              <el-form-item label="产品属性编号">
                <el-input v-model="form.product_attribute_number"></el-input>
              </el-form-item>
              <el-form-item label="产品属性名称">
                <el-input v-model="form.product_attribute_name"></el-input>
              </el-form-item>
              <el-form-item label="产品属性值">
                <el-input v-model="form.product_attribute_value"></el-input>
              </el-form-item>
              <el-form-item label="更新时间">
                <el-input v-model="form.product_attribute_update_time"></el-input>
              </el-form-item>
              <el-form-item label="产品维修值">
                <el-input v-model="form.product_attribute_repair"></el-input>
              </el-form-item>
              <el-form-item label="产品预警值">
                <el-input v-model="form.product_attribute_warning"></el-input>
              </el-form-item>
              <el-form-item label="产品报警值">
                <el-input v-model="form.product_attribute_erro"></el-input>
              </el-form-item>
              <el-form-item label="产品属性是否监控">
                <el-input v-model="form.product_attribute_monitor"></el-input>
              </el-form-item>
              <el-form-item label="产品属性对应的产品类型ID">
                <el-input v-model="form.product_type_id"></el-input>
              </el-form-item>
              <el-form-item label="产品备注">
                <el-input v-model="form.product_attribute_comment"></el-input>
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
        product_attribute_id:'',
        product_attribute_number:'',
        product_attribute_name:'',
        product_attribute_value:'',
        product_attribute_update_time:'',
        product_attribute_repair:'',
        product_attribute_warning:'',
        product_attribute_erro:'',
        product_attribute_monitor:'',
        product_type_id:'',
        product_attribute_comment:'',
      }
    }
  },
  methods: {
    saveUserInfo(){
      this.show=!this.show;
      this.form={};
    },
    handleDelete(index, row) {
      console.log(index, row);
      console.log(row.product_attribute_name);
      this.$http.get("http://121.5.74.11:8080/productTypeAttribute/deleteProductTypeAttribute/detail", {params:{"product_attribute_id":row.product_attribute_id}}).then(res=>{
        if(res.data.status){
          this.$message({
            message: res.data.msg,
            type: 'success'
          });
          this.findAllTableData();
        }else{
          this.$message.error(res.data.msg);
        }
      });
    },
    handleEdit(index, row) {
      console.log(index, row);
      this.show=!this.show;
      this.form=row;
    },
    findAllTableData(){
      this.$http.get("http://121.5.74.11:8080/productTypeAttribute/getAllProductTypeAttribute").then(res=>{
        this.tableData=res.data;
      });
    },
    onSubmit() {
      console.log(this.form.product_attribute_name);
      this.$http.post("http://121.5.74.11:8080/productTypeAttribute/addProductTypeAttribute",this.form).then(res=>{
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
      console.log(this.form.product_attribute_name);
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

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
                <el-form-item label="产品ID">
                  <span>{{ props.row.product_id }}</span>
                </el-form-item>
                <el-form-item label="产品名称">
                  <span>{{ props.row.product_name }}</span>
                </el-form-item>
                <el-form-item label="产品型号">
                  <span>{{ props.row.product_version }}</span>
                </el-form-item>
                <el-form-item label="产品发布日期">
                  <span>{{ props.row.product_release_date }}</span>
                </el-form-item>
                <el-form-item label="产品更新时间">
                  <span>{{ props.row.product_update_time }}</span>
                </el-form-item>
                <el-form-item label="产品图片地址">
                  <span>{{ props.row.product_pic_url }}</span>
                </el-form-item>
                <el-form-item label="产品编号">
                  <span>{{ props.row.product_number }}</span>
                </el-form-item>
                <el-form-item label="产品资料地址">
                  <span>{{ props.row.product_file_url }}</span>
                </el-form-item>
                <el-form-item label="产品备注">
                  <span>{{ props.row.product_comment }}</span>
                </el-form-item>
                <el-form-item label="产品所属类型ID">
                  <span>{{ props.row.product_type_id }}</span>
                </el-form-item>
              </el-form>
            </template>
          </el-table-column>
          <el-table-column label="产品名称" prop="product_name"></el-table-column>
          <el-table-column label="产品型号" prop="product_name"></el-table-column>
          <el-table-column label="产品备注" prop="product_comment"></el-table-column>
          <el-table-column align="right">
            <template slot="header" slot-scope="scope">
              <el-input v-model="search" size="mini" placeholder="输入关键字搜索"/>
            </template>
            <template slot-scope="scope">
              <el-button size="mini" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
            </template>
          </el-table-column>
        </el-table>
        <el-button style="margin: 10px 0px;"  @click="saveUserInfo" type="success" size="mini">添加</el-button>
        <transition name="el-zoom-in-center">
          <div v-show="show" class="transition-box">
            <el-form ref="form" :model="form" label-width="80px">
              <el-form-item label="产品ID">
                <el-input v-model="form.product_id"></el-input>
              </el-form-item>
              <el-form-item label="产品名称">
                <el-input v-model="form.product_name"></el-input>
              </el-form-item>
              <el-form-item label="产品型号">
                <el-input v-model="form.product_version"></el-input>
              </el-form-item>
              <el-form-item label="产品发布日期">
                <el-input v-model="form.product_release_date"></el-input>
              </el-form-item>
              <el-form-item label="产品更新时间">
                <el-input v-model="form.product_update_time"></el-input>
              </el-form-item>
              <el-form-item label="产品图片地址">
                <el-input v-model="form.product_pic_url"></el-input>
              </el-form-item>
              <el-form-item label="产品编号">
                <el-input v-model="form.product_number"></el-input>
              </el-form-item>
              <el-form-item label="产品资料地址">
                <el-input v-model="form.product_file_url"></el-input>
              </el-form-item>
              <el-form-item label="产品备注">
                <el-input v-model="form.product_comment"></el-input>
              </el-form-item>
              <el-form-item label="产品所属类型ID">
                <el-input v-model="form.product_type_id"></el-input>
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
        product_id:'',
        product_name: '',
        product_version: '',
        product_release_date: '',
        product_update_time: '',
        product_pic_url: '',
        product_number:'',
        product_file_url: '',
        product_comment: '',
        product_type_id: ''
      }
    }
  },
  methods: {
    saveUserInfo(){
      this.show=true;
      this.form={};
    },
    handleEdit(index, row) {
      console.log(index, row);
      this.show=true;
      this.form=row;
    },
    findAllTableData(){
      this.$http.get("http://121.5.74.11:8080/product/getAllProduct").then(res=>{
        this.tableData=res.data;
      });
    },
    onSubmit() {
      console.log(this.form.product_name);
      this.$http.post("http://121.5.74.11:8080/product/addProduct",this.form).then(res=>{
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
    onEdit(){
      console.log(this.form.product_name);
      this.$http.post("http://121.5.74.11:8080/product/updateProduct",this.form).then(res=>{
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

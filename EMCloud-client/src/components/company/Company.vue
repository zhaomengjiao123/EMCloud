<template>
  <div>
    <el-table
      :data="tableData.filter(data => !search || data.name.toLowerCase().includes(search.toLowerCase()))"
      style="width: 100%">
      <el-table-column
        label="公司id"
        width="180">
        <template slot-scope="scope">
          <i class="el-icon-time"></i>
          <span style="margin-left: 10px">{{ scope.row.company_id }}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="公司名称"
        width="180">
        <template slot-scope="scope">
          <el-popover trigger="hover" placement="top">
            <div slot="reference" class="name-wrapper">
              <el-tag size="medium">{{ scope.row.company_name }}</el-tag>
            </div>
          </el-popover>
        </template>
      </el-table-column>
      <el-table-column label="公司类型" prop="company_type"></el-table-column>
      <el-table-column label="公司位置" prop="company_local"></el-table-column>
      <el-table-column label="公司备注" prop="company_comment"></el-table-column>
      <el-table-column align="right">
        <template slot="header" slot-scope="scope">
          <el-input
            v-model="search"
            size="mini"
            placeholder="输入关键字搜索"/>
        </template>
        <template slot-scope="scope">
          <el-button
            size="mini"
            @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
          <el-popconfirm
            confirm-button-text='好的'
            cancel-button-text='不用了'
            icon="el-icon-info"
            icon-color="red"
            title="这是一段内容确定删除吗？"
            @confirm="handleDelete(scope.$index, scope.row)"
          >
            <el-button slot="reference" size="mini" type="danger">删除</el-button>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>
    <el-button style="margin: 10px 0px;"  @click="saveUserInfo" type="success" size="mini">添加</el-button>
    <transition name="el-zoom-in-center">
      <div v-show="show" class="transition-box">
        <el-form ref="form" :model="form" label-width="80px">
          <el-form-item label="公司id">
            <el-input v-model="form.company_id"></el-input>
          </el-form-item>
          <el-form-item label="公司名称">
            <el-input v-model="form.company_name"></el-input>
          </el-form-item>
          <el-form-item label="公司类型">
            <el-input v-model="form.company_type"></el-input>
          </el-form-item>
          <el-form-item label="公司位置">
            <el-input v-model="form.company_local"></el-input>
          </el-form-item>
          <el-form-item label="公司备注">
            <el-input v-model="form.company_comment"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="onSubmit">立即创建</el-button>
            <el-button type="primary" @click="onEdit">编辑</el-button>
            <el-button @click="saveUserInfo">重置</el-button>
          </el-form-item>
        </el-form>
      </div>
    </transition>
  </div>
</template>

<script>
export default {
  name: "List",
  data() {
    return {
      tableData: [],
      search:"",
      show:false,
      form: {
        company_id: '',
        company_name: '',
        company_type: '',
        company_local: '',
        company_comment:'',
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
    handleDelete(index, row) {
      console.log(index, row);
      console.log(row.company_id);
      this.$http.delete("http://121.5.74.11:8080/company/deleteCompany/detail", {params:{"company_id":row.company_id}}).then(res=>{
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
    onSubmit() {
      let userData = new URLSearchParams();
      userData.append('company_id', this.form.company_id)
      userData.append('company_name', this.form.company_name)
      userData.append('company_type', this.form.company_type)
      userData.append('company_local', this.form.company_local)
      userData.append('company_comment', this.form.company_comment)
      this.$http.post("http://121.5.74.11:8080/company/addCompany",userData).then(res=>{
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
      console.log(this.form.company_id);
      this.$http.post("http://121.5.74.11:8080/company/update",this.form).then(res=>{
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
    findAllTableData(){
      this.$http.get("http://121.5.74.11:8080/company/getAllCompany").then(res=>{
        this.tableData=res.data;
      });
    }
  },
  created(){
    this.findAllTableData();
  }
}
</script>

<style scoped>
.transition-box {
  margin-bottom: 10px;
  width: 100%;
  height: 600px;
  border-radius: 4px;
  padding: 40px 20px;
  box-sizing: border-box;
  margin-right: 20px;
}
</style>

<template>
  <div>
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>用户管理</el-breadcrumb-item>
      <el-breadcrumb-item>超级管理员</el-breadcrumb-item>
    </el-breadcrumb>
    <el-card class="box-card">
      <el-row :gutter="20">
        <el-col :span="4">
          <el-button type="primary" @click="addDialogVisible = true">添加管理员</el-button>
        </el-col>
        <el-col :span="4">
          <el-button type="primary" @click="addDialogVisible1 = true">添加普通用户</el-button>
        </el-col>
        <el-col :span="5">
          <el-input placeholder="请输入内容" v-model="queryInfo.query">
            <el-button slot="append" icon="el-icon-search" @click="getUserByCompany" ></el-button>
          </el-input>
        </el-col>
        <el-col :span="5">
          <el-input placeholder="请输入用户手机号" v-model="queryInfo1.query1">
            <el-button slot="append" icon="el-icon-search" @click="getUserByPhone" ></el-button>
          </el-input>
        </el-col>
      </el-row>
      <el-table :data="userlist" border stripe>
        <el-table-column type="index" label="#"></el-table-column>
        <el-table-column prop="user_name" label="用户名"></el-table-column>
        <el-table-column prop="user_phone" label="手机号"></el-table-column>
        <el-table-column prop="user_tele" label="电话"></el-table-column>
        <el-table-column prop="user_company" label="公司名"></el-table-column>
        <el-table-column prop="user_depart" label="部门"></el-table-column>
        <el-table-column prop="user_auth" label="权限"></el-table-column>
        <el-table-column label="操作"
        width="300px">
          <template slot-scope="scope">
            <el-button
              type="primary"
              icon="el-icon-edit"
              size="small"
              @click="edit(scope.row)"
            >修改</el-button>
            <el-button
              type="danger"
              icon="el-icon-delete"
              size="small"
              @click="deleteUser(scope.row)"
            >删除</el-button>
            <el-button
              type="warning"
              icon="el-icon-edit"
              size="small"
              @click="updatePasswd(scope.row)"
            >重置密码</el-button>
          </template>
        </el-table-column>
      </el-table>

<!--      <el-pagination-->
<!--        @size-change="handleSizeChange"-->
<!--        @current-change="handleCurrentChange"-->
<!--        :current-page="queryInfo.pagenum"-->
<!--        :page-sizes="[5, 10, 15, 20]"-->
<!--        :page-size="queryInfo.pagesize"-->
<!--        layout="total, sizes, prev, pager, next, jumper"-->
<!--        :total="total">-->
<!--      </el-pagination>-->

    </el-card>

    <el-dialog title="添加管理员" :visible.sync="addDialogVisible" width="40%" >
      <el-form :model="addAdminForm" ref="addAdminFormRef" label-width="100px">
        <el-form-item label="用户名" prop="actname">
          <el-input v-model="addAdminForm.user_name"></el-input>
        </el-form-item>
        <el-form-item label="手机号" prop="actuser_phone">
          <el-input v-model="addAdminForm.user_phone"></el-input>
        </el-form-item>
        <el-form-item label="电话" prop="actuser_tele">
          <el-input v-model="addAdminForm.user_tele"></el-input>
        </el-form-item>
        <el-form-item label="公司" prop="actuser_company">
          <el-input v-model="addAdminForm.user_company"></el-input>
        </el-form-item>
        <el-form-item label="部门" prop="actuser_depart">
          <el-input v-model="addAdminForm.user_depart"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="addDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="addAdmin">确 定</el-button>
      </span>
    </el-dialog>


    <el-dialog title="添加普通用户" :visible.sync="addDialogVisible1" width="40%" >
      <el-form :model="addUserForm" ref="addAdminFormRef" label-width="100px">
        <el-form-item label="用户名" prop="actuser_name">
          <el-input v-model="addUserForm.user_name"></el-input>
        </el-form-item>
        <el-form-item label="手机号" prop="actuser_phone">
          <el-input v-model="addUserForm.user_phone"></el-input>
        </el-form-item>
        <el-form-item label="电话" prop="actuser_tele">
          <el-input v-model="addUserForm.user_tele"></el-input>
        </el-form-item>
        <el-form-item label="公司" prop="actuser_company">
          <el-input v-model="addUserForm.user_company"></el-input>
        </el-form-item>
        <el-form-item label="部门" prop="actuser_depart">
          <el-input  v-model="addUserForm.user_depart"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="addDialogVisible1 = false">取 消</el-button>
        <el-button type="primary" @click="addUser">确 定</el-button>
      </span>
    </el-dialog>


    <el-dialog title="修改信息" :visible.sync="addDialogVisible2" width="40%" >
      <el-form :model="editForm" ref="addAdminFormRef" label-width="100px">
        <el-form-item label="用户名" prop="actuser_name">
          <el-input v-model="editForm.user_name"></el-input>
        </el-form-item>
        <el-form-item label="手机号" prop="actuser_phone">
          <el-input v-model="editForm.user_phone" disabled></el-input>
        </el-form-item>
        <el-form-item label="电话" prop="actuser_tele">
          <el-input v-model="editForm.user_tele"></el-input>
        </el-form-item>
        <el-form-item label="公司" prop="actuser_company">
          <el-input v-model="editForm.user_company"></el-input>
        </el-form-item>
        <el-form-item label="部门" prop="actuser_depart">
          <el-input  v-model="editForm.user_depart"></el-input>
        </el-form-item>
        <el-form-item label="权限" prop="actuser_auth">
          <el-input  v-model="editForm.user_auth"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="addDialogVisible2 = false">取 消</el-button>
        <el-button type="primary" @click="editUser">确 定</el-button>
      </span>
    </el-dialog>

  </div>
</template>

<script>
import {
  addAdmin,
  addUser,
  deleteUser,
  getUser,
  getUserByCompany,
  getUserByPhone,
  updatePasswd,
  updateUser, updateUserSuper
} from "../../api";

export default {
name: "SuperAdmin",
  data(){
    return {
      // intervalId:null,
      userlist: [{
        user_name: '',
        user_phone: '',
        user_tele: '',
        user_company: '',
        user_depart: '',
        user_auth:''
      },
      ],
      addDialogVisible: false,//添加管理员的页面
      addDialogVisible1: false,//添加用户的页面
      addDialogVisible2: false,//修改的页面
      addAdminForm: {
        user_name: '',
        user_phone: '',
        user_tele: '',
        user_company: '',
        user_depart: '',
      },
      addUserForm: {
        user_name: '',
        user_phone: '',
        user_tele: '',
        user_company: '',
        user_depart: '',
      },
      editForm: {
        user_name: '',
        user_phone: '',
        user_tele: '',
        user_company: '',
        user_depart: '',
        user_auth:''
      },
      queryInfo:{
        query:'',
        // pagenum:1,
        // pagesize:5
      },

      total: 0,
      queryInfo1:{
        query1:'',
      }
    }
  },
  created () {
    this.getUser()
    // this.dataRefreh()
  },
  methods: {

    // dataRefreh() {
    //   // 计时器正在进行中，退出函数
    //   if (this.intervalId != null) {
    //     return;
    //   }
    //   // 计时器为空，操作
    //   this.intervalId = setInterval(() => {
    //     // console.log("刷新" + new Date());
    //     this.getUser(); //加载数据函数
    //   }, 5000);
    // },
    // // 停止定时器
    // clear() {
    //   clearInterval(this.intervalId); //清除计时器
    //   this.intervalId = null; //设置为null
    // },
    //
    // handleSizeChange(pagesize) {
    //   this.queryInfo.pagesize = pagesize;
    //   this.getUser();
    // },
    // handleCurrentChange(pagenum) {
    //   this.queryInfo.pagenum = pagenum;
    //   this.getUser();
    // },

    edit(row){
      this.addDialogVisible2 = true
      this.editForm={
        user_name: row.user_name,
        user_phone: row.user_phone,
        user_tele: row.user_tele,
        user_company: row.user_company,
        user_depart: row.user_depart,
        user_auth: row.user_auth
      }
    },
    deleteUser(row){
      let params = new URLSearchParams()
      params.append('user_phone', row.user_phone)
      console.log(row.user_phone)
      deleteUser(params)
        .then(res => {
          if (res.code == 1) {
            this.$message({
              message: '删除成功',
              type: 'success'
            })
            this.getUser()
          } else {
            this.$message({
              message: '删除失败',
              type: 'error'
            })
          }
        })

    },

    updatePasswd(row){
      let params = new URLSearchParams()
      params.append('user_phone', row.user_phone)
      updatePasswd(params)
        .then(res => {
          if (res.code == 1) {
            this.$message({
              message: '修改成功',
              type: 'success'
            })
          } else {
            this.$message({
              message: '修改失败',
              type: 'error'
            })
          }
        })
        .catch(err => {
          console.log(err)
        })
    },

    getUser(){
      this.userlist=[];
      getUser()
        .then(res => {
          this.userlist = res
          console.log(res)
        })
        .catch(err => {
          console.log(err)
        })
    },

    getUserByCompany(){
      this.userlist=[
      ];
      let params = new URLSearchParams()
      params.append('company_id', this.queryInfo.query)
      getUserByCompany(params)
        .then(res => {
          this.userlist = res
          console.log(res)
        })
        .catch(err => {
          console.log(err)
        })
    },
    getUserByPhone(){
      this.userlist=[
      ];
      if(this.queryInfo1.query1){
        let params = new URLSearchParams()
        params.append('user_phone', this.queryInfo1.query1)
        getUserByPhone(params)
          .then(res => {
            this.userlist = res
            console.log(res)
          })
          .catch(err => {
            console.log(err)
          })
      }else{
        this.getUser()
      }

    },
    addAdmin() {
      let params = new URLSearchParams()
      params.append('user_name', this.addAdminForm.user_name)
      params.append('user_phone', this.addAdminForm.user_phone)
      params.append('user_tele', this.addAdminForm.user_tele)
      params.append('company_id', this.addAdminForm.user_company)
      params.append('depart_id', this.addAdminForm.user_depart)

      addAdmin(params)
        .then(res => {
          if (res.code == 1) {
            this.$message({
              message: '添加成功',
              type: 'success'
            })
            this.getUser()
          } else {
            this.$message({
              message: '添加失败',
              type: 'error'
            })
          }
        })
        .catch(err => {
          console.log(err)
        })
      this.addDialogVisible = false

    },
    addUser() {
      let params = new URLSearchParams()
      params.append('user_name', this.addUserForm.user_name)
      params.append('user_phone', this.addUserForm.user_phone)
      params.append('user_tele', this.addUserForm.user_tele)
      params.append('company_id', this.addUserForm.user_company)
      params.append('depart_id', this.addUserForm.user_depart)

      addUser(params)
        .then(res => {
          if (res.code == 1) {
            this.$message({
              message: '添加成功',
              type: 'success'
            })
            this.getUser()
          } else {
            this.$message({
              message: '添加失败',
              type: 'error'
            })
          }
        })
        .catch(err => {
          console.log(err)
        })
      this.addDialogVisible1 = false
    },
    editUser() {
      let params = new URLSearchParams()
      params.append('user_name', this.editForm.user_name)
      params.append('user_phone', this.editForm.user_phone)
      params.append('user_tele', this.editForm.user_tele)
      params.append('company_id', this.editForm.user_company)
      params.append('depart_id', this.editForm.user_depart)
      params.append('user_auth', this.editForm.user_auth)

      updateUserSuper(params)
        .then(res => {
          if (res.code == 1) {
            this.$message({
              message: '修改成功',
              type: 'success'
            })
            this.getUser()
          } else {
            this.$message({
              message: '修改失败',
              type: 'error'
            })
          }
        })
        .catch(err => {
          console.log(err)
        })
      this.addDialogVisible2 = false
      }
  },
  // destroyed(){
  //   // 在页面销毁后，清除计时器
  //   this.clear();
  // }
}
</script>

<style scoped>
.box-card{
  margin-top: 30px;
}

</style>

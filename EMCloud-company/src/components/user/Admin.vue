<template>
  <div>
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>用户管理</el-breadcrumb-item>
      <el-breadcrumb-item>管理员</el-breadcrumb-item>
    </el-breadcrumb>
    <el-card class="box-card">
      <el-row :gutter="20">
        <el-col :span="4">
          <el-button type="primary" @click="addDialogVisible1 = true">添加普通用户</el-button>
        </el-col>
        <el-col :span="5">
          <el-input placeholder="请输入用户手机号" v-model="queryInfo1.query1">
            <el-button slot="append" icon="el-icon-search" @click="getUserByPhone" ></el-button>
          </el-input>
        </el-col>
      </el-row>
      <el-table :data="userlist" border stripe>
        <el-table-column type="index" label="序号"></el-table-column>
        <el-table-column prop="user_name" label="用户名"></el-table-column>
        <el-table-column prop="user_phone" label="手机号"></el-table-column>
        <el-table-column prop="user_tele" label="电话"></el-table-column>
        <el-table-column prop="user_company" label="公司名"></el-table-column>
        <el-table-column prop="user_depart" label="部门"></el-table-column>
        <el-table-column label="操作">
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
          </template>
        </el-table-column>
      </el-table>

      <el-pagination
        class="page"
        background
        layout="prev,pager, next, jumper"
        :total="total>5000?5000:total"
        :page-sizes="[10, 20, 30, 40]"
        :page-size="10"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
      ></el-pagination>

    </el-card>

    <el-dialog title="添加普通用户" :visible.sync="addDialogVisible1" width="40%" >
      <el-form :model="addUserForm" :rules="addAdminFormRules" ref="addAdminFormRef" label-width="100px">
        <el-form-item label="用户名" prop="user_name">
          <el-input v-model="addUserForm.user_name"></el-input>
        </el-form-item>
        <el-form-item label="手机号" prop="user_phone">
          <el-input v-model="addUserForm.user_phone"></el-input>
        </el-form-item>
        <el-form-item label="电话" prop="user_tele">
          <el-input v-model="addUserForm.user_tele"></el-input>
        </el-form-item>
        <el-form-item label="公司" prop="user_company">
          <el-select v-model="addUserForm.user_company"
                     @change="getOptionInfo2"
                     placeholder="请选择公司">
            <el-option
              v-for="item in optionData"
              :key="item.company_id"
              :label="item.company_name"
              :value="item.company_id">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="部门" prop="user_depart">

          <el-select v-model="addUserForm.user_depart"
                     placeholder="请选择部门">
            <el-option
              v-for="item in optionData2"
              :key="item.depart_id"
              :label="item.depart_name"
              :value="item.depart_id">
            </el-option>
          </el-select>
<!--          <el-input  v-model="addUserForm.user_depart"></el-input>-->
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="addDialogVisible1 = false">取 消</el-button>
        <el-button type="primary" @click="addUser">确 定</el-button>
      </span>
    </el-dialog>


    <el-dialog title="修改信息" :visible.sync="addDialogVisible2" width="40%" >
      <el-form :model="editForm" ref="addAdminFormRef" label-width="100px">
        <el-form-item label="用户名" prop="user_name">
          <el-input v-model="editForm.user_name"></el-input>
        </el-form-item>
        <el-form-item label="手机号" prop="user_phone">
          <el-input v-model="editForm.user_phone" disabled></el-input>
        </el-form-item>
        <el-form-item label="电话" prop="user_tele">
          <el-input v-model="editForm.user_tele"></el-input>
        </el-form-item>
        <el-form-item label="公司" prop="user_company">
          <el-input v-model="editForm.user_company" disabled></el-input>
        </el-form-item>
        <el-form-item label="部门" prop="user_depart">
<!--          <el-input  v-model="editForm.user_depart"></el-input>-->
          <el-select v-model="editForm.user_depart"
                     placeholder="请选择部门">
            <el-option
              v-for="item in optionData1"
              :key="item.depart_id"
              :label="item.depart_name"
              :value="item.depart_id">
            </el-option>
          </el-select>
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
  addUser,
  deleteUser, getCompany, getDepart, getDepartByCompany,
  getUser,
  getUserByCompany,
  getUserByPhone,
  getUserByPhoneInCompany,
  updateUser
} from "../../api";

export default {
  name: "SuperAdmin",
  data(){
    return {
      userlist: [{
        user_name: '',
        user_phone: '',
        user_tele: '',
        user_company: '',
        user_depart: '',
      },
      ],
      addDialogVisible1: false,//添加用户的页面
      addDialogVisible2: false,//修改的页面
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
      },
      queryInfo1:{
        query1:'',
      },
      tableData: [
        {
          user_name: '',
          user_phone: '',
          user_tele: '',
          user_company: '',
          user_depart: '',
          user_auth:''
        },
      ],
      optionData:[],
      optionData1:[],
      optionData2:[],
      pageSize: 8,
      currentPage: 1,
      total: 0,
      addAdminFormRules: {
        user_phone: [
          {required: true, message: '请输入手机号', trigger: 'blur'},
          {pattern:/^1(3\d|4[5-9]|5[0-35-9]|6[567]|7[0-8]|8\d|9[0-35-9])\d{8}$/,message: '手机号格式不正确',trigger: 'blur'}
        ]
      },
    }
  },
  created () {
    this.getUser()
    this.getOptionInfo()
    this.getOptionInfo1()
  },
  methods: {
    getOptionInfo(){
      getCompany()
        .then(res => {
          this.optionData=res
        })
    },
    getOptionInfo1(){
      let params = new URLSearchParams()
      console.log(sessionStorage.getItem("company"))
      params.append('company_id', sessionStorage.getItem("company"))
      getDepartByCompany(params)
        .then(res => {
          this.optionData1=res;
          console.log(this.optionData1)
        })
    },
    getOptionInfo2(){
      let params = new URLSearchParams()
      params.append('company_id', this.addUserForm.user_company)
      getDepartByCompany(params)
        .then(res => {
          this.optionData2=res;
        })
    },

    handleSizeChange() {},
    handleCurrentChange(val) {
      this.currentPage = val;
      if(this.queryInfo1.query1==null){
        this.getUser()
      }else {
        this.getUserByPhone()
      }
    },
    edit(row){
      this.addDialogVisible2 = true
      let b = this.optionData1.find(item => item.depart_name === row.user_depart,).depart_id
      this.editForm={
        user_name: row.user_name,
        user_phone: row.user_phone,
        user_tele: row.user_tele,
        user_company: row.user_company,
        user_depart: b
      }
    },
    deleteUser(row){
        this.$confirm('此操作将永久删除该项目, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
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
        }).catch(() => {
          this.$message.info('已取消删除');
        });

      // let params = new URLSearchParams()
      // params.append('user_phone', row.user_phone)
      // console.log(row.user_phone)
      // deleteUser(params)
      //   .then(res => {
      //     if (res.code == 1) {
      //       this.$message({
      //         message: '删除成功',
      //         type: 'success'
      //       })
      //       this.getUser()
      //     } else {
      //       this.$message({
      //         message: '删除失败',
      //         type: 'error'
      //       })
      //     }
      //   })
    },

    getUser(){
      this.userlist=[
      ];
      let params = new URLSearchParams()
      params.append('company_id', sessionStorage.getItem("company"))
      getUserByCompany(params)
        .then(res => {
          this.tableData = res;
          this.total = res.length;
          //重点：
          let begin = (this.currentPage - 1) * this.pageSize;
          let end = this.currentPage * this.pageSize;
          this.userlist = this.tableData.slice(begin, end);

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
        params.append('company_id', sessionStorage.getItem("company"))
        getUserByPhoneInCompany(params)
          .then(res => {
            this.tableData = res;
            this.total = res.length;
            //重点：
            let begin = (this.currentPage - 1) * this.pageSize;
            let end = this.currentPage * this.pageSize;
            this.userlist = this.tableData.slice(begin, end);
            // this.userlist = res
            // console.log(res)
          })
          .catch(err => {
            console.log(err)
          })
      }else{
        this.getUser()

      }


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

      updateUser(params)
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

}
</script>

<style scoped>
.box-card{
  margin-top: 30px;
}

</style>

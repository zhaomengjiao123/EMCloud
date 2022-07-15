<template>
  <div class="fillcontain">
    <el-breadcrumb separator-class="el-icon-arrow-right">
<!--      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>-->
      <el-breadcrumb-item>系统设置</el-breadcrumb-item>
      <el-breadcrumb-item>用户个人信息</el-breadcrumb-item>
    </el-breadcrumb>
    <el-card class="box-card" style="width: 800px;margin-left: 200px;margin-top: 30px">
      <div slot="header" class="clearfix">
        <span>个人信息</span>
        <el-button size="small" type="primary"  @click="updateInfo" style="margin-left: 5% ">编辑个人信息</el-button>
        <el-button size="small" type="primary"  @click="updatePasswd" style="margin-left: 15% ">修改密码</el-button>
        <el-button size="small" plain icon="el-icon-delete" type="danger" style="margin-left: 20%  "
                   @click="deleteUser(tableData[0].user_phone)" >注销账号</el-button>
      </div>
      <el-col :span="12">
      </el-col>
      <div  class="text item">
        <span>真实姓名：</span><span>{{tableData[0].user_name}}</span>
      </div>
      <div  class="text item">
        <span>电话号码：</span><span>{{tableData[0].user_phone}}</span>
      </div>
      <div  class="text item">
        <span>电话：</span><span>{{tableData[0]. user_tele}}</span>
      </div>
      <div  class="text item">
        <span>公司：</span><span>{{tableData[0].user_company}}</span>
      </div>
      <div  class="text item">
        <span>部门：</span><span>{{tableData[0].user_depart}}</span>
      </div>
    </el-card>

    <el-dialog title="修改信息" :visible.sync="addDialogVisible" width="40%" >
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
          <el-input v-model="editForm.user_company" disabled></el-input>
        </el-form-item>
        <el-form-item label="部门" prop="actuser_depart">
          <el-input  v-model="editForm.user_depart" disabled></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="addDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="editUser">确 定</el-button>
      </span>
    </el-dialog>

    <el-dialog title="修改密码" :visible.sync="addDialogVisible1" width="50%" >
      <el-form :model="editForm1" ref="addAdminFormRef" label-width="110px">
        <el-form-item label="旧密码" prop="actuser_name">
          <el-input v-model="editForm1.old_passwd"></el-input>
        </el-form-item>
        <el-form-item label="新密码" prop="actuser_phone">
          <el-input v-model="editForm1.new_passwd1"></el-input>
        </el-form-item>
        <el-form-item label="再次输入新密码" prop="actuser_tele">
          <el-input v-model="editForm1.new_passwd2"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="addDialogVisible1 = false">取 消</el-button>
        <el-button type="primary" @click="editUserPasswd">确 定</el-button>
      </span>
    </el-dialog>


  </div>

</template>

<script>
import {deleteUser, getUserByPhone, updateUserInfo, updateUserPasswd} from "../../api";

export default {
  data(){
    return {
      tableData:{
        user_name: '',
        user_phone: '',
        user_tele: '',
        user_company: '',
        user_depart: '',
      },
      addDialogVisible: false,
      addDialogVisible1: false,
      editForm: {
        user_name: '',
        user_phone: '',
        user_tele: '',
        user_company: '',
        user_depart: '',
      },
      editForm1: {
        old_passwd: '',
        new_passwd1: '',
        new_passwd2: '',
      },
    }
  },
  created(){
    this.getUserByPhone();
  },
  methods: {
    updateInfo(){
      this.addDialogVisible = true
      this.editForm={
        user_name: this.tableData[0].user_name,
        user_phone:this.tableData[0].user_phone,
        user_tele: this.tableData[0].user_tele,
        user_company:this.tableData[0].user_company,
        user_depart: this.tableData[0].user_depart,
      }
    },

    updatePasswd(){
      this.addDialogVisible1 = true
    },

    editUser() {
      let params = new URLSearchParams()
      params.append('user_name', this.editForm.user_name)
      params.append('user_phone', this.editForm.user_phone)
      params.append('user_tele', this.editForm.user_tele)

      updateUserInfo(params)
        .then(res => {
          if (res.code == 1) {
            this.$message({
              message: '修改成功',
              type: 'success'
            })
            this.getUserByPhone()
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
      this.addDialogVisible = false
    },

    editUserPasswd() {
      if(this.editForm1.new_passwd1 != this.editForm1.new_passwd2 )
      {
        this.$message({
          type: 'error',
          message: "两次输入的密码不同"
        });
      }else{
        let params = new URLSearchParams()
        params.append('user_phone', this.tableData[0].user_phone)
        params.append('user_oldpasswd', this.editForm1.old_passwd)
        params.append('user_passwd', this.editForm1.new_passwd1)

        updateUserPasswd(params)
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
        this.addDialogVisible1 = false
      }


    },

    getUserByPhone() {
      let params = new URLSearchParams()
      params.append('user_phone', sessionStorage.getItem("user_phone"))
      getUserByPhone(params)
        .then(res => {
          this.tableData = res
        })
        .catch(err => {
          console.log(err)
        })

    },

    deleteUser(user_phone){
      let params = new URLSearchParams()
      params.append('user_phone', user_phone)
      deleteUser(params)
        .then(res => {
          if (res.code == 1) {
            this.$message({
              message: '删除成功',
              type: 'success'
            })
          } else {
            this.$message({
              message: '删除失败',
              type: 'error'
            })
          }
        })
    },

  },


}
</script>

<style lang="less">
.explain_text{
  margin-top: 20px;
  text-align: center;
  font-size: 20px;
  color: #333;
}
.admin_set{
  width: 60%;
  background-color: #F9FAFC;
  min-height: 400px;
  margin: 20px auto 0;
  border-radius: 10px;
  ul > li{
    padding: 20px;
    span{
      color: #666;
    }
  }
}
.admin_title{
  margin-top: 20px;
  text-align: center;
}
.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  margin-top: 10px;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}
.avatar-uploader .el-upload:hover {
  border-color: #20a0ff;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 120px;
  height: 120px;
  line-height: 120px;
  text-align: center;
}
.avatar {
  width: 120px;
  height: 120px;
  display: block;
}
.text {
  font-size: 14px;
}

.item {
  margin-bottom: 30px;
  margin-bottom: 30px;
}

.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}
.clearfix:after {
  clear: both
}


</style>

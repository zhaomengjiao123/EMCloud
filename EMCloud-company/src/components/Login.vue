<template>
  <div class="login-container">
    <div class="login-left">
      <img src="../assets/imgs/login_img.png">
    </div>
    <div class="login-box">
      <div class="login-logo">
        <img src="../assets/imgs/login_logo.png">
      </div>
      <div class="title">
        亚历山大管理系统
      </div>

      <el-form ref="loginFormRef" class="login-form" :model="loginForm" :rules="loginRules">
        <el-form-item prop="username">
          <el-input prefix-icon="iconfont icon-user" placeholder="请输入用户名" v-model="loginForm.username"></el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input prefix-icon="iconfont icon-lock_fill" show-password placeholder="请输入密码" v-model="loginForm.password"></el-input>
        </el-form-item>
        <el-form-item class="btn1">
              <el-button type="primary" class="login-btn" @click="login">登录</el-button>
              <el-button type="primary" class="btn" @click="logon">注册</el-button>
        </el-form-item>

      </el-form>
    </div>
  </div>
</template>

<script>
import {login, updateUserInfo} from "../api";

export default {
  name: "Login.vue",
  data() {
    return {
      loginForm: {
        username: '13711111111',
        password: '123456'
      },
      loginRules: {
        username: [
          {required: true, message: '请输入用户名', tragger: 'blur'},
          {min: 3,max: 11, message: '请输入长度为11位的字符！', tragger: 'blur'}
        ],
        password: [
          {required: true, message: '请输入用户名', tragger: 'blur'},
          {min: 6,max: 20, message: '密码长度须在6到20位之间！', tragger: 'blur'}
        ]
      },
    }
  },
  methods: {
    login() {

      let params = new URLSearchParams()
      params.append('user_phone', this.loginForm.username)
      params.append('user_passwd', this.loginForm.password)
      login(params)
        .then(res => {
          if (res.code == 1) {
            this.$message({
              message: '登陆成功',
              type: 'success'
            })
            sessionStorage.setItem('user_auth',res.user_auth);
            sessionStorage.setItem('user_phone',res.user_phone);
            sessionStorage.setItem('user_name',res.user_name);
            sessionStorage.setItem('company_id',res.company_id);
            console.log("CCCC:",sessionStorage.getItem("company_id"))
            console.log(res.user_phone)
            this.$router.push('/home');
          } else {
            this.$message({
              message: '登陆失败',
              type: 'error'
            })
          }
        })
        .catch(err => {
          console.log(err)
          this.$message({
            message: '登陆失败',
            type: 'error'
          })
        })

    },
    logon(){
      this.$router.push('/logon');
    }
  }
}
</script>

<style lang="less" scoped>
.login-container{
  height: 100%;
  background-color: lightskyblue;
  display: flex;
  justify-content: center;
  align-items: center;

  .login-left {
    margin-right: 100px;
  }

  .login-box {
    width: 400px;
    height: 350px;
    background-color: #ffffff;
    padding: 50px;
    border-radius: 6px;
    box-sizing: border-box;
    position: relative;

    .login-logo {
      width: 100px;
      height: 100px;
      border: lightskyblue solid 2px;
      border-radius: 100px;
      text-align: center;
      position: absolute;
      top: -60px;
      right: 150px;
      background-color: white;
    }
    .title {
      color: lightsteelblue;
      font-size: 22px;
      position: absolute;
      top: 70px;
      right: 120px;
    }
    .login-form {
      position: absolute;
      top: 120px;
      width: 80%;

      //.login-btn {
      //  width: 40%;
      //}
      //.btn{
      //  width: 40%;
      //}
      .btn1{
        display:flex;
        justify-content: center;
      }
    }
  }
}
</style>

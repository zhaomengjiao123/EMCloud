<template>
  <div id="container">
    <div id="output">
      <div class="containerT">
        <el-form ref="loginFormRef" class="form" :model="loginForm" :rules="loginRules">
          <el-form-item prop="user_phone">
            <el-input class="input" prefix-icon="iconfont icon-user" placeholder="请输入用户名" v-model="loginForm.user_phone"></el-input>
          </el-form-item>
          <el-form-item prop="user_passwd">
            <el-input class="input" prefix-icon="iconfont icon-lock_fill" show-password placeholder="请输入密码" v-model="loginForm.user_passwd"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" class="btn" @click="login">登录</el-button>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" class="btn" @click="logon">注册</el-button>
          </el-form-item>
        </el-form>
      </div>
    </div>
  </div>
</template>

<script>
import $ from "jquery";

export default {
  name: "Login.vue",
  data() {
    return {
      loginForm: {
        user_phone: 'admin',
        user_passwd: '123456'
      },
      loginRules: {
        user_phone: [
          {required: true, message: '请输入用户名', tragger: 'blur'},
          {min: 3,max: 6, message: '请输入长度为11位的字符！', tragger: 'blur'}
        ],
        user_passwd: [
          {required: true, message: '请输入密码', tragger: 'blur'},
          {min: 6,max: 20, message: '密码长度须在6到20位之间！', tragger: 'blur'}
        ]
      },
    }
  },
  created() {
    $(function(){
      Victor("container", "output");   //登陆背景函数
      $("#entry_name").focus();
      $(document).keydown(function(event){
        if(event.keyCode==13){
          $("#entry_btn").click();
        }
      });
    });
  },
  methods: {
    login() {
      let userData = new URLSearchParams()
      userData.append('user_phone', this.loginForm.user_phone)
      userData.append('user_passwd', this.loginForm.user_passwd)
      this.$http//用this.$axios就代表main.js中的Vue.prototype.$axios = axios的axios
        .post('http://121.5.74.11:8080/user/login', userData)
        .then((res) => {//回调函数当post成功后执行
          if (res.data.code === 0) {//如果后端返回的状态码是0，失败
            this.$message.error('登陆失败！');
          }
          else{
            this.$message.success('登陆成功！');//调用第一个弹窗方法表示登录成功
            sessionStorage.setItem('userInfo',JSON.stringify(res.data));
            this.$router.push('/welcome');
          }
        })
    },
    logon(){
      this.$router.push('/logon');
    }
  }
}
</script>


<style scoped>
*{margin:0;padding:0;font-size:13px;font-family:microsoft yahei}
html,body{width:100%;height:100%;background:#fff}

#container{width:100%;height:100%;position:fixed;top:0;left:0;z-index:999}
#output{width:100%;height:100%}
.prompt{width:60%;height:30px;margin:5px auto;text-align:center;line-height:30px;font-family:microsoft yahei,Arial,sans-serif;font-size:13px;color:#df0000}
.containerT{width:400px;height:300px;text-align:center;position:absolute;top:50%;left:50%;margin:-150px 0 0 -200px;border-radius:3px}
.containerT h1{font-size:18px;font-family:microsoft yahei,Arial,sans-serif;-webkit-transition-duration:1s;transition-duration:1s;-webkit-transition-timing-function:ease-in-put;transition-timing-function:ease-in-put;font-weight:500}
.form{padding:20px 0;position:relative;z-index:2}
.form .input{-webkit-appearance:none;-moz-appearance:none;appearance:none;outline:0;border:1px solid rgba(255,255,255,.4);background-color:rgba(255,255,255,.2);width:270px;border-radius:3px;padding:8px 3px;margin:0 auto 10px;display:block;text-align:center;font-size:15px;color:#fff;-webkit-transition-duration:.25s;transition-duration:.25s;font-weight:300}
.form .input:hover{background-color:rgba(255,255,255,.4)}
.form .input:focus{background-color:#fff;width:230px;color:#333}
.form .btn{-webkit-appearance:none;-moz-appearance:none;appearance:none;outline:0;background-color:#fff;border:0;padding:10px 15px;color:#333;border-radius:3px;width:200px;cursor:pointer;font-family:microsoft yahei,Arial,sans-serif;font-size:16px;font-weight:700;-webkit-transition-duration:.25s;transition-duration:.25s}
.form .btn:hover{background-color:#f5f7f9}
</style>

<template>
  <div id="container">
    <div id="output">
      <div class="containerT">
        <el-form ref="loginFormRef" class="form" :model="loginForm" :rules="loginRules">
          <el-form-item prop="user_name">
            <el-input class="input" placeholder="请输入用户名" v-model="loginForm.user_name"></el-input>
          </el-form-item>
          <el-form-item prop="user_phone">
            <el-input class="input" placeholder="请输入手机号" v-model="loginForm.user_phone"></el-input>
          </el-form-item>
          <el-form-item prop="user_tele">
            <el-input class="input" placeholder="请输入电话" v-model="loginForm.user_tele"></el-input>
          </el-form-item>
          <el-form-item prop="user_company">
            <el-input class="input" placeholder="请输入用户所属公司" v-model="loginForm.user_company"></el-input>
          </el-form-item>
          <el-form-item prop="user_depart">
            <el-input class="input" placeholder="请输入用户所属部门" v-model="loginForm.user_depart"></el-input>
          </el-form-item>
          <el-form-item prop="user_passwd">
            <el-input class="input" placeholder="请输入用户密码" v-model="loginForm.user_passwd"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button class="btn" @click="logon">注册</el-button>
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
        user_name: '',
        user_phone: '',
        user_tele: '',
        user_company: '',
        user_depart: '',
        user_passwd: ''
      },
      loginRules: {
        user_name: [
          {required: true, message: '请输入用户名', tragger: 'blur'},
        ],
        user_phone: [
          {required: true, message: '请输入手机号', tragger: 'blur'},
        ],
        user_tele: [
          {required: true, message: '请输入电话', tragger: 'blur'},
        ],
        user_company: [
          {required: true, message: '请输入用户所属公司', tragger: 'blur'},
        ],
        user_depart: [
          {required: true, message: '请输入用户所属部门', tragger: 'blur'},
        ],
        user_passwd: [
          {required: true, message: '请输入密码', tragger: 'blur'},
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
    logon(){
      let userLogonData = new URLSearchParams()
      userLogonData.append('user_name', this.loginForm.user_name)
      userLogonData.append('user_phone', this.loginForm.user_phone)
      userLogonData.append('user_tele', this.loginForm.user_tele)
      userLogonData.append('user_company', this.loginForm.user_company)
      userLogonData.append('user_depart', this.loginForm.user_depart)
      userLogonData.append('user_passwd', this.loginForm.user_passwd)
      this.$axios//用this.$axios就代表main.js中的Vue.prototype.$axios = axios的axios
        .post('user/add', userLogonData)// userLogonData是传入的请求参数
        .then((res) => {//回调函数当post成功后执行
          if (res.data.code === 0) {//如果后端返回的状态码是0，失败
            this.$message.error('注册失败！');
          }
          else{
            this.$message.success('注册成功！请输入手机号密码登录！');//调用弹窗方法表示登录成功
            this.$router.push('/home');//返回到登录界面
          }
        })
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
.containerT{width:400px;height:300px;text-align:center;position:absolute;top:40%;left:50%;margin:-150px 0 0 -200px;border-radius:3px}
.containerT h1{font-size:18px;font-family:microsoft yahei,Arial,sans-serif;-webkit-transition-duration:1s;transition-duration:1s;-webkit-transition-timing-function:ease-in-put;transition-timing-function:ease-in-put;font-weight:500}
.form{padding:20px 0;position:relative;z-index:2;}
.form .input{-webkit-appearance:none;-moz-appearance:none;appearance:none;outline:0;border:1px solid rgba(255,255,255,.4);background-color:rgba(255,255,255,.2);width:270px;border-radius:3px;padding:8px 3px;margin:0 auto 10px;display:block;text-align:center;font-size:15px;color:#fff;-webkit-transition-duration:.25s;transition-duration:.25s;font-weight:300}
.form .input:hover{background-color:rgba(255,255,255,.4)}
.form .input:focus{background-color:#fff;width:230px;color:#333}
.form .btn{-webkit-appearance:none;-moz-appearance:none;appearance:none;outline:0;background-color:#fff;border:0;padding:10px 15px;color:#333;border-radius:3px;width:200px;cursor:pointer;font-family:microsoft yahei,Arial,sans-serif;font-size:16px;font-weight:700;-webkit-transition-duration:.25s;transition-duration:.25s}
.form .btn:hover{background-color:#f5f7f9}
</style>

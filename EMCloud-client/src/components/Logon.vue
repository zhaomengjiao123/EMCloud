<template>
  <div class="login-container">
    <div class="login-left">
      <img src="../assets/imgs/login_img.png">
    </div>
    <div class="login-box">
      <div class="title">
        亚历山大管理系统注册
      </div>
      <el-form ref="AddFormRef" class="form" :model="AddForm" :rules="loginRules">
        <el-form-item prop="user_name">
          <el-input class="input" placeholder="请输入用户名" v-model="AddForm.user_name"></el-input>
        </el-form-item>
        <el-form-item prop="user_phone">
          <el-input class="input" placeholder="请输入手机号" v-model="AddForm.user_phone"></el-input>
        </el-form-item>
        <el-form-item prop="user_tele">
          <el-input class="input" placeholder="请输入电话" v-model="AddForm.user_tele"></el-input>
        </el-form-item>
        <el-form-item  prop="user_company">
          <el-select  v-model="AddForm.user_company"
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
        <el-form-item  prop="user_depart">
          <el-select  v-model="AddForm.user_depart"
                      placeholder="请选择部门">
            <el-option
              v-for="item in optionData1"
              :key="item.depart_id"
              :label="item.depart_name"
              :value="item.depart_id">
            </el-option>
          </el-select>
        </el-form-item>


        <!--        <el-form-item prop="user_company">-->
        <!--          <el-input class="input" placeholder="请输入用户所属公司" v-model="AddForm.user_company"></el-input>-->
        <!--        </el-form-item>-->
        <!--        <el-form-item prop="user_depart">-->
        <!--          <el-input class="input" placeholder="请输入用户所属部门" v-model="AddForm.user_depart"></el-input>-->
        <!--        </el-form-item>-->
        <el-form-item prop="user_passwd">
          <el-input class="input" placeholder="请输入用户密码" v-model="AddForm.user_passwd"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" class="btn" @click="logon">注册</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
import {getCompany, getDepart, getDepartByCompany, register} from "../api";

export default {
  name: "Logon.vue",
  data() {

    return {
      AddForm: {
        user_name: '',
        user_phone: '',
        user_tele: '',
        user_company: '',
        user_depart: '',
        user_passwd: ''
      },
      optionData:[
      ],
      optionData1:[
      ],
      loginRules: {
        user_name: [
          {required: true, message: '请输入用户名', tragger: 'blur'},
        ],
        user_phone: [
          {required: true, message: '请输入手机号', trigger: 'blur'},
          {pattern:/^1(3\d|4[5-9]|5[0-35-9]|6[567]|7[0-8]|8\d|9[0-35-9])\d{8}$/,message: '手机号格式不正确',trigger: 'blur'}
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
    this.getOptionInfo()
    this.getOptionInfo1()
  },
  methods: {

    getOptionInfo(){
      getCompany()
        .then(res => {
          this.optionData=res;
        })
    },


    getOptionInfo2(){
      let params = new URLSearchParams()
      params.append('company_id', this.AddForm.user_company)
      getDepartByCompany(params)
        .then(res => {
          this.optionData1=res;
        })
    },


    getOptionInfo1(){
      getDepart()
        .then(res => {
          this.optionData1=res;
          console.log(this.optionData)
        })
    },


    logon() {
      let params = new URLSearchParams()
      params.append('user_name', this.AddForm.user_name)
      params.append('user_phone', this.AddForm.user_phone)
      params.append('user_tele', this.AddForm.user_tele)
      params.append('company_id', this.AddForm.user_company)
      params.append('depart_id', this.AddForm.user_depart)
      params.append('user_passwd', this.AddForm.user_passwd)
      register(params)
        .then(res => {
          if (res.code == 1) {
            this.$message({
              message: '注册成功',
              type: 'success'
            })
            this.$router.push('/login');
          } else {
            this.$message({
              message: '注册失败',
              type: 'error'
            })
          }
        })
        .catch(err => {
          console.log(err)
        })

    },
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
    height: 550px;
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
      top: 10px;
      right: 100px;
      text-align: center;
    }
    .form {
      position: absolute;
      top: 60px;
      width: 80%;

      .btn{
        width: 100%;
      }
    }
  }
}
</style>








<!--<template>-->
<!--  <div id="container">-->
<!--    <div id="output">-->
<!--      <div class="containerT">-->
<!--        <el-form ref="loginFormRef" class="form" :model="loginForm" :rules="loginRules">-->
<!--          <el-form-item prop="user_name">-->
<!--            <el-input class="input" placeholder="请输入用户名" v-model="loginForm.user_name"></el-input>-->
<!--          </el-form-item>-->
<!--          <el-form-item prop="user_phone">-->
<!--            <el-input class="input" placeholder="请输入手机号" v-model="loginForm.user_phone"></el-input>-->
<!--          </el-form-item>-->
<!--          <el-form-item prop="user_tele">-->
<!--            <el-input class="input" placeholder="请输入电话" v-model="loginForm.user_tele"></el-input>-->
<!--          </el-form-item>-->
<!--          <el-form-item prop="user_company">-->
<!--            <el-input class="input" placeholder="请输入用户所属公司" v-model="loginForm.user_company"></el-input>-->
<!--          </el-form-item>-->
<!--          <el-form-item prop="user_depart">-->
<!--            <el-input class="input" placeholder="请输入用户所属部门" v-model="loginForm.user_depart"></el-input>-->
<!--          </el-form-item>-->
<!--          <el-form-item prop="user_passwd">-->
<!--            <el-input class="input" placeholder="请输入用户密码" v-model="loginForm.user_passwd"></el-input>-->
<!--          </el-form-item>-->
<!--          <el-form-item>-->
<!--            <el-button class="btn" @click="logon">注册</el-button>-->
<!--          </el-form-item>-->
<!--        </el-form>-->
<!--      </div>-->
<!--    </div>-->
<!--  </div>-->
<!--</template>-->

<!--<script>-->
<!--import $ from "jquery";-->

<!--export default {-->
<!--  name: "Login.vue",-->
<!--  data() {-->
<!--    return {-->
<!--      loginForm: {-->
<!--        user_name: '',-->
<!--        user_phone: '',-->
<!--        user_tele: '',-->
<!--        user_company: '',-->
<!--        user_depart: '',-->
<!--        user_passwd: ''-->
<!--      },-->
<!--      loginRules: {-->
<!--        user_name: [-->
<!--          {required: true, message: '请输入用户名', tragger: 'blur'},-->
<!--        ],-->
<!--        user_phone: [-->
<!--          {required: true, message: '请输入手机号', tragger: 'blur'},-->
<!--        ],-->
<!--        user_tele: [-->
<!--          {required: true, message: '请输入电话', tragger: 'blur'},-->
<!--        ],-->
<!--        user_company: [-->
<!--          {required: true, message: '请输入用户所属公司', tragger: 'blur'},-->
<!--        ],-->
<!--        user_depart: [-->
<!--          {required: true, message: '请输入用户所属部门', tragger: 'blur'},-->
<!--        ],-->
<!--        user_passwd: [-->
<!--          {required: true, message: '请输入密码', tragger: 'blur'},-->
<!--        ]-->
<!--      },-->
<!--    }-->
<!--  },-->
<!--  created() {-->
<!--    $(function(){-->
<!--      Victor("container", "output");   //登陆背景函数-->
<!--      $("#entry_name").focus();-->
<!--      $(document).keydown(function(event){-->
<!--        if(event.keyCode==13){-->
<!--          $("#entry_btn").click();-->
<!--        }-->
<!--      });-->
<!--    });-->
<!--  },-->
<!--  methods: {-->
<!--    logon(){-->
<!--      let userLogonData = new URLSearchParams()-->
<!--      userLogonData.append('user_name', this.loginForm.user_name)-->
<!--      userLogonData.append('user_phone', this.loginForm.user_phone)-->
<!--      userLogonData.append('user_tele', this.loginForm.user_tele)-->
<!--      userLogonData.append('user_company', this.loginForm.user_company)-->
<!--      userLogonData.append('user_depart', this.loginForm.user_depart)-->
<!--      userLogonData.append('user_passwd', this.loginForm.user_passwd)-->
<!--      this.$axios//用this.$axios就代表main.js中的Vue.prototype.$axios = axios的axios-->
<!--        .post('user/add', userLogonData)// userLogonData是传入的请求参数-->
<!--        .then((res) => {//回调函数当post成功后执行-->
<!--          if (res.data.code === 0) {//如果后端返回的状态码是0，失败-->
<!--            this.$message.error('注册失败！');-->
<!--          }-->
<!--          else{-->
<!--            this.$message.success('注册成功！请输入手机号密码登录！');//调用弹窗方法表示登录成功-->
<!--            this.$router.push('/home');//返回到登录界面-->
<!--          }-->
<!--        })-->
<!--    }-->
<!--  }-->
<!--}-->
<!--</script>-->

<!--<style scoped>-->
<!--*{margin:0;padding:0;font-size:13px;font-family:microsoft yahei}-->
<!--html,body{width:100%;height:100%;background:#fff}-->

<!--#container{width:100%;height:100%;position:fixed;top:0;left:0;z-index:999}-->
<!--#output{width:100%;height:100%}-->
<!--.prompt{width:60%;height:30px;margin:5px auto;text-align:center;line-height:30px;font-family:microsoft yahei,Arial,sans-serif;font-size:13px;color:#df0000}-->
<!--.containerT{width:400px;height:300px;text-align:center;position:absolute;top:40%;left:50%;margin:-150px 0 0 -200px;border-radius:3px}-->
<!--.containerT h1{font-size:18px;font-family:microsoft yahei,Arial,sans-serif;-webkit-transition-duration:1s;transition-duration:1s;-webkit-transition-timing-function:ease-in-put;transition-timing-function:ease-in-put;font-weight:500}-->
<!--.form{padding:20px 0;position:relative;z-index:2;}-->
<!--.form .input{-webkit-appearance:none;-moz-appearance:none;appearance:none;outline:0;border:1px solid rgba(255,255,255,.4);background-color:rgba(255,255,255,.2);width:270px;border-radius:3px;padding:8px 3px;margin:0 auto 10px;display:block;text-align:center;font-size:15px;color:#fff;-webkit-transition-duration:.25s;transition-duration:.25s;font-weight:300}-->
<!--.form .input:hover{background-color:rgba(255,255,255,.4)}-->
<!--.form .input:focus{background-color:#fff;width:230px;color:#333}-->
<!--.form .btn{-webkit-appearance:none;-moz-appearance:none;appearance:none;outline:0;background-color:#fff;border:0;padding:10px 15px;color:#333;border-radius:3px;width:200px;cursor:pointer;font-family:microsoft yahei,Arial,sans-serif;font-size:16px;font-weight:700;-webkit-transition-duration:.25s;transition-duration:.25s}-->
<!--.form .btn:hover{background-color:#f5f7f9}-->
<!--</style>-->

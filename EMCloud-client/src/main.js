// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import ElementUI from 'element-ui'
import VueQuillEditor from 'vue-quill-editor'
import 'quill/dist/quill.core.css'
import 'quill/dist/quill.snow.css'
import 'quill/dist/quill.bubble.css'
import 'element-ui/lib/theme-chalk/index.css'
import axios from "axios"
axios.defaults.baseURL = "https://121.5.74.11:8080/"
//拦截所有请求，为请求头添加token认证
// axios.interceptors.request.use(config => {
//   const userInfo = JSON.parse(sessionStorage.getItem('userInfo'));
//   config.headers.Authorization = userInfo ? userInfo.token : '';
//   return config;
// })

import "./assets/css/global.css"
import "./assets/fonts/iconfont.css"
import ZKtable from 'vue-table-with-tree-grid'
import utils from  "./libs/utils"
import dataV from '@jiaminghi/data-view'
// import VueLazyload from 'vue-lazyload'
import 'echarts/extension/bmap/bmap'

// main.js
import * as echarts from "echarts"
Vue.prototype.$echarts = echarts

Vue.config.productionTip = false

Vue.config.productionTip = false
Vue.use(ElementUI)
Vue.prototype.$http = axios
Vue.component('tree-table',ZKtable)
Vue.prototype.$utils = utils
Vue.use(VueQuillEditor)
Vue.use(dataV)
// Vue.use(VueLazyload)

/* eslint-disable no-new */
// new Vue({
//   el: '#app',
//   router,
//   components: { App },
//   template: '<App/>'
// })

new Vue({
  el: '#app',
  router,
  render: h => h(App)
}).$mount('#app') //手动挂载

import Vue from 'vue'
import Router from 'vue-router'
import Login from '../components/Login3.vue'

Vue.use(Router)
//ES6的import方法实现路由懒加载
  const routes =  [
    {
      path: '/',
      redirect: '/shouye'
    },
    {
      path: '/login',
      component: () => import('../components/Login.vue')
    },
    {
      path: '/logon',
      component: () => import('../components/Logon.vue')
    },
    {
      path: '/shouye',
      component: () => import('../components/Home2.vue')
    },
    {
      path: '/home',
      component: () => import('../components/Home.vue'),
      redirect: '/welcome',
      children: [
        {
          path: '/welcome',
          component: () => import('../components/index/Welcome.vue')
        },
        {
          path: '/companies',
          component: () => import('../components/company/Company.vue')
        },
        // {
        //   path: '/users',
        //   component: () => import('../components/user/User.vue')
        // },
        {
          path: '/sales',
          component: () => import('../components/sale/Sales.vue')
        },
        {
          path: '/type',
          component: () => import('../components/product/pro_type.vue')
        },
        {
          path: '/prop',
          component: () => import('../components/product/pro_prop.vue')
        },
        {
          path: '/info',
          component: () => import('../components/product/pro_info.vue')
        },
        {
          path: '/monitor',
          component: () => import('../components/product/pro_monitor.vue')
        },
        {
          path: '/warn',
          component: () => import('../components/product/pro_warn.vue')
        },
        {
          path: '/rights',
          component: () => import('../components/right/Right.vue')
        },
        {
          path: '/menu',
          component: () => import('../components/right/Menu.vue')
        },
        {
          path: '/menuadmin',
          component: () => import('../components/right/MenuAdmin.vue')
        },
        {
          path: '/categories',
          component: () => import('../components/goods/Cate.vue')
        },
        {
          path: '/params',
          component: () => import('../components/goods/Params.vue')
        },
        {
          path: '/goods',
          component: () => import('../components/goods/List.vue')
        },
        {
          path: '/goods/add',
          component: () => import('../components/goods/Add.vue')
        },
        {
          path: '/goods/edit',
          component: () => import('../components/goods/Edit.vue')
        },
        {
          path: '/equipment',
          component: () => import('../components/equipment/equipment_map.vue')
        },
        {
          path: '/equipment/bigScreen',
          component: () => import('../components/equipment/equipment_map.vue')
        },
        {
          path: '/equipment/exception',
          component: () => import('../components/equipment/equipment_exceptionList.vue')
        },

        {
          path: '/dataanalysis/company/exception',
          component: () => import('../components/dataanalysis/Exception.vue')
        },
        {
          path: '/dataanalysis/company/exceptionanalysis',
          component: () => import('../components/dataanalysis/ExceptionAnalysis.vue')
        },
        {
          path: '/dataanalysis/company/task',
          component: () => import('../components/dataanalysis/Task.vue')
        },
        {
          path: '/dataanalysis/company/taskanalysis',
          component: () => import('../components/dataanalysis/TaskAnalysis.vue')
        },

          {
          path: '/user/superadmin',
          component: () => import('../components/user/SuperAdmin.vue')
        },
        {
          path: '/user/admin',
          component: () => import('../components/user/Admin.vue')
        },
        {
          path: '/user/user',
          component: () => import('../components/user/User.vue')
        },
        {
          path: '/exception/company_warning',
          component: () => import('../components/exception/WarningList.vue')
        },
        {
          path: '/exception/company_emergency',
          component: () => import('../components/exception/EmergencyWarningList.vue')
        },
        {
          path: '/exception/company_erro',
          component: () => import('../components/exception/ErroList.vue')
        },
        {
          path: '/monitoring/company_equipment_state_list',
          component: () => import('../components/monitoring/EquipmentStateMonitor.vue')
        },
      ]
    }
  ]

  const router = new Router({
  routes
  })
//路由前置守卫 to：目标路由对象，from：来源, next: 放行
// router.beforeEach((to,from,next) => {
//   if(to.path == '/login') //如果访问登录页，直接放行
//     return next();
//   const userInfo = JSON.parse(sessionStorage.getItem('userInfo'));
//   if(!userInfo) //如果用户未登录成功，则直接返回登录界面
//     return next('/login');
//   next();
// })

export default router

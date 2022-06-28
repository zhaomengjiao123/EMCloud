import Vue from 'vue'
import Router from 'vue-router'
import Login from '../components/Login.vue'

Vue.use(Router)
//ES6的import方法实现路由懒加载
  const routes =  [
    {
      path: '/',
      redirect: '/login'
    },
    {
      path: '/login',
      component: Login
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
          path: '/company',
          component: () => import('../components/company/Company.vue')
        },
        {
          path: '/user',
          component: () => import('../components/user/User.vue')
        },
        {
          path: '/product/product_attr',
          component: () => import('../components/product/product_attr/Product_attr.vue')
        },
        {
          path: '/product/product_type',
          component: () => import('../components/product/product_type/Product_type.vue')
        },
        {
          path: '/product/product_info',
          component: () => import('../components/product/product_info/Product_info.vue')
        },
        {
          path: '/salesman',
          component: () => import('../components/salesman/Salesman.vue')
        },
        {
          path: '/equipment',
          component: () => import('../components/equipment/Equipment.vue')
        }
      ]
    },
  ]

  const router = new Router({
  routes
  })
//路由前置守卫 to：目标路由对象，from：来源, next: 放行
router.beforeEach((to,from,next) => {
  if(to.path == '/login') //如果访问登录页，直接放行
    return next();
  const userInfo = JSON.parse(sessionStorage.getItem('userInfo'));
  if(!userInfo) //如果用户未登录成功，则直接返回登录界面
    return next('/login');
  next();
})

export default router

<template>
  <el-container>
    <el-header>
      <div class="left">
<!--        <img src="../assets/imgs/shop.png" >-->
        <span>企业设备管理</span>
      </div>
      <div class="right">
        <span>欢迎您：{{name}}</span>
        <el-button type="warning" @click="exit">退出</el-button>
      </div>
    </el-header>
    <el-container>
      <el-aside width= "200px" height="100%">
        <div class="toggle-btn"@click="isCollapse=!isCollapse" >|||</div>
        <el-menu :default-active="$route.path" unique-opened router :collapse="isCollapse" :collapse-transition="false">
          <el-submenu :index="item.id+''" v-for="item in menuData" :key="item.id">
            <template slot="title">
              <i :class="iconList[item.id]"></i>
              <span>{{item.authName}}</span>
            </template>
            <el-menu-item :index="'/'+child.path" v-for="child in item.children" :key="child.id">
              <template>
                <i class="el-icon-menu"></i>
                <span>{{child.authName}}</span>
              </template>
            </el-menu-item>
          </el-submenu>
        </el-menu>
      </el-aside>
      <el-main>
        <router-view></router-view>
      </el-main>
    </el-container>
  </el-container>

</template>

<script>
import { getMenuList} from "../api";

export default {
  name: "Home.vue",
  data() {
    return {
      name:'',
      userInfo: null,
      user_phone:null,
      menuData: [
        {
          id: '',
          order:'',
          path: '',
          authName: '',
          children: [
                {
                  authName: '',
                  path: '',
                  children:[]
                },
              ]
            },
      ],

      iconList: {
        "201": "iconfont icon-shouye",
        "125": "iconfont icon-users",
        "103": "iconfont icon-tijikongjian",
        "101": "iconfont icon-shangpin",
        "102": "iconfont icon-danju",
        "406": "iconfont icon-baobiao",
        "72c": "iconfont icon-user",
        "888": "iconfont icon-shangpin",
        "709": "iconfont lock_fill",
        "8c9": "iconfont morentouxiang"
      },
      list:{
        1:"201",
        2:"125",
        3:"103",
        4:"101",
        5:"102",
        6:"406",
        0:"72c",
        7:"888",
        8:"709",
        9:"8c9"
      },
      isCollapse: true
      //isCollapse: false,
     }
  },
  created() {
    this.getMenuList();
    this.name=sessionStorage.getItem("user_name")
  },
  methods: {
    exit() {
      this.$confirm('此操作将退出登录, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$message({
          type: 'success',
          message: '已退出当前账号!',
        });
        sessionStorage.clear();
        this.$router.push('/login');
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消退出'
        });
      });
    },

    getMenuList() {
      let params = new URLSearchParams()
      this.menuData=[]
      let a= sessionStorage.getItem("user_phone")
        console.log("user_phone"+a)
      params.append('user_phone',sessionStorage.getItem("user_phone"))//这里获取session中的电话号码
      getMenuList(params)
        .then(res => {
          if (res) {
            console.log(res)
            for(let i=0;i<res.length;i++){
              this.menuData[i]={},
              this.menuData[i].id=this.list[i];
              this.menuData[i].order=i;
              this.menuData[i].authName=res[i].menu_name;
              this.menuData[i].path=res[i].menu_link;
              if(res[i].children!=null){
                console.log(res[i].children.length)
                this.menuData[i].children=this.getChildren(res[i].children,res[i].children.length)
              }

              console.log(this.menuData[i])

            }

            }
          })
    },

    getChildren(children,childrenlenght){
      console.log(childrenlenght)
      let data=[]
      for(let j=0;j<childrenlenght;j++){
        data[j]={};
        data[j].authName=children[j].menu_name;
        data[j].path=children[j].menu_link;
        if(children[j].children!=null){
          data[j].children=this.getChildren(children[j].children,children[j].children.length)
        }
      }
      return data
    },



  }
}
</script>

<style lang="less" scoped>
.el-container {
  height: 100%;
  min-width: 1000px;
}
.el-header {
  background: url("../assets/imgs/header_bg.gif") repeat-x center;
  height: 50px!important; //!important优先级最高
  display: flex;
  justify-content: space-between;
  align-items: center;
  color: #ffffff;

  .left {
    img {
      width: 40px;
      height: 40px;
    }
    margin-right: 700px;
    display: flex;
    align-items: center;
  }
}
.el-aside {
  .toggle-btn {
    background: #607099;
    line-height: 24px;
    text-align: center;
    color: #ffffff;
    letter-spacing: 3px;
    cursor: pointer; //游标变成小白手
  }

}

//.el-container {
//  height: 100%;
//  min-width: 1000px;
//}
//.el-header {
//  //background: url("../assets/imgs/header_bg.gif") repeat-x center;
//  background:#000000;
//  height: 50px!important; //!important优先级最高
//  display: flex;
//  justify-content: space-between;
//  align-items: center;
//  color: #ffffff;
//
//  .left {
//    img {
//      width: 40px;
//      height: 40px;
//    }
//    margin-right: 700px;
//    display: flex;
//    align-items: center;
//  }
//}
//.el-aside {
//
//  background:#000000;
//  .toggle-btn {
//    background: #607099;
//    line-height: 24px;
//    text-align: center;
//    color: #ffffff;
//    letter-spacing: 3px;
//    cursor: pointer; //游标变成小白手
//  }
//  .el-menu{
//    border-right:none;
//  }
//}
//.el-divider{
//  margin: 0px 0;
//  background: 0 0;
//  border-top: 1px solid #E6EBF5;
//}
//.el-main{
//  background:#000000;
//}
</style>



























<!--<template>-->
<!--  <el-container>-->
<!--    <el-header>-->
<!--      <div class="left">-->
<!--        <img src="../assets/imgs/shop.png" >-->
<!--        <span>电商管理系统</span>-->
<!--      </div>-->
<!--      <div class="right">-->
<!--        <span>欢迎您：{{"userInfo.username"}}</span>-->
<!--        <el-button type="warning" @click="exit">退出</el-button>-->
<!--      </div>-->
<!--    </el-header>-->
<!--    <el-container>-->
<!--      <el-aside width= "isCollapse ? 200px : 50px">-->
<!--        <div class="toggle-btn" @click="isCollapse=!isCollapse">|||</div>-->
<!--        <el-menu :default-active="$route.path" unique-opened router :collapse="isCollapse" :collapse-transition="false">-->
<!--            <el-submenu :index="item.id+''" v-for="item in menuData2" :key="item.id">-->
<!--              <template slot="title">-->
<!--                <i :class="iconList[item.id]"></i>-->
<!--                <span>{{item.authName}}</span>-->
<!--              </template>-->
<!--              <el-menu-item :index="'/'+child.path" v-for="child in item.children" :key="child.id">-->
<!--                <template>-->
<!--                  <i class="el-icon-menu"></i>-->
<!--                  <span>{{child.authName}}</span>-->
<!--                </template>-->
<!--              </el-menu-item>-->
<!--            </el-submenu>-->
<!--        </el-menu>-->
<!--      </el-aside>-->
<!--      <el-main>-->
<!--        <router-view></router-view>-->
<!--      </el-main>-->
<!--    </el-container>-->
<!--  </el-container>-->
<!--</template>-->

<!--<script>-->
<!--export default {-->
<!--  name: "Home.vue",-->
<!--  data() {-->
<!--    return {-->
<!--      userInfo: null,-->
<!--      menuData: [],-->
<!--      iconList: {-->
<!--        "201": "iconfont icon-shouye",-->
<!--        "125": "iconfont icon-users",-->
<!--        "103": "iconfont icon-tijikongjian",-->
<!--        "101": "iconfont icon-shangpin",-->
<!--        "102": "iconfont icon-danju",-->
<!--        "406": "iconfont icon-baobiao",-->
<!--        "72c": "iconfont icon-user",-->
<!--      },-->
<!--      isCollapse: false,-->
<!--      menuData2: [-->
<!--        {-->
<!--          authName: "首页",-->
<!--          children: [-->
<!--            {-->
<!--              authName: "欢迎页",-->
<!--              id: '202',-->
<!--              path: "welcome"-->
<!--            }-->
<!--          ],-->
<!--          id: "201",-->
<!--          order: 1,-->
<!--          path: "welcome"-->
<!--        },-->
<!--        {-->
<!--          authName: "客户管理",-->
<!--          children: [-->
<!--            {-->
<!--              authName: "企业管理",-->
<!--              id: '110',-->
<!--              path: "companies"-->
<!--            },-->
<!--            {-->
<!--              authName: "用户管理",-->
<!--              id: '110',-->
<!--              path: "users"-->
<!--            },-->
<!--            {-->
<!--              authName: "销售管理",-->
<!--              id: '110',-->
<!--              path: "sales"-->
<!--            },-->
<!--          ],-->
<!--          id: "125",-->
<!--          order: 2,-->
<!--          path: "users"-->
<!--        },-->
<!--        {-->
<!--          authName: "系统设置",-->
<!--          children: [-->
<!--            {-->
<!--              authName: "角色列表",-->
<!--              id: '111',-->
<!--              path: "roles"-->
<!--            },-->
<!--            {-->
<!--              authName: "权限列表",-->
<!--              id: '112',-->
<!--              path: "rights"-->
<!--            },-->
<!--            {-->
<!--              authName: "菜单管理",-->
<!--              id: '111',-->
<!--              path: "menu"-->
<!--            },-->
<!--          ],-->
<!--          id: "103",-->
<!--          order: 3,-->
<!--          path: "rights"-->
<!--        },-->
<!--        {-->
<!--          authName: "产品管理",-->
<!--          children: [-->
<!--            {-->
<!--              authName: "产品类型",-->
<!--              id: '104',-->
<!--              order: 2,-->
<!--              path: "type"-->
<!--            },-->
<!--            {-->
<!--              authName: "产品属性",-->
<!--              id: '115',-->
<!--              order: 3,-->
<!--              path: "prop"-->
<!--            },-->
<!--            {-->
<!--              authName: "产品信息",-->
<!--              id: '121',-->
<!--              order: 4,-->
<!--              path: "info"-->
<!--            },-->
<!--            {-->
<!--              authName: "产品监控",-->
<!--              id: '121',-->
<!--              order: 5,-->
<!--              path: "monitor"-->
<!--            },-->
<!--            {-->
<!--              authName: "告警处理",-->
<!--              id: '121',-->
<!--              order: 6,-->
<!--              path: "warn"-->
<!--            },-->
<!--          ],-->
<!--          id: "101",-->
<!--          order: 4,-->
<!--          path: "goods"-->
<!--        },-->
<!--        {-->
<!--          authName: "数据分析",-->
<!--          children: [-->
<!--            {-->
<!--              authName: "设备异常信息",-->
<!--              id: '110',-->
<!--              path: "dataanalysis/exception"-->
<!--            },-->
<!--            {-->
<!--              authName: "设备异常分析",-->
<!--              id: '105',-->
<!--              path: "dataanalysis/exceptionanalysis"-->
<!--            },-->
<!--            {-->
<!--              authName: "设备任务信息",-->
<!--              id: '106',-->
<!--              path: "dataanalysis/task"-->
<!--            },-->
<!--            {-->
<!--              authName: "设备任务分析",-->
<!--              id: '107',-->
<!--              path: "dataanalysis/taskanalysis"-->
<!--            },-->
<!--          ],-->
<!--          id: "406",-->
<!--          order: 2,-->
<!--          path: "dataanalysis"-->
<!--        },-->
<!--        {-->
<!--          authName: "用户管理",-->
<!--          children: [-->
<!--            {-->
<!--              authName: "超级管理员管理用户",-->
<!--              id: '110',-->
<!--              path: "user/superadmin"-->
<!--            },-->
<!--            {-->
<!--              authName: "管理员管理用户",-->
<!--              id: '105',-->
<!--              path: "user/admin"-->
<!--            },-->
<!--            {-->
<!--              authName: "用户管理",-->
<!--              id: '105',-->
<!--              path: "user/user"-->
<!--            },-->
<!--          ],-->
<!--          id: "72c",-->
<!--          order: 2,-->
<!--          path: "user"-->
<!--        },-->
<!--        {-->
<!--          authName: "设备管理",-->
<!--          children: [-->
<!--            {-->
<!--              authName: "设备分布",-->
<!--              id: '104',-->
<!--              order: 2,-->
<!--              path: "equipment/bigScreen"-->
<!--            },-->
<!--            {-->
<!--              authName: "城市地图",-->
<!--              id: '105',-->
<!--              order: 3,-->
<!--              path: "equipment/cityMap"-->
<!--            }-->
<!--          ],-->
<!--          id: "102",-->
<!--          order: 5,-->
<!--          path: "equipment"-->
<!--        }-->
<!--        ]-->
<!--    }-->
<!--  },-->
<!--  created() {-->
<!--    //this.userInfo = JSON.parse(localStorage.getItem('userInfo'));-->
<!--    //this.getMenuList();-->
<!--  },-->
<!--  methods: {-->
<!--    exit() {-->
<!--      this.$confirm('此操作将退出登录, 是否继续?', '提示', {-->
<!--        confirmButtonText: '确定',-->
<!--        cancelButtonText: '取消',-->
<!--        type: 'warning'-->
<!--      }).then(() => {-->
<!--        this.$message({-->
<!--          type: 'success',-->
<!--          message: '已退出当前账号!',-->
<!--        });-->
<!--        sessionStorage.clear();-->
<!--        this.$router.push('/login');-->
<!--      }).catch(() => {-->
<!--        this.$message({-->
<!--          type: 'info',-->
<!--          message: '已取消退出'-->
<!--        });-->
<!--      });-->
<!--    },-->
<!--    async getMenuList() {-->
<!--      const {data:res} = await this.$http.get('menus');-->
<!--      if(res.meta.status != 200) {-->
<!--        return this.$message.error('请求失败');-->
<!--      }-->
<!--      this.menuData = res.data;-->
<!--      console.log(this.menuData);-->
<!--    },-->
<!--  }-->
<!--}-->
<!--</script>-->

<!--<style lang="less" scoped>-->
<!--  .el-container {-->
<!--    height: 100%;-->
<!--    min-width: 1000px;-->
<!--  }-->
<!--  .el-header {-->
<!--    background: url("../assets/imgs/header_bg.gif") repeat-x center;-->
<!--    height: 50px!important; //!important优先级最高-->
<!--    display: flex;-->
<!--    justify-content: space-between;-->
<!--    align-items: center;-->
<!--    color: #ffffff;-->

<!--    .left {-->
<!--      img {-->
<!--        width: 40px;-->
<!--        height: 40px;-->
<!--      }-->
<!--      margin-right: 700px;-->
<!--      display: flex;-->
<!--      align-items: center;-->
<!--    }-->
<!--  }-->
<!--  .el-aside {-->
<!--    .toggle-btn {-->
<!--      background: #607099;-->
<!--      line-height: 24px;-->
<!--      text-align: center;-->
<!--      color: #ffffff;-->
<!--      letter-spacing: 3px;-->
<!--      cursor: pointer; //游标变成小白手-->
<!--    }-->
<!--  }-->
<!--</style>-->

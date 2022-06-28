<template>
  <el-container>
    <el-header>
      <div class="left">
        <img src="../assets/imgs/shop.png" >
        <span>电商管理系统</span>
      </div>
      <div class="right">
        <span>欢迎您：{{userInfo.username}}</span>
        <el-button type="warning" @click="exit">退出</el-button>
      </div>
    </el-header>
    <el-container>
      <el-aside width= "150px">
        <el-menu :default-active="activeIndex" class="el-menu-demo" mode="vertical" @select="handleSelect">
          <el-menu-item index="/welcome">主页</el-menu-item>
          <el-menu-item index="/company">企业管理</el-menu-item>
          <el-menu-item index="/user">用户管理</el-menu-item>
          <el-scrollbar>
            <el-submenu index="1">
              <template slot="title">产品管理</template>
              <el-menu-item index="/product/product_info" >产品信息</el-menu-item>
              <el-menu-item index="/product/product_type">产品类型</el-menu-item>
              <el-menu-item index="/product/product_attr">产品属性</el-menu-item>
            </el-submenu>
          </el-scrollbar>
          <el-menu-item index="/salesman">销售员管理</el-menu-item>
          <el-menu-item index="/equipment">设备管理</el-menu-item>
        </el-menu>
      </el-aside>
      <el-main>
        <router-view/>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
export default {
  name: "Home.vue",
  data() {
    return {
      userInfo: null,
      activeIndex: this.$router.path,
      }
  },
  created() {
    this.userInfo = JSON.parse(sessionStorage.getItem('userInfo'));
    this.getMenuList();
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
    handleSelect(key, keyPath) {
      console.log(key, keyPath);
      this.$router.push(key);
    },
    async getMenuList() {
      const {data:res} = await this.$http.get('menus');
      if(res.meta.status != 200) {
        return this.$message.error('请求失败');
      }
      this.menuData = res.data;
      //console.log(this.menuData);
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
    .el-scrollbar {
      overflow-x: hidden;
    }
  }
</style>

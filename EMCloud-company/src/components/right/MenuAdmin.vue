<template>
  <div>
    <el-breadcrumb separator-class="el-icon-arrow-right">
<!--      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>-->
      <el-breadcrumb-item>系统设置</el-breadcrumb-item>
      <el-breadcrumb-item>菜单管理</el-breadcrumb-item>
    </el-breadcrumb>
    <el-card class="box-card">

      <el-table
        ref="Table"
        :data="tableData"
        row-key="menu_id"
        border stripe
        :tree-props="{children: 'children', hasChildren: 'hasChildren' }">
        <el-table-column prop="menu_id" label="id"></el-table-column>
        <el-table-column prop="menu_name" label="名称"></el-table-column>
        <el-table-column prop="menu_link" label="链接"></el-table-column>
        <el-table-column prop="menu_visible_user" label="用户是否可见"></el-table-column>
      </el-table>



    </el-card>

  </div>
</template>

<script>
import {
getUserMenuToAdmin,
} from "../../api";

export default {
  name: "menuadmin",
  data(){
    return {
      tableData: [],

    }
  },
  created () {
    this.getMenu()
  },
  methods: {


    getMenu() {
      this.tableData = [];
      getUserMenuToAdmin()
        .then(res => {
          console.log(res)
          this.tableData = res;
          for (let item of this.tableData) {
            if(item.menu_visible_user==1){
              item.menu_visible_user='显示'
            }else{
              item.menu_visible_user='不显示'
            }
            if(item.children!=null){
              this.getChildren(item.children)
            }

          }

        })
        .catch(err => {
          console.log(err)
        })
    },

    getChildren(data){
      for (let item of data) {
        if(item.menu_visible_user==1){
          item.menu_visible_user='显示'
        }else{
          item.menu_visible_user='不显示'
        }
        if(item.children!=null){
          this.getChildren(item.children)
        }

      }
    },

  }


}
</script>

<style scoped>
.box-card{
  margin-top: 30px;
}

</style>

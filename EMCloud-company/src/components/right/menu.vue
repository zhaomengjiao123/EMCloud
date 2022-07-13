<template>
  <div>
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>系统设置</el-breadcrumb-item>
      <el-breadcrumb-item>菜单管理</el-breadcrumb-item>
    </el-breadcrumb>
    <el-card class="box-card">

      <el-table
        ref="Table"
        :data="tableData"
        row-key="menu_id"
        border stripe
        default-expand-all
        :tree-props="{children: 'children', hasChildren: 'hasChildren' }">
        <el-table-column prop="menu_id" label="id"></el-table-column>
        <el-table-column prop="menu_name" label="名称"></el-table-column>
        <el-table-column prop="menu_link" label="链接"></el-table-column>
        <el-table-column prop="menu_visible_superadmin" label="超级管理员是否可见"></el-table-column>
        <el-table-column prop="menu_visible_admin" label="管理员是否可见">
            <template slot-scope="scope">
              <el-switch
                v-model="scope.row.menu_visible_admin"
                :active-value="1"
                :inactive-value="0"
                @change="switchChangeAdmin(scope.row)"
                active-color="#13ce66"
                inactive-color="#ff4949"
              >
              </el-switch>
            </template>
        </el-table-column>
        <el-table-column prop="menu_visible_user" label="用户是否可见">
          <template slot-scope="scope">
            <el-switch
              v-model="scope.row.menu_visible_user"
              :active-value="1"
              :inactive-value="0"
              @change="switchChangeUser(scope.row)"
              active-color="#13ce66"
              inactive-color="#ff4949"
            >
            </el-switch>
          </template>
        </el-table-column>
      </el-table>



    </el-card>

  </div>
</template>

<script>
import {
  addAdmin,
  addUser,
  deleteUser, getAllMenu, getCompany, getDepart, getDepartByCompany, getProType,
  getUser,
  getUserByCompany,
  getUserByPhone, getUserByPhone2, updateMenuAdminInvis, updateMenuAdminVis, updateMenuUserInvis, updateMenuUserVis,
  updatePasswd,
  updateUser, updateUserSuper
} from "../../api";

export default {
  name: "menu",
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
      getAllMenu()
        .then(res => {
          console.log(res)
          this.tableData = res;
          for (let item of this.tableData) {
           if(item.menu_visible_superadmin==1){
             item.menu_visible_superadmin='显示'
           }else{
             item.menu_visible_superadmin='不显示'
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
        if(item.menu_visible_superadmin==1){
          item.menu_visible_superadmin='显示'
        }else{
          item.menu_visible_superadmin='不显示'
        }
        if(item.children!=null){
          this.getChildren(item.children)
        }

      }
    },

    switchChangeAdmin(row){
      let params = new URLSearchParams()
      params.append('menu_id', row.menu_id)
      if(row.menu_visible_admin==1){
        updateMenuAdminVis(params)
          .then(res => {
            if (res.code == 1) {
              this.$message({
                message: '修改成功',
                type: 'success'
              })
              this.getMenu()
            } else {
              this.$message({
                message: '修改失败',
                type: 'error'
              })
            }
          })
          .catch(err => {
            console.log(err)
          })
      }else if(row.menu_visible_admin==0){
        updateMenuAdminInvis(params)
          .then(res => {
            if (res.code == 1) {
              this.$message({
                message: '修改成功',
                type: 'success'
              })
              this.getMenu()
            } else {
              this.$message({
                message: '修改失败',
                type: 'error'
              })
            }
          })
          .catch(err => {
            console.log(err)
          })
      }

    },

    switchChangeUser(row){
      let params = new URLSearchParams()
      params.append('menu_id', row.menu_id)
      if(row.menu_visible_user==1){
        updateMenuUserVis (params)
          .then(res => {
            if (res.code == 1) {
              this.$message({
                message: '修改成功',
                type: 'success'
              })
              this.getMenu()
            } else {
              this.$message({
                message: '修改失败',
                type: 'error'
              })
            }
          })
          .catch(err => {
            console.log(err)
          })
      }else{
        updateMenuUserInvis(params)
          .then(res => {
            if (res.code == 1) {
              this.$message({
                message: '修改成功',
                type: 'success'
              })
              this.getMenu()
            } else {
              this.$message({
                message: '修改失败',
                type: 'error'
              })
            }
          })
          .catch(err => {
            console.log(err)
          })
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

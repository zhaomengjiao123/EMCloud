<template>
  <div>
    <el-card >
      <!--面包屑导航区-->
      <el-breadcrumb separator-class="el-icon-arrow-right">
        <el-breadcrumb-item>产品管理</el-breadcrumb-item>
        <el-breadcrumb-item>产品信息</el-breadcrumb-item>
      </el-breadcrumb>
      <!--卡片视图区域-->
      <br>
      <el-row style="margin-left: 40px;margin-top: 20px">
        <el-col :span="4">
          <el-button size="small" type="primary" @click="add1">添加产品</el-button>
        </el-col>
        <el-col :span="8">
          <el-input size="small" placeholder="请输入内容" v-model="queryInfo.query" clearable @clear="getGoodsList">
            <el-button slot="append" icon="el-icon-search" @click="getGoodsList"></el-button>
          </el-input>
        </el-col>
      </el-row>

      <!--    添加产品  -->
      <el-dialog title="添加产品信息" width="65%" :visible.sync="dialogFormVisible" center >
<!--        <el-row>-->
<!--          <el-col :span="12">-->
<!--            <div class="grid-content">-->
              <el-form :model="questionForm"  ref="questionForm" :rules="rules" label-position="right" label-width="150px" style="width: 400px; margin-left:10px;">
                <el-form-item label="产品名称" prop="name">
                  <el-input v-model="questionForm.product_name" placeholder="必填"></el-input>
                </el-form-item>
                <el-form-item label="产品编号" prop="number" >
                  <el-input v-model="questionForm.product_number" placeholder="必填"></el-input>
                </el-form-item>
                <el-form-item label="产品备注" prop="comment">
                  <el-input v-model="questionForm.product_comment" ></el-input>
                </el-form-item>
                <el-form-item label="产品型号" prop="version">
                  <el-input v-model="questionForm.product_version" placeholder="必填"></el-input>
                </el-form-item>
                <el-form-item label="产品所属类型ID" prop="type_id">
                  <el-input v-model="questionForm.product_type_id" placeholder="必填"></el-input>
                </el-form-item>
                <el-form-item label="产品资料地址" prop="file_url">
                  <el-input v-model="questionForm.product_file_url" ></el-input>
                </el-form-item>
                <el-form-item label="产品发布日期" prop="date" >
                  <el-input v-model="questionForm.product_release_date" placeholder="必填"></el-input>
                </el-form-item>

                <el-form-item label="上传图片"  prop="imageUrl" >
                  <el-upload
                    class="avatar-uploader"
                    action="http://localhost:8080/file/upload/img"
                    :show-file-list="false"
                    :on-success="handleAvatarSuccess"
                    :before-upload="beforeAvatarUpload">
                    <img  width="200px" height="150px" v-if="questionForm.product_pic_url" :src="questionForm.product_pic_url" class="avatar">
                    <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                  </el-upload>
                </el-form-item>
              </el-form>
        <div slot="footer" ref="questionForm" class="dialog-footer">
          <el-button @click="update">取消</el-button>
          <el-button type="primary" @click=" addProduct()" >确定</el-button>
        </div>
      </el-dialog>

      <!-- table表格区域 -->
      <el-table :data="goodsList.slice((queryInfo.pagenum - 1) * queryInfo.pagesize, queryInfo.pagenum * queryInfo.pagesize)"  height="450px" border stripe>
        <el-table-column type="index" label="#" ></el-table-column>
        <el-table-column  label="产品名称" prop="product_name" width="200px"></el-table-column>
        <el-table-column  label="产品型号" prop="product_version" width="75px"></el-table-column>
        <el-table-column  label="产品编号" prop="product_number" width="100px"></el-table-column>
        <el-table-column  label="类型ID" prop="product_type_id" width="75px"></el-table-column>
<!--        <el-table-column  label="产品图片地址" prop="product_pic_url" width="130px"></el-table-column >-->
<!--        <el-table-column  label="产品资料地址" prop="product_file_url" width="130px"></el-table-column>-->
        <el-table-column  label="产品备注" prop="product_comment" width="80px"></el-table-column>
        <el-table-column  label="产品编号" prop="product_number" width="75px"></el-table-column>
<!--        <el-table-column  label="产品所属类型ID" prop="product_type_id" width="115px"></el-table-column>-->
        <el-table-column  label="产品图片地址" prop="product_pic_url" width="130px">
          <template slot-scope="scope1">
            <img width="80px" height="100px" v-if="scope1.row.product_pic_url" :src="scope1.row.product_pic_url" >
          </template>
        </el-table-column >

        <el-table-column  label="产品发布日期" prop="product_release_date" width="150px"></el-table-column>
        <el-table-column  label="产品更新时间" prop="product_update_time" width="150px"></el-table-column>
        <el-table-column  label="操作" width="150px">
          <template slot-scope="scope">
            <el-button type="primary"  size="mini" @click="handleEdit(scope.row)">编辑</el-button>
            <el-button type="danger"  size="mini" @click="remove(scope.row)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>

      <!--      编辑产品信息-->
      <el-dialog title="更新产品信息" width="65%" :visible.sync="editFormVisible" center >
        <el-form :model="questionForm"  ref="questionForm" :rules="rules" label-position="right" label-width="150px" style="width: 400px; margin-left:10px;">
          <el-form-item label="产品名称" prop="name">
            <el-input v-model="questionForm.product_name" placeholder="必填"></el-input>
          </el-form-item>
          <el-form-item label="产品编号" prop="number" >
            <el-input v-model="questionForm.product_number" placeholder="必填"></el-input>
          </el-form-item>
          <el-form-item label="产品备注" prop="comment">
            <el-input v-model="questionForm.product_comment" ></el-input>
          </el-form-item>
          <el-form-item label="产品型号" prop="version">
            <el-input v-model="questionForm.product_version" placeholder="必填"></el-input>
          </el-form-item>
          <el-form-item label="产品所属类型ID" prop="type_id">
            <el-input v-model="questionForm.product_type_id" placeholder="必填"></el-input>
          </el-form-item>
<!--          <el-form-item label="产品资料地址" prop="file_url">-->
<!--            <el-input v-model="questionForm.product_file_url" ></el-input>-->
<!--          </el-form-item>-->
          <el-form-item label="产品发布日期" prop="date" >
            <el-input v-model="questionForm.product_release_date" placeholder="必填"></el-input>
          </el-form-item>
          <el-form-item label="上传图片"  prop="imageUrl" >
            <el-upload
              class="avatar-uploader"
              action="http://121.5.74.11:8080/file/upload/img"
              :on-success="handleAvatarSuccess"
              :before-upload="beforeAvatarUpload">
              <img width="200px" height="150px" v-if="questionForm.product_pic_url" :src="questionForm.product_pic_url" class="avatar">
              <i v-else class="el-icon-plus avatar-uploader-icon"></i>
            </el-upload>

          </el-form-item>
        </el-form>
        <span slot="footer" class="dialog-footer">
          <el-button @click="update">取消</el-button>
          <el-button type="primary" @click="onEdit">确定</el-button>
        </span>
      </el-dialog>

      <!-- 分页区域 -->
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="queryInfo.pagenum"
        :page-sizes="[5, 8, 10,15]"
        :page-size="queryInfo.pagesize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total" background>
      </el-pagination>
    </el-card>
  </div>
</template>


<script>
import {deleteProduct, deleteUser} from "../../api";

export default {
  data () {
    return {
      //查询参数对象
      queryInfo:{
        query: '',
        pagenum: 1,
        pagesize: 8,
      },
      // 商品列表
      goodsList: [],
       imageUrl:'',
      // 总数据条数
      total: 0,
      questionForm: {
        product_id:'',
        product_name: '',
        product_version: '',
        product_release_date: '',
        product_update_time: '',
        product_number:'',
        product_file_url: '',
        product_comment: '',
        product_type_id: '',
        product_pic_url: '',
      },
      dialogFormVisible: false,
      editFormVisible: false,
      rules:{
        name: [
          { required: true, message: ' ', trigger: 'blur' },
        ],
        version: [
          { required: true, message: ' ', trigger: 'blur' },
        ],
        date: [
          { required: true, message: ' ', trigger: 'blur' },
        ],
        number: [
          { required: true, message: ' ', trigger: 'blur' },
        ],
        type_id: [
          { required: true, message: ' ', trigger: 'blur' },
        ],
      },
    }
  },

  created(){
    this.getGoodsList();
  },
  methods: {
    //列表清空
    update(){
      this.questionForm={};
      this.dialogFormVisible=false;
      this.editFormVisible=false;
    },

    //图片回显
    handleAvatarSuccess(res, file) {
      console.log(res)
      this.questionForm.product_pic_url = res
      sessionStorage.setItem("imgUrl",this.questionForm.product_file_url)
    },
    //图片上传大小限制
    beforeAvatarUpload(file) {
      const isLt2M = file.size / 1024 / 1024 < 10;
      if (!isLt2M) {
        this.$message.error('上传图片大小不能超过 10MB!');
      }
      return isLt2M;
    },


    getCurrentTime() {
      //获取当前时间并打印
      let yy = new Date().getFullYear();
      let mm = new Date().getMonth()+1;
      let dd = new Date().getDate();
      let date= yy + '-' + (mm >= 10 ? mm : '0' + mm) + '-' + (dd >= 10 ? dd : '0' + dd);
      let hh = new Date().getHours();
      let mf = new Date().getMinutes()<10 ? '0'+new Date().getMinutes() : new Date().getMinutes();
      let ss = new Date().getSeconds()<10 ? '0'+new Date().getSeconds() : new Date().getSeconds();
      let datetime = date+' '+hh+':'+mf+':'+ss;
      return datetime;
    },
    // 根据分页获取对应的商品列表
    async getGoodsList(){
      this.$http.get("product/getAllProduct").then(res=>{
        console.log(res.data)
        this.goodsList=res.data;
        this.total=res.data.length;
      });
    },
    add1(){
      this.dialogFormVisible = true;
    },
    //添加商品
    addProduct(){
      this.questionForm.product_update_time=this.getCurrentTime();
      this.$http.post("product/addProduct",this.questionForm).then(res=>{
        if(res.data.code){
          this.$message({
            message: '恭喜你，'+res.data.msg,
            type: 'success'
          });
          this.getGoodsList();
          //清空表单信息
          this.questionForm={};
          //隐藏表单
          this.dialogFormVisible = false;
        }
        else{
          this.$message.error('添加失败');
        }
      });
    },
    // 更新产品类型信息
    handleEdit(row){
      this.editFormVisible=true;
      this.questionForm=row;
    },
    onEdit(){
      this.questionForm.product_update_time=this.getCurrentTime();
      this.$http.post("product/updateProduct",this.questionForm).then(res=>{
        if(res.data.code){
          this.$message({
            message: '恭喜你，'+res.data.msg,
            type: 'success'
          });
          this.getGoodsList();
          //清空表单信息
          this.questionForm={};
          //隐藏表单
          this.editFormVisible = false;
        }else{
          this.$message.error('更新失败');
        }
      });
    },
    async remove(row){
      const confirmResult = await this.$confirm('此操作将永久删除该产品属性，是否继续？','提示',{
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).catch(err => err)
      // 用户取消了删除操作
      if(confirmResult !== 'confirm') return this.$message.info('已取消删除！')
      // 删除的业务逻辑
      console.log(row.product_id);

          let params = new URLSearchParams()
          params.append('product_id', row.product_id)
          deleteProduct(params)
            .then(res => {
              if (res.code == 1) {
                this.$message({
                  message: '删除成功',
                  type: 'success'
                })
                this.getGoodsList()
              } else {
                this.$message({
                  message: '删除失败',
                  type: 'error'
                })
              }
            })

    },
    // 监听当前页数变化的事件
    handleSizeChange(newSize){
      this.queryInfo.pagesize = newSize;
      this.queryInfo.pagenum = 1;
      this.getGoodsList()
    },
    // 监听当前页码变化的事件
    handleCurrentChange(newPage){
      this.queryInfo.pagenum = newPage
      this.getGoodsList()
    },
    // 根据id删除对应的商品
  },
}
</script>


<style scoped>


</style>


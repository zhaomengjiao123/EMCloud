<!--  -->
<template>
  <div>
    <el-card >
      <!--面包屑导航区-->
      <el-breadcrumb separator-class="el-icon-arrow-right">
        <el-breadcrumb-item :to="{ path: '/welcome' }">客户管理</el-breadcrumb-item>
        <el-breadcrumb-item>销售管理</el-breadcrumb-item>
      </el-breadcrumb>
      <!--卡片视图区域-->
      <br>

      <el-row :gutter="20">
        <el-col :span="8">
          <el-input placeholder="请输入内容" v-model="queryInfo.query" clearable @clear="getGoodsList">
            <el-button slot="append" icon="el-icon-search" @click="getGoodsList"></el-button>
          </el-input>
        </el-col>
        <el-col :span="4">
          <el-button type="primary" @click="dialogFormVisible = true">添加销售员</el-button>
        </el-col>
      </el-row>

      <!--    添加产品  -->
      <el-dialog title="添加销售员" width="36%" :visible.sync="dialogFormVisible" center >
        <el-form :model="questionForm" ref="questionForm" :rules="rules" label-position="right" label-width="110px" style="width: 400px; margin-left:50px;">
          <el-form-item label="销售员id" prop="number">
            <el-input v-model="questionForm.salesman_id" placeholder="必填"></el-input>
          </el-form-item>
          <el-form-item label="销售员姓名" prop="name">
            <el-input v-model="questionForm.salesman_name" placeholder="必填"/>
          </el-form-item>
        </el-form>
        <div slot="footer" ref="questionForm" class="dialog-footer">
          <el-button @click="update">取消</el-button>
          <el-button type="primary" @click=" addProduct()">确定</el-button>
        </div>
      </el-dialog>

      <!-- table表格区域 -->
      <el-table :data="goodsList.slice((queryInfo.pagenum - 1) * queryInfo.pagesize, queryInfo.pagenum * queryInfo.pagesize)"  height="500px" border stripe>
        <el-table-column type="index" label="#" ></el-table-column>
        <el-table-column  label="销售员id" prop="salesman_id" ></el-table-column>
        <el-table-column  label="销售员姓名" prop="salesman_name" ></el-table-column>
        <el-table-column  label="操作" width="150px">
          <template slot-scope="scope">
            <el-button type="primary"  size="mini" @click="handleEdit(scope.row)">编辑</el-button>
            <el-button type="danger"  size="mini" @click="remove(scope.row)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>

      <!--      编辑产品信息-->
      <el-dialog title="更新销售员信息" width="36%" :visible.sync="editFormVisible" center >
        <el-form :model="questionForm" ref="dataForm" :rules="rules" label-position="right" label-width="110px" style="width: 400px; margin-left:50px;">
          <el-form-item label="销售员id" prop="number">
            <el-input v-model="questionForm.salesman_id" placeholder="必填"></el-input>
          </el-form-item>
          <el-form-item label="销售员姓名" prop="name">
            <el-input v-model="questionForm.salesman_name" placeholder="必填"/>
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
      // 总数据条数
      total: 0,
      questionForm: {
        salesman_id: "",
        salesman_name: "",
      },
      dialogFormVisible: false,
      editFormVisible: false,
      rules:{
        number: [
          { required: true, message: '请输入销售员id', trigger: 'blur' },
        ],
        name: [
          { required: true, message: '请输入销售员姓名', trigger: 'blur' },
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
    // 根据分页获取对应的商品列表
    async getGoodsList(){
      this.$http.get("http://121.5.74.11:8080/salesman/getAll").then(res=>{
        this.goodsList=res.data;
        this.total=res.data.length;
      });
    },
    //添加商品
    addProduct(){
      this.$http.post("http://121.5.74.11:8080/salesman/addSalesman",this.questionForm).then(res=>{
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
      this.$http.post("http://121.5.74.11:8080/productType/updateProductType",this.questionForm).then(res=>{
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
    remove(row){

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


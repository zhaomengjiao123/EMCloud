<!--  -->
<template>
  <div>
    <el-card >
      <!--面包屑导航区-->
      <el-breadcrumb separator-class="el-icon-arrow-right">
        <el-breadcrumb-item :to="{ path: '/welcome' }">产品管理</el-breadcrumb-item>
        <el-breadcrumb-item>产品属性</el-breadcrumb-item>
      </el-breadcrumb>
      <!--卡片视图区域-->
      <br>

      <el-row :gutter="20">
        <el-col :span="8">
          <el-input placeholder="请输入内容" v-model="queryn" clearable @clear="getGoodsList">
            <el-button slot="append" icon="el-icon-search" @click="chaxunn(queryn)"></el-button>
          </el-input>
        </el-col>
        <el-col :span="4">
          <el-button type="primary" @click="add1">添加属性</el-button>
        </el-col>


      </el-row>

      <!--    添加产品  -->
      <el-dialog title="添加产品类型的属性" width="65%" :visible.sync="dialogFormVisible" center >
        <el-row>
          <el-col :span="12">
            <div class="grid-content">
              <el-form :model="questionForm"  ref="questionForm" :rules="rules" label-position="right" label-width="150px" style="width: 400px; margin-left:10px;">
                <el-form-item label="产品属性编号" prop="number">
                  <el-input v-model="questionForm.product_attribute_number" placeholder="必填"></el-input>
                </el-form-item>
                <el-form-item label="产品属性值" prop="value">
                  <el-input v-model="questionForm.product_attribute_value" ></el-input>
                </el-form-item>
                <el-form-item label="产品预警值" prop="warning">
                  <el-input v-model="questionForm.product_attribute_warning" ></el-input>
                </el-form-item>
                <el-form-item label="产品属性是否监控" prop="monitor">
                  <el-input v-model="questionForm.product_attribute_monitor" ></el-input>
                </el-form-item>
                <el-form-item label="产品备注" prop="comment">
                  <el-input v-model="questionForm.product_attribute_comment"></el-input>
                </el-form-item>
              </el-form>
            </div>
          </el-col>
          <el-col :span="12">
            <div class="grid-content">
              <el-form :model="questionForm"  ref="questionForm" :rules="rules" label-position="right" label-width="150px" style="width: 400px; margin-left:10px;">
                <el-form-item label="产品属性名称" prop="name">
                  <el-input v-model="questionForm.product_attribute_name" placeholder="必填"></el-input>
                </el-form-item>
                <el-form-item label="产品维修值" prop="repair">
                  <el-input v-model="questionForm.product_attribute_repair"></el-input>
                </el-form-item>
                <el-form-item label="产品报警值" prop="error">
                  <el-input v-model="questionForm.product_attribute_erro" ></el-input>
                </el-form-item>
                <el-form-item label="对应产品类型ID" prop="type_id" >
                  <el-select v-model="questionForm.product_type_id" placeholder="必填" style="width: 250px">
                    <el-option v-for="item in product_id" :key="item.product_type_name"  :value="item.product_type_id"></el-option>
                  </el-select>
                </el-form-item>
                <el-form-item label="更新时间" prop="time">
                  <el-input v-model="questionForm.product_attribute_update_time" :disabled="true"></el-input>
                </el-form-item>
              </el-form>
            </div>
          </el-col>
        </el-row>
        <div slot="footer" ref="questionForm" class="dialog-footer">
          <el-button @click="update">取消</el-button>
          <el-button type="primary" @click=" addProduct()" >确定</el-button>
        </div>
      </el-dialog>

      <!-- table表格区域 -->
      <el-table :data="newgoodsList.slice((queryInfo.pagenum - 1) * queryInfo.pagesize, queryInfo.pagenum * queryInfo.pagesize)"  height="500px" border stripe>
        <el-table-column type="index" label="#" ></el-table-column>
        <el-table-column  label="产品属性编号" prop="product_attribute_number" width="100px"></el-table-column>
        <el-table-column  label="产品属性名称" prop="product_attribute_name" width="100px"></el-table-column>
        <el-table-column  label="产品属性值" prop="product_attribute_value" width="90px"></el-table-column>
        <el-table-column  label="产品预警值" prop="product_attribute_warning" width="90px"></el-table-column>
        <el-table-column  label="产品报警值" prop="product_attribute_erro" width="90px"></el-table-column>
        <el-table-column  label="产品维修值" prop="product_attribute_repair" width="90px"></el-table-column>
        <el-table-column  label="是否监控" prop="product_attribute_monitor" width="75px"></el-table-column>
        <el-table-column  label="对应产品类型ID" prop="product_type_id" width="115px"></el-table-column>
        <el-table-column  label="备注" prop="product_attribute_comment"></el-table-column>
        <el-table-column  label="更新时间" prop="product_attribute_update_time" width="150px"></el-table-column >
        <el-table-column  label="操作" width="150px">
          <template slot-scope="scope">
            <el-button type="primary"  size="mini" @click="handleEdit(scope.row)">编辑</el-button>
            <el-button type="danger"  size="mini" @click="remove(scope.row)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>

      <!--      编辑产品信息-->
      <el-dialog title="更新产品类型的属性" width="65%" :visible.sync="editFormVisible" center >
        <el-row>
          <el-col :span="12">
            <div class="grid-content">
              <el-form :model="questionForm"  ref="questionForm" :rules="rules" label-position="right" label-width="150px" style="width: 400px; margin-left:10px;">
                <el-form-item label="产品属性编号" prop="number">
                  <el-input v-model="questionForm.product_attribute_number" placeholder="必填"></el-input>
                </el-form-item>
                <el-form-item label="产品属性值" prop="value">
                  <el-input v-model="questionForm.product_attribute_value" :disabled="true"></el-input>
                </el-form-item>
                <el-form-item label="产品预警值" prop="warning">
                  <el-input v-model="questionForm.product_attribute_warning" :disabled="true"></el-input>
                </el-form-item>
                <el-form-item label="产品属性是否监控" prop="monitor">
                  <el-input v-model="questionForm.product_attribute_monitor" :disabled="true"></el-input>
                </el-form-item>
                <el-form-item label="产品备注" prop="comment">
                  <el-input v-model="questionForm.product_attribute_comment"></el-input>
                </el-form-item>
              </el-form>
            </div>
          </el-col>
          <el-col :span="12">
            <div class="grid-content">
              <el-form :model="questionForm"  ref="questionForm" :rules="rules" label-position="right" label-width="150px" style="width: 400px; margin-left:10px;">
                <el-form-item label="产品属性名称" prop="name">
                  <el-input v-model="questionForm.product_attribute_name" placeholder="必填"></el-input>
                </el-form-item>
                <el-form-item label="产品维修值" prop="repair">
                  <el-input v-model="questionForm.product_attribute_repair" :disabled="true"></el-input>
                </el-form-item>
                <el-form-item label="产品报警值" prop="error">
                  <el-input v-model="questionForm.product_attribute_erro" :disabled="true"></el-input>
                </el-form-item>
                <el-form-item label="对应产品类型ID" prop="type_id" >
                  <el-input v-model="questionForm.product_type_id" :disabled="true"></el-input>
                </el-form-item>
                <el-form-item label="更新时间" prop="time">
                  <el-input v-model="questionForm.product_attribute_update_time" :disabled="true"></el-input>
                </el-form-item>
              </el-form>
            </div>
          </el-col>
        </el-row>
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
      queryn:'',
      product_id: [],
      newgoodsList:[],
      // 商品列表
      goodsList: [],
      // 总数据条数
      total: 0,
      questionForm: {
        product_attribute_id:'',
        product_attribute_number:'',
        product_attribute_name:'',
        product_attribute_value:'',
        product_attribute_update_time:'',
        product_attribute_repair:'',
        product_attribute_warning:'',
        product_attribute_erro:'',
        product_attribute_monitor:'',
        product_type_id:'',
        product_attribute_comment:'',
      },
      dialogFormVisible: false,
      editFormVisible: false,
      rules:{
        number: [
          { required: true, message: ' ', trigger: 'blur' },
        ],
        name: [
          { required: true, message: ' ', trigger: 'blur' },
        ],
        time: [
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
    searchid(keywords) {
      return this.goodsList.filter(item =>{
        if(item.product_attribute_number.includes(keywords)){
          return item
        }
        else if(item.product_attribute_name.includes(keywords)){
          return item
        }
      })
    },
    chaxunn(queryn){
      let val=this.queryn;
      if (val == '') {
        this.newgoodsList=this.goodsList = this.tableData
      } else {
        this.newgoodsList =this.searchid(val)
      }
    },
    //列表清空
    update(){
      this.questionForm={};
      this.dialogFormVisible=false;
      this.editFormVisible=false;
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
      this.$http.get("http://121.5.74.11:8080/productTypeAttribute/getAllProductTypeAttribute").then(res=>{
        this.newgoodsList=this.goodsList=res.data;
        this.total=res.data.length;
      });

      this.$http.get("http://121.5.74.11:8080/productType/getAllProductType").then(res => {
        this.product_id = res.data;
      });
    },
    add1(){
      this.dialogFormVisible = true;
      this.questionForm.product_attribute_update_time=this.getCurrentTime();
    },
    //添加商品
    addProduct(){
      this.questionForm.product_attribute_update_time=this.getCurrentTime();
      this.$http.post("http://121.5.74.11:8080/productTypeAttribute/addProductTypeAttribute",this.questionForm).then(res=>{
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
      this.questionForm.product_attribute_update_time=this.getCurrentTime();
      this.$http.post("http://121.5.74.11:8080/productTypeAttribute/updateProductTypeAttribute",this.questionForm).then(res=>{
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
      console.log(row.product_attribute_id);
      this.$http.get("http://121.5.74.11:8080/productTypeAttribute/deleteProductTypeAttribute/detail",{params:{"product_attribute_id":row.product_attribute_id}}).then(res=>{
        if(res.data.code){
          this.$message({
            message: '恭喜你，'+res.data.msg,
            type: 'success'
          });
          this.getGoodsList();
          //隐藏表单
          this.editFormVisible = false;
        }else{
          this.$message.error('删除失败');
        }
      });
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


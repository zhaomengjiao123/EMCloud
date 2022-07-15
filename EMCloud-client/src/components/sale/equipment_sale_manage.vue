<!--  -->
<template>
  <div>
    <el-card >
    <!--面包屑导航区-->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item>销售管理</el-breadcrumb-item>
      <el-breadcrumb-item>设备销售管理</el-breadcrumb-item>
    </el-breadcrumb>
    <!--卡片视图区域-->
    <br>

      <el-row style="margin-left: 40px;margin-top: 20px">
        <el-col :span="4">
          <el-button size="small" type="primary" @click="add1">添加设备</el-button>
        </el-col>
        <el-col :span="8">
          <el-input size="small" placeholder="请输入内容" v-model="query" clearable @clear="getGoodsList">
            <el-button slot="append"  icon="el-icon-search" @click="chaxun(query)"></el-button>
          </el-input>
        </el-col>
      </el-row>

      <!--    添加产品  -->
      <el-dialog title="添加设备" width="36%" :visible.sync="dialogFormVisible" center >
        <el-form :model="questionForm" ref="questionForm"  :rules="rules" label-position="right" label-width="110px" style="width: 400px; margin-left:50px;">
          <el-form-item label="产品" prop="product_id">
            <el-select v-model="questionForm.product_id" placeholder="请选择" style="width: 240px; ">
              <el-option v-for="item in product" :key="item.product_id" :label="item.product_name" :value="item.product_id"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="客户" prop="company_id">
            <el-select v-model="questionForm.company_id" placeholder="请选择" style="width: 240px; ">
              <el-option v-for="item in company" :key="item.company_id" :label="item.company_name" :value="item.company_id">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="设备编码" prop="equipment_number">
            <el-input v-model="questionForm.equipment_number" style="width: 240px; " placeholder="必填"/>
          </el-form-item>
          <el-form-item label="销售人员" prop="salesman_id">
            <el-select v-model="questionForm.salesman_id" placeholder="请选择" style="width: 240px; ">
              <el-option v-for="item in salesman" :key="item.salesman_id" :label="item.salesman_name" :value="item.salesman_id">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="销售时间" required>
            <div class="block">
              <span class="demonstration"></span>
              <el-form-item prop="sell_time">
                <el-date-picker v-model="questionForm.sell_time" type="date" value-format="yyyy-MM-dd" style="width: 240px; " placeholder="选择日期">
                </el-date-picker>
              </el-form-item>
            </div>
          </el-form-item>
          <el-form-item label="是否在线" prop="equipment_state">
            <el-radio-group v-model="questionForm.equipment_state" style="width: 240px; ">
              <el-radio label="1">在线</el-radio>
              <el-radio label="0">宕机</el-radio>
            </el-radio-group>
          </el-form-item>
          <el-form-item label="备注信息">
            <el-input v-model="questionForm.equipment_comment" style="width: 240px; " />
          </el-form-item>
        </el-form>
        <div slot="footer" ref="questionForm" class="dialog-footer">
          <el-button @click="update">取消</el-button>
          <el-button type="primary" @click=" addProduct()">确定</el-button>
        </div>
      </el-dialog>

      <!-- table表格区域 -->
      <el-table :data="newgoodsList.slice((queryInfo.pagenum - 1) * queryInfo.pagesize, queryInfo.pagenum * queryInfo.pagesize)"  height="450px" border stripe>
        <el-table-column type="index" label="#" ></el-table-column>
        <el-table-column  label="产品" prop="product_name" width="200px"></el-table-column>
        <el-table-column  label="客户" prop="company_name" width="150px"></el-table-column>
        <el-table-column  label="设备编码" prop="equipment_number" width="120px"></el-table-column>
        <el-table-column  label="销售人" prop="salesman_name" width="120px"></el-table-column>
        <el-table-column  label="销售时间" prop="sell_time" width="120px"></el-table-column >
        <el-table-column  label="是否在线" prop="equipment_state" width="120px"></el-table-column>
        <el-table-column  label="备注" prop="equipment_comment" ></el-table-column>
        <el-table-column  label="操作" width="150px">
          <template slot-scope="scope">
            <el-button type="primary"  size="mini" @click="handleEdit(scope.row)">编辑</el-button>
            <el-button type="danger"  size="mini" @click="remove(scope.row)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>

      <!--      编辑产品信息-->
      <el-dialog title="修改设备" width="36%" :visible.sync="editFormVisible" center >
        <el-form :model="questionForm" ref="questionForm"  :rules="rules" label-position="right" label-width="110px" style="width: 400px; margin-left:50px;">
          <el-form-item label="产品" prop="product_id">
            <el-select v-model="questionForm.product_id" placeholder="请选择" style="width: 240px; ">
              <el-option v-for="item in product" :key="item.product_id" :label="item.product_name" :value="item.product_id">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="客户" prop="company_id">
            <el-select v-model="questionForm.company_id" placeholder="请选择" style="width: 240px; ">
              <el-option v-for="item in company" :key="item.company_id" :label="item.company_name" :value="item.company_id">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="设备编码" prop="equipment_number">
            <el-input v-model="questionForm.equipment_number" style="width: 240px; " placeholder="必填"/>
          </el-form-item>
          <el-form-item label="销售人员" prop="salesman_id">
            <el-select v-model="questionForm.salesman_id" placeholder="请选择" style="width: 240px; ">
              <el-option v-for="item in salesman" :key="item.salesman_id" :label="item.salesman_name" :value="item.salesman_id">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="销售时间" required>
            <div class="block">
              <span class="demonstration"></span>
              <el-form-item prop="sell_time">
                <el-date-picker v-model="questionForm.sell_time" type="date" value-format="yyyy-MM-dd" style="width: 240px; " placeholder="选择日期">
                </el-date-picker>
              </el-form-item>
            </div>
          </el-form-item>
          <el-form-item label="是否在线" prop="equipment_state">
            <el-radio-group v-model="questionForm.equipment_state" style="width: 240px; ">
              <el-radio label="1">在线</el-radio>
              <el-radio label="0">宕机</el-radio>
            </el-radio-group>
          </el-form-item>
          <el-form-item label="备注信息">
            <el-input v-model="questionForm.equipment_comment" style="width: 240px; " />
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
        pagenum: 1,
        pagesize: 5,
      },
      query:'',
      // 商品列表
      newgoodsList:[],
      goodsList: [],
      tableData: [],
      product: [],
      company: [],
      salesman: [],
      // 总数据条数
      total: 0,
      questionForm: {
        product_name: '',
        company_name: '',
        product_id: '',
        equipment_id: '',
        company_id: '',
        salesman_id: '',
        equipment_number: '',
        salesman_name: '',
        sell_time: '',
        equipment_state: '',
        equipment_comment: '',
      },
      dialogFormVisible: false,
      editFormVisible: false,
      rules: {
        product_id: [
          {required: true, message: '请选择产品', trigger: 'change'}
        ],
        company_id: [
          {required: true, message: '请选择公司', trigger: 'change'}
        ],
        salesman_id: [
          {required: true, message: '请选择销售员', trigger: 'change'}
        ],
        equipment_number: [
          {required: true, message: '请输入设备编码', trigger: 'change'},
        ],
        sell_time: [
          {type: 'date', required: true, message: '请选择日期', trigger: 'change'}
        ],
        equipment_state: [
          {required: true, message: '请选择在线状态', trigger: 'change'}
        ]
      },
    }
  },

  created(){
    this.getGoodsList();
  },
  methods: {
    //列表清空
    update(){
      this.dialogFormVisible=false;
      this.editFormVisible=false;
    },
    // 根据分页获取对应的商品列表
    async getGoodsList(){
      this.$http.get("http://121.5.74.11:8080/equipment/getAll").then(res=>{
        this.newgoodsList=this.goodsList=res.data;
        this.total=res.data.length;
      });
      this.$http.get("http://121.5.74.11:8080/product/getAllProduct").then(res => {
        this.product = res.data;
      });
      this.$http.get("http://121.5.74.11:8080/company/getAllCompany").then(res => {
        this.company = res.data;
      });
      this.$http.get("http://121.5.74.11:8080/salesman/getAll").then(res => {
        this.salesman = res.data;
      });
    },
    add1(){
      this.questionForm={};
      this.dialogFormVisible = true;
    },
    //添加商品
    addProduct(){
      let userData = new URLSearchParams();
      userData.append('product_id', this.questionForm.product_id)
      userData.append('company_id', this.questionForm.company_id)
      userData.append('equipment_number', this.questionForm.equipment_number)
      userData.append('salesman_id', this.questionForm.salesman_id)
      userData.append('sell_time', this.questionForm.sell_time)
      userData.append('equipment_state', this.questionForm.equipment_state)
      userData.append('equipment_comment', this.questionForm.equipment_comment)
      this.$http.post("http://121.5.74.11:8080/equipment/addEquipment",userData).then(res=>{
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
      this.$http.post("http://121.5.74.11:8080/equipment/update",this.questionForm).then(res=>{
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
      const confirmResult = await this.$confirm('此操作将永久删除该设备，是否继续？','提示',{
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).catch(err => err)
      // 用户取消了删除操作
      if(confirmResult !== 'confirm') return this.$message.info('已取消删除！')
      // 删除的业务逻辑
      this.$http.get("http://121.5.74.11:8080/equipment/deleteEquipment/detail",{params:{"equipment_id":row.equipment_id}}).then(res=>{
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
    searchid(keywords) {
      return this.goodsList.filter(item =>{
        if(item.equipment_id== keywords){
          return item
        }
        else if(item.product_name.includes(keywords)){
          return item
        }
        else if(item.equipment_number.includes(keywords)){
          return item
        }
        else if(item.salesman_name.includes(keywords)){
          return item
        }
        else if(item.company_name.includes(keywords)){
          return item
        }
      })
    },
    chaxun(query){
      let val=this.query;
      if (val == '') {
        this.newgoodsList=this.goodsList = this.tableData
        this.total=this.newgoodsList.length;
      } else {
        this.newgoodsList =this.searchid(val)
        this.total=this.newgoodsList.length;
      }
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


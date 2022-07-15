<template>
  <div>
      <!--面包屑导航区-->
      <el-breadcrumb separator-class="el-icon-arrow-right">
        <el-breadcrumb-item>客户管理</el-breadcrumb-item>
        <el-breadcrumb-item>企业管理</el-breadcrumb-item>
      </el-breadcrumb>
      <!--卡片视图区域-->
      <br>
        <el-card >

<!--      <div class="handle-box">-->
<!--        <el-button  style="float: left;margin-right: 20px" type="primary" size="small" @click="dialogFormVisible = true">添加公司</el-button>-->
<!--        <el-input style="width: 300px;float:left;" size="small" placeholder="根据公司名称查询" v-model="queryn" clearable @clear="getGoodsList">-->
<!--          <el-button slot="append" icon="el-icon-search" @click="chaxun(queryn)"></el-button>-->
<!--        </el-input>-->
<!--      </div>-->
      <el-row style="margin-left: 40px;margin-top: 20px">
        <el-col :span="4" >
          <el-button type="primary" size="small" @click="dialogFormVisible = true">添加公司</el-button>
        </el-col>
        <el-col :span="8">
          <el-input size="small" placeholder="根据公司名称查询" v-model="queryn" clearable @clear="getGoodsList">
            <el-button slot="append" icon="el-icon-search" @click="chaxun(queryn)"></el-button>
          </el-input>
        </el-col>

      </el-row>

      <!--    公司  -->
      <el-dialog title="添加公司" width="36%" :visible.sync="dialogFormVisible" center>
        <el-form :model="questionForm" ref="questionForm"  :rules="rules" label-position="right" label-width="110px" style="width: 400px; margin-left:50px;">
          <el-form-item label="公司名称" prop="name">
            <el-input v-model="questionForm.company_name" placeholder="必填" style="width: 280px"></el-input>
          </el-form-item>
          <el-form-item label="公司类型" prop="type">
            <el-input v-model="questionForm.company_type" placeholder="必填" style="width: 280px"/>
          </el-form-item>
          <el-form-item label="公司所在省份" prop="province">
            <el-select v-model="questionForm.company_local_province" placeholder="请选择省份" @change="provinceChange($event)" style="width: 280px">
              <el-option v-for="(item,index) in provinceList" :key="index" :value="item.name"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="公司所在城市" prop="city" >
            <el-select v-model="questionForm.company_local_city" placeholder="请选择城市" @change="cityChange($event)" style="width: 280px">
              <el-option v-for="(item,index) in cityList" :key="index" :value="item.name"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="公司备注">
            <el-input v-model="questionForm.company_comment" style="width: 280px"/>
          </el-form-item>
        </el-form>
        <div slot="footer" ref="questionForm" class="dialog-footer">
          <el-button @click="update">取消</el-button>
          <el-button type="primary" @click=" addProduct()">确定</el-button>
        </div>
      </el-dialog>

      <!-- table表格区域 -->
      <el-table :data="newgoodsList.slice((queryInfo.pagenum - 1) * queryInfo.pagesize, queryInfo.pagenum * queryInfo.pagesize)" border stripe height="450px">
        <el-table-column type="index" label="#" ></el-table-column>
        <el-table-column  label="公司名称" prop="company_name" ></el-table-column>
        <el-table-column  label="公司类型" prop="company_type"></el-table-column>
        <el-table-column  label="公司所在省份" prop="company_local_province" ></el-table-column >
        <el-table-column  label="公司所在城市" prop="company_local_city"></el-table-column >
        <el-table-column  label="公司备注" prop="company_comment" ></el-table-column>
        <el-table-column  label="操作" >
          <template slot-scope="scope">
            <el-button type="primary"  size="mini" @click="handleEdit(scope.row)">编辑</el-button>
            <el-button type="danger"  size="mini" @click="remove(scope.row)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>


      <!--      编辑公司信息-->
      <el-dialog title="更新公司信息" width="36%" :visible.sync="editFormVisible" center >
        <el-form :model="questionForm" ref="questionForm"  :rules="rules" label-position="right" label-width="110px" style="width: 400px; margin-left:50px;">
          <el-form-item label="公司名称" prop="name">
            <el-input v-model="questionForm.company_name" placeholder="必填" style="width: 280px"></el-input>
          </el-form-item>
          <el-form-item label="公司类型" prop="type">
            <el-input v-model="questionForm.company_type" placeholder="必填" style="width: 280px"/>
          </el-form-item>
          <el-form-item label="公司所在省份" prop="province">
            <el-select v-model="questionForm.company_local_province" placeholder="请选择省份" @change="provinceChange($event)" style="width: 280px">
              <el-option v-for="(item,index) in provinceList" :key="index" :value="item.name"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="公司所在城市" prop="city" >
            <el-select v-model="questionForm.company_local_city" placeholder="请选择城市" @change="cityChange($event)" style="width: 280px">
              <el-option v-for="(item,index) in cityList" :key="index" :value="item.name"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="公司备注">
            <el-input v-model="questionForm.company_comment" style="width: 280px"/>
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
      queryn:'',
      // 商品列表
      newgoodsList:[],
      goodsList: [],
      // 总数据条数
      total: 0,
      questionForm: {
        company_id:"",
        company_name: "",
        company_type: "",
        company_local_province: "",
        company_local_city: "",
        company_comment: "",
      },
      provinceList:[],
      cityList:[],
      countyList:[],
      CITY:[],
      XIAN:[],
      dialogFormVisible: false,
      editFormVisible: false,
      rules:{
        name: [
          { required: true, message: '请输入公司名称', trigger: 'blur' },
        ],
        type: [
          { required: true, message: '请输入公司类型', trigger: 'blur' },
        ],
        province: [
          { required: true, message: '请输入公司所在省份', trigger: 'blur' },
        ],
        city: [
          { required: true, message: '请输入公司所在城市', trigger: 'blur' },
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
        if(item.company_id== keywords){
          return item
        }
        else if(item.company_name.includes(keywords)){
          return item
        }
        else if(item.company_type.includes(keywords)){
          return item
        }
        else if(item.company_local_province.includes(keywords)){
          return item
        }
        else if(item.company_local_city.includes(keywords)){
          return item
        }
      })
    },
    chaxun(queryn){
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
    // 根据分页获取对应的商品列表
    async getGoodsList(){
      this.$http.get("http://121.5.74.11:8080/company/getAllCompany").then(res=>{
        this.newgoodsList=this.goodsList=res.data;
        this.total=res.data.length;
      });

      this.$http({
        method:"get",
        url:"https://restapi.amap.com/v3/config/district?parameters",
        params:{
          key:"3a708a4ef5e3af28694b1c861985a5ce",
          keyWords:"中国",
          subdistrict:3
        }
      }).then((res)=>{
        this.provinceList = res.data.districts[0].districts  /* 省*/
        /* 进行遍历赋值*/
        /* 市区和县区*/
        let newProvince = this.provinceList
        for(let i = 0; i < newProvince.length; i++){              /* 省级*/
          for(let j = 0; j < newProvince[i].districts.length; j++){       /* 市级*/
            let city = newProvince[i].districts[j].name
            this.CITY.push({id:j+1,name:city,code:i+1})
            for(let k = 0;k<newProvince[i].districts[j].districts.length; k++){/* 县级*/
              let xian = newProvince[i].districts[j].districts[k].name
              this.XIAN.push({id:k+1,name:xian,code:j+1,cityCountyName:city})
            }
          }
        }
        for(let m = 0; m < newProvince.length;m++){
          newProvince[m] = {...newProvince[m],...{code:m+1}}
        }
        this.provinceList = newProvince
      })
    },
    provinceChange(that){
      // 根据选中省，匹配市
      let cityCode = 0
      let newCityArry = []
      this.provinceList.forEach((item,index)=>{
        if(item.name == that){
          cityCode = item.code
        }
      })
      // console.log(cityCode)
      if(cityCode){
        this.cityList = []
        this.CITY.forEach((item,index)=>{
          if(item.code == cityCode){
            this.cityList.push(item)
          }
        })  /* 市匹配成功*/
      }
    },
    cityChange(that){
      let countyCode = 0
      let cityname = ''
      let newCountyArry = []
      this.cityList.forEach((item,index)=>{
        if(item.name == that){
          countyCode = item.id
          cityname = item.name
        }
      })
      if(countyCode){
        this.countyList = []
        this.XIAN.forEach((item,index)=>{
          if(item.code == countyCode && item.cityCountyName == cityname){
            this.countyList.push(item)
          }
        })
      }
    },
    //添加商品
    addProduct(){
      let userData = new URLSearchParams();
      userData.append('company_name', this.questionForm.company_name)
      userData.append('company_type', this.questionForm.company_type)
      //userData.append('salesman_name', this.form.salesman_name)
      userData.append('company_local_province', this.questionForm.company_local_province)
      userData.append('company_local_city', this.questionForm.company_local_city)
      //原方法
      userData.append('company_comment', this.questionForm.company_comment)
      this.$http.post("http://121.5.74.11:8080/company/addCompany",userData).then(res=>{
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
      this.$http.post("http://121.5.74.11:8080/company/update",this.questionForm).then(res=>{
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
      const confirmResult = await this.$confirm('此操作将永久删除该公司信息，是否继续？','提示',{
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).catch(err => err)
      // 用户取消了删除操作
      if(confirmResult !== 'confirm') return this.$message.info('已取消删除！')
      // 删除的业务逻辑
      console.log(row.company_id);
      this.$http.delete("http://121.5.74.11:8080/company/deleteCompany/detail",{params:{"company_id":row.company_id}}).then(res=>{
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


<style lang="scss" scoped>


</style>

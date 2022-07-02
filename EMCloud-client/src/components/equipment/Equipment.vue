<template>
  <div>
    <el-table
      :data="tableData.filter(data => !search || data.name.toLowerCase().includes(search.toLowerCase()))"
      style="width: 100%">
      <el-table-column
        label="产品"
        width="180">
        <template slot-scope="scope">
          <i class="el-icon-time"></i>
          <span style="margin-left: 10px">{{ scope.row.product_name}}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="客户"
        width="180">
        <template slot-scope="scope">
          <el-popover trigger="hover" placement="top">
            <div slot="reference" class="name-wrapper">
              <el-tag size="medium">{{ scope.row.company_name}}</el-tag>
            </div>
          </el-popover>
        </template>
      </el-table-column>
      <el-table-column label="设备id" v-if="false" prop="equipment_id"></el-table-column>
      <el-table-column label="公司id" v-if="false" prop="company_id"></el-table-column>
      <el-table-column label="产品id" v-if="false" prop="product_id"></el-table-column>
      <el-table-column label="销售员id" v-if="false" prop="salesman_id"></el-table-column>
      <el-table-column label="设备编码" prop="equipment_number"></el-table-column>
      <el-table-column label="销售人" prop="salesman_name"></el-table-column>
      <el-table-column label="销售时间" prop="sell_time"></el-table-column>
      <el-table-column label="是否在线1/0" prop="equipment_state"></el-table-column>
      <el-table-column label="备注" prop="equipment_comment"></el-table-column>
      <el-table-column align="right">
        <template slot="header" slot-scope="scope">
          <el-input
            v-model="search"
            size="mini"
            placeholder="输入关键字搜索"/>
        </template>
        <template slot-scope="scope">
          <el-button
            size="mini"
            @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
          <el-popconfirm
            confirm-button-text='好的'
            cancel-button-text='不用了'
            icon="el-icon-info"
            icon-color="red"
            title="这是一段内容确定删除吗？"
            @confirm="handleDelete(scope.$index, scope.row)"
          >
            <el-button slot="reference" size="mini" type="danger">删除</el-button>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>
    <el-button style="margin: 10px 0px;"  @click="saveUserInfo" type="success" size="mini">添加</el-button>
    <transition name="el-zoom-in-center">
      <div v-show="show" class="transition-box">
        <el-form :model="form" :rules="rules" ref="form" label-width="80px" >
          <!--        <el-form ref="form" :model="form" label-width="80px">-->
          <el-form-item label="产品" prop="product_id">
            <!--            <el-input v-model="form.product_name"></el-input>-->
            <el-select v-model="form.product_id" placeholder="请选择">
              <el-option
                v-for="item in product"
                :key="item.product_id"
                :label="item.product_name"
                :value="item.product_id">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="客户" prop="company_id">
            <!--            <el-input v-model="form.company_name"></el-input>-->
            <el-select v-model="form.company_id" placeholder="请选择">
              <el-option
                v-for="item in company"
                :key="item.company_id"
                :label="item.company_name"
                :value="item.company_id">
              </el-option>
            </el-select>
          </el-form-item>
          <!--          <el-form-item label="产品id">-->
          <!--            <el-input v-model="form.product_id"></el-input>-->
          <!--          </el-form-item>-->
          <!--          <el-form-item label="设备id">-->
          <!--            <el-input v-model="form.equipment_id"></el-input>-->
          <!--          </el-form-item>-->
          <!--          <el-form-item label="公司id">-->
          <!--            <el-input v-model="form.company_id"></el-input>-->
          <!--          </el-form-item>-->
          <!--          <el-form-item label="销售员id">-->
          <!--            <el-input v-model="form.salesman_id"></el-input>-->
          <!--          </el-form-item>-->
          <el-form-item label="设备编码" prop="equipment_number">
            <el-input v-model="form.equipment_number"></el-input>
          </el-form-item>
          <el-form-item label="销售人员" prop="salesman_id">
            <!--            <el-input v-model="form.salesman_name"></el-input>-->
            <el-select v-model="form.salesman_id" placeholder="请选择">
              <el-option
                v-for="item in salesman"
                :key="item.salesman_id"
                :label="item.salesman_name"
                :value="item.salesman_id">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="销售时间" required>
            <!--        <el-input v-model="form.sell_time"></el-input>-->
            <div class="block">
              <span class="demonstration"></span>
              <el-form-item prop="sell_time">
                <el-date-picker
                  v-model="form.sell_time"
                  type="date"
                  value-format="yyyy-MM-dd"
                  placeholder="选择日期">
                </el-date-picker>
              </el-form-item>
            </div>
          </el-form-item>
          <el-form-item label="是否在线" prop="equipment_state">
            <!--            <el-input v-model="form.equipment_state"></el-input>-->
            <el-radio-group v-model="form.equipment_state">
              <el-radio label="1">在线</el-radio>
              <el-radio label="0">宕机</el-radio>
            </el-radio-group>
          </el-form-item>
          <el-form-item label="备注信息">
            <el-input v-model="form.equipment_comment"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="onSubmit">立即创建</el-button>
            <el-button type="primary" @click="onEdit">编辑</el-button>
            <el-button @click="saveUserInfo">重置</el-button>
          </el-form-item>
        </el-form>
      </div>
    </transition>
  </div>
</template>

<script>
  export default {
    name: "machine",
    data() {
      return {
        tableData: [],
        product: [],
        company: [],
        salesman: [],
        search: "",
        show: false,
        form: {
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

        rules: {
          product_id: [
            {required: true, message: '请选择产品', trigger: 'blur'}
          ],
          company_id: [
            {required: true, message: '请选择公司', trigger: 'blur'}
          ],
          salesman_id: [
            {required: true, message: '请选择销售员', trigger: 'blur'}
          ],
          equipment_number: [
            { required: true, message: '请输入设备编码', trigger: 'blur' },
          ],
          sell_time: [
            { type: 'date', required: true, message: '请选择日期', trigger: 'blur' }
          ],
          equipment_state: [
            { required: true, message: '请选择在线状态', trigger: 'blur' }
          ]
        },
      }
    },
    methods: {
      saveUserInfo(){
        this.show=true;
        this.form={};
      },
      // submitForm(formName) {
      //   this.$refs[formName].validate((valid) => {
      //     if (valid) {
      //       alert('submit!');
      //     } else {
      //       console.log('error submit!!');
      //       return false;
      //     }
      //   });
      // },
      // resetForm(formName) {
      //   this.$refs[formName].resetFields();
      // },

      handleEdit(index, row) {
        console.log(index, row);
        this.show=true;
        this.form=row;
      },
      handleDelete(index, row) {
        console.log(index, row);
        console.log(row.equipment_id);
        this.$http.get("http://121.5.74.11:8080/equipment/deleteEquipment/detail",{params:{"equipment_id":row.equipment_id}}).then(res=>{
          if(res.data.status){
            this.$message({
              message: res.data.msg,
              type: 'success'
            });
            this.findAllTableData();
          }else{
            this.$message.error(res.data.msg);
          }
        });
      },
      onSubmit() {
        let userData = new URLSearchParams();
        userData.append('product_id', this.form.product_id)
        // userData.append('product_name', this.form.product_name)
        userData.append('company_id', this.form.company_id)
        //userData.append('company_name', this.form.company_name)
        userData.append('equipment_number', this.form.equipment_number)
        //userData.append('salesman_name', this.form.salesman_name)
        userData.append('salesman_id', this.form.salesman_id)
        userData.append('sell_time', this.form.sell_time)
        //原方法
        userData.append('equipment_state', this.form.equipment_state)

        //判断form.equipment_state是在线or宕机，传入接口1or0
        //  if (this.form.equipment_state.isEqual("在线"))
        // userData.append('equipment_state', 1)
        // else if (this.form.equipment_state.isEqual("宕机"))
        //   userData.append('equipment_state', 0)
        userData.append('equipment_comment', this.form.equipment_comment)
        this.$http.post("http://121.5.74.11:8080/equipment/addEquipment",userData).then(res=>{
          if(res.data.status){
            this.$message({
              message: '恭喜你，'+res.data.msg,
              type: 'success'
            });
            //清空表单信息
            this.form={};
            //隐藏表单
            this.show=false;
            this.findAllTableData();
          }else{
            this.$message.error(res.data.msg);
          }
        });
      },
      onEdit(){
        console.log(this.form.company_id);
        this.$http.post("http://121.5.74.11:8080/equipment/update",this.form).then(res=>{
          console.log(res.data);
          if(res.data.status){
            this.$message({
              message: '恭喜你，'+res.data.msg,
              type: 'success'
            });
            //清空表单信息
            this.form={};
            //隐藏表单
            this.show=false;
            this.findAllTableData();
          }else{
            this.$message.error(res.data.msg);
          }
        });
      },
      findAllTableData(){
        // console.log(row.equipment_id);
        this.$http.get("http://121.5.74.11:8080/equipment/getAll").then(res=>{
          this.tableData=res.data;
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
      }
    },
    created(){
      this.findAllTableData();
    }
  }
</script>

<style scoped>
  .transition-box {
    margin-bottom: 10px;
    width: 100%;
    height: 600px;
    border-radius: 4px;
    padding: 40px 20px;
    box-sizing: border-box;
    margin-right: 20px;
  }
</style>

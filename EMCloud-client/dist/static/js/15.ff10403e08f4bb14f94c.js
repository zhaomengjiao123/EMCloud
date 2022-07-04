webpackJsonp([15],{UCzy:function(e,t,r){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var o=r("Xxa5"),a=r.n(o),s=r("exGp"),n=r.n(s),l={name:"Role.vue",data:function(){return{roleList:[],roleInfo:{},addDialogVisible:!1,addRules:{rolename:[{required:!0,message:"请输入角色名称",trigger:"blur"}],roledesc:[{required:!0,message:"请输入角色描述",trigger:"blur"}]},currentRole:{},editDialogVisible:!1,editRules:{rolename:[{required:!0,message:"请输入角色名称",trigger:"blur"}],roledesc:[{required:!0,message:"请输入角色描述",trigger:"blur"}]},showDialogVisible:!1,rightList:[],rightProps:{label:"authName",children:"children"},checkedArr:[],roleId:""}},created:function(){this.getRoleList()},methods:{getRoleList:function(){var e=this;return n()(a.a.mark(function t(){var r,o;return a.a.wrap(function(t){for(;;)switch(t.prev=t.next){case 0:return t.next=2,e.$http.get("roles");case 2:if(r=t.sent,200===(o=r.data).meta.status){t.next=7;break}return console.log(o.meta.status),t.abrupt("return",e.$message.error("获取角色列表失败"));case 7:e.roleList=o.data;case 8:case"end":return t.stop()}},t,e)}))()},addRole:function(e){var t,r=this;this.$refs.addRoleRef.validate((t=n()(a.a.mark(function t(o){var s;return a.a.wrap(function(t){for(;;)switch(t.prev=t.next){case 0:if(o){t.next=2;break}return t.abrupt("return",r.$message.error("不符合格式要求"));case 2:return t.next=4,r.$http.post("roles",{roleName:e.rolename,roleDesc:e.roledesc});case 4:s=t.sent,201!==s.data.meta.status&&r.$message.error("创建失败"),r.getRoleList(),r.$message.success("创建成功"),r.addDialogVisible=!1;case 10:case"end":return t.stop()}},t,r)})),function(e){return t.apply(this,arguments)}))},closeAddDialog:function(){this.$refs.addRoleRef.resetFields()},closeEditDialog:function(){this.$refs.editRoleRef.resetFields()},showEditRoleDialog:function(e){var t=this;return n()(a.a.mark(function r(){var o,s;return a.a.wrap(function(r){for(;;)switch(r.prev=r.next){case 0:return t.editDialogVisible=!0,r.next=3,t.$http.get("roles/"+e);case 3:if(o=r.sent,200===(s=o.data).meta.status){r.next=7;break}return r.abrupt("return",t.$message.error("获取角色失败"));case 7:t.currentRole=s.data,console.log(t.currentRole);case 9:case"end":return r.stop()}},r,t)}))()},editRole:function(){var e,t=this;this.$refs.editRoleRef.validate((e=n()(a.a.mark(function e(r){var o;return a.a.wrap(function(e){for(;;)switch(e.prev=e.next){case 0:if(r){e.next=2;break}return e.abrupt("return");case 2:return e.next=4,t.$http.put("roles/"+t.currentRole.roleId,{roleName:t.currentRole.rolename,roleDesc:t.currentRole.roledesc});case 4:if(o=e.sent,201===o.data.meta.status){e.next=8;break}return e.abrupt("return",t.$message.error("更新失败"));case 8:console.log(t.currentRole.roleId),t.editDialogVisible=!1,t.getRoleList(),t.$message.success("更新成功");case 12:case"end":return e.stop()}},e,t)})),function(t){return e.apply(this,arguments)}))},open:function(e){var t=this;this.$confirm("此操作将永久删除该角色, 是否继续?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then(n()(a.a.mark(function r(){var o;return a.a.wrap(function(r){for(;;)switch(r.prev=r.next){case 0:return r.next=2,t.$http.delete("roles/"+e);case 2:if(o=r.sent,200===o.data.meta.status){r.next=6;break}return r.abrupt("return",t.$message.error("删除失败"));case 6:t.getRoleList(),t.$message({type:"success",message:"删除成功!"});case 8:case"end":return r.stop()}},r,t)}))).catch(function(){t.$message({type:"info",message:"已取消删除"})})},removeRight:function(e,t){var r=this;this.$confirm("此操作将永久删除该权限, 是否继续?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then(n()(a.a.mark(function o(){var s,n;return a.a.wrap(function(o){for(;;)switch(o.prev=o.next){case 0:return o.next=2,r.$http.delete("roles/"+e.id+"/rights/"+t);case 2:if(s=o.sent,200===(n=s.data).meta.status){o.next=6;break}return o.abrupt("return",r.$message.error("失败"));case 6:e.children=n.data,r.$message({type:"success",message:"删除成功!"});case 8:case"end":return o.stop()}},o,r)}))).catch(function(){r.$message({type:"info",message:"已取消删除"})})},showRightTree:function(e){var t=this;return n()(a.a.mark(function r(){var o,s;return a.a.wrap(function(r){for(;;)switch(r.prev=r.next){case 0:return r.next=2,t.$http.get("rights/tree");case 2:if(o=r.sent,200===(s=o.data).meta.status){r.next=6;break}return r.abrupt("return",t.$message.error("获取列表失败"));case 6:t.rightList=s.data,t.getCheckedArr(e),console.log(t.checkedArr),t.showDialogVisible=!0,t.roleId=e.id;case 11:case"end":return r.stop()}},r,t)}))()},getCheckedArr:function(e){var t=this;if(!e.children)return this.checkedArr.push(e.id);e.children.forEach(function(e){t.getCheckedArr(e)})},closeRightsDialog:function(){this.checkedArr=[]},allotRights:function(){var e=this;return n()(a.a.mark(function t(){var r,o,s,n;return a.a.wrap(function(t){for(;;)switch(t.prev=t.next){case 0:return r=e.$refs.treeRef.getCheckedKeys().concat(e.$refs.treeRef.getHalfCheckedKeys()),o=r.join(","),t.next=4,e.$http.post("roles/"+e.roleId+"/rights",{rids:o});case 4:if(s=t.sent,200===(n=s.data).meta.status){t.next=9;break}return console.log(n.meta.status),t.abrupt("return",e.$message.error("分配权限失败"));case 9:e.showDialogVisible=!1,e.getRoleList();case 11:case"end":return t.stop()}},t,e)}))()}}},i={render:function(){var e=this,t=e.$createElement,r=e._self._c||t;return r("div",[r("el-breadcrumb",{attrs:{"separator-class":"el-icon-arrow-right"}},[r("el-breadcrumb-item",{attrs:{to:{path:"/"}}},[e._v("首页")]),e._v(" "),r("el-breadcrumb-item",[e._v("权限管理")]),e._v(" "),r("el-breadcrumb-item",[e._v("角色列表")])],1),e._v(" "),r("el-card",{staticClass:"box-card"},[r("el-row",[r("el-col",[r("el-button",{attrs:{type:"primary"},on:{click:function(t){e.addDialogVisible=!0}}},[e._v("添加角色")])],1)],1),e._v(" "),r("el-table",{attrs:{data:e.roleList,border:"",stripe:""}},[r("el-table-column",{attrs:{type:"expand",label:"明细"},scopedSlots:e._u([{key:"default",fn:function(t){return e._l(t.row.children,function(o,a){return r("el-row",{class:{bdbottom:!0,bdtop:0==a,middle:!0}},[r("el-col",{attrs:{span:5}},[r("el-tag",{key:o.name,attrs:{closable:"",type:o.type},on:{close:function(r){return e.removeRight(t.row,o.id)}}},[e._v("\n                    "+e._s(o.authName)+"\n                  ")]),e._v(" "),r("i",{staticClass:"el-icon-caret-right"})],1),e._v(" "),r("el-col",{attrs:{span:19}},e._l(o.children,function(o,a){return r("el-row",{class:{bdtop:0!=a,middle:!0}},[r("el-col",{attrs:{span:6}},[r("el-tag",{key:o.name,attrs:{closable:"",type:"success"},on:{close:function(r){return e.removeRight(t.row,o.id)}}},[e._v("\n                      "+e._s(o.authName)+"\n                    ")]),e._v(" "),r("i",{staticClass:"el-icon-caret-right"})],1),e._v(" "),r("el-col",{attrs:{span:18}},e._l(o.children,function(o){return r("el-tag",{key:o.id,attrs:{closable:"",type:"warning"},on:{close:function(r){return e.removeRight(t.row,o.id)}}},[e._v("\n                        "+e._s(o.authName)+"\n                    ")])}),1)],1)}),1)],1)})}}])}),e._v(" "),r("el-table-column",{attrs:{type:"index",label:"序列"}}),e._v(" "),r("el-table-column",{attrs:{prop:"roleName",label:"角色名称"}}),e._v(" "),r("el-table-column",{attrs:{prop:"roleDesc",label:"角色描述"}}),e._v(" "),r("el-table-column",{attrs:{label:"操作"},scopedSlots:e._u([{key:"default",fn:function(t){return[r("el-button",{attrs:{type:"primary",icon:"el-icon-edit",size:"mini"},on:{click:function(r){return e.showEditRoleDialog(t.row.id)}}},[e._v("编辑")]),e._v(" "),r("el-button",{attrs:{type:"danger",icon:"el-icon-delete",size:"mini"},on:{click:function(r){return e.open(t.row.id)}}},[e._v("删除")]),e._v(" "),r("el-button",{attrs:{type:"warning",icon:"el-icon-setting",size:"mini"},on:{click:function(r){return e.showRightTree(t.row)}}},[e._v("分配权限")])]}}])})],1)],1),e._v(" "),r("el-dialog",{attrs:{title:"添加角色",visible:e.addDialogVisible,width:"50%"},on:{"update:visible":function(t){e.addDialogVisible=t},close:e.closeAddDialog}},[r("el-form",{ref:"addRoleRef",staticClass:"demo-ruleForm",attrs:{model:e.roleInfo,rules:e.addRules,"label-width":"100px"}},[r("el-form-item",{attrs:{label:"角色名称",prop:"rolename"}},[r("el-input",{model:{value:e.roleInfo.rolename,callback:function(t){e.$set(e.roleInfo,"rolename",t)},expression:"roleInfo.rolename"}})],1),e._v(" "),r("el-form-item",{attrs:{label:"角色描述",prop:"roledesc"}},[r("el-input",{model:{value:e.roleInfo.roledesc,callback:function(t){e.$set(e.roleInfo,"roledesc",t)},expression:"roleInfo.roledesc"}})],1)],1),e._v(" "),r("span",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[r("el-button",{on:{click:function(t){e.addDialogVisible=!1}}},[e._v("取 消")]),e._v(" "),r("el-button",{attrs:{type:"primary"},on:{click:function(t){return e.addRole(e.roleInfo)}}},[e._v("确 定")])],1)],1),e._v(" "),r("el-dialog",{attrs:{title:"修改角色",visible:e.editDialogVisible,width:"50%"},on:{"update:visible":function(t){e.editDialogVisible=t},close:e.closeEditDialog}},[r("el-form",{ref:"editRoleRef",staticClass:"demo-ruleForm",attrs:{model:e.currentRole,rules:e.editRules,"label-width":"100px"}},[r("el-form-item",{attrs:{label:"角色名称",prop:"rolename"}},[r("el-input",{model:{value:e.currentRole.rolename,callback:function(t){e.$set(e.currentRole,"rolename",t)},expression:"currentRole.rolename"}})],1),e._v(" "),r("el-form-item",{attrs:{label:"角色描述",prop:"roledesc"}},[r("el-input",{model:{value:e.currentRole.roledesc,callback:function(t){e.$set(e.currentRole,"roledesc",t)},expression:"currentRole.roledesc"}})],1)],1),e._v(" "),r("span",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[r("el-button",{on:{click:function(t){e.editDialogVisible=!1}}},[e._v("取 消")]),e._v(" "),r("el-button",{attrs:{type:"primary"},on:{click:e.editRole}},[e._v("确 定")])],1)],1),e._v(" "),r("el-dialog",{attrs:{title:"分配角色",visible:e.showDialogVisible,width:"50%"},on:{"update:visible":function(t){e.showDialogVisible=t},close:e.closeRightsDialog}},[r("el-tree",{ref:"treeRef",attrs:{data:e.rightList,props:e.rightProps,"node-key":"id","show-checkbox":"","default-expand-all":"","default-checked-keys":e.checkedArr}}),e._v(" "),r("span",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[r("el-button",{on:{click:function(t){e.showDialogVisible=!1}}},[e._v("取 消")]),e._v(" "),r("el-button",{attrs:{type:"primary"},on:{click:e.allotRights}},[e._v("确 定")])],1)],1)],1)},staticRenderFns:[]};var c=r("VU/8")(l,i,!1,function(e){r(!function(){var e=new Error('Cannot find module "!!../../../node_modules/extract-text-webpack-plugin/dist/loader.js?{"omit":1,"remove":true}!vue-style-loader!css-loader?{"sourceMap":true}!../../../node_modules/vue-loader/lib/style-compiler/index?{"vue":true,"id":"data-v-e2b4db7e","scoped":true,"hasInlineConfig":false}!less-loader?{"sourceMap":true}!../../../node_modules/vue-loader/lib/selector?type=styles&index=0!./Role.vue"');throw e.code="MODULE_NOT_FOUND",e}())},"data-v-e2b4db7e",null);t.default=c.exports}});
//# sourceMappingURL=15.ff10403e08f4bb14f94c.js.json

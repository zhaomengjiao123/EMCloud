/* eslint-disable*/
import axios from 'axios'
import { get, post, deletes, put } from './http'



//================>设备预警/报警/紧急警告接口
export const getWarningByCid = (params) => get(`equipmentWarning/getWarningByCid`,params)
export const getErroByCid = (params) => get(`equipmentErro/getErroByCid`,params)
export const getEmergencyByCid = (params) => get(`equipmentEmergency/getEmergencyByCid`,params)

//================>设备统计接口大屏
export const getEquipmentCountOfCompany = () => get(`equipment/getEquipmentCountOfCompany`)
export const getWarningNumByCid = (params) => get(`equipment/getWarningNumByCid`,params)
export const getErroNumByCid = (params) => get(`equipment/getErroNumByCid`,params)
export const getEmergencyNumByCid = (params) => get(`equipment/getEmergencyNumByCid`,params)
export const getOnlineAndNotOnlineEquipmentNum = () => get(`equipment/getOnlineAndNotOnlineEquipmentNum`)
//查询全部的设备数量
export const getAllEquipmentCount = () => get(`equipment/getAllEquipmentCount`)
//查询全部的异常数量
export const getAllExceptionCount = () => get(`equipment/getAllExceptionCount`)




//查询设备在线和不在线的数量
export const getEquipState = () => get(`equipment/getOnlineAndNotOnlineEquipmentNum`);

//查询不同类型产品的异常数量
export const getEquipExType = () => get(`productType/getProductTypeAndTaskNum`);

//查询不同类型产品的任务数量
export const getEquipTaskType = () => get(`productType/getProductTypeAndTaskNum`);

//查询一段时间内所有异常数量（时间是天）
export const getExceptionByDay= (params) => get(`getExceptionByDay`, params);

//查询一段时间内所有异常数量（时间是月）
export const getExceptionByMouth= (params) => get(`getExceptionByMouth`, params);

//查询一段时间内某个产品异常数量（时间是天）
export const getProExceptionByDay= (params) => get(`getProExceptionByDay`, params);

//查询一段时间内某个产品异常数量（时间是月）
export const getProExceptionByMouth= (params) => get(`getProExceptionByMouth`, params);

//查询一段时间内不同类型产品的异常数量（时间是天）
export const getExceptionTypeByDay= (params) => get(`getExceptionTypeByDay`, params);

//查询一段时间内不同类型产品的异常数量（时间是月）
export const getExceptionTypeByMouth = (params) => get(`getExceptionTypeByMouth`, params);

//查询一段时间内所有任务数量（时间是天）
export const getTaskByDay= (params) => get(`getTaskByDay`, params);

//查询一段时间内所有任务数量（时间是月）
export const getTaskByMouth= (params) => get(`getTaskByMouth`, params);

//查询一段时间内某个产品任务数量（时间是天）
export const getProTaskByDay= (params) => get(`getProTaskByDay`, params);

//查询一段时间内某个产品任务数量（时间是月）
export const getProTaskByMouth= (params) => get(`getProTaskByMouth`, params);

//查询一段时间内不同类型产品的任务数量（时间是天）
export const getTaskTypeByDay= (params) => get(`getTaskTypeByDay`, params);

//查询一段时间内不同类型产品的任务数量（时间是月）
export const getTaskTypeByMouth = (params) => get(`getTaskTypeByMouth`, params);


//获取所有产品类型
export const getProType= () => get(`productType/getAllProductType`);

//获取所有用户
export const getUser= () => get(`user/getalluser`);

//获取该公司用户
export const getUserByCompany= (params) => get(`user/admin/getuser`,params);

//获取某个用户
export const getUserByPhone= (params) => get(`user/getuserofphone1`,params);

//获取公司某个用户
export const getUserByPhoneInCompany= (params) => get(`user/getuserofphone2`,params);

//添加管理员
export const addAdmin = (params) => post(`user/superadmin/add`,params)

//添加用户
export const addUser = (params) => post(`user/admin/add`,params)

//修改用户信息
export const updateUser = (params) => put(`user/admin/updateinfo`,params)

//用户修改用户信息
export const updateUserInfo = (params) => put(`user/updateinfo`,params)

//超级管理员修改用户信息（带权限）
export const updateUserSuper = (params) => put(`user/admin/updateinfosuper`,params)

//重置用户信密码
export const updatePasswd = (params) => put(`user/admin/updatepasswd`,params)

//修改用户信密码
export const updateUserPasswd = (params) => put(`user/updatepasswd`,params)

//删除用户信息
export const deleteUser = (params) => post(`user/delete`,params)


//获取所有公司
export const getCompany= () => get(`company/getAllCompany`);

//获取所有部门
export const getDepart= () => get(`depart/getalldepart`);

//获取某个公司所有部门
export const getDepartByCompany= (params) => get(`depart/getdepartbycompany`,params);




// //查询设备在线和不在线的数量
// export const getEquipState = () => get(`getEquipState`);
//
// //查询不同类型产品的异常数量
// export const getEquipExType = () => get(`getEquipExType`);
//
// //查询不同类型产品的任务数量
// export const getEquipTaskType = () => get(`getEquipTaskType`);
//
// //查询一段时间内所有异常数量（时间是天）
// export const getExceptionByDay= (params) => get(`getExceptionByDay`, params);
//
// //查询一段时间内所有异常数量（时间是月）
// export const getExceptionByMouth= (params) => get(`getExceptionByMouth`, params);
//
// //查询一段时间内某个产品异常数量（时间是天）
// export const getProExceptionByDay= (params) => get(`getProExceptionByDay`, params);
//
// //查询一段时间内某个产品异常数量（时间是月）
// export const getProExceptionByMouth= (params) => get(`getProExceptionByMouth`, params);
//
// //查询一段时间内不同类型产品的异常数量（时间是天）
// export const getExceptionTypeByDay= (params) => get(`getExceptionTypeByDay`, params);
//
// //查询一段时间内不同类型产品的异常数量（时间是月）
// export const getExceptionTypeByMouth = (params) => get(`getExceptionTypeByMouth`, params);
//
// //查询一段时间内所有任务数量（时间是天）
// export const getTaskByDay= (params) => get(`getTaskByDay`, params);
//
// //查询一段时间内所有任务数量（时间是月）
// export const getTaskByMouth= (params) => get(`getTaskByMouth`, params);
//
// //查询一段时间内某个产品任务数量（时间是天）
// export const getProTaskByDay= (params) => get(`getProTaskByDay`, params);
//
// //查询一段时间内某个产品任务数量（时间是月）
// export const getProTaskByMouth= (params) => get(`getProTaskByMouth`, params);
//
// //查询一段时间内不同类型产品的任务数量（时间是天）
// export const getTaskTypeByDay= (params) => get(`getTaskTypeByDay`, params);
//
// //查询一段时间内不同类型产品的任务数量（时间是月）
// export const getTaskTypeByMouth = (params) => get(`getTaskTypeByMouth`, params);
//
//
// //获取所有产品类型
// export const getProType= () => get(`productType/getAllProductType`);
//
// //获取所有用户
// export const getUser= () => get(`user/getalluser`);
//
// //获取该公司用户
// export const getUserByCompany= (params) => get(`user/admin/getuser`,params);
//
// //获取某个用户
// export const getUserByPhone= (params) => get(`user/getuserofphone1`,params);
//
// //获取公司某个用户
// export const getUserByPhoneInCompany= (params) => get(`user/getuserofphone2`,params);
//
// //添加管理员
// export const addAdmin = (params) => post(`user/superadmin/add`,params)
//
// //添加用户
// export const addUser = (params) => post(`user/admin/add`,params)
//
// //修改用户信息
// export const updateUser = (params) => put(`user/admin/updateinfo`,params)
//
// //用户修改用户信息
// export const updateUserInfo = (params) => put(`user/updateinfo`,params)
//
// //超级管理员修改用户信息（带权限）
// export const updateUserSuper = (params) => put(`user/admin/updateinfosuper`,params)
//
// //重置用户信密码
// export const updatePasswd = (params) => put(`user/admin/updatepasswd`,params)
//
// //修改用户信密码
// export const updateUserPasswd = (params) => put(`user/updatepasswd`,params)
//
// //删除用户信息
// export const deleteUser = (params) => post(`user/delete`,params)
//
//
// //获取所有公司
// export const getCompany= () => get(`company/getAllCompany`);
//
// //获取所有部门
// export const getDepart= () => get(`depart/getalldepart`);
//
// //获取某个公司所有部门
// export const getDepartByCompany= (params) => get(`depart/getdepartbycompany`,params);

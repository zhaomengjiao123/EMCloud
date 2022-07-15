/* eslint-disable*/
import axios from 'axios'
import { get, post, deletes, put } from './http'



//================>设备预警/报警/紧急警告接口
export const getWarningByCid = (params) => get(`equipmentWarning/getWarningByCid`,params)
export const getErroByCid = (params) => get(`equipmentErro/getErroByCid`,params)
export const getEmergencyByCid = (params) => get(`equipmentEmergency/getEmergencyByCid`,params)

//================>地图数据接口
//查询某个城市的地图数据
export const getMapJson = (params) => get(`util/getMapJson`,params)

//================>设备统计接口大屏
//查询每个公司的设备数量
export const getEquipmentCountOfCompany = () => get(`equipment/getEquipmentCountOfCompany`)
//查询每个城市的设备数量
export const getAllEquipmentNumAndCity = () => get('equipment/getAllEquipmentNumAndCity')
//根据公司ID查询本公司预警/报警/紧急告警数量
export const getWarningNumByCid = (params) => get(`equipment/getWarningNumByCid`,params)
export const getErroNumByCid = (params) => get(`equipment/getErroNumByCid`,params)
export const getEmergencyNumByCid = (params) => get(`equipment/getEmergencyNumByCid`,params)
//查询在线设备/不在线设备数
export const getOnlineAndNotOnlineEquipmentNum = () => get(`equipment/getOnlineAndNotOnlineEquipmentNum`)
//查询有异常的设备的数量
export const getAbnormalEquipmentNum = () => get('equipment/getAbnormalEquipmentNum')
//查询全部的设备数量
export const getAllEquipmentCount = () => get(`equipment/getAllEquipmentCount`)
//查询全部的异常数量
export const getAllExceptionCount = () => get(`equipment/getAllExceptionCount`)
//查询大屏设备轮播图信息
export const getBigScreenEquipmentInfo = () => get(`equipment/getBigScreenEquipmentInfo`)
//查询每个城市的所有设备的报警数量
export const getErroCountOfAllCity = () => get('equipmentErro/getErroCountOfAllCity')

//=======================>设备异常查询
//查询全部的异常信息
export const getAllExceptionInfo = () => get(`equipmentErro/getAllExceptionInfo`)

export const getAllExceptionInfoByCid = (params) => get(`equipmentErro/getAllExceptionInfoByCid`,params)
//查询全部的报警信息
export const getAllErroInfo = () => get('equipmentErro/getAllErroInfo')
//查询全部的紧急告警信息
export const getAllEmergencyInfo = () => get('equipmentEmergency/getAllEmergencyInfo')
//查询全部的预警信息
export const getAllWarningInfo = () => get('equipmentWarning/getAllWarningInfo')
//查询某公司全部的设备编号
export const getEquipmentNumberListByCid = (params) => get(`equipment/getEquipmentNumberListByCid`,params)
//根据设备编号查询设备的预警信息
export const getWarningByEquipmentNumberAndCid = (params) => get(`equipmentWarning/getWarningByEquipmentNumberAndCid`,params)
//根据设备编号查询设备的报警信息
export const getErroByEquipmentNumberAndCid = (params) => get(`equipmentErro/getErroByEquipmentNumberAndCid`,params)
//根据设备编号查询设备的紧急警告信息
export const getEmergencyByEquipmentNumberAndCid = (params) => get(`equipmentEmergency/getEmergencyByEquipmentNumberAndCid`,params)
//===============>设备实时监控接口
//得到设备最新更新时间列表
export const getEquipmentListOfUpdateTimeByCidAndEn = (params) => get(`equipmentState/getEquipmentListOfUpdateTimeByCidAndEn`,params)
//查询某个具体设备的实时状态信息
export const getStateInfoByEidAndTime = (params) => get(`equipmentState/getStateInfoByEidAndTime`,params)
//得到某公司设备最新更新时间列表
export const getEquipmentListOfUpdateTimeByCid = (params) => get(`equipmentState/getEquipmentListOfUpdateTimeByCid`,params)










//获取所有产品类型
export const getProType= () => get(`product/getAllProduct`);

//查询设备在线和不在线的数量
export const getEquipState = (params) => get(`equipment/getOnlineAndNotOnlineEquipmentNum2`,params);


//异常分析
//查询不同类型产品的异常数量

export const getEquipExType = (params) => get(`productType/getAllProductTypeExceptionNum2`,params);

//查询一段时间内所有异常数量（时间是天）
export const getExceptionByDay= (params) => get(`allExceptionAndTime/getAllExceptionAndTimeByDay2`, params);

//查询一段时间内所有异常数量（时间是月）
export const getExceptionByMouth= (params) => get(`allExceptionAndTime/getAllExceptionAndTimeByMonth2`, params);

//查询一段时间内某个产品异常数量（时间是天）
export const getProExceptionByDay= (params) => get(`allExceptionAndTime/getExceptionTypeAndTimeVOByDay2`, params);

//查询一段时间内某个产品异常数量（时间是月）
export const getProExceptionByMouth= (params) => get(`allExceptionAndTime/getExceptionTypeAndTimeVOByMonth2`, params);

//查询一段时间内不同类型产品的异常数量（时间是天）
export const getExceptionTypeByDay= (params) => get(`allExceptionAndTime/getAllExceptionTypeAndTimeVOByDay2`, params);

//查询一段时间内不同类型产品的异常数量（时间是月）
export const getExceptionTypeByMouth = (params) => get(`allExceptionAndTime/getAllExceptionTypeAndTimeVOByMonth2`, params);



//任务分析
//查询不同类型产品的任务数量
export const getEquipTaskType = (params) => get(`productType/getProductTypeAndTaskNum2`,params);

//查询一段时间内所有任务数量（时间是天）
export const getTaskByDay= (params) => get(`TaskNumInTime/getAllTaskNumInTimeByDay2`, params);

//查询一段时间内所有任务数量（时间是月）
export const getTaskByMouth= (params) => get(`TaskNumInTime/getAllTaskNumInTimeByMouth2`, params);

//查询一段时间内某个产品任务数量（时间是天）
export const getProTaskByDay= (params) => get(`TaskNumInTime/getProductTaskNumInTimeByPidByDay2`, params);

//查询一段时间内某个产品任务数量（时间是月）
export const getProTaskByMouth= (params) => get(`TaskNumInTime/getProductTaskNumInTimeByPidByMouth2`, params);

//查询一段时间内不同类型产品的任务数量（时间是天）
export const getTaskTypeByDay= (params) => get(`TaskNumInTime/getDiffProductTypeAndTaskNumInTimeByDay2`, params);

//查询一段时间内不同类型产品的任务数量（时间是月）
export const getTaskTypeByMouth = (params) => get(`TaskNumInTime/getDiffProductTypeAndTaskNumInTimeByDay2`, params);





//用户管理
//获取所有用户
export const getUser= () => get(`user/getalluser`);

//获取该公司用户
export const getUserByCompany= (params) => get(`user/admin/getuser`,params);

//获取某个用户
export const getUserByPhone= (params) => get(`user/getuserofphone1`,params);


//模糊获取某个用户
export const getUserByPhone2= (params) => get(`user/getuserofphone2`,params);

//获取公司某个用户
export const getUserByPhoneInCompany= (params) => get(`user/getuserofcompanyid`,params);

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


//获取菜单
export const getMenuList= (params) => get(`menu/getmenu`,params);


//超级管理员获取菜单
export const getAllMenu= () => get(`menu/getallmenu`);

//管理员获取用户菜单
export const getUserMenuToAdmin= () => get(`menu/getmenutoadmin`);

//超级管理员修改用户权限，使其可见
export const updateMenuUserVis = (params) => put(`menu/updatemenuuservis`,params)


//超级管理员修改用户权限，使其不可见
export const updateMenuUserInvis = (params) => put(`menu/updatemenuuserinvis`,params)

//超级管理员修改管理员权限，使其可见
export const updateMenuAdminVis = (params) => put(`menu/updatemenuadminvis`,params)

//超级管理员修改管理员权限，使其不可见
export const updateMenuAdminInvis = (params) => put(`menu/updatemenuadmininvis`,params)

//登陆
export const login = (params) => post(`user/login`,params)

//注册
export const register = (params) => post(`user/add`,params)




//获取任务
export const getTask= () => get(`getTask`);

//获取任务
export const getTaskById= (params) => get(`getTaskbyId`,params);

//获取任务
export const getTaskByCompanyId= (params) => get(`getTaskbyCompanyId`,params);

//删除产品
export const deleteProduct= (params) => post(`product/delete`,params);

//获取任务
export const getTaskByEquip= (params) => get(`getTaskbyEquipId`,params);


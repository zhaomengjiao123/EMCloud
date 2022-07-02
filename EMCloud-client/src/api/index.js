/* eslint-disable*/
import axios from 'axios'
import { get, post, deletes, put } from './http'



// =======================> 用户 API
// 登录
export const loginIn = (params) => post(`user/login/status`, params)
// 注册
export const SignUp = (params) => post(`user/signup`, params)
// 更新用户信息
export const updateUserMsg = (params) => post(`user/update`, params)
// 返回指定ID的用户
export const getUserOfId = (userId) => get(`user/detail?userId=${userId}`)

//========================>车票 API
// 查询符合日期的车票信息
export const getTrainOfDateStation = (params) => get('train/searchTrain',params)
//查询指定ID的列车信息
export const getTrainOfId = (train_id) => get(`train/getTrainOfId/detail?train_id=${train_id}`)
//查询座位信息
export const getTrainOfSeat =(params) => get('train/searchSeat',params)

//=======================>乘客API
//返回指定用户的乘车人信息
export const getPassengerOfuserId = (userId) => get(`passenger/getPassenger/detail?userId=${userId}`)
//添加乘车人
export const setPassenger = (params) => post(`passenger/setPassenger`,params)
//删除乘车人
export const deletePassenger = (params) => get(`passenger/deletePassenger`,params)
//编辑乘车人信息
export const updatePassengerMsg = (params) => post(`passenger/updatePassenger`,params)
//返回指定的乘车人
export const getPassengerOfId = (passenger_id) => get(`passenger/getPassengerOfId/detail?passenger_id=${passenger_id}`)
//根据身份证号查询乘客
export const getPassengerOfIden = (passenger_iden) => get(`passenger/getPassengerOfIden/detail?passenger_iden=${passenger_iden}`)



//========================>换乘API
export const getTrainTransfer = (params) => get('parking/getTrainTransfer',params)

//========================>单程API
export const getTrainSingle = (params) => get('parking/getTrainSingle',params)

//========================>订单API
//添加订单
export const addOrder = (params) => post(`order/creatOrder`,params)

//查询指定用户的未支付订单
export const getNoPayOrder = (userId) =>get(`order/getNoPayOrder/detail?userId=${userId}`)
//用户删除订单
export const deleteOrder = (order_id) =>get(`order/deleteOrder/detail?order_id=${order_id}`)
//用户支付订单
export const payOrder = (order_id) => post(`order/payOrder/detail?order_id=${order_id}`)
//查询用户某种状态的订单
export const getOrderOfState = (params) => get(`order/getOrderOfState`,params)
//用户改签订单
export const updateOrder = (params) => post(`order/updateOrder`,params)
//查询指定用户的已失效订单
//export const getInvalidOrder = (userId) =>get(`order/getInvalidOrder/detail?userId=${userId}`)

//========================>座位API
export const getRestSeat = (params) => get(`seat/getRestSeat`,params)

//查询指定列车的全部可选座位列表
export const getChooseSeat = (params) => get(`seat/getSeatChoose`,params)


//========================>经停API
export const getAllParking = () => get(`parking/getAllParking`)
//返回指定ID的列车经停信息
export const getTrainPark = (train_id) => get(`parking/getTrainPark/detail?train_id=${train_id}`)
// =======================> 收藏 API
// 返回的指定用户ID的收藏列表
export const getCollectionOfUser = (userId) => get(`collection/detail?userId=${userId}`)
// 添加收藏的歌曲 type: 0 代表歌曲， 1 代表歌单
export const setCollection = (params) => post(`collection/add`, params)

//查询设备在线和不在线的数量
export const getEquipState = () => get(`getEquipState`);

//查询不同类型产品的异常数量
export const getEquipExType = () => get(`getEquipExType`);

//查询不同类型产品的任务数量
export const getEquipTaskType = () => get(`getEquipTaskType`);

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



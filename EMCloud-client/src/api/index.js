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

export const getEquipState = () => get(`getEquipState`);
export const getEquipExType = () => get(`getEquipExType`);
export const getEquipTaskType = () => get(`getEquipTaskType`);

export const getExceptionByDay= (params) => get(`getExceptionByDay`, params);
export const getExceptionByMouth= (params) => get(`getExceptionByMouth`, params);
export const getExceptionTypeByDay= (params) => get(`getExceptionTypeByDay`, params);
export const getExceptionTypeByMouth = (params) => get(`getExceptionTypeByMouth`, params);

export const getTaskByDay= (params) => get(`getTaskByDay`, params);
export const getTaskByMouth= (params) => get(`getTaskByMouth`, params);
export const getTaskTypeByDay= (params) => get(`getTaskTypeByDay`, params);
export const getTaskTypeByMouth = (params) => get(`getTaskTypeByMouth`, params);

/* eslint-disable*/
import axios from 'axios'
import { get, post, deletes, put } from './http'


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







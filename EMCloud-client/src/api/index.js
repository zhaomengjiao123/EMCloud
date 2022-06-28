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

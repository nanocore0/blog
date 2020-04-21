import axios from 'axios'
import store from '../store'
import {getToken} from '@/util/auth'

// 创建axios实例
const service = axios.create({
  baseURL: process.env.BASE_URL,
  timeout: 15000
})

// request拦截器
service.interceptors.request.use(
  config => {
    if (store.getters.token && store.getters.token != '') {
      config.headers['Authorization'] = 'bearer ' + store.getters.token
    }
    //config.headers.post['Content-Type'] = 'application/x-www-form-urlencoded'
    return config
  },
  error => {
    console.log(error)
    return Promise.reject(error)
  }
)

// response 拦截器
service.interceptors.response.use(
  response => {
    return response
  },
  error => {
    console.log(error)
    return Promise.reject(error)
  }
)

export default service


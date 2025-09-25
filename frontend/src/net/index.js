/**
 * 网络请求模块
 * 封装HTTP请求方法，处理CSRF防护、错误处理和文件上传下载
 */

import axios from "axios"
import 'element-plus/es/components/message/style/css'
import { ElMessage } from "element-plus"

// 默认错误处理函数
const defaultError = () => ElMessage.error('发生了一些错误，请联系管理员')
const defaultFailure = (message) => ElMessage.warning(message)

// JWT令牌管理函数
function getToken() {
    return localStorage.getItem('jwt_token')
}

function setToken(token) {
    localStorage.setItem('jwt_token', token)
}

function removeToken() {
    localStorage.removeItem('jwt_token')
    localStorage.removeItem('username')
}

/**
 * 从Cookie中读取指定名称的值
 * @param {string} name - Cookie名称
 * @returns {string|null} Cookie值或null
 */
function getCookie(name) {
    const value = `; ${document.cookie}`
    const parts = value.split(`; ${name}=`)
    if (parts.length === 2) return parts.pop().split(';').shift()
    return null
}

// 请求拦截器：自动添加JWT令牌和CSRF令牌
axios.interceptors.request.use((config) => {
    // 启用跨域请求携带凭证
    config.withCredentials = true
    
    // 添加JWT令牌到请求头
    const token = getToken()
    if (token) {
        config.headers = config.headers || {}
        config.headers.Authorization = `Bearer ${token}`
    }
    
    // 从Cookie中获取CSRF令牌
    const csrfToken = getCookie('XSRF-TOKEN')
    
    if (csrfToken) {
        config.headers = config.headers || {}
        config.headers['X-XSRF-TOKEN'] = csrfToken
    }
    
    return config
})

// 响应拦截器：处理401未授权错误
axios.interceptors.response.use(
    (response) => response,
    (error) => {
        if (error.response?.status === 401) {
            removeToken()
            ElMessage.error('登录已过期，请重新登录')
            // 动态导入router避免循环依赖
            import('@/router').then(({ default: router }) => {
                router.push('/')
            })
        }
        return Promise.reject(error)
    }
)

/**
 * 发送POST请求
 * @param {string} url - 请求URL
 * @param {Object} data - 请求数据
 * @param {Function} success - 成功回调函数
 * @param {Function} failure - 失败回调函数
 * @param {Function} error - 错误回调函数
 */
function post(url, data, success, failure = defaultFailure, error = defaultError) {
    axios.post(url, data, {
        headers: {
            'Content-Type': 'application/json'
        }
    }).then(({ data }) => {
        if (data.success) {
            success(data.data)
        } else {
            failure(data.data)
        }
    }).catch(error)
}

/**
 * 上传文件
 * @param {string} url - 上传URL
 * @param {File} file - 要上传的文件
 * @param {Function} success - 成功回调函数
 * @param {Function} failure - 失败回调函数
 * @param {Function} error - 错误回调函数
 */
function postFile(url, file, success, failure = defaultFailure, error = defaultError) {
    const formData = new FormData()
    formData.append('file', file)

    axios.post(url, formData, {
        headers: {
            'Content-Type': 'multipart/form-data'
        }
    }).then(({ data }) => {
        if (data.success) {
            success(data.data)
        } else {
            failure(data.data)
        }
    }).catch(error)
}

/**
 * 下载文件
 * @param {string} url - 下载URL
 */
function downloadFile(url) {
    axios.post(url, null, {
        responseType: 'blob' // 设置响应类型为blob，用于处理二进制文件
    }).then((response) => {
        // 创建Blob对象
        const blob = new Blob([response.data])
        const downloadUrl = window.URL.createObjectURL(blob)
        
        // 创建下载链接并触发下载
        const link = document.createElement('a')
        link.href = downloadUrl
        link.setAttribute('download', 'resnet18_parameters.txt') // 默认文件名
        document.body.appendChild(link)
        link.click()
        document.body.removeChild(link)
        
        // 清理URL对象
        window.URL.revokeObjectURL(downloadUrl)
    }).catch((error) => {
        ElMessage.warning('文件下载失败：' + error.message)
    })
}

export { post, postFile, downloadFile, setToken, removeToken, getToken }

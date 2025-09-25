
/**
 * 应用程序入口文件
 * 负责初始化Vue应用、配置插件和挂载应用
 */

import { createApp } from 'vue'
import { createPinia } from 'pinia'
import axios from "axios"

// 导入应用组件和路由
import App from './App.vue'
import router from './router'

// 导入UI组件库
import ElementPlus from 'element-plus' // Vue3专用版本的Element Plus组件库
import 'element-plus/dist/index.css'

// 导入全局样式
import './styles/animations.css'

// 创建Vue应用实例
const app = createApp(App)

// 配置axios默认基础URL
// 开发环境使用本地服务器http://localhost:8082，生产环境使用远程服务器https://api.sinkshark.site
axios.defaults.baseURL = 'http://localhost:8082' 

// 注册插件
app.use(createPinia()) // 状态管理
app.use(router)        // 路由管理
app.use(ElementPlus)   // UI组件库

// 挂载应用到DOM
app.mount('#app')

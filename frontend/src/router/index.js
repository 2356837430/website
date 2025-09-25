/**
 * 路由配置文件
 * 定义应用程序的所有路由规则和导航守卫
 */

import { createRouter, createWebHistory } from 'vue-router'
import { ElMessage } from 'element-plus'
import { getToken } from '@/net'

// 创建路由实例
const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    // 欢迎页面路由组 - 包含登录和注册功能
    {
      path: '/',
      name: 'welcome',
      component: () => import('@/views/WelcomeView.vue'),
      children: [
        {
          path: '',
          name: 'welcome-login',
          component: () => import('@/components/welcome/LoginPage.vue')
        },
        {
          path: 'register',
          name: 'welcome-register',
          component: () => import('@/components/welcome/RegisterPage.vue')
        }
      ]
    },
    // 主页面路由
    {
      path: '/main',
      name: 'main',
      component: () => import('@/views/MainPage.vue'),
    },
    // 内容页面路由组 - 关于安全调查的详细信息页面
    {
      path: '/page1',
      name: 'page1',
      component: () => import('@/components/page/Page1.vue'),
    },
    {
      path: '/page2',
      name: 'page2',
      component: () => import('@/components/page/Page2.vue'),
    },
    {
      path: '/page3',
      name: 'page3',
      component: () => import('@/components/page/Page3.vue'),
    }
  ]
})

// 全局前置守卫 - 实现登录状态检查
router.beforeEach((to, from, next) => {
  // 获取JWT令牌
  const token = getToken()
  
  // 检查用户是否已登录或访问的是公开页面
  const isLoggedIn = !!token
  const isPublicPage = to.name === 'welcome-login' || to.name === 'welcome-register'

  if (isLoggedIn || isPublicPage) {
    // 已登录或访问公开页面，允许访问
    next()
  } else {
    // 未登录且访问受保护页面，重定向到登录页
    ElMessage({
      message: '请先登录！',
      type: 'warning',
    })
    next({ name: 'welcome-login' })
  }
})

export default router

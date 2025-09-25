<script setup>
/**
 * 欢迎页面组件
 * 作为登录和注册页面的容器，负责获取CSRF令牌
 */

import { onMounted } from 'vue'
import axios from 'axios'

// 组件挂载时获取CSRF令牌
onMounted(async () => {
    try {
        // 调用公开接口获取CSRF令牌，服务器会自动下发XSRF-TOKEN Cookie
        await axios.get('/public/csrf', { withCredentials: true })
    } catch (error) {
        console.warn('获取CSRF令牌失败:', error)
        // 获取令牌失败不影响页面显示，只是后续POST请求可能会被CSRF拦截
    }
})
</script>

<template>
    <div class="welcome-container">
        <!-- 全屏背景层 -->
        <div class="background-full"></div>

        <!-- 内容层：占满全屏，右侧放表单面板 -->
        <div class="content-layer">
            <div class="form-section">
                <router-view v-slot="{ Component }">
                    <transition name="el-fade-in-linear" mode="out-in">
                        <component :is="Component" />
                    </transition>
                </router-view>
            </div>
        </div>
    </div>
</template>

<style scoped>
/* 主容器：占满整个视口，隐藏溢出内容，相对定位 */
.welcome-container {
    width: 100vw;
    height: 100vh;
    overflow: hidden;
    position: relative;
}

/* 全屏背景层：固定定位覆盖整个窗口，使用背景图片 */
.background-full {
    position: fixed;
    inset: 0; /* 等同于 top:0, right:0, bottom:0, left:0 */
    background: url('@/img/home_page.jpg') center/cover no-repeat;
    opacity: 0.85; /* 背景透明度 */
    z-index: 0; /* 最底层 */
}

/* 背景色彩滤镜：在背景图片上添加渐变遮罩，柔化效果 */
.background-full::before {
    content: '';
    position: absolute;
    inset: 0;
    background: linear-gradient(45deg, rgba(255, 182, 193, 0.22), rgba(255, 165, 0, 0.18));
}

/* 内容层：绝对定位占满容器，右侧对齐，用于放置表单 */
.content-layer {
    position: absolute;
    inset: 0;
    display: flex;
    justify-content: flex-end; /* 右侧对齐 */
    align-items: center;
    padding: 0 40px;
    z-index: 1; /* 在背景之上 */
}

/* 表单面板：右侧白色半透明卡片 */
.form-section {
    width: 400px;
    background: rgba(255, 255, 255, 0.96); /* 半透明白色背景 */
    backdrop-filter: blur(10px); /* 背景模糊效果 */
    box-shadow: 0 10px 30px rgba(0, 0, 0, 0.12); /* 阴影效果 */
    border-radius: 8px; /* 圆角 */
    display: flex;
    align-items: center;
    justify-content: center;
    padding: 40px 32px;
}

/* 响应式设计：移动端适配 */
@media (max-width: 768px) {
    .main-title { 
        font-size: 24px; /* 移动端字体缩小 */
        top: 20px; 
    }
    .content-layer { 
        padding: 0 16px; /* 移动端减少内边距 */
    }
    .form-section { 
        width: 100%; /* 移动端占满宽度 */
        padding: 24px 18px; 
    }
}
</style>
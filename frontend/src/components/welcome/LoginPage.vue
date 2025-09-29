<template>
    <div class="login-container">
        <div class="form-header">
            <h2 class="form-title">用户登陆</h2>
        </div>
        
        <div class="form-content">
            <div class="input-group">
                <label class="input-label">账号(邮箱或用户名):</label>
                <el-input 
                    v-model="form.username" 
                    type="text" 
                    placeholder="请输入账号"
                    class="custom-input"
                >
                    <template #prefix>
                        <el-icon><User /></el-icon>
                    </template>
                </el-input>
            </div>
            
            <div class="input-group">
                <label class="input-label">密码:</label>
                <el-input 
                    v-model="form.password" 
                    type="password" 
                    placeholder="请输入密码"
                    class="custom-input"
                >
                    <template #prefix>
                        <el-icon><Lock /></el-icon>
                    </template>
                </el-input>
            </div>
            
            <div class="button-group">
                <el-button 
                    @click="login()" 
                    type="primary" 
                    class="login-button"
                >
                    登陆账号
                </el-button>
            </div>
            
            <div class="register-link">
                <span>没有账号? </span>
                <el-link 
                    @click="router.push('register')" 
                    type="primary"
                    class="link-text"
                >
                    马上注册
                </el-link>
            </div>
        </div>
    </div>
</template>

<script setup>
/**
 * 登录页面组件
 * 提供用户登录功能，包括表单验证和登录状态管理
 */

import { Lock, User } from "@element-plus/icons-vue"
import { reactive } from "vue"
import { ElMessage } from "element-plus"
import router from "@/router"
import { post, setToken } from "@/net"

// 登录表单数据
const form = reactive({
    username: '',
    password: ''
})

/**
 * 处理用户登录
 * 验证表单数据并发送登录请求
 */
const login = () => {  
    // 表单验证
    if (!form.username || !form.password) { 
        ElMessage.warning('请填写用户名和密码！')
        return
    }
    
    // 发送登录请求
    post('/api/users/login', {
        username: form.username,
        password: form.password
    }, (data) => {
        // 登录成功处理
        ElMessage.success('登录成功')
        
        // 保存JWT令牌和用户信息
        setToken(data.token)
        localStorage.setItem('username', data.username)
        
        // 跳转到主页面
        router.push('/main')
    })
}
</script>

<style scoped>
.login-container {
    width: 100%;
    max-width: 350px;
    text-align: center;
}

.form-header {
    margin-bottom: 30px;
}

.form-title {
    font-size: 24px;
    font-weight: bold;
    color: #8B4513;
    margin: 0;
}

.form-content {
    text-align: left;
}

.input-group {
    margin-bottom: 20px;
}

.input-label {
    display: block;
    font-size: 14px;
    color: #8B4513;
    margin-bottom: 8px;
    font-weight: 500;
}

.custom-input {
    width: 100%;
}

.custom-input :deep(.el-input__wrapper) {
    background: transparent;
    border: none;
    border-bottom: 2px solid #e0e0e0;
    border-radius: 0;
    box-shadow: none;
    padding: 12px 0;
}

.custom-input :deep(.el-input__wrapper:hover) {
    border-bottom-color: #409EFF;
}

.custom-input :deep(.el-input__wrapper.is-focus) {
    border-bottom-color: #409EFF;
    box-shadow: none;
}

.custom-input :deep(.el-input__inner) {
    color: #333;
    font-size: 16px;
}

.custom-input :deep(.el-input__prefix) {
    color: #8B4513;
    font-size: 18px;
}

.button-group {
    margin-top: 30px;
    text-align: center;
}

.login-button {
    width: 100%;
    height: 45px;
    background: #8B4513;
    border: none;
    border-radius: 6px;
    font-size: 16px;
    font-weight: 500;
    color: white;
}

.login-button:hover {
    background: #A0522D;
}

.register-link {
    margin-top: 20px;
    text-align: center;
    font-size: 14px;
    color: #8B4513;
}

.link-text {
    font-weight: 500;
    text-decoration: none;
}

.link-text:hover {
    text-decoration: underline;
}

/* 移动端适配 */
@media (max-width: 768px) {
    .login-container {
        max-width: 100%;
        padding: 0 10px;
    }
    
    .form-title {
        font-size: 22px;
    }
    
    .input-label {
        font-size: 15px;
        margin-bottom: 6px;
    }
    
    .custom-input :deep(.el-input__inner) {
        font-size: 16px; /* 防止iOS自动缩放 */
    }
    
    .custom-input :deep(.el-input__wrapper) {
        padding: 14px 0; /* 增加触摸区域 */
    }
    
    .login-button {
        height: 48px; /* 增加按钮高度，便于触摸 */
        font-size: 17px;
        border-radius: 8px;
        margin-top: 10px;
    }
    
    .register-link {
        margin-top: 25px;
        font-size: 15px;
    }
    
    .form-header {
        margin-bottom: 25px;
    }
    
    .input-group {
        margin-bottom: 22px;
    }
    
    .button-group {
        margin-top: 35px;
    }
}

/* 小屏幕手机适配 */
@media (max-width: 480px) {
    .login-container {
        padding: 0 5px;
    }
    
    .form-title {
        font-size: 20px;
    }
    
    .custom-input :deep(.el-input__wrapper) {
        padding: 16px 0; /* 进一步增加触摸区域 */
    }
    
    .login-button {
        height: 50px;
        font-size: 18px;
    }
}
</style>

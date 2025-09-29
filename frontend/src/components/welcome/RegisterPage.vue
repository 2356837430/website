<template>
    <div class="register-container">
        <div class="form-header">
            <h2 class="form-title">用户注册</h2>
        </div>
        
        <div class="form-content">
            <el-form :model="form" :rules="rules" @validate="onValidate" ref="formRef">
                <div class="input-group">
                    <el-form-item prop="username">
                        <label class="input-label">
                            账号(邮箱或用户名):
                            <el-input 
                                v-model="form.username" 
                                :maxlength="8" 
                                type="text" 
                                placeholder="请输入账号"
                                class="custom-input"
                            >
                                <template #prefix>
                                    <el-icon><User /></el-icon>
                                </template>
                            </el-input>
                        </label>
                    </el-form-item>
                </div>
                
                <div class="input-group">
                    <el-form-item prop="password">
                        <label class="input-label">
                            密码:
                            <el-input 
                                v-model="form.password" 
                                :maxlength="16" 
                                type="password" 
                                placeholder="请输入密码"
                                class="custom-input"
                            >
                                <template #prefix>
                                    <el-icon><Lock /></el-icon>
                                </template>
                            </el-input>
                        </label>
                    </el-form-item>
                </div>
                
                <div class="input-group">
                    <el-form-item prop="password_repeat">
                        <label class="input-label">
                            确认密码:
                            <el-input 
                                v-model="form.password_repeat" 
                                :maxLength="16" 
                                type="password" 
                                placeholder="请再次输入密码"
                                class="custom-input"
                            >
                                <template #prefix>
                                    <el-icon><Lock /></el-icon>
                                </template>
                            </el-input>
                        </label>
                    </el-form-item>
                </div>
                
                <div class="input-group">
                    <el-form-item prop="captchaCode">
                        <label class="input-label">
                            验证码:
                            <div class="captcha-container">
                                <el-input 
                                    v-model="form.captchaCode" 
                                    placeholder="请输入验证码"
                                    class="captcha-input"
                                    maxlength="4"
                                >
                                    <template #prefix>
                                        <el-icon><Key /></el-icon>
                                    </template>
                                </el-input>
                                <div class="captcha-image-container">
                                    <img 
                                        v-if="captchaImage" 
                                        :src="captchaImage" 
                                        @click="refreshCaptcha"
                                        class="captcha-image"
                                        alt="验证码"
                                        title="点击刷新验证码"
                                    />
                                    <el-button 
                                        v-else
                                        @click="refreshCaptcha"
                                        type="primary"
                                        size="small"
                                    >
                                        获取验证码
                                    </el-button>
                                </div>
                            </div>
                        </label>
                    </el-form-item>
                </div>
            </el-form>
            
            <div class="button-group">
                <el-button 
                    @click="register" 
                    type="primary" 
                    class="register-button"
                >
                    注册账号
                </el-button>
            </div>
            
            <div class="login-link">
                <span>已有账号? </span>
                <el-link 
                    @click="router.push('/')" 
                    type="primary"
                    class="link-text"
                >
                    返回登录
                </el-link>
            </div>
        </div>
    </div>
</template>

<script setup>
/**
 * 注册页面组件
 * 提供用户注册功能，包括表单验证、验证码验证和注册状态管理
 */

import router from "@/router"
import { Lock, User, Key } from "@element-plus/icons-vue"
import { reactive, ref, onMounted } from "vue"
import { post } from "@/net"
import { ElMessage } from "element-plus"

// 注册表单数据
const form = reactive({
    username: '',
    password: '',
    password_repeat: '',
    captchaCode: '',
})

// 验证码相关状态
const captchaImage = ref('')
const captchaId = ref('')

// 表单引用
const formRef = ref()

/**
 * 密码确认验证器
 * 确保两次输入的密码一致
 */
const validatePassword = (rule, value, callback) => {
    if (value === '') {
        callback(new Error('请再次输入密码'))
    } else if (value !== form.password) {
        callback(new Error("两次输入的密码不一致"))
    } else {
        callback()
    }
}

// 表单验证规则
const rules = {
    password_repeat: [
        { validator: validatePassword, trigger: ['blur','change'] },
    ],
}

/**
 * 表单验证回调（当前未使用）
 */
const onValidate = (prop, isValid) => {}

/**
 * 获取新的验证码
 * 从服务器获取验证码图片和ID
 */
const refreshCaptcha = () => {
    post('/api/captcha/generate', {}, (data) => {
        captchaImage.value = data.captchaImage
        captchaId.value = data.captchaId
    }, (error) => {
        ElMessage.error('获取验证码失败：' + error)
    })
}

/**
 * 处理用户注册
 * 验证表单数据、验证码并发送注册请求
 */
const register = () => {
    formRef.value.validate((isValid) => {
        if (isValid) {
            // 验证码检查
            if (!captchaId.value || !form.captchaCode) {
                ElMessage.warning('请先获取并输入验证码')
                return
            }
            
            // 发送注册请求
            post('/api/users/register', {
                username: form.username,
                password: form.password,
                captchaId: captchaId.value,
                captchaCode: form.captchaCode,
            }, (message) => {
                // 注册成功处理
                ElMessage.success(message)
                router.push("/")
            }, (error) => {
                // 显示错误信息
                ElMessage.error(error)
                
                // 如果验证码错误，自动刷新验证码
                if (error.includes('验证码')) {
                    refreshCaptcha()
                }
            })
        } else {
            ElMessage.warning('请完整填写上述表单注册内容')
        }
    })
}

// 组件挂载时自动获取验证码
onMounted(() => {
    refreshCaptcha()
})
</script>

<style scoped>
.register-container {
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

/* 验证码容器样式 */
.captcha-container {
    display: flex;
    gap: 10px;
    align-items: center;
}

.captcha-input {
    flex: 1;
}

.captcha-image-container {
    flex-shrink: 0;
}

.captcha-image {
    width: 120px;
    height: 40px;
    border: 1px solid #e0e0e0;
    border-radius: 4px;
    cursor: pointer;
    transition: border-color 0.3s;
}

.captcha-image:hover {
    border-color: #409EFF;
}

/* 移除表单项的默认样式 */
:deep(.el-form-item) {
    margin-bottom: 0;
}

:deep(.el-form-item__error) {
    color: #f56c6c;
    font-size: 12px;
    margin-top: 4px;
}

.button-group {
    margin-top: 30px;
    text-align: center;
}

.register-button {
    width: 100%;
    height: 45px;
    background: #8B4513;
    border: none;
    border-radius: 6px;
    font-size: 16px;
    font-weight: 500;
    color: white;
}

.register-button:hover {
    background: #A0522D;
}

.login-link {
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
    .register-container {
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
    
    .custom-input :deep(.el-input__inner),
    .captcha-input :deep(.el-input__inner) {
        font-size: 16px; /* 防止iOS自动缩放 */
    }
    
    .custom-input :deep(.el-input__wrapper),
    .captcha-input :deep(.el-input__wrapper) {
        padding: 14px 0; /* 增加触摸区域 */
    }
    
    .register-button {
        height: 48px; /* 增加按钮高度，便于触摸 */
        font-size: 17px;
        border-radius: 8px;
        margin-top: 10px;
    }
    
    .login-link {
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
    
    /* 验证码在移动端的优化 */
    .captcha-container {
        flex-direction: column;
        gap: 12px;
        align-items: stretch;
    }
    
    .captcha-input {
        flex: none;
        width: 100%;
    }
    
    .captcha-image-container {
        display: flex;
        justify-content: center;
        flex-shrink: 0;
    }
    
    .captcha-image {
        width: 140px;
        height: 45px;
        touch-action: manipulation; /* 优化触摸体验 */
    }
}

/* 小屏幕手机适配 */
@media (max-width: 480px) {
    .register-container {
        padding: 0 5px;
    }
    
    .form-title {
        font-size: 20px;
    }
    
    .custom-input :deep(.el-input__wrapper),
    .captcha-input :deep(.el-input__wrapper) {
        padding: 16px 0; /* 进一步增加触摸区域 */
    }
    
    .register-button {
        height: 50px;
        font-size: 18px;
    }
    
    .captcha-image {
        width: 160px;
        height: 50px;
    }
}
</style>

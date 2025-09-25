# 加拿大学签安全调查信息平台

一个基于Spring Boot + Vue 3的全栈Web应用，专门为关注加拿大学签安全调查的用户提供详细信息和指导。

## 项目概述

本平台汇集了关于加拿大学签安全调查的官方信息和实践经验，提供用户注册登录、信息浏览等功能，帮助用户了解：

- 安全调查的定义和触发因素
- 调查流程和时间线
- 强制令的作用和适用条件
- 相关数据分析和建议

## 技术架构

### 后端技术栈
- **框架**: Spring Boot 3.2.4
- **Java版本**: JDK 21
- **数据库**: MySQL 8.0
- **ORM**: MyBatis + Spring Data JPA
- **缓存**: Redis
- **安全框架**: Spring Security + JWT（jjwt）
- **构建工具**: Maven
- **其他组件**:
  - Kaptcha (验证码生成)
  - Lombok (代码简化)

### 前端技术栈
- **框架**: Vue 3 (Composition API)
- **构建工具**: Vite 4.3.4
- **UI组件库**: Element Plus
- **状态管理**: Pinia
- **路由管理**: Vue Router 4
- **HTTP客户端**: Axios
- **样式**: CSS3 + 自定义动画

## 项目结构

```
website/
├── backend/                    # Spring Boot后端
│   ├── src/main/java/
│   │   └── org/example/backend/
│   │       ├── BackendApplication.java
│   │       ├── config/         # 配置类
│   │       │   ├── SecurityConfiguration.java
│   │       │   └── RedisConfig.java
│   │       ├── controller/     # 控制器层
│   │       │   ├── UserController.java
│   │       │   ├── CaptchaController.java
│   │       │   ├── FileController.java
│   │       │   └── CsrfController.java
│   │       ├── service/        # 服务层
│   │       │   ├── UserService.java
│   │       │   ├── CaptchaService.java
│   │       │   └── RateLimitService.java
│   │       ├── entity/         # 实体类
│   │       │   ├── Account.java
│   │       │   ├── MyData.java
│   │       │   └── RestBean.java
│   │       └── mapper/         # 数据访问层
│   │           └── UserMapper.java
│   ├── src/main/resources/
│   │   ├── application.properties
│   │   └── db/create.sql
│   └── pom.xml
├── frontend/                   # Vue 3前端
│   ├── src/
│   │   ├── components/         # 组件目录
│   │   │   ├── page/          # 内容页面组件
│   │   │   └── welcome/       # 登录注册组件
│   │   ├── views/             # 页面视图
│   │   ├── router/            # 路由配置
│   │   ├── net/               # 网络请求模块
│   │   ├── stores/            # 状态管理
│   │   ├── composables/       # 组合式函数
│   │   ├── styles/            # 全局样式
│   │   └── img/               # 图片资源
│   ├── package.json
│   └── vite.config.js
└── README.md                   # 项目说明文档
```

## 核心功能

### 🔐 用户认证系统
- 用户注册和登录
- 图形验证码验证
- CSRF防护机制（CookieCsrfTokenRepository + X-XSRF-TOKEN）
- JWT 无状态认证（后端验证 Authorization: Bearer <token>）
- 频率限制保护

### 🛡️ 安全特性
- Spring Security安全框架
- CORS跨域配置（允许凭证、按域白名单）
- CSRF令牌防护（前后端双 Cookie + 请求头）
- JWT 令牌认证（无状态，不依赖服务端 Session）
- 请求频率限制（基于 Redis）

### 📱 前端特性
- 响应式设计，支持移动端
- 现代化UI界面
- 流畅的页面切换动画
- Element Plus组件库
- 错误处理和加载状态

### 📄 信息展示页面
- 主页面：安全调查信息概览
- 详情页面1：为什么学签会进入安调
- 详情页面2：安全调查的具体流程
- 详情页面3：强制令可以结束安调吗

## 环境要求

### 后端环境
- JDK 21+
- Maven 3.6+
- MySQL 8.0+
- Redis 6.0+

### 前端环境
- Node.js 16.0+
- npm 8.0+

## 快速开始

### 1. 数据库配置

```sql
-- 创建数据库
CREATE DATABASE backend CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- 导入表结构
USE backend;
SOURCE backend/src/main/resources/db/create.sql;
```

### 2. 后端启动

```bash
# 进入后端目录
cd backend

# 修改application.properties中的数据库配置
# spring.datasource.url=jdbc:mysql://localhost:3306/backend
# spring.datasource.username=your_username
# spring.datasource.password=your_password

# 启动Redis服务
redis-server

# 编译并启动后端服务
./mvnw spring-boot:run
```

后端服务将在 `http://localhost:8082` 启动

### 3. 前端启动

```bash
# 进入前端目录
cd frontend

# 安装依赖
npm install

# 启动开发服务器
npm run dev
```

前端应用将在 `http://localhost:8080` 启动

## API接口文档

### 用户认证接口

#### 用户注册
- **URL**: `POST /api/users/register`
- **参数**: `{username, password, captchaId, captchaCode}`
- **返回**: 注册结果（`RestBean<String>`）

#### 用户登录（返回 JWT）
- **URL**: `POST /api/users/login`
- **参数**: `{username, password}`
- **返回**: `{token, username}`（`RestBean<Map<String,String>>`）

#### 获取当前用户信息（需携带JWT）
- **URL**: `GET /api/users/profile`
- **请求头**: `Authorization: Bearer <token>`
- **返回**: `{username}`（`RestBean<Map<String,String>>`）

#### 登出（可选，清理前端令牌即可）
- **URL**: `POST /api/users/logout`
- **说明**: JWT 无状态，登出通常在前端移除令牌实现

### 验证码接口

#### 生成验证码
- **URL**: `POST /api/captcha/generate`
- **返回**: `{captchaId, captchaImage}`

### 安全接口

#### 获取CSRF令牌
- **URL**: `GET /public/csrf`
- **返回**: CSRF令牌信息

### 文件操作接口

#### 文件上传
- **URL**: `POST /api/upload`
- **参数**: `multipart/form-data`

#### 文件下载
- **URL**: `POST /api/download`

## 配置说明

### 后端配置 (application.properties)

```properties
# 服务器端口
server.port=8082

# 数据库配置
spring.datasource.url=jdbc:mysql://localhost:3306/backend
spring.datasource.username=root
spring.datasource.password=your_password

# Redis配置
spring.data.redis.host=localhost
spring.data.redis.port=6379

# CORS配置
cors.allowed-origins=http://localhost:8080,https://sinkshark.site

# JWT配置
jwt.secret=请使用Base64编码的密钥
jwt.expiration=86400000
```

### 前端配置

在 `src/main.js` 中配置后端API地址：

```javascript
// 开发环境
axios.defaults.baseURL = 'http://localhost:8082'

// 生产环境
axios.defaults.baseURL = 'https://api.sinkshark.site'
```

在 `src/net/index.js` 中：
- 请求拦截器自动注入 `Authorization: Bearer <token>`（从 `localStorage` 读取）
- 从 Cookie 读取 `XSRF-TOKEN` 并写入 `X-XSRF-TOKEN` 请求头
- 响应拦截 401 时清理本地令牌并重定向登录

## 技术规划补充

项目已完成 JWT 无状态认证接入。后续可选增强：
- Access Token + Refresh Token 双令牌机制
- 令牌黑名单（基于 Redis）
- 强制 HTTPS 与安全头配置（生产环境）
- 更细粒度的权限与角色控制

## 部署说明

### 后端部署

```bash
# 打包应用
./mvnw clean package

# 运行jar包
java -jar target/backend-0.0.1-SNAPSHOT.jar
```

### 前端部署

```bash
# 构建生产版本
npm run build

# 部署dist目录到Web服务器
```

## 开发指南

### 后端开发
- 遵循Spring Boot最佳实践
- 使用MyBatis进行数据库操作
- 实现RESTful API设计
- 添加适当的异常处理和日志

### 前端开发
- 使用Vue 3 Composition API
- 组件化开发思想
- 响应式设计原则
- 统一的代码风格和注释

### 添加新功能
1. 后端：创建Controller → Service → Mapper
2. 前端：创建组件 → 配置路由 → 添加API调用
3. 测试功能完整性

## 安全注意事项

- 数据库密码不要提交到版本控制
- 生产环境使用HTTPS
- 定期更新依赖包版本
- 监控系统日志和异常

---

**注意**: 本平台提供的信息仅供参考，不构成法律建议。具体申请情况请咨询专业移民律师。

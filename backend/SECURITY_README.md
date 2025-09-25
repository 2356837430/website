# 防恶意注册系统 - Redis管理命令

## 限流逻辑说明
- **同一IP在10分钟内最多注册3次**（无论成功还是失败都计入）
- **超过3次自动封禁IP 720小时（30天）**
- **注册成功后不会重置计数器**

## Redis管理命令

### 启动 Redis（已配置全局 PATH）
# 启动服务端（Redis 服务器）
redis-server

# 启动客户端（连接到本地 6379）
redis-cli -h 127.0.0.1 -p 6379

### 查看黑名单
# 查看所有黑名单IP
redis-cli KEYS "ip_blacklist:*"

# 查看特定IP是否在黑名单中
redis-cli GET "ip_blacklist:0:0:0:0:0:0:0:1"

# 查看IP的过期时间
redis-cli TTL "ip_blacklist:0:0:0:0:0:0:0:1"

### 管理黑名单
# 手动添加IP到黑名单（24小时）
redis-cli SETEX "ip_blacklist:0:0:0:0:0:0:0:1" 86400 "1"

# 删除黑名单IP
redis-cli DEL "ip_blacklist:0:0:0:0:0:0:0:1"

### 查看限流状态
# 查看所有限流IP
redis-cli KEYS "register_limit:*"

# 查看特定IP的注册次数
redis-cli GET "register_limit:0:0:0:0:0:0:0:1"

# 查看限流剩余时间
redis-cli TTL "register_limit:0:0:0:0:0:0:0:1"

### 重置IP限制
# 重置特定IP的限流
redis-cli DEL "register_limit:0:0:0:0:0:0:0:1"

# 完全重置IP（删除限流和黑名单）
redis-cli DEL "register_limit:0:0:0:0:0:0:0:1" "ip_blacklist:0:0:0:0:0:0:0:1"

### 一键清空所有 IP（限流 + 黑名单）
redis-cli EVAL "local c=0; for _,k in ipairs(redis.call('keys','register_limit:*')) do c=c+redis.call('del',k) end; for _,k in ipairs(redis.call('keys','ip_blacklist:*')) do c=c+redis.call('del',k) end; return c" 0
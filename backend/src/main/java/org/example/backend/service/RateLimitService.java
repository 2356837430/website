package org.example.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

/**
 * 限流服务
 * 用于防止恶意注册和频繁请求
 */
@Service
public class RateLimitService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    // 注册限流配置
    private static final String REGISTER_RATE_LIMIT_PREFIX = "register_limit:";
    private static final int REGISTER_MAX_ATTEMPTS = 3; // 最大尝试次数
    private static final Duration REGISTER_TIME_WINDOW = Duration.ofMinutes(10); // 时间窗口

    // IP黑名单配置
    private static final String IP_BLACKLIST_PREFIX = "ip_blacklist:";
    private static final Duration IP_BLACKLIST_DURATION = Duration.ofHours(720); // IP封禁720小时（30天）

    /**
     * 检查是否允许注册
     * 注意：无论注册成功还是失败，都会计入尝试次数
     * @param clientIp 客户端IP地址
     * @return true表示允许注册，false表示被限流
     */
    public boolean isRegisterAllowed(String clientIp) {
        String key = REGISTER_RATE_LIMIT_PREFIX + clientIp;
        
        // 检查IP是否在黑名单中
        if (isIpBlacklisted(clientIp)) {
            return false;
        }
        
        String attempts = redisTemplate.opsForValue().get(key);
        if (attempts == null) {
            // 第一次尝试
            redisTemplate.opsForValue().set(key, "1", REGISTER_TIME_WINDOW);
            return true;
        }
        
        int currentAttempts = Integer.parseInt(attempts);
        if (currentAttempts >= REGISTER_MAX_ATTEMPTS) {
            // 超过限制，将IP加入黑名单
            addToBlacklist(clientIp);
            return false;
        }
        
        // 增加尝试次数
        redisTemplate.opsForValue().increment(key);
        return true;
    }

    /**
     * 获取剩余尝试次数
     * @param clientIp 客户端IP地址
     * @return 剩余尝试次数
     */
    public int getRemainingAttempts(String clientIp) {
        String key = REGISTER_RATE_LIMIT_PREFIX + clientIp;
        String attempts = redisTemplate.opsForValue().get(key);
        
        if (attempts == null) {
            return REGISTER_MAX_ATTEMPTS;
        }
        
        int currentAttempts = Integer.parseInt(attempts);
        return Math.max(0, REGISTER_MAX_ATTEMPTS - currentAttempts);
    }

    /**
     * 检查IP是否在黑名单中
     * @param clientIp 客户端IP地址
     * @return true表示在黑名单中
     */
    public boolean isIpBlacklisted(String clientIp) {
        String key = IP_BLACKLIST_PREFIX + clientIp;
        return redisTemplate.hasKey(key);
    }

    /**
     * 将IP添加到黑名单
     * @param clientIp 客户端IP地址
     */
    public void addToBlacklist(String clientIp) {
        String key = IP_BLACKLIST_PREFIX + clientIp;
        redisTemplate.opsForValue().set(key, "1", IP_BLACKLIST_DURATION);
    }

    /**
     * 从黑名单中移除IP
     * @param clientIp 客户端IP地址
     */
    public void removeFromBlacklist(String clientIp) {
        String key = IP_BLACKLIST_PREFIX + clientIp;
        redisTemplate.delete(key);
    }

    /**
     * 重置注册限流计数器
     * @param clientIp 客户端IP地址
     */
    public void resetRegisterLimit(String clientIp) {
        String key = REGISTER_RATE_LIMIT_PREFIX + clientIp;
        redisTemplate.delete(key);
    }
}

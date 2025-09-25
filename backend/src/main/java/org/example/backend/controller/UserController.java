package org.example.backend.controller;

import org.example.backend.entity.Account;
import org.example.backend.entity.MyData;
import org.example.backend.entity.RestBean;
import org.example.backend.service.UserService;
import org.example.backend.service.RateLimitService;
import org.example.backend.service.CaptchaService;
import org.example.backend.util.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RateLimitService rateLimitService;

    @Autowired
    private CaptchaService captchaService;
    
    @Autowired
    private JwtTokenProvider tokenProvider;

    @PostMapping("/register")
    public RestBean<String> registerUser(@RequestBody MyData request, HttpServletRequest httpRequest) {
        String username = request.getUsername();
        String password = request.getPassword();
        String captchaId = request.getCaptchaId();
        String captchaCode = request.getCaptchaCode();

        // 获取客户端IP地址
        String clientIp = getClientIpAddress(httpRequest);

        // 检查限流
        if (!rateLimitService.isRegisterAllowed(clientIp)) {
            int remainingAttempts = rateLimitService.getRemainingAttempts(clientIp);
            if (remainingAttempts == 0) {
                return RestBean.failure(HttpStatus.TOO_MANY_REQUESTS.value(), 
                    "注册尝试次数过多，IP已被暂时封禁，请720小时后再试");
            }
            return RestBean.failure(HttpStatus.TOO_MANY_REQUESTS.value(), 
                "注册尝试次数过多，剩余尝试次数：" + remainingAttempts);
        }

        // 验证验证码
        if (captchaId == null || captchaCode == null || !captchaService.validateCaptcha(captchaId, captchaCode)) {
            return RestBean.failure(HttpStatus.BAD_REQUEST.value(), "验证码错误或已过期");
        }

        // 检查用户名是否已存在
        Account existingAccount = userService.findAccountByName(username);
        if (existingAccount != null) {
            return RestBean.failure(HttpStatus.CONFLICT.value(), "用户名已存在");
        }

        // 创建账户
        userService.createAccount(username, password);
        
        return RestBean.success("注册成功");
    }

    @PostMapping("/login")
    public RestBean<?> loginUser(@RequestBody MyData request) {
        String username = request.getUsername();
        String password = request.getPassword();

        boolean loginResult = userService.login(username, password);
        if (!loginResult) {
            return RestBean.failure(HttpStatus.UNAUTHORIZED.value(), "用户名或密码错误");
        }

        // 生成JWT令牌
        String token = tokenProvider.generateToken(username);
        
        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        response.put("username", username);
        
        return RestBean.success(response);
    }
    
    /**
     * 获取当前用户信息
     * @param authentication 认证信息
     * @return 用户信息
     */
    @GetMapping("/profile")
    public RestBean<Map<String, String>> getUserProfile(Authentication authentication) {
        String username = authentication.getName();
        Map<String, String> profile = new HashMap<>();
        profile.put("username", username);
        return RestBean.success(profile);
    }
    
    /**
     * 用户登出
     * @return 登出结果
     */
    @PostMapping("/logout")
    public RestBean<String> logout() {
        return RestBean.success("登出成功");
    }

    /**
     * 获取客户端真实IP地址
     * @param request HTTP请求对象
     * @return 客户端IP地址
     */
    private String getClientIpAddress(HttpServletRequest request) {
        String xForwardedFor = request.getHeader("X-Forwarded-For");
        if (xForwardedFor != null && !xForwardedFor.isEmpty() && !"unknown".equalsIgnoreCase(xForwardedFor)) {
            // 多次反向代理后会有多个IP值，第一个为真实IP
            return xForwardedFor.split(",")[0].trim();
        }
        
        String xRealIp = request.getHeader("X-Real-IP");
        if (xRealIp != null && !xRealIp.isEmpty() && !"unknown".equalsIgnoreCase(xRealIp)) {
            return xRealIp;
        }
        
        String proxyClientIp = request.getHeader("Proxy-Client-IP");
        if (proxyClientIp != null && !proxyClientIp.isEmpty() && !"unknown".equalsIgnoreCase(proxyClientIp)) {
            return proxyClientIp;
        }
        
        String wlProxyClientIp = request.getHeader("WL-Proxy-Client-IP");
        if (wlProxyClientIp != null && !wlProxyClientIp.isEmpty() && !"unknown".equalsIgnoreCase(wlProxyClientIp)) {
            return wlProxyClientIp;
        }
        
        return request.getRemoteAddr();
    }
}


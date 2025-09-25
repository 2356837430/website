package org.example.backend.controller;

import org.example.backend.entity.RestBean;
import org.example.backend.service.CaptchaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 验证码控制器
 */
@RestController
@RequestMapping("/api/captcha")
public class CaptchaController {

    @Autowired
    private CaptchaService captchaService;

    /**
     * 获取验证码
     * @return 验证码ID和Base64图片
     */
    @PostMapping("/generate")
    public RestBean<Map<String, String>> generateCaptcha() {
        try {
            CaptchaService.CaptchaResult result = captchaService.generateCaptcha();
            
            Map<String, String> response = new HashMap<>();
            response.put("captchaId", result.getCaptchaId());
            response.put("captchaImage", result.getBase64Image());
            
            return RestBean.success(response);
        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "生成验证码失败");
            return RestBean.failure(500, errorResponse);
        }
    }
}

package org.example.backend.service;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Base64;
import java.util.Properties;
import java.util.UUID;

/**
 * 验证码服务
 * 用于生成和验证图形验证码
 */
@Service
public class CaptchaService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    private static final String CAPTCHA_PREFIX = "captcha:";
    private static final Duration CAPTCHA_EXPIRE_TIME = Duration.ofMinutes(5); // 验证码5分钟过期

    private DefaultKaptcha kaptcha;

    public CaptchaService() {
        initKaptcha();
    }

    /**
     * 初始化验证码生成器
     */
    private void initKaptcha() {
        kaptcha = new DefaultKaptcha();
        Properties properties = new Properties();
        
        // 验证码配置
        properties.setProperty("kaptcha.border", "yes");
        properties.setProperty("kaptcha.border.color", "105,179,90");
        properties.setProperty("kaptcha.textproducer.font.color", "blue");
        properties.setProperty("kaptcha.image.width", "120");
        properties.setProperty("kaptcha.image.height", "40");
        properties.setProperty("kaptcha.textproducer.font.size", "30");
        properties.setProperty("kaptcha.session.key", "code");
        properties.setProperty("kaptcha.textproducer.char.length", "4");
        properties.setProperty("kaptcha.textproducer.font.names", "宋体,楷体,微软雅黑");
        
        // 设置字符集，避免0、O、I、l等容易混淆的字符
        properties.setProperty("kaptcha.textproducer.char.string", "23456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefghjkmnpqrstuvwxyz");
        
        Config config = new Config(properties);
        kaptcha.setConfig(config);
    }

    /**
     * 生成验证码
     * @return 包含验证码ID和Base64编码图片的Map
     */
    public CaptchaResult generateCaptcha() {
        // 生成验证码文本
        String captchaText = kaptcha.createText();
        
        // 生成验证码图片
        BufferedImage captchaImage = kaptcha.createImage(captchaText);
        
        // 生成唯一ID
        String captchaId = UUID.randomUUID().toString();
        
        // 将验证码文本存储到Redis
        String key = CAPTCHA_PREFIX + captchaId;
        redisTemplate.opsForValue().set(key, captchaText.toLowerCase(), CAPTCHA_EXPIRE_TIME);
        
        // 将图片转换为Base64
        String base64Image = imageToBase64(captchaImage);
        
        return new CaptchaResult(captchaId, base64Image);
    }

    /**
     * 验证验证码
     * @param captchaId 验证码ID
     * @param userInput 用户输入的验证码
     * @return true表示验证通过
     */
    public boolean validateCaptcha(String captchaId, String userInput) {
        if (captchaId == null || userInput == null) {
            return false;
        }
        
        String key = CAPTCHA_PREFIX + captchaId;
        String storedCaptcha = redisTemplate.opsForValue().get(key);
        
        if (storedCaptcha == null) {
            return false; // 验证码已过期或不存在
        }
        
        // 验证码验证成功后删除
        redisTemplate.delete(key);
        
        return storedCaptcha.equals(userInput.toLowerCase().trim());
    }

    /**
     * 将BufferedImage转换为Base64字符串
     * @param image 图片对象
     * @return Base64编码的图片字符串
     */
    private String imageToBase64(BufferedImage image) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(image, "png", baos);
            byte[] imageBytes = baos.toByteArray();
            return "data:image/png;base64," + Base64.getEncoder().encodeToString(imageBytes);
        } catch (IOException e) {
            throw new RuntimeException("生成验证码图片失败", e);
        }
    }

    /**
     * 验证码结果类
     */
    public static class CaptchaResult {
        private String captchaId;
        private String base64Image;

        public CaptchaResult(String captchaId, String base64Image) {
            this.captchaId = captchaId;
            this.base64Image = base64Image;
        }

        public String getCaptchaId() {
            return captchaId;
        }

        public String getBase64Image() {
            return base64Image;
        }
    }
}

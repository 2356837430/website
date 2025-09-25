package org.example.backend.entity;

import lombok.Data;

@Data
public class MyData{
    String username;
    String password;
    String captchaId;    // 验证码ID
    String captchaCode;  // 验证码内容
}

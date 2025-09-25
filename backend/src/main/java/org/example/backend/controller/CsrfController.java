package org.example.backend.controller;

import org.example.backend.entity.RestBean;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/public")
public class CsrfController {

    @GetMapping("/csrf")
    public RestBean<String> getCsrfToken(HttpServletRequest request) {
        // 强制获取 CSRF 令牌，确保 Spring Security 生成并下发 XSRF-TOKEN Cookie
        CsrfToken csrfToken = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
        if (csrfToken == null) {
            csrfToken = (CsrfToken) request.getAttribute("_csrf");
        }
        
        return RestBean.success("CSRF token issued");
    }
}
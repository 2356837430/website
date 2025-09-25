package org.example.backend.config;

import org.example.backend.filter.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.util.StringUtils;
import org.springframework.security.web.csrf.CsrfTokenRequestHandler;
import org.springframework.security.web.csrf.XorCsrfTokenRequestAttributeHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.function.Supplier;
import java.util.Arrays;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

@Configuration
@EnableMethodSecurity
public class SecurityConfiguration {

    @Value("${cors.allowed-origins}")
    private String allowedOriginsConfig;
    
    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // 从配置文件读取允许的域名，用逗号分隔
        List<String> allowedOrigins = Arrays.asList(allowedOriginsConfig.split(","));

        CorsConfiguration cors = new CorsConfiguration();
        cors.setAllowedOrigins(allowedOrigins);
        cors.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "PATCH"));
        cors.setAllowedHeaders(List.of("Content-Type", "Authorization", "X-XSRF-TOKEN"));
        cors.setAllowCredentials(true);
        cors.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource corsSource = new UrlBasedCorsConfigurationSource();
        corsSource.registerCorsConfiguration("/**", cors);

        http
                .cors(c -> c.configurationSource(corsSource))
                // 开启 CSRF 防护，并使用 CookieCsrfTokenRepository 以双 Cookie 模式存储 CSRF 令牌
                // 这样会将 CSRF Token 以名为 XSRF-TOKEN 的 Cookie 下发给前端（非 HttpOnly），
                // 前端可以读取该 Cookie 并在后续请求中通过 X-CSRF-TOKEN 请求头携带令牌，防止 CSRF 攻击
                .csrf(csrf -> csrf
                        .csrfTokenRepository(csrfTokenRepository())
                        .csrfTokenRequestHandler(new SpaCsrfTokenRequestHandler())
                )
                // 配置会话管理为无状态，使用JWT令牌
                .sessionManagement(session -> 
                    session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        // 放行无需认证的端点（此认证是身份登录，认证并非CSRF令牌认证）
                        .requestMatchers("/api/users/login", "/api/users/register", "/public/csrf", "/api/captcha/generate").permitAll()
                        // 其他请求需要认证
                        .anyRequest().authenticated()
                )
                // 添加JWT认证过滤器
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    private CookieCsrfTokenRepository csrfTokenRepository() {
        CookieCsrfTokenRepository repository = CookieCsrfTokenRepository.withHttpOnlyFalse();
        repository.setCookieCustomizer(cookie -> cookie
                .sameSite("None")
                .secure(true)
        );
        return repository;
    }
}

final class SpaCsrfTokenRequestHandler implements CsrfTokenRequestHandler {
	private final CsrfTokenRequestHandler plain = new CsrfTokenRequestAttributeHandler();
	private final CsrfTokenRequestHandler xor = new XorCsrfTokenRequestAttributeHandler();

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response, Supplier<CsrfToken> csrfToken) {
		/*
		 * Always use XorCsrfTokenRequestAttributeHandler to provide BREACH protection of
		 * the CsrfToken when it is rendered in the response body.
		 */
		this.xor.handle(request, response, csrfToken);
		/*
		 * Render the token value to a cookie by causing the deferred token to be loaded.
		 */
		csrfToken.get();
	}

	@Override
	public String resolveCsrfTokenValue(HttpServletRequest request, CsrfToken csrfToken) {
		String headerValue = request.getHeader(csrfToken.getHeaderName());
		/*
		 * If the request contains a request header, use CsrfTokenRequestAttributeHandler
		 * to resolve the CsrfToken. This applies when a single-page application includes
		 * the header value automatically, which was obtained via a cookie containing the
		 * raw CsrfToken.
		 *
		 * In all other cases (e.g. if the request contains a request parameter), use
		 * XorCsrfTokenRequestAttributeHandler to resolve the CsrfToken. This applies
		 * when a server-side rendered form includes the _csrf request parameter as a
		 * hidden input.
		 */
		return (StringUtils.hasText(headerValue) ? this.plain : this.xor).resolveCsrfTokenValue(request, csrfToken);
	}
}

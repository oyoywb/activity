package com.oywb.weixin.activities.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.jose.jws.SignatureAlgorithm;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;
import java.security.KeyPair;
import java.security.interfaces.RSAPublicKey;
import java.util.Map;

@Configuration
@EnableWebSecurity(debug = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

/*    @Bean
    SecurityFilterChain securityFilterChain() throws Exception {
        HttpSecurity http = this.getHttp();
    }*/

    // 当用户尝试访问安全的REST资源而不提供任何凭据时，将调用此方法发送401响应
    private static void authenticationEntryPoint(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        editResponseHeader(response);

        Map<String, Object> err = Map.of("errcode", 1111, "errmsg", "请登录!");
        response.getWriter().write(new ObjectMapper().writeValueAsString(err));
    }

    /**
     * @see AccessDeniedHandlerImpl#handle(HttpServletRequest, HttpServletResponse, AccessDeniedException)
     */
    private static void accessDeniedHandle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
        request.setAttribute(WebAttributes.ACCESS_DENIED_403, accessDeniedException);
        response.setStatus(HttpStatus.FORBIDDEN.value());
        editResponseHeader(response);

        Map<String, Object> err = Map.of("errcode", 2222, "errmsg", "权限不足，请联系管理员!");
        response.getWriter().write(new ObjectMapper().writeValueAsString(err));
    }

    private static void editResponseHeader(HttpServletResponse response) {
        response.setCharacterEncoding(Charset.defaultCharset().displayName());// 解决中文乱码
        response.addHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE);
    }

    /**
     * 定义JwtDecoder
     *
     * <p>如果oauth2ResourceServer使用jwt进行认证时，必须定义一个 JwtDecoder 的 Bean，否则程序启动时会报异常。</p>
     * <p>这里实例化一个{@link NimbusJwtDecoder}，并指定从JKS密钥库文件里读取公钥。</p>
     */
    @Bean
    JwtDecoder jwtDecoderByPublicKeyValue(KeyPair keyPair) {
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        // @formatter:off
        return NimbusJwtDecoder
                .withPublicKey(publicKey)
                .signatureAlgorithm(SignatureAlgorithm.RS256)
                .build();
        // @formatter:on
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.applyPermitDefaultValues();
        // 允许所有http请求方法
        // configuration.setAllowedMethods(Collections.singletonList(CorsConfiguration.ALL));
        configuration.addAllowedMethod(HttpMethod.PUT);// 添加允许PUT方法，默认设置里并没有。
        configuration.addAllowedMethod(HttpMethod.DELETE);// 添加允许DELETE方法，默认设置里并没有。
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        // 可以添加多个路径以支持跨域请求
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/accesstoken")
                .permitAll();

        // 配置跨域策略，会引用 CorsConfigurationSource 的Bean的配置，需要自己定义。
        http.cors();

        // 设置所有http请求都必需认证用户才能访问，必须设置。
        // 可以根据系统需求定制URL的访问权限。
        http.authorizeRequests().antMatchers("/cors","/swagger-ui/**", "/v3/api-docs/**","/user/**", "/swagger-resources/**", "**"
                        ).permitAll()
                .anyRequest().authenticated();

        // 默认开启了 http.csrf()，但 jwt() 后，会忽略header含有Bearer token的请求。
        http.oauth2ResourceServer().jwt();

        // 设置不创建 HttpSession
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // 设置请求api时没有token，或权限不足时的处理响应的逻辑。
        // BearerTokenAuthenticationFilter 当发现请求没有 Authorization: Bearer 的头部时，就会调用 AuthenticationEntryPoint 处理响应。
        // 详细看 BearerTokenAuthenticationFilter的doFilterInternal方法源码。
        http.exceptionHandling()
                .authenticationEntryPoint(SecurityConfig::authenticationEntryPoint)
                .accessDeniedHandler(SecurityConfig::accessDeniedHandle);
    }
}

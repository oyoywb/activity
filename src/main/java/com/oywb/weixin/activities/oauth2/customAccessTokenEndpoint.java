package com.oywb.weixin.activities.oauth2;

import com.oywb.weixin.activities.config.WeChatProperties;
import com.oywb.weixin.activities.dao.UserRepository;
import com.oywb.weixin.activities.dto.SessionDto;
import com.oywb.weixin.activities.service.impl.UserServiceImpl;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.security.KeyPair;
import java.util.*;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class customAccessTokenEndpoint {

    final WeChatProperties weChatProperties;
    final RestTemplate restTemplate;
    final KeyPair keyPair;
    final UserServiceImpl userService;

    private final UserRepository userRepository;

    public customAccessTokenEndpoint(RestTemplateBuilder restTemplateBuilder, WeChatProperties weChatProperties, KeyPair keyPair, UserServiceImpl userService
            , UserRepository userRepository) {
        this.restTemplate = restTemplateBuilder.build();
        this.weChatProperties = weChatProperties;
        this.keyPair = keyPair;
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @PostMapping("/accesstoken")
    public Object createAccessToken(@RequestParam(value = "code") String code) {
        return createJwt(getSession(weChatProperties.getMiniProgram().getAppId(), weChatProperties.getMiniProgram().getAppSecret(), code));
    }

    public Map createJwt(SessionDto sessionDto) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("openid", sessionDto.getOpenid());
        claims.put("session_key", sessionDto.getSession_key());
        // 设置权限标记。Spring Security ResourceServer默认会检查Jwt中claims的scope，如果有这个scope属性会添加一个 SCOPE_前缀+scope属性值 的权限。
        // scope属性值可以设置为列表或数组，这样可以设置多个 SCOPE_xxx 的权限。
        // 在这里可以添加保存用户到数据库，或从数据库读取用户的权限。
        claims.put("scope", Arrays.asList("wechat", "user"));
        // claims.put("scope", "wechat");
        // Spring Security ResourceServer默认会把 Jwt 字符串中 sub字段值 设置 为 Authentication 对象的 name 值，这个值是标识登录用户的，所以这里赋值为小程序授权用户的openid。
        String subject = sessionDto.getOpenid();
        // @formatter:off
        String  accesstoken = Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(1)))// 有效期 1 天。
                .signWith(keyPair.getPrivate(), SignatureAlgorithm.RS256)
                .compact();
        // @formatter:on
        return Map.of("accesstoken", accesstoken, "user", userRepository.findByOpenid(sessionDto.getOpenid()));
    }

    private SessionDto getSession(Object... uriVariables) {
        ResponseEntity<SessionDto> sessionResponseEntity = this.restTemplate.getForEntity(weChatProperties.getMiniProgram().getCode2SessionApi(), SessionDto.class, uriVariables);
        userService.checkAndCreate(sessionResponseEntity.getBody().getOpenid());
        return Optional.ofNullable(sessionResponseEntity.getBody()).orElseThrow(() -> new RuntimeException("获取openid失败"));
    }

}
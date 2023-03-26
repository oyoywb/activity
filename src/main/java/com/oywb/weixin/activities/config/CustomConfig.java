package com.oywb.weixin.activities.config;

import com.oywb.weixin.activities.util.KeyStoreKeyFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Configuration
public class CustomConfig {
    @Bean
    public HttpMessageConverter<Object> customHttpMessageConverter() {
        MappingJackson2HttpMessageConverter httpMessageConverter = new MappingJackson2HttpMessageConverter();
        List<MediaType> mediaTypes = new ArrayList<>(Collections.singletonList(MediaType.TEXT_PLAIN));
        httpMessageConverter.setSupportedMediaTypes(mediaTypes);
        httpMessageConverter.setDefaultCharset(StandardCharsets.UTF_8);
        return httpMessageConverter;
    }

    //加载 JKS密钥库文件
    /**
     * 在resource目录下生成jwt使用的jks密钥库文件:
     * <p>
     * keytool -genkeypair -alias wechatjks -keyalg RSA -keypass lizhx@dgut.edu.cn -keystore wechat.jks -storepass lizhx@dgut.edu.cn -validity 36500
     * </p>
     * <p>后面的询问，最后一步输入 y ,其它回车即可。</p>
     * <p>
     * 上面的命令生成 2,048 位 RSA 密钥对和自签名证书 (SHA256withRSA) (有效期为 36,500 天)
     * </p>
     */
    @Bean
    public KeyPair keyPair() {
        return new KeyStoreKeyFactory(new ClassPathResource("wechat.jks"), "lizhx@dgut.edu.cn".toCharArray())// 密钥对的文件、密码
                .getKeyPair("wechatjks");// 生成密钥对时设置的别名-alias
    }

}

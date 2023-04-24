package com.oywb.weixin.activities;

import com.oywb.weixin.activities.config.minio.MinioConfig;
import com.oywb.weixin.activities.config.WeChatProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

@SpringBootApplication
@EnableScheduling
@EnableConfigurationProperties({MinioConfig.class, WeChatProperties.class})
@EnableTransactionManagement
@EnableWebSocket
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class ActivitiesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ActivitiesApplication.class, args);
	}

}

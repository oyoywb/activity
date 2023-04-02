package com.oywb.weixin.activities.config.minio;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;


@Component
@ConfigurationProperties(prefix = "minio")
@Data
public class MinioConfig {
    private String endpoint;

    private String accessKey;

    private String secretKey;

    private List<String> bucket;

}

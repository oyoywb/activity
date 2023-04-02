package com.oywb.weixin.activities.config.minio;

import io.minio.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.InputStream;

@Component
@Slf4j
public class Minio {

    private final MinioConfig minioConfig;

    private MinioClient minioClient = null;

    public Minio(MinioConfig minioConfig) {
        this.minioConfig = minioConfig;
    }

    @PostConstruct
    public void init() {
        minioClient = MinioClient.builder().endpoint(minioConfig.getEndpoint()).credentials(minioConfig.getAccessKey(), minioConfig.getSecretKey()).build();

        minioConfig.getBucket().forEach(bucket -> {
            try {
                boolean isExist = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucket).build());
                if (!isExist) {
                    minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucket).build());
                }
            } catch (Exception e) {
                log.error("create bucket fail", e);
            }
        });
    }


    public void upload(String fileName, String bucketName, MultipartFile file) {
        try {
            InputStream inputStream = file.getInputStream();

            minioClient.putObject(PutObjectArgs.builder().contentType("application/octet-stream").bucket(bucketName).object(fileName)
                    .stream(inputStream, file.getSize(), -1).build());


            inputStream.close();

        } catch (Exception e) {
            log.error("upload file to minio fail");
        }
    }
}

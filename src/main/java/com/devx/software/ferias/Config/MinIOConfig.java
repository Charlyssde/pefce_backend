package com.devx.software.ferias.Config;

import io.minio.MinioClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MinIOConfig {


//    private static final String END_POINT = "http://10.1.16.167:9000";
//    private static final String USERNAME = "devx";
//    private static final String PASSWORD = "devx-minio";

    private static final String END_POINT = "http://centos7.soluto.mx:9100";
    private static final String USERNAME = "minioadmin";
    private static final String PASSWORD = "minioadmin";
    
    
//    private static final String END_POINT = "http://localhost:9000";
//    private static final String USERNAME = "minio";
//    private static final String PASSWORD = "minio123";
    
    @Bean
    public MinioClient minioClient() {
        return new MinioClient.Builder()
                .endpoint(END_POINT)
                .credentials(USERNAME, PASSWORD)
                .build();
    }
}

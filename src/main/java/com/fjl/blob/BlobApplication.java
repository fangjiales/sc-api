package com.fjl.blob;

import com.fjl.blob.properties.JwtProperties;
import com.fjl.blob.properties.MinioProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(value = {JwtProperties.class, MinioProperties.class})
public class BlobApplication {
    public static void main(String[] args) {
        SpringApplication.run(BlobApplication.class, args);
    }
}

package com.fjl.blob.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "sc-blob.jwt")
public class JwtProperties {
    private String salt;
    private Long expiration;
}

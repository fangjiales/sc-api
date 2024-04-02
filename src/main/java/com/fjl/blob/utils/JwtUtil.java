package com.fjl.blob.utils;

import com.fjl.blob.properties.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;

@Component
public class JwtUtil {
    @Autowired
    private JwtProperties jwtProperties;

    private SecretKey secretKey() {
        return Keys.hmacShaKeyFor(jwtProperties.getSalt().getBytes());
    }

    /**
     * 生成jwt
     * 使用Hs256算法, 私匙使用固定秘钥
     *
     * @param claims    设置的信息
     * @return
     */
    public String createJWT(Map<String, Object> claims) {
        long expMillis = System.currentTimeMillis() + jwtProperties.getExpiration();
        Date exp = new Date(expMillis);
        JwtBuilder jwtBuilder = Jwts.builder()
                .claims(claims)
                .signWith(secretKey(), Jwts.SIG.HS256)
                .expiration(exp);
        return jwtBuilder.compact();
    }

    /**
     * Token解密
     *
     * @param token 加密后的token
     * @return
     */
    public Claims parseJWT(String token) {
        return Jwts.parser()
                .verifyWith(secretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}


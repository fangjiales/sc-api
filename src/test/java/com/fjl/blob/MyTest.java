package com.fjl.blob;

import com.fjl.blob.mapper.BlobMenuMapper;
import com.fjl.blob.properties.MinioProperties;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.UUID;

@SpringBootTest
public class MyTest {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    public void test() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String clm = passwordEncoder.encode("123456");
        System.out.println(clm);

//        HashMap<String, Object> map = new HashMap<>();
//        map.put("name", "ok");
//        map.put("money", 777);
//        String jwt = JwtUtil.createJWT(1000 * 60 * 60, map);
//        System.out.println(jwt);
//        Claims claims = JwtUtil.parseJWT(jwt);
//        System.out.println(claims);
//        String name = (String) claims.get("name");
//        Integer money = (Integer) claims.get("money");
//        System.out.println(name + " " + money);

//        LoginUser obj = (LoginUser) redisTemplate.opsForValue().get("login:1");
//        System.out.println(obj);
//        System.out.println(obj.getBlobUser());
    }

    @Autowired
    private BlobMenuMapper blobMenuMapper;

    @Test
    public void menuTest() {
        List<String> perms = blobMenuMapper.getPermsByUserId(1);
        System.out.println(perms);
    }

    @Autowired
    private MinioProperties minioProperties;

    @Test
    public void minioTest() {
        String uuid = UUID.randomUUID().toString();
        System.out.println(uuid);
    }
}

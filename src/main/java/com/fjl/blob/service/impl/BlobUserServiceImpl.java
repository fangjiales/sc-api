package com.fjl.blob.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fjl.blob.entity.BlobUser;
import com.fjl.blob.entity.LoginUser;
import com.fjl.blob.enums.ResultCodeEnums;
import com.fjl.blob.mapper.BlobUserMapper;
import com.fjl.blob.result.Result;
import com.fjl.blob.service.BlobUserService;
import com.fjl.blob.utils.ContextUtil;
import com.fjl.blob.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Objects;

/**
 * @author f_lesky
 * @description 针对表【user】的数据库操作Service实现
 * @createDate 2024-03-28 08:27:21
 */
@Service
public class BlobUserServiceImpl extends ServiceImpl<BlobUserMapper, BlobUser> implements BlobUserService {
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private BlobUserMapper blobUserMapper;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public Result login(BlobUser blobUser) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(blobUser.getUsername(), blobUser.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        if (Objects.isNull(authenticate)) {
            throw new RuntimeException("登录失败");
        }
        LoginUser principal = (LoginUser) authenticate.getPrincipal();
        Integer id = principal.getBlobUser().getId();
        String username = principal.getBlobUser().getUsername();
        HashMap<String, Object> claims = new HashMap<>();
        claims.put("id", id);
        claims.put("username", username);
        String token = jwtUtil.createJWT(claims);
        redisTemplate.opsForValue().set("login:" + id, principal);
        HashMap<String, String> res = new HashMap<>();
        res.put("token", token);
        return Result.build(res, ResultCodeEnums.LOGIN_SUCCESS);
    }

    @Override
    public Result logout() {
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Integer id = loginUser.getBlobUser().getId();
        redisTemplate.delete("login:" + id);
        ContextUtil.removeUserId();
        return Result.build(ResultCodeEnums.LOGOUT_SUCCESS);
    }

    @Override
    public Result getUserInfo() {
        Integer userId = ContextUtil.getUserId();
        BlobUser blobUser = blobUserMapper.selectById(userId);
        return Result.ok(blobUser);
    }
}





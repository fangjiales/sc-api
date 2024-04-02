package com.fjl.blob.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.fjl.blob.entity.BlobUser;
import com.fjl.blob.entity.LoginUser;
import com.fjl.blob.mapper.BlobRoleMapper;
import com.fjl.blob.mapper.BlobUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private BlobUserMapper blobUserMapper;

    @Autowired
    private BlobRoleMapper blobRoleMapper;

    @Override
    public UserDetails loadUserByUsername(String username) {
        LambdaQueryWrapper<BlobUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(BlobUser::getUsername, username);
        BlobUser blobUser = blobUserMapper.selectOne(wrapper);
        if (Objects.isNull(blobUser)) {
            throw new RuntimeException("用户名或密码错误");
        }
        if (blobUser.getStatus() == 0) {
            throw new RuntimeException("该用户已被禁用");
        }
//        List<String> permission = new ArrayList<>(Arrays.asList("list", "login"));
//        List<String> permission = blobMenuMapper.getPermsByUserId(blobUser.getId());
        List<String> permission = blobRoleMapper.getRoleNameByUserId(blobUser.getId());
        return new LoginUser(blobUser, permission);
    }
}
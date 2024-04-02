package com.fjl.blob.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fjl.blob.entity.BlobUser;
import com.fjl.blob.model.dto.UserDto;
import com.fjl.blob.result.Result;
import com.fjl.blob.service.BlobUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = {"/user"})
public class UserController {
    @Autowired
    private BlobUserService blobUserService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping(value = {"/login"})
    public Result login(@RequestBody BlobUser blobUser) {
        return blobUserService.login(blobUser);
    }

    @PostMapping(value = {"/logout"})
    public Result logout() {
        return blobUserService.logout();
    }

    @GetMapping(value = {"/list"})
//    @PreAuthorize(value = "@myExceptionRoot.hasAuthority('user.list')")
//    @PreAuthorize(value = "hasAuthority('user.list')")
    public Result retrieveUser(@RequestParam(value = "page") Integer page,
                               @RequestParam(value = "size") Integer size,
                               @RequestParam(value = "keyword") String keyword) {
        IPage<BlobUser> blobUserIPage = new Page<>(page, size);
        LambdaQueryWrapper<BlobUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(BlobUser::getUsername, keyword);
        wrapper.orderByDesc(BlobUser::getSort);
        IPage<BlobUser> res = blobUserService.page(blobUserIPage, wrapper);
        return Result.ok(res);
    }

    @GetMapping(value = {"/getById/{id}"})
    public Result getById(@PathVariable(value = "id") Integer id) {
        BlobUser blobUser = blobUserService.getById(id);
        return Result.ok(blobUser);
    }

    @GetMapping(value = {"/info"})
    public Result retrieveUserInfo() {
        return blobUserService.getUserInfo();
    }

    @PostMapping(value = {"/add"})
    public Result addUser(@RequestBody UserDto userDto) {
        BlobUser blobUser = new BlobUser();
        BeanUtils.copyProperties(userDto, blobUser);
        String encodePassword = passwordEncoder.encode(userDto.getPassword());
        blobUser.setPassword(encodePassword);
        blobUserService.save(blobUser);
        return Result.ok(null);
    }

    @PutMapping(value = {"/update"})
    public Result updateUser(@RequestBody UserDto userDto) {
        String password = passwordEncoder.encode(userDto.getPassword());
        userDto.setPassword(password);
        BlobUser blobUser = new BlobUser();
        BeanUtils.copyProperties(userDto, blobUser);
        blobUserService.updateById(blobUser);
        return Result.ok(null);
    }

    @DeleteMapping(value = {"/batchDelete"})
    public Result batchDelete(@RequestBody List<Integer> ids) {
        blobUserService.removeBatchByIds(ids);
        return Result.ok(null);
    }
}

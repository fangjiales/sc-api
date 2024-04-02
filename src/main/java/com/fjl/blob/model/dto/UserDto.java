package com.fjl.blob.model.dto;

import lombok.Data;

@Data
public class UserDto {
    /**
     * 用户id
     */
    private Integer id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 用户头像图片地址
     */
    private String avatar;

    /**
     * 性别1女0男
     */
    private Integer sex;

    /**
     * 用户手机号
     */
    private String phone;

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 用户简介
     */
    private String introduction;

    /**
     * 用户状态1启用0禁用
     */
    private Integer status;
}

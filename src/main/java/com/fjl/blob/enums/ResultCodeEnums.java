package com.fjl.blob.enums;

import lombok.Getter;

@Getter
public enum ResultCodeEnums {
    SUCCESS(20000,"成功"),
    LOGIN_SUCCESS(20002, "登录成功"),
    LOGOUT_SUCCESS(20003, "注销成功"),
    FAIL(40001, "失败"),
    AUTHENTICATION_FAIL(40002, "用户认证失败，请重新登录"),
    LACK_PERMISSION(40003, "权限不足"),
    NO_FOUND(40004, "Not Found"),
    USERNAME_PASSWORD_ERROR(40005, "用户名或密码错误"),
    USER_DISABLE(40006, "该用户已被禁用"),
    SYSTEM_BUSY(50000, "系统繁忙")
    ;
    private Integer code;

    private String message;

    private ResultCodeEnums(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}

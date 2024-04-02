package com.fjl.blob.result;

import com.fjl.blob.enums.ResultCodeEnums;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> {
    private String message;
    private Integer code;
    private T data;

    public static <T> Result<T> build(ResultCodeEnums resultCodeEnums) {
        Result<T> result = new Result<>();
        result.setCode(resultCodeEnums.getCode());
        result.setMessage(resultCodeEnums.getMessage());
        return result;
    }

    public static <T> Result<T> build(T data, ResultCodeEnums resultCodeEnums) {
        Result<T> result = build(resultCodeEnums);
        result.setData(data);
        return result;
    }

    public static <T> Result<T> build(String message, Integer code) {
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setMessage(message);
        result.setData(null);
        return result;
    }

    public static <T> Result<T> build(T data, String message, Integer code) {
        Result<T> result = build(message, code);
        result.setData(data);
        return result;
    }

    public static <T> Result<T> ok(T data) {
        Result<T> result = build(data, ResultCodeEnums.SUCCESS);
        return result;
    }

    public static <T> Result<T> fail(T data) {
        return build(data, ResultCodeEnums.FAIL);
    }
}

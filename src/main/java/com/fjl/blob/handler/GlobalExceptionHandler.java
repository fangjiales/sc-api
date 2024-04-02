package com.fjl.blob.handler;

import com.fjl.blob.enums.ResultCodeEnums;
import com.fjl.blob.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(value = {AuthenticationException.class})
    public Result authenticationException(AuthenticationException ex) {
        log.error("{}", ex.getMessage(), ex);
        throw ex;
    }

    @ExceptionHandler(value = {Exception.class})
    public Result exceptionHandler(Exception ex) {
        log.error("{}", ex.getMessage(), ex);
        return Result.build(ResultCodeEnums.SYSTEM_BUSY);
    }
}

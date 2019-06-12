package com.customer.securityloginmethods.config.common;

import com.customer.securityloginmethods.config.exceptions.CustomerException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;

/**
 * @author xch
 * @since 2019/6/12 8:59
 **/
@RestControllerAdvice
public class ExceptionAdvice {


    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseBean handle403(AccessDeniedException e){
        return ResponseBean.builder().code(1).msg("权限不足").build();
    }


    /**
     * 处理自定义异常
     * @return
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CustomerException.class)
    public ResponseBean handel400(CustomerException e){
        return ResponseBean.builder().code(e.getCode()).msg(e.getMessage()).build();
    }

    /**
     * 处理系统异常
     * @return
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ResponseBean handel500(Exception e){
        e.printStackTrace();
        return ResponseBean.builder().code(1).msg("服务其内部错误").build();
    }
}

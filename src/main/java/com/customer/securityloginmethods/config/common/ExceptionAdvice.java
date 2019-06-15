package com.customer.securityloginmethods.config.common;

import com.customer.securityloginmethods.config.exceptions.CustomerException;
import com.customer.securityloginmethods.vo.ResponseMsg;
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
    public ResponseMsg handle403(AccessDeniedException e){
        return ResponseMsg.fail("权限不足");
    }


    /**
     * 处理自定义异常
     * @return
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CustomerException.class)
    public ResponseMsg handel400(CustomerException e){
        return ResponseMsg.fail(e.getMessage(),e.getCode());
    }

    /**
     * 处理系统异常
     * @return
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ResponseMsg handel500(Exception e){
        e.printStackTrace();
        return ResponseMsg.fail("服务其内部错误");
    }
}

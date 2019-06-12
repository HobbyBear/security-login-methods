package com.customer.securityloginmethods.config.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 自定义异常
 * @author xch
 * @since 2019/6/12 9:00
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class CustomerException extends RuntimeException {

    private Integer code;

    public CustomerException(Integer code,String msg ){
        super(msg);
        this.code = code;
    }

    public CustomerException(String msg){
        super(msg);
        this.code = 1;
    }
}

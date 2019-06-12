package com.customer.securityloginmethods.config.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xch
 * @since 2019/6/12 8:48
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseBean<T> {

    /**
     * 1-失败 0-成功
     */
    private Integer code;

    private String msg;

    private T data;




}

package com.customer.securityloginmethods.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 19624
 */
@Data
@NoArgsConstructor
public class ResponseMsg<T> {
    /**
     * 1 失败
     * 0成功
     */
    private Integer code;

    private String msg;

    private Integer status;

    private T data;


    private ResponseMsg(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private ResponseMsg(Integer code, String msg, T data) {
        this.msg = msg;
        this.code = code;
        this.data = data;
    }

    private ResponseMsg(Integer code, String msg, Integer status) {
        this.msg = msg;
        this.code = code;
        this.status = status;
    }

    private ResponseMsg(Integer code, String msg, T data,Integer status) {
        this.msg = msg;
        this.code = code;
        this.data = data;
        this.status = status;
    }

    public static ResponseMsg success(String msg) {
        return new ResponseMsg(0, msg);
    }

    public static <T> ResponseMsg<T> success(T data) {
        return new ResponseMsg<>(0, "请求成功", data);
    }

    public static <T> ResponseMsg<T> success(String msg, T data) {
        return new ResponseMsg<>(0, msg, data);
    }

    public static ResponseMsg fail(String msg) {
        return new ResponseMsg(1, msg);
    }

    public static <T> ResponseMsg<T> fail(T data) {
        return new ResponseMsg<>(1, "请求失败", data);
    }

    public static <T> ResponseMsg<T> fail(String msg, T data) {
        return new ResponseMsg<T>(1, msg, data);
    }

    /**********************附带状态码的方法**********************/
    public static ResponseMsg success(String msg, Integer status) {
        return new ResponseMsg(0, msg, status);
    }

    public static <T> ResponseMsg<T> success(T data,Integer status) {
        return new ResponseMsg<>(0, "请求成功", data,status);
    }

    public static <T> ResponseMsg<T> success(String msg, T data,Integer status) {
        return new ResponseMsg<>(0, msg, data,status);
    }

    public static ResponseMsg fail(String msg,Integer status) {
        return new ResponseMsg(1, msg,status);
    }

    public static <T> ResponseMsg<T> fail(T data,Integer status) {
        return new ResponseMsg<>(1, "请求失败", data,status);
    }

    public static <T> ResponseMsg<T> fail(String msg, T data,Integer status) {
        return new ResponseMsg<T>(1, msg, data,status);
    }

}

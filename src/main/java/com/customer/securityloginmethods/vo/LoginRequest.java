package com.customer.securityloginmethods.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 19624
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {

    /**
     * 手机号或用户名或邮件
     */
    private String usernameOrEmailOrPhone;


    private String password;
}

package com.customer.securityloginmethods.controller;


import com.customer.securityloginmethods.entity.UserInfo;
import com.customer.securityloginmethods.service.UserService;
import com.customer.securityloginmethods.vo.LoginRequest;
import com.customer.securityloginmethods.vo.ResponseMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 19624
 */
@RestController
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;


    @PostMapping("/login")
    public ResponseMsg login(@RequestBody LoginRequest loginRequest){
        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(loginRequest.getUsernameOrEmailOrPhone(),loginRequest.getPassword());
        Authentication authentication = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return ResponseMsg.success("登陆成功");
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/logout")
    public ResponseMsg logout(){
        SecurityContextHolder.clearContext();
        return ResponseMsg.success("退出成功");
    }

    /**
     * todo 邮件注册
     * @param userInfo
     * @return
     */
    @PostMapping("/register")
    public ResponseMsg register(@RequestBody UserInfo userInfo){
        userService.register(userInfo);
        return ResponseMsg.success("注册成功");
    }

}

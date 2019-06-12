package com.customer.securityloginmethods.controller;

import com.customer.securityloginmethods.config.common.ResponseBean;
import com.customer.securityloginmethods.entity.UserInfo;
import com.customer.securityloginmethods.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xch
 * @since 2019/6/12 8:53
 **/
@RestController
@Slf4j(topic = "spring security 属性")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public ResponseBean login(@RequestBody UserInfo userInfo) {
        loginService.login(userInfo.getUsername(),userInfo.getPassword());
        return ResponseBean.builder().code(0).msg("登陆成功").build();
    }

    @PreAuthorize("hasAnyRole('admin')")
    @RequestMapping("adminAccess")
    public String adminAccess(){
        log.info("权限：{}",  SecurityContextHolder.getContext().getAuthentication().getAuthorities());
        log.info("用户名:{}",SecurityContextHolder.getContext().getAuthentication().getName());
        log.info("userDetail:{}",SecurityContextHolder.getContext().getAuthentication().getDetails());
        log.info("principal: {}",SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        log.info("credentials:{}",SecurityContextHolder.getContext().getAuthentication().getDetails());
        return  "admin访问成功";
    }

    @PreAuthorize("hasAnyRole('usr')")
    @RequestMapping("usrAccess")
    public String usrAccess(){
        return "usr访问成功";
    }

    @RequestMapping("authenticated")
    public String authenticated(){
        return "需要登陆才能访问的url路径";
    }

    @RequestMapping("annymous")
    public String annymous(){
        System.out.println( SecurityContextHolder.getContext().getAuthentication().getAuthorities());
        return "不需要登陆就能访问成功";
    }
}

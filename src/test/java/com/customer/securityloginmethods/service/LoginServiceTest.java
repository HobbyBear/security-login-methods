package com.customer.securityloginmethods.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author xch
 * @since 2019/6/12 9:36
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class LoginServiceTest {

    @Autowired
    private LoginService loginService;

    @Test
    public void testLogin(){
        loginService.login("xch","123");
    }
}

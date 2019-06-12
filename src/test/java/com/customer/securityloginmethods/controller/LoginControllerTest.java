package com.customer.securityloginmethods.controller;

import com.alibaba.fastjson.JSON;
import com.customer.securityloginmethods.entity.UserInfo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

/**
 * @WithMockUser 模拟用户，手动指定用户名和授权
 * @WithAnonymousUser 模拟匿名用户
 * @WithUserDetails 模拟用户，给定用户名，通过自定义UserDetailService来认证
 * @WithSecurityContext 通过SecurityContext构造器模拟用户
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class LoginControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void beforeTest(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(springSecurity())
                .build();
    }

    @Test
    public void testLogin() throws Exception {
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername("xch");
        userInfo.setPassword("123");
        mockMvc.perform(MockMvcRequestBuilders.post("/login")
                .content(JSON.toJSONString(userInfo))
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
        ).andExpect(authenticated());
    }


    @Test
    @WithMockUser(username = "xch",roles = "admin")
    public void testAdminAccess() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/adminAccess"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testLogOut() throws Exception {
      mockMvc.perform(MockMvcRequestBuilders.get("logOut"))
              .andExpect(unauthenticated());
    }

}

package com.customer.securityloginmethods.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author xch
 * @since 2019/6/12 8:44
 **/
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AjaxAccessDeniedHandler deniedHandler;

    @Autowired
    private AjaxAuthenticationEntryPoint authenticationEntryPoint;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/login", "/annymous").permitAll()
                .anyRequest().authenticated()
                .and().
                csrf().disable()
                .exceptionHandling()
                .accessDeniedHandler(deniedHandler)
                .authenticationEntryPoint(authenticationEntryPoint)
        ;
    }
}

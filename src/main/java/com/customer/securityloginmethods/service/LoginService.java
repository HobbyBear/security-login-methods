package com.customer.securityloginmethods.service;

import com.customer.securityloginmethods.config.exceptions.CustomerException;
import com.customer.securityloginmethods.dao.RoleRepository;
import com.customer.securityloginmethods.dao.UserInfoRepository;
import com.customer.securityloginmethods.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author xch
 * @since 2019/6/12 8:46
 **/
@Service
public class LoginService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private RoleRepository roleRepository;


    public void login(String username, String password) throws CustomerException {
        UserInfo userInfo = userInfoRepository.findByUsername(username);
        Optional.ofNullable(userInfo).orElseThrow(() -> new CustomerException("用户名不存在"));
        if (userInfo.getPassword().equals(password)) {
            List<String> roleList = roleRepository.findRoleNameListByUsername(username);
            SecurityContextHolder.getContext().setAuthentication(genToken(username,roleList));
        } else {
            throw new CustomerException("密码错误");
        }
    }

    /**
     * 生成验证之后的token
     * @param username
     * @param roleList
     * @return
     */
    private UsernamePasswordAuthenticationToken genToken(String username, List<String> roleList) {
        roleList = roleList.stream().map(r -> "ROLE_" + r).collect(Collectors.toList());
        List<GrantedAuthority> authorityList = AuthorityUtils.createAuthorityList(roleList.toArray(new String[roleList.size()]));
        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(username, "",
                        authorityList);
        return token;
    }
}

package com.customer.securityloginmethods.service;


import cn.hutool.core.bean.BeanUtil;
import com.customer.securityloginmethods.dao.RoleDao;
import com.customer.securityloginmethods.dao.UserDao;
import com.customer.securityloginmethods.entity.UserInfo;
import com.customer.securityloginmethods.vo.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author 19624
 */
@Service
public class CustomerUserDetailService implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;


    @Override
    public UserDetails loadUserByUsername(String usernameOrEmailOrPhone) throws UsernameNotFoundException {
        UserInfo userInfo = userDao.findByUsernameOrEmailOrPhone(usernameOrEmailOrPhone,usernameOrEmailOrPhone,usernameOrEmailOrPhone)
                .orElseThrow(()-> new UsernameNotFoundException("用户不存在："+usernameOrEmailOrPhone));
        UserPrincipal userPrincipal = new UserPrincipal();
        BeanUtil.copyProperties(userInfo,userPrincipal);
        userPrincipal.setRoles(roleDao.findRoleListByUserId(userInfo.getId()));
        return userPrincipal;
    }
}

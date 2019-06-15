package com.customer.securityloginmethods.service;


import com.customer.securityloginmethods.config.common.Constant;
import com.customer.securityloginmethods.dao.UserDao;
import com.customer.securityloginmethods.dao.UserRoleDao;
import com.customer.securityloginmethods.entity.UserInfo;
import com.customer.securityloginmethods.entity.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 19624
 */
@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserRoleDao userRoleDao;

    @Transactional(rollbackFor = Exception.class)
    public void register(UserInfo user){
        //todo 检测用户名电话号码等是否重复
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userDao.save(user);
        userRoleDao.save(UserRole.builder().roleId(Constant.Role.ROLE_USER).userId(user.getId()).build());
        }

}

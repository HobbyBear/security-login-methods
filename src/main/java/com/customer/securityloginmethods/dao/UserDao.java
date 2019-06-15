package com.customer.securityloginmethods.dao;


import com.customer.securityloginmethods.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author 19624
 */
public interface UserDao extends JpaRepository<UserInfo,Long> {

    Optional<UserInfo> findByUsernameOrEmailOrPhone(String username, String email, String phone);

}

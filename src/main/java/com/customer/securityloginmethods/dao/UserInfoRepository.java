package com.customer.securityloginmethods.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.customer.securityloginmethods.entity.UserInfo;

public interface UserInfoRepository extends JpaRepository<UserInfo, Integer>, JpaSpecificationExecutor<UserInfo> {

    UserInfo findByUsername(String username);
}
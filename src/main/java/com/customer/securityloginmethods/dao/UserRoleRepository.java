package com.customer.securityloginmethods.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.customer.securityloginmethods.entity.UserRole;

public interface UserRoleRepository extends JpaRepository<UserRole, Integer>, JpaSpecificationExecutor<UserRole> {

}
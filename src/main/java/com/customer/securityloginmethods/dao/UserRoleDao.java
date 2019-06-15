package com.customer.securityloginmethods.dao;

import com.customer.securityloginmethods.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author 19624
 */
public interface UserRoleDao extends JpaRepository<UserRole,Long> {
}

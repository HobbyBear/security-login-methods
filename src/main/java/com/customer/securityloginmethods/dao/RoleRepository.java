package com.customer.securityloginmethods.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.customer.securityloginmethods.entity.Role;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Integer>, JpaSpecificationExecutor<Role> {

    @Query(nativeQuery = true,
            value = "select r.role_name from user_role t,role r,user_info i where t.user_id = i.id and r.id = t.role_id and i.username = ?1")
    List<String> findRoleNameListByUsername(String username);
}
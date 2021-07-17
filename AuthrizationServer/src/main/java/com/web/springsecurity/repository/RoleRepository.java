package com.web.springsecurity.repository;

import com.web.springsecurity.model.Role;
import com.web.springsecurity.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByRole(String role);

    @Query(value = "select r.role from roles as r inner join user as u on r.role_id = u.user_id;", nativeQuery = true)
    String findRole();
}

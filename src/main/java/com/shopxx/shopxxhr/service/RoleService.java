package com.shopxx.shopxxhr.service;

import com.shopxx.shopxxhr.entity.Role;

import java.util.List;

public interface RoleService {

    List<Role> getAllRoles();

    Role saveOrUpdateRole(Role role);

    void deleteRoleById(Integer id);
}

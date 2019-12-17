package com.shopxx.shopxxhr.service.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.shopxx.shopxxhr.entity.Role;
import com.shopxx.shopxxhr.repository.RoleRepository;
import com.shopxx.shopxxhr.service.RoleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    @PersistenceContext
    protected EntityManager entityManager;
    @Autowired
    RoleRepository roleRepository;

    protected JPAQueryFactory jpaQueryFactory;

    @PostConstruct
    public void init() {
        jpaQueryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    @Transactional
    public Role saveOrUpdateRole(Role role) {
        if (!StringUtils.startsWith(role.getName(), "ROLE_")) {
            role.setName("ROLE_" + role.getName());
        }
        return roleRepository.save(role);
    }

    @Override
    @Transactional
    public void deleteRoleById(Integer id) {
        roleRepository.deleteById(id);
    }

}
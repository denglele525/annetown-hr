package com.shopxx.shopxxhr.service.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.shopxx.shopxxhr.entity.Role;
import com.shopxx.shopxxhr.repository.RoleRepository;
import com.shopxx.shopxxhr.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
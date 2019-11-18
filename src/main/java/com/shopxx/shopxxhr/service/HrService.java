package com.shopxx.shopxxhr.service;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.shopxx.shopxxhr.entity.Hr;
import com.shopxx.shopxxhr.entity.QHr;
import com.shopxx.shopxxhr.entity.QRole;
import com.shopxx.shopxxhr.entity.Role;
import com.shopxx.shopxxhr.repository.HrRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class HrService implements UserDetailsService {
    @Autowired
    HrRespository hrRespository;

    @Autowired
    @PersistenceContext
    protected EntityManager entityManager;

    protected JPAQueryFactory jpaQueryFactory;

    @PostConstruct
    public void init() {
        jpaQueryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Hr hr = hrRespository.findByUsername(s);
        if (hr == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        hr.setRole(getHrRolesById(hr.getId()));
        return hr;
    }

    public List<Role> getHrRolesById(Integer id) {
        QRole qRole = QRole.role;
        QHr qHr = QHr.hr;
        List<Role> roles = jpaQueryFactory.selectFrom(qRole).
                innerJoin(qRole.hr, qHr).
                where(qHr.id.eq(id)).fetch();
        return roles;
    }

}

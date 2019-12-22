package com.shopxx.shopxxhr.service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.shopxx.shopxxhr.entity.*;
import com.shopxx.shopxxhr.repository.HrRespository;
import com.shopxx.shopxxhr.repository.HrRoleRepository;
import com.shopxx.shopxxhr.utils.HrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class HrService implements UserDetailsService {

    @Autowired
    HrRespository hrRespository;
    @Autowired
    HrRoleRepository hrRoleRepository;

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

    public List<Hr> getAllHrs(String keywords) {
        QHr qHr = QHr.hr;
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(qHr.id.notIn(HrUtil.getCurrentHr().getId()));
        Optional.ofNullable(keywords).ifPresent(it -> booleanBuilder.and(qHr.name.like("%" + it + "%")));
        QRole qRole = QRole.role;
        List<Hr> hrs = jpaQueryFactory.select(Projections.bean(Hr.class, qHr.id, qHr.name, qHr.phone, qHr.telephone, qHr.address, qHr.enabled, qHr.username, qHr.userface, qHr.remark))
                .from(qHr)
                .leftJoin(qHr.role, qRole)
                .where(booleanBuilder)
                .distinct()
                .fetch();
        hrs.stream().forEach(
                hr -> {
                    List<Role> roles = jpaQueryFactory.select(Projections.bean(Role.class, qRole.id, qRole.name, qRole.nameZh))
                            .from(qRole)
                            .innerJoin(qRole.hr, qHr)
                            .where(qHr.id.eq(hr.getId()))
                            .fetch();
                    hr.setRole(roles);
                }
        );
        return hrs;
    }

    @Transactional
    public Hr saveOrUpdateHr(Hr hr) {
        Hr pHr = hrRespository.findById(hr.getId()).orElse(null);
        if (hr != null) {
            hr.setPassword(pHr.getPassword());
        }
        return hrRespository.save(hr);
    }

    @Transactional
    public void updateHrRole(Integer hrid, Integer[] rids) {
        hrRoleRepository.deleteByHrId(hrid);
        Optional.ofNullable(rids)
                .ifPresent(
                        it -> Arrays.stream(it).forEach(
                                rid -> {
                                    HrRole pHrRole = new HrRole();
                                    pHrRole.setRoleId(rid);
                                    pHrRole.setHrId(hrid);
                                    hrRoleRepository.save(pHrRole);
                                }
                        )
                );
    }

    @Transactional
    public void deleteHrById(Integer id) {
        hrRespository.deleteById(id);
    }

}
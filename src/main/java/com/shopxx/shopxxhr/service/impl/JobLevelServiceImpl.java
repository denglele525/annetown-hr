package com.shopxx.shopxxhr.service.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.shopxx.shopxxhr.entity.JobLevel;
import com.shopxx.shopxxhr.repository.JobLevelRepository;
import com.shopxx.shopxxhr.service.JobLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class JobLevelServiceImpl implements JobLevelService {

    @Autowired
    @PersistenceContext
    protected EntityManager entityManager;
    @Autowired
    JobLevelRepository jobLevelRepository;

    protected JPAQueryFactory jpaQueryFactory;

    @PostConstruct
    public void init() {
        jpaQueryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public List<JobLevel> getAllJobLevels() {
        return jobLevelRepository.findAll();
    }

    @Override
    @Transactional
    public JobLevel saveOrUpdateJobLevel(JobLevel jobLevel) {
        jobLevel.setCreateDate(new Date());
        return jobLevelRepository.save(jobLevel);
    }

    @Override
    @Transactional
    public void deleteJobLevelById(Integer id) {
        jobLevelRepository.deleteById(id);
    }

    @Override
    public JobLevel findOne(JobLevel jobLevel) {
        return jobLevelRepository.findById(jobLevel.getId()).orElse(null);
    }

    @Override
    @Transactional
    public void deleteJobLevelsByIds(Integer[] ids) {
        Optional.ofNullable(ids)
                .ifPresent(
                        it -> Arrays.asList(it).stream().forEach(
                                id -> deleteJobLevelById(id)
                        )
                );
    }

}

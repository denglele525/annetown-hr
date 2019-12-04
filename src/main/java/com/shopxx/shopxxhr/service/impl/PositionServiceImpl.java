package com.shopxx.shopxxhr.service.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.shopxx.shopxxhr.entity.Position;
import com.shopxx.shopxxhr.repository.PositionRepository;
import com.shopxx.shopxxhr.service.PositionService;
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
public class PositionServiceImpl implements PositionService {

    @Autowired
    @PersistenceContext
    protected EntityManager entityManager;
    @Autowired
    PositionRepository positionRepository;

    protected JPAQueryFactory jpaQueryFactory;

    @PostConstruct
    public void init() {
        jpaQueryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public List<Position> getAllPositions() {
        return positionRepository.findAll();

    }

    @Override
    @Transactional
    public Position saveOrUpdatePosition(Position position) {
        position.setCreateDate(new Date());
        return positionRepository.save(position);
    }

    @Override
    @Transactional
    public void deletePositionById(Integer id) {
        positionRepository.deleteById(id);
    }

    @Override
    public Position findOne(Position position) {
        return positionRepository.findById(position.getId()).orElse(null);
    }

    @Override
    @Transactional
    public void deletePositionsByIds(Integer[] ids) {
        Optional.ofNullable(ids)
                .ifPresent(
                        it -> Arrays.asList(it).stream().forEach(
                                id -> deletePositionById(id)
                        )
                );
    }

}

package com.shopxx.shopxxhr.repository;

import com.shopxx.shopxxhr.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Repository
@Validated
public interface PositionRepository extends JpaRepository<Position, Integer>, JpaSpecificationExecutor<Position>, PagingAndSortingRepository<Position, Integer> {

    List<Position> findAll();

//    int addPosition(Position position);

}

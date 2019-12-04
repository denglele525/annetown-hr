package com.shopxx.shopxxhr.repository;

import com.shopxx.shopxxhr.entity.JobLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.validation.annotation.Validated;

@Repository
@Validated
public interface JobLevelRepository extends JpaRepository<JobLevel, Integer>, JpaSpecificationExecutor<JobLevel>, PagingAndSortingRepository<JobLevel, Integer> {

}

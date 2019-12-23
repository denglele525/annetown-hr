package com.shopxx.shopxxhr.repository;

import com.shopxx.shopxxhr.entity.HrRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.validation.annotation.Validated;

@Repository
@Validated
public interface HrRoleRepository extends JpaRepository<HrRole, Integer>, JpaSpecificationExecutor<HrRole>, PagingAndSortingRepository<HrRole, Integer>, BaseRepository<HrRole, Integer> {

    void deleteByHrId(Integer hrId);

}

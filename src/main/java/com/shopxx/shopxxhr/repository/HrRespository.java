package com.shopxx.shopxxhr.repository;

import com.shopxx.shopxxhr.entity.Hr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface HrRespository extends JpaRepository<Hr, Integer>, JpaSpecificationExecutor<Hr>, PagingAndSortingRepository<Hr, Integer> {

    Hr findByUsername(String username);

}

package com.shopxx.shopxxhr.repository;

import com.shopxx.shopxxhr.entity.Hr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.validation.annotation.Validated;

@Repository
@Validated
public interface HrRespository extends JpaRepository<Hr, Integer>, JpaSpecificationExecutor<Hr>, PagingAndSortingRepository<Hr, Integer>, BaseRepository<Hr, Integer> {

    Hr findByUsername(String username);

}

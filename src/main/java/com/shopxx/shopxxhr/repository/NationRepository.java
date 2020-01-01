package com.shopxx.shopxxhr.repository;

import com.shopxx.shopxxhr.entity.Nation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.validation.annotation.Validated;

@Repository
@Validated
public interface NationRepository extends JpaRepository<Nation, Integer>, JpaSpecificationExecutor<Nation>, PagingAndSortingRepository<Nation, Integer>, BaseRepository<Nation, Integer> {

}
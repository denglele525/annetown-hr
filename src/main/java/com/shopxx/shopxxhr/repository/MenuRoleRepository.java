package com.shopxx.shopxxhr.repository;

import com.shopxx.shopxxhr.entity.MenuRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.validation.annotation.Validated;

@Repository
@Validated
public interface MenuRoleRepository extends JpaRepository<MenuRole, Integer>, JpaSpecificationExecutor<MenuRole>, PagingAndSortingRepository<MenuRole, Integer> {

    void deleteByRoleId(Integer roleId);

}
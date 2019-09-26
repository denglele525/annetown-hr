package com.shopxx.shopxxhr.repository;

import com.shopxx.shopxxhr.entity.MenuRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MenuRoleRepository extends JpaRepository<MenuRole, Long> {

    @Query("SELECT mid from MenuRole WHERE rid= :id")
    List<Long> getMenusByRid(@Param("id") Long rid);

}

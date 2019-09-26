package com.shopxx.shopxxhr.repository;

import com.shopxx.shopxxhr.entity.Hr;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HrRespository extends JpaRepository<Hr, Long> {

    Hr findByUsername(String username);

}

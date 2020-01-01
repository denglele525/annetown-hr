package com.shopxx.shopxxhr.service.impl;

import com.shopxx.shopxxhr.entity.Politicsstatus;
import com.shopxx.shopxxhr.repository.PoliticsstatusRepository;
import com.shopxx.shopxxhr.service.PoliticsstatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PoliticsstatusServiceImpl implements PoliticsstatusService {

    @Autowired
    PoliticsstatusRepository politicsstatusRepository;

    @Override
    public List<Politicsstatus> getAllPoliticsstatuses() {
        return politicsstatusRepository.findAll();
    }

}

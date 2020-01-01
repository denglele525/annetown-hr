package com.shopxx.shopxxhr.service.impl;

import com.shopxx.shopxxhr.entity.Nation;
import com.shopxx.shopxxhr.repository.NationRepository;
import com.shopxx.shopxxhr.service.NationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NationServiceImpl implements NationService {

    @Autowired
    NationRepository nationRepository;

    @Override
    public List<Nation> getAllNations() {
        return nationRepository.findAll();
    }

}

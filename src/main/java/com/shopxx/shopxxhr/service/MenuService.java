package com.shopxx.shopxxhr.service;

import com.shopxx.shopxxhr.entity.Menu;
import com.shopxx.shopxxhr.repository.MenuRespository;
import com.shopxx.shopxxhr.repository.MenuRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by deng on 2019/8/11.
 */
@Service
@Transactional
@CacheConfig(cacheNames = "menus_cache")
public class MenuService {

    @Autowired
    MenuRespository menuRespository;
    @Autowired
    MenuRoleRepository menuRoleRepository;

    //    @Cacheable(key = "#root.methodName")
    public List<Menu> getAllMenu() {
        return menuRespository.findAll();
    }

//    public List<Menu> getMenusByHrId() {
//        return menuRespository.findAllById(HrUtils.getCurrentHr().getId())
//    }
//
//    public List<Menu> menuTree() {
//        return menuMapper.menuTree();
//    }
//
//    public List<Long> getMenusByRid(Long rid) {
//        return menuRoleRepository.getMenusByRid(rid);
//    }

}

package com.shopxx.shopxxhr.service;

import com.shopxx.shopxxhr.entity.Menu;

import java.util.List;

public interface MenuService {

    List<Menu> getMenusByHrId(Integer id);

    List<Menu> getAllMenusWithRole();

}

package com.shopxx.shopxxhr.service;

import com.shopxx.shopxxhr.entity.RespPageBean;

public interface EmployeeService {

    RespPageBean getEmployeeByPage(Integer page, Integer size);

}

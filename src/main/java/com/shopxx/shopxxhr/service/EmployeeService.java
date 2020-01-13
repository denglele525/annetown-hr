package com.shopxx.shopxxhr.service;

import com.shopxx.shopxxhr.entity.Employee;
import com.shopxx.shopxxhr.entity.RespPageBean;

public interface EmployeeService {

    RespPageBean getEmployeeByPage(Integer page, Integer size, String keyword);

    Employee saveOrUpdateEmp(Employee employee);

    Integer maxWorkID();

    void deleteEmpById(Integer id);
}

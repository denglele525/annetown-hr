package com.shopxx.shopxxhr.service;

import com.querydsl.core.types.Predicate;
import com.shopxx.shopxxhr.entity.Employee;
import com.shopxx.shopxxhr.entity.RespPageBean;

import java.util.List;

public interface EmployeeService {

    RespPageBean getEmployeeByPage(Integer page, Integer size, Predicate predicate);

    Employee saveOrUpdateEmp(Employee employee);

    Integer maxWorkID();

    void deleteEmpById(Integer id);

    void addEmps(List<Employee> employees);
}

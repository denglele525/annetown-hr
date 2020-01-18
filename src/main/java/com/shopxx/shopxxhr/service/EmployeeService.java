package com.shopxx.shopxxhr.service;

import com.querydsl.core.types.Predicate;
import com.shopxx.shopxxhr.entity.Employee;
import com.shopxx.shopxxhr.entity.RespPageBean;

import java.util.Date;
import java.util.List;

public interface EmployeeService {

    RespPageBean getEmployeeByPage(Integer page, Integer size, Predicate predicate, Date[] beginDateScope);

    Employee saveOrUpdateEmp(Employee employee);

    Integer maxWorkID();

    void deleteEmpById(Integer id);

    void addEmps(List<Employee> employees);
}

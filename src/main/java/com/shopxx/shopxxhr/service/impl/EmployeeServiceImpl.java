package com.shopxx.shopxxhr.service.impl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPAExpressions;
import com.shopxx.shopxxhr.entity.Employee;
import com.shopxx.shopxxhr.entity.QEmployee;
import com.shopxx.shopxxhr.entity.RespPageBean;
import com.shopxx.shopxxhr.repository.EmployeeRepository;
import com.shopxx.shopxxhr.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public RespPageBean getEmployeeByPage(Integer page, Integer size, String keyword) {
        QEmployee qEmployee = QEmployee.employee;
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        if (keyword != null) {
            booleanBuilder.and(qEmployee.name.like("%" + keyword + "%"));
        }
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<Employee> all = employeeRepository.findAll(booleanBuilder, pageable);
        long totalSize = all.getTotalElements();
        List<Employee> employees = all.getContent();
        RespPageBean respPageBean = new RespPageBean();
        respPageBean.setData(employees);
        respPageBean.setTotal(totalSize);
        return respPageBean;
    }

    @Override
    public Employee saveOrUpdateDepartment(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Integer maxWorkID() {
        QEmployee qEmployee = QEmployee.employee;
        Employee employee = employeeRepository.findOne(qEmployee.workId.eq(JPAExpressions.select(qEmployee.workId.max()).from(qEmployee))).orElse(null);
        if (employee != null) {
            return employee.getWorkId();
        }
        return 0;
    }

}

package com.shopxx.shopxxhr.service.impl;

import com.shopxx.shopxxhr.entity.Employee;
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
    public RespPageBean getEmployeeByPage(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<Employee> all = employeeRepository.findAll(pageable);
        int totalSize = all.getSize();
        List<Employee> employees = all.getContent();
        RespPageBean respPageBean = new RespPageBean();
        respPageBean.setData(employees);
        respPageBean.setTotal(totalSize);
        return respPageBean;
    }

}

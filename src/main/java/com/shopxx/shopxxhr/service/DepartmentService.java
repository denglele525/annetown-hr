package com.shopxx.shopxxhr.service;

import com.shopxx.shopxxhr.entity.Department;

import java.util.List;

public interface DepartmentService {

    List<Department> getAllDepartmentsByParentId(Integer pid);

    Department saveOrUpdateDepartment(Department dep);

}

package com.shopxx.shopxxhr.Controller.system.basic;

import com.shopxx.shopxxhr.bean.RespBean;
import com.shopxx.shopxxhr.entity.Department;
import com.shopxx.shopxxhr.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/system/basic/department")
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;

    @GetMapping("/")
    public List<Department> getAllDepartments() {
        return departmentService.getAllDepartmentsByParentId(-1);
    }

    @PostMapping("/")
    public RespBean addDep(@RequestBody Department dep) {
        Department result = departmentService.saveOrUpdateDepartment(dep);
        if (result != null) {
            return RespBean.ofSuccess(result);
        }
        return RespBean.ofError("添加失败！");
    }

}

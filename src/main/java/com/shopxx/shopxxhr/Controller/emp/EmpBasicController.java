package com.shopxx.shopxxhr.Controller.emp;

import com.shopxx.shopxxhr.bean.RespBean;
import com.shopxx.shopxxhr.entity.Employee;
import com.shopxx.shopxxhr.entity.RespPageBean;
import com.shopxx.shopxxhr.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/emp/basic")
public class EmpBasicController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/")
    public RespPageBean getEmployeeByPage(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size, String keyword) {
        return employeeService.getEmployeeByPage(page, size, keyword);
    }

    @PostMapping("/")
    public RespBean addEmp(@RequestBody Employee employee) {
        Employee result = employeeService.saveOrUpdateDepartment(employee);
        if (result != null) {
            return RespBean.ofSuccess(result);
        }
        return RespBean.ofError("添加失败！");
    }

}

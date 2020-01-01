package com.shopxx.shopxxhr.Controller.emp;

import com.shopxx.shopxxhr.bean.RespBean;
import com.shopxx.shopxxhr.entity.*;
import com.shopxx.shopxxhr.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/emp/basic")
public class EmpBasicController {

    @Autowired
    EmployeeService employeeService;
    @Autowired
    NationService nationService;
    @Autowired
    PoliticsstatusService politicsstatusService;
    @Autowired
    JobLevelService jobLevelService;
    @Autowired
    PositionService positionService;
    @Autowired
    DepartmentService departmentService;

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

    @GetMapping("/nations")
    public List<Nation> getAllNations() {
        return nationService.getAllNations();
    }

    @GetMapping("/politicsstatus")
    public List<Politicsstatus> getAllPoliticsstatuses() {
        return politicsstatusService.getAllPoliticsstatuses();
    }

    @GetMapping("/joblevels")
    public List<JobLevel> getAllJobLevels() {
        return jobLevelService.getAllJobLevels();
    }

    @GetMapping("/positions")
    public List<Position> getAllPositions() {
        return positionService.getAllPositions();
    }

    @GetMapping("/maxWorkID")
    public RespBean maxWorkID() {
        Integer maxWorkID = employeeService.maxWorkID();
        if (maxWorkID != 0) {
            return RespBean.build()
                    .setCode(200)
                    .setMessage(null)
                    .setData(String.format("%08d", maxWorkID + 1));
        }
        return RespBean.ofError("查询工号不正确!");
    }

    @GetMapping("/deps")
    public List<Department> getAllDepartments() {
        return departmentService.getAllDepartmentsByParentId(-1);
    }

}

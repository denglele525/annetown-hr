package com.shopxx.shopxxhr.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ExceptionEnum {

    POSITION_DELETE_FAILED(500, "删除职位失败(该数据有关联数据)"),
    DEPARTMENT_SUBDEPARTMENT_FAILED(500, "删除部门失败(该部门下有子部门)"),
    DEPARTMENT_EMPLOYEE_FAILED(500, "删除部门失败(该部门下有员工)"),
    JOBLEVEL_DELETE_FAILED(500, "删除职称失败(该数据有关联数据)"),
    HR_DELETE_FAILED(500, "删除操作员失败"),
    ROLR_DELETE_FAILED(500, "删除角色失败(该数据有关联数据)"),
    EMPLOYEE_DELETE_FAILED(500, "删除员工失败(该数据有关联数据)");
    private int value;

    private String message;

}
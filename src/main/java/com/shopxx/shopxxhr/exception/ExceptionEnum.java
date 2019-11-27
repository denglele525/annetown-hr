package com.shopxx.shopxxhr.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ExceptionEnum {

    POSITION_DELETE_FAILED(500, "删除职位失败(该数据有关联数据)");
    private int value;

    private String message;

}
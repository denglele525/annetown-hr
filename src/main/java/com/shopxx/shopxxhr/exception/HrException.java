package com.shopxx.shopxxhr.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class HrException extends RuntimeException {

    private ExceptionEnum exceptionEnum;

}
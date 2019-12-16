package com.shopxx.shopxxhr.utils;

import com.shopxx.shopxxhr.entity.Hr;
import org.springframework.security.core.context.SecurityContextHolder;

public class HrUtil {

    public static Hr getCurrentHr() {
        return (Hr) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}

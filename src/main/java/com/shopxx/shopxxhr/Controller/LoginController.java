package com.shopxx.shopxxhr.Controller;

import com.shopxx.shopxxhr.bean.RespBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @GetMapping("login")
    public RespBean login() {
        return RespBean.ofError("尚未登录，请重新登录！");
    }

}

package com.shopxx.shopxxhr.Controller.system;

import com.shopxx.shopxxhr.bean.RespBean;
import com.shopxx.shopxxhr.entity.Hr;
import com.shopxx.shopxxhr.service.HrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/system/hr")
public class HrController {

    @Autowired
    HrService hrService;

    @GetMapping("/")
    public List<Hr> getAllHrs() {
        return hrService.getAllHrs();
    }

    @PutMapping("/")
    public RespBean saveOrUpdateHr(@RequestBody Hr hr) {
        if (hrService.saveOrUpdateHr(hr) != null) {
            return RespBean.ok("更新成功");
        }
        return RespBean.ofError("更新失败！");
    }

}

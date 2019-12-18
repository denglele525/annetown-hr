package com.shopxx.shopxxhr.Controller.system;

import com.shopxx.shopxxhr.bean.RespBean;
import com.shopxx.shopxxhr.entity.Hr;
import com.shopxx.shopxxhr.entity.Role;
import com.shopxx.shopxxhr.service.HrService;
import com.shopxx.shopxxhr.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/system/hr")
public class HrController {

    @Autowired
    HrService hrService;
    @Autowired
    RoleService roleService;

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

    @GetMapping("/roles")
    public List<Role> getAllRoles() {
        return roleService.getAllRoles();
    }

    @PutMapping("/role")
    public RespBean updateHrRole(Integer hrid, Integer[] rids) {
        hrService.updateHrRole(hrid, rids);
        return RespBean.ok("更新成功！");
    }

}

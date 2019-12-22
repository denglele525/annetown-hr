package com.shopxx.shopxxhr.Controller.system;

import com.shopxx.shopxxhr.bean.RespBean;
import com.shopxx.shopxxhr.entity.Hr;
import com.shopxx.shopxxhr.entity.Role;
import com.shopxx.shopxxhr.exception.ExceptionEnum;
import com.shopxx.shopxxhr.exception.HrException;
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
    public List<Hr> getAllHrs(String keywords) {
        return hrService.getAllHrs(keywords);
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

    @DeleteMapping("/{id}")
    public RespBean deleteHrById(@PathVariable Integer id) {
        try {
            hrService.deleteHrById(id);
        } catch (Exception e) {
            throw new HrException(ExceptionEnum.HR_DELETE_FAILED);
        }
        return RespBean.ok("删除操作员成功！");
    }

}

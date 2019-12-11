package com.shopxx.shopxxhr.Controller.system.basic;

import com.shopxx.shopxxhr.bean.RespBean;
import com.shopxx.shopxxhr.entity.Menu;
import com.shopxx.shopxxhr.entity.Role;
import com.shopxx.shopxxhr.exception.ExceptionEnum;
import com.shopxx.shopxxhr.exception.HrException;
import com.shopxx.shopxxhr.service.MenuService;
import com.shopxx.shopxxhr.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/system/basic/permiss")
public class PermissController {

    @Autowired
    RoleService roleService;
    @Autowired
    MenuService menuService;

    @GetMapping("/")
    public List<Role> getAllRoles() {
        return roleService.getAllRoles();
    }

    @GetMapping("/menus")
    public List<Menu> getAllMenus() {
        return menuService.getAllMenus();
    }

    @GetMapping("/mids/{rid}")
    public List<Integer> getMidsByRid(@PathVariable Integer rid) {
        return menuService.getMidsByRid(rid);
    }

    @PutMapping("/")
    public RespBean updateMenuRole(Integer rid, Integer[] mids) {
        menuService.updateMenuRole(rid, mids);
        return RespBean.ok("更新成功！");
    }

    @PostMapping("/role")
    public RespBean addRole(@RequestBody Role role) {
        Role result = roleService.saveOrUpdateRole(role);
        if (result != null) {
            return RespBean.ok("添加成功！");
        }
        return RespBean.ofError("添加失败！");
    }

    @DeleteMapping("/{id}")
    public RespBean deleteRoleById(@PathVariable Integer id) {
        try {
            roleService.deleteRoleById(id);
        } catch (Exception e) {
            throw new HrException(ExceptionEnum.ROLR_DELETE_FAILED);
        }
        return RespBean.ok("删除成功！");
    }

}
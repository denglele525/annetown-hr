package com.shopxx.shopxxhr.Controller.system.basic;

import com.shopxx.shopxxhr.bean.RespBean;
import com.shopxx.shopxxhr.entity.Position;
import com.shopxx.shopxxhr.exception.ExceptionEnum;
import com.shopxx.shopxxhr.exception.HrException;
import com.shopxx.shopxxhr.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/system/basic/pos")
public class PositionController {

    @Autowired
    PositionService positionService;

    @GetMapping("/")
    List<Position> getAllPositions() {
        return positionService.getAllPositions();
    }

    @PostMapping("/")
    public RespBean saveOrUpdatePosition(@RequestBody Position position) {
        Position result = positionService.saveOrUpdatePosition(position);
        if (result != null) {
            return RespBean.ok("添加(更新)职位成功！");
        }
        return RespBean.ofError("添加(更新)职位失败！");
    }

    @DeleteMapping("/{id}")
    public RespBean deletePositionById(@PathVariable Integer id) {
        try {
            positionService.deletePositionById(id);
        } catch (Exception e) {
            throw new HrException(ExceptionEnum.POSITION_DELETE_FAILED);
        }
        return RespBean.ok("删除职位成功！");
    }

}
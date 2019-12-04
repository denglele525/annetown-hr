package com.shopxx.shopxxhr.Controller.system.basic;

import com.shopxx.shopxxhr.bean.RespBean;
import com.shopxx.shopxxhr.entity.JobLevel;
import com.shopxx.shopxxhr.exception.ExceptionEnum;
import com.shopxx.shopxxhr.exception.HrException;
import com.shopxx.shopxxhr.service.JobLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/system/basic/joblevel")
public class JobLevelController {

    @Autowired
    JobLevelService jobLevelService;

    @GetMapping("/")
    List<JobLevel> getAllJobLevels() {
        return jobLevelService.getAllJobLevels();
    }

    @PostMapping("/")
    public RespBean saveOrUpdateJobLevel(@RequestBody JobLevel jobLevel) {
        JobLevel result = jobLevelService.saveOrUpdateJobLevel(jobLevel);
        if (result != null) {
            return RespBean.ok("添加(更新)职称成功！");
        }
        return RespBean.ofError("添加(更新)职称失败！");
    }

    @DeleteMapping("/{id}")
    public RespBean deleteJobLevelById(@PathVariable Integer id) {
        try {
            jobLevelService.deleteJobLevelById(id);
        } catch (Exception e) {
            throw new HrException(ExceptionEnum.JOBLEVEL_DELETE_FAILED);
        }
        return RespBean.ok("删除职称成功！");
    }

    @DeleteMapping("/")
    public RespBean deletJobLevels(Integer[] ids) {
        try {
            jobLevelService.deleteJobLevelsByIds(ids);
        } catch (Exception e) {
            e.printStackTrace();
            throw new HrException(ExceptionEnum.JOBLEVEL_DELETE_FAILED);
        }
        return RespBean.ok("删除职称成功！");
    }

}

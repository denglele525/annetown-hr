package com.shopxx.shopxxhr.service;

import com.shopxx.shopxxhr.entity.JobLevel;

import java.util.List;

public interface JobLevelService {

    List<JobLevel> getAllJobLevels();

    JobLevel saveOrUpdateJobLevel(JobLevel jobLevel);

    void deleteJobLevelById(Integer id);

    JobLevel findOne(JobLevel jobLevel);

    void deleteJobLevelsByIds(Integer[] ids);

}

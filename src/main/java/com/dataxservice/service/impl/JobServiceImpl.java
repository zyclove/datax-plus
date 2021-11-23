package com.dataxservice.service.impl;

import com.dataxservice.dao.DataJobDao;
import com.dataxservice.models.DataJob;
import com.dataxservice.service.JobService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("jobService")
public class JobServiceImpl implements JobService {
    @Resource
    private DataJobDao jobDao;


    @Override
    public int addJob(DataJob job) {
        return jobDao.addJob(job);
    }
}

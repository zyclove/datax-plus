package com.dataxservice.service.impl;

import com.dataxservice.daos.JobLogDao;
import com.dataxservice.service.JobLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("jobLogsService")
public class JobLogServiceImpl implements JobLogService {
    @Resource
    private JobLogDao jobLogDao;

    @Override
    public int simpleCount() {
        return jobLogDao.testCount();
    }
}

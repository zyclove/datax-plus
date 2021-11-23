package com.dataxservice.service.impl;

import com.dataxservice.dao.DataJobLogDao;
import com.dataxservice.service.JobLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("jobLogService")
public class JobLogServiceImpl implements JobLogService {
    @Resource
    private DataJobLogDao jobLogDao;

    @Override
    public int simpleCount() {
        return jobLogDao.testCount();
    }
}

package com.dataxservice.services.impls;

import com.dataxservice.daos.JobLogDao;
import com.dataxservice.services.JobLogsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("jobLogsService")
public class JobLogsServiceImpl implements JobLogsService {
    @Resource
    private JobLogDao jobLogDao;

    @Override
    public int simpleCount() {
        return jobLogDao.testCount();
    }
}

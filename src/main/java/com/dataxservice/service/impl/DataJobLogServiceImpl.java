package com.dataxservice.service.impl;

import com.dataxservice.dao.DataJobLogDao;
import com.dataxservice.service.DataJobLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("dataJobLogService")
public class DataJobLogServiceImpl implements DataJobLogService {
    @Resource
    private DataJobLogDao dataJobLogDao;

    @Override
    public int simpleCount() {
        return dataJobLogDao.testCount();
    }
}

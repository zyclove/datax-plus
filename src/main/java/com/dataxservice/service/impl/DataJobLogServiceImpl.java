package com.dataxservice.service.impl;

import com.dataxservice.dao.DataJobLogDao;
import com.dataxservice.models.DataJobLog;
import com.dataxservice.service.DataJobLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("dataJobLogService")
public class DataJobLogServiceImpl implements DataJobLogService {
    @Resource
    private DataJobLogDao dataJobLogDao;

    @Override
    public int simpleCount() {
        return dataJobLogDao.testCount();
    }

    @Override
    public int retrieveBiggestLogIdByJobId(DataJobLog dataJobLog) {
        return dataJobLogDao.retrieveBiggestLogIdByJobId(dataJobLog);
    }

    @Override
    public int addDataJobLog(DataJobLog dataJobLog) {
        return dataJobLogDao.addDataJobLog(dataJobLog);
    }

    @Override
    public List<DataJobLog> retrieveLogsBiggerThanSpecifiedLogId(DataJobLog dataJobLog) {
        return dataJobLogDao.retrieveLogsBiggerThanSpecifiedLogId(dataJobLog);
    }
}

package com.datax.plus.service.impl;

import com.datax.plus.service.DataJobLogService;
import com.datax.plus.dao.DataJobLogDao;
import com.datax.plus.model.DataJobLog;
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

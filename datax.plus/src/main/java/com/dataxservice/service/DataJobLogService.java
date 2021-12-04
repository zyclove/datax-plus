package com.dataxservice.service;


import com.dataxservice.model.DataJobLog;

import java.util.List;

public interface DataJobLogService {
    public int simpleCount();

    int retrieveBiggestLogIdByJobId(DataJobLog dataJobLog);

    int addDataJobLog(DataJobLog dataJobLog);

    List<DataJobLog> retrieveLogsBiggerThanSpecifiedLogId(DataJobLog dataJobLog);

}

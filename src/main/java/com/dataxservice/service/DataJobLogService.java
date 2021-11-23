package com.dataxservice.service;


import com.dataxservice.models.DataJobLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DataJobLogService {
    public int simpleCount();

    int retrieveBiggestLogIdByJobId(DataJobLog dataJobLog);

    int addDataJobLog(DataJobLog dataJobLog);

    List<DataJobLog> retrieveLogsBiggerThanSpecifiedLogId(DataJobLog dataJobLog);

}

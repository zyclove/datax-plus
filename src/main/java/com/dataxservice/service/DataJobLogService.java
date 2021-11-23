package com.dataxservice.service;


import com.dataxservice.models.DataJobLog;

public interface DataJobLogService {
    public int simpleCount();

    int retrieveBiggestLogIdByJobId(DataJobLog dataJobLog);

    int addDataJobLog(DataJobLog dataJobLog);
}

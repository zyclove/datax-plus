package com.dataxservice.dao;

import com.dataxservice.models.DataJobLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface DataJobLogDao {
    int testCount();

    int retrieveBiggestLogIdByJobId(@Param("dataJobLog") DataJobLog dataJobLog);
    
    int addDataJobLog(@Param("dataJobLog") DataJobLog dataJobLog);
}

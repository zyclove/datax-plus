package com.datax.plus.dao;

import com.datax.plus.model.DataJobLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DataJobLogDao {
    int testCount();

    int retrieveBiggestLogIdByJobId(@Param("dataJobLog") DataJobLog dataJobLog);
    
    int addDataJobLog(@Param("dataJobLog") DataJobLog dataJobLog);

    List<DataJobLog> retrieveLogsBiggerThanSpecifiedLogId(@Param("dataJobLog") DataJobLog dataJobLog);
}

package com.dataxservice.dao;

import com.dataxservice.models.JobLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface JobLogDao {
    int testCount();

    int addLog(@Param("jobLog") JobLog jobLog);
}

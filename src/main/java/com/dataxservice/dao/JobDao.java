package com.dataxservice.dao;

import com.dataxservice.models.Job;
import org.apache.ibatis.annotations.Param;

public interface JobDao {
    int addJob(@Param("job") Job job);
}

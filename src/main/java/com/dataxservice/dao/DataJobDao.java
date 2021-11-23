package com.dataxservice.dao;

import com.dataxservice.models.DataJob;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface DataJobDao {
    int addDataJob(@Param("dataJob") DataJob datajob);
}

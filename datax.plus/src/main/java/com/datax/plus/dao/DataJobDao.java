package com.datax.plus.dao;

import com.datax.plus.model.DataJob;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface DataJobDao {
    int addDataJob(@Param("dataJob") DataJob datajob);
    int updateDataJobStatus(@Param("dataJob") DataJob datajob);
    DataJob retrieveDataJobById(@Param("dataJob") DataJob datajob);
}

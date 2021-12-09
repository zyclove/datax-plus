package com.datax.plus.dao;

import com.datax.plus.model.DataJob;
import com.datax.plus.model.view.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DataJobDao {
    int addDataJob(@Param("dataJob") DataJob datajob);
    int updateDataJobStatus(@Param("dataJob") DataJob datajob);
    DataJob retrieveDataJobById(@Param("dataJob") DataJob datajob);
    List<DataJob> pageRetrieveDataJob(@Param("dataJob") DataJob datajob, @Param("page") Page page);
    Long pageRetrieveDataJobCount(@Param("dataJob") DataJob datajob);
}

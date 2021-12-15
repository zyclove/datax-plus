package com.datax.plus.dao;

import com.datax.plus.model.DataJob;
import com.datax.plus.model.DataSource;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface DataSourceDao {
    int addDataSource(@Param("dataSource") DataSource dataSource);
    int updateDataSource(@Param("dataSource") DataSource dataSource);


}

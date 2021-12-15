package com.datax.plus.dao;

import com.datax.plus.model.DataSource;
import com.datax.plus.model.view.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DataSourceDao {
    int addDataSource(@Param("dataSource") DataSource dataSource);
    int updateDataSource(@Param("dataSource") DataSource dataSource);
    List<DataSource> pageRetrieveDataSource(@Param("dataSource") DataSource dataSource, @Param("page") Page page);
    Long pageRetrieveDataSourceCount(@Param("dataSource") DataSource dataSource);
}

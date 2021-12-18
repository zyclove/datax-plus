package com.datax.plus.dao;

import com.datax.plus.model.DataSourceType;
import com.datax.plus.model.view.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DataSourceTypeDao {

    List<DataSourceType> pageRetrieveDataSourceType(@Param("dataSourceType") DataSourceType dataSourceType, @Param("page") Page page);
    Long pageRetrieveDataSourceTypeCount(@Param("dataSourceType") DataSourceType dataSourceType);
}

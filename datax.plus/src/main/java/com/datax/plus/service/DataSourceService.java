package com.datax.plus.service;

import com.datax.plus.model.DataSource;
import com.datax.plus.model.view.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DataSourceService {
    int addDataSource( DataSource dataSource);
    int updateDataSource( DataSource dataSource);
    List<DataSource> pageRetrieveDataSource(DataSource dataSource, Page page);
    Long pageRetrieveDataSourceCount(DataSource dataSource);
    int deleteDataSource(DataSource dataSource);
    DataSource getDataSourceByDataSourceId(int dataSourceId);
}

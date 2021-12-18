package com.datax.plus.service;

import com.datax.plus.model.DataSourceType;
import com.datax.plus.model.view.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DataSourceTypeService {
    List<DataSourceType> pageRetrieveDataSourceType( DataSourceType dataSourceType, Page page);
    Long pageRetrieveDataSourceTypeCount(DataSourceType dataSourceType);
}

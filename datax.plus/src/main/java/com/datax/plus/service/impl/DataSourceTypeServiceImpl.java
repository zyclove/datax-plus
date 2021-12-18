package com.datax.plus.service.impl;

import com.datax.plus.dao.DataSourceDao;
import com.datax.plus.dao.DataSourceTypeDao;
import com.datax.plus.model.DataSourceType;
import com.datax.plus.model.view.Page;
import com.datax.plus.service.DataSourceTypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("dataSourceTypeService")
public class DataSourceTypeServiceImpl implements DataSourceTypeService {
    @Resource
    private DataSourceTypeDao dataSourceTypeDao;

    @Override
    public List<DataSourceType> pageRetrieveDataSourceType(DataSourceType dataSourceType, Page page) {
        return dataSourceTypeDao.pageRetrieveDataSourceType(dataSourceType, page);
    }

    @Override
    public Long pageRetrieveDataSourceTypeCount(DataSourceType dataSourceType) {
        return dataSourceTypeDao.pageRetrieveDataSourceTypeCount(dataSourceType);
    }
}

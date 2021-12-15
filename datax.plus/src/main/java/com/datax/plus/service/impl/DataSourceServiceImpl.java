package com.datax.plus.service.impl;

import com.datax.plus.dao.DataSourceDao;
import com.datax.plus.model.DataSource;
import com.datax.plus.model.view.Page;
import com.datax.plus.service.DataSourceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("dataSourceService")
public class DataSourceServiceImpl implements DataSourceService {
    @Resource
    private DataSourceDao dataSourceDao;

    @Override
    public int addDataSource(DataSource dataSource) {
        return dataSourceDao.addDataSource(dataSource);
    }

    @Override
    public int updateDataSource(DataSource dataSource) {
        return dataSourceDao.updateDataSource(dataSource);
    }

    @Override
    public List<DataSource> pageRetrieveDataSource(DataSource dataSource, Page page) {
        return dataSourceDao.pageRetrieveDataSource(dataSource, page);
    }

    @Override
    public Long pageRetrieveDataSourceCount(DataSource dataSource) {
        return dataSourceDao.pageRetrieveDataSourceCount(dataSource);
    }
}

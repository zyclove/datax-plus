package com.datax.plus.service.impl;

import com.datax.plus.model.view.Page;
import com.datax.plus.service.DataJobService;
import com.datax.plus.dao.DataJobDao;
import com.datax.plus.model.DataJob;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("dataJobService")
public class DataJobServiceImpl implements DataJobService {
    @Resource
    private DataJobDao dataJobDao;


    @Override
    public int addDataJob(DataJob dataJob) {
        return dataJobDao.addDataJob(dataJob);
    }

    @Override
    public int updateDataJobStatus(DataJob datajob) {
        return dataJobDao.updateDataJobStatus(datajob);
    }

    @Override
    public DataJob retrieveDataJobById(DataJob datajob) {
        return dataJobDao.retrieveDataJobById(datajob);
    }

    @Override
    public List<DataJob> pageRetrieveDataJob(DataJob datajob, Page page) {
        return dataJobDao.pageRetrieveDataJob(datajob, page);
    }

    @Override
    public Long pageRetrieveDataJobCount(DataJob datajob) {
        return dataJobDao.pageRetrieveDataJobCount(datajob);
    }
}

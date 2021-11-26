package com.dataxservice.service.impl;

import com.dataxservice.dao.DataJobDao;
import com.dataxservice.model.DataJob;
import com.dataxservice.service.DataJobService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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
}

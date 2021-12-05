package com.datax.plus.service;

import com.datax.plus.model.DataJob;


public interface DataJobService {
    int addDataJob(DataJob datajob);

    int updateDataJobStatus(DataJob datajob);

    DataJob retrieveDataJobById(DataJob datajob);

}

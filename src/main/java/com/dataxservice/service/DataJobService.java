package com.dataxservice.service;

import com.dataxservice.model.DataJob;
import org.apache.ibatis.annotations.Param;


public interface DataJobService {
    int addDataJob(DataJob datajob);

    int updateDataJobStatus( DataJob datajob);

}

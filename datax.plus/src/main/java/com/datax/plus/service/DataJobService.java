package com.datax.plus.service;

import com.datax.plus.model.DataJob;
import com.datax.plus.model.view.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface DataJobService {
    int addDataJob(DataJob datajob);

    int updateDataJobStatus(DataJob datajob);

    DataJob retrieveDataJobById(DataJob datajob);

    List<DataJob> pageRetrieveDataJob(@Param("dataJob") DataJob datajob, @Param("page") Page page);

    Long pageRetrieveDataJobCount(@Param("dataJob") DataJob datajob);

}

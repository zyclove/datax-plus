package com.datax.plus.controller;

import com.datax.plus.model.DataSource;
import com.datax.plus.model.User;
import com.datax.plus.model.view.DataSourceList;
import com.datax.plus.model.view.HttpRequestResult;
import com.datax.plus.model.view.Page;
import com.datax.plus.model.view.ResultBaseVO;
import com.datax.plus.service.DataSourceService;
import com.datax.plus.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/dataSource")
public class DataSourceController {

    @Autowired
    private DataSourceService dataSourceService;

    @RequestMapping(value = "/list/{pageNum}/{limit}/{dataSourceId}", method = RequestMethod.GET)
    public @ResponseBody
    HttpRequestResult list(@PathVariable int pageNum,
                           @PathVariable int limit,
                           @PathVariable int dataSourceId) {
        HttpRequestResult req = new HttpRequestResult();
        req.setCode(20000);
        req.setMsg("");

        Page page = new Page(pageNum, limit);

        DataSource searchBean = new DataSource();
        searchBean.setDataSourceId(dataSourceId);
        Long total = dataSourceService.pageRetrieveDataSourceCount(searchBean);
        List<DataSource> results = dataSourceService.pageRetrieveDataSource(searchBean, page);

        DataSourceList dataSourceListObj = new DataSourceList();

        dataSourceListObj.setList(results);
        dataSourceListObj.setTotal(total);

        req.setData(dataSourceListObj);
        return req;
    }

    @RequestMapping(value = "/addOrUpdate", method = RequestMethod.POST)
    public @ResponseBody
    HttpRequestResult addOrUpdate(@RequestBody DataSource dataSource) {
        HttpRequestResult req = new HttpRequestResult();
        req.setCode(20000);
        req.setMsg("");
        ResultBaseVO resultData = new ResultBaseVO();
        if (dataSource.getDataSourceId() == 0) {
            dataSourceService.addDataSource(dataSource);
            if (dataSource.getDataSourceId() > 0) {
                resultData.setCode(1);
            }
        }
        req.setData(resultData);
        return req;
    }

}

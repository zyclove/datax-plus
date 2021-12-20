package com.datax.plus.controller;

import com.datax.plus.core.simpleDbAccess.DbAccessUtil;
import com.datax.plus.model.DataSource;
import com.datax.plus.model.DataSourceType;
import com.datax.plus.model.User;
import com.datax.plus.model.view.*;
import com.datax.plus.service.DataSourceService;
import com.datax.plus.service.DataSourceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/dataSource")
public class DataSourceController {

    @Autowired
    private DataSourceService dataSourceService;

    @Autowired
    private DataSourceTypeService dataSourceTypeService;

    @RequestMapping(value = "/typeList/{pageNum}/{limit}/{dataSourceTypeId}", method = RequestMethod.GET)
    public @ResponseBody
    HttpRequestResult typeList(@PathVariable int pageNum,
                               @PathVariable int limit,
                               @PathVariable int dataSourceTypeId) {
        HttpRequestResult req = new HttpRequestResult();
        req.setCode(20000);
        req.setMsg("");

        Page page = new Page(pageNum, limit);

        DataSourceType searchBean = new DataSourceType();
        searchBean.setDataSourceTypeId(dataSourceTypeId);
        Long total = dataSourceTypeService.pageRetrieveDataSourceTypeCount(searchBean);
        List<DataSourceType> results = dataSourceTypeService.pageRetrieveDataSourceType(searchBean, page);

        DataSourceTypeList dataSourceTypeListObj = new DataSourceTypeList();

        dataSourceTypeListObj.setList(results);
        dataSourceTypeListObj.setTotal(total);

        req.setData(dataSourceTypeListObj);
        return req;
    }


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

    @RequestMapping(value = "/checkConnection", method = RequestMethod.POST)
    public @ResponseBody
    HttpRequestResult checkConnection(@RequestBody DataSource dataSource) {
        HttpRequestResult req = new HttpRequestResult();
        req.setCode(20000);
        req.setMsg("");
        ResultBaseVO resultData = new ResultBaseVO();

        int checkResult = 0;

        DbAccessUtil dbAccessUtil = null;
        try {
            dbAccessUtil = new DbAccessUtil(dataSource);
            List<Map<String, Object>> list = dbAccessUtil.simpleQuery(DbAccessUtil.SHOW_TABLES, null);
            checkResult = 1;
        } catch (SQLException e) {
            // e.printStackTrace();
        }

        resultData.setCode(checkResult);
        req.setData(resultData);
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
        } else {
            int result = dataSourceService.updateDataSource(dataSource);
            if (result > 0) {
                resultData.setCode(1);
            }
        }
        req.setData(resultData);
        return req;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public @ResponseBody
    HttpRequestResult delete(@PathVariable int id) {
        HttpRequestResult req = new HttpRequestResult();
        req.setCode(20000);
        req.setMsg("");
        DataSource dataSource = new DataSource();
        dataSource.setDataSourceId(id);
        ResultBaseVO resultData = new ResultBaseVO();
        int result = dataSourceService.deleteDataSource(dataSource);
        if (result > 0) {
            resultData.setCode(1);
        }
        req.setData(resultData);
        return req;
    }

}

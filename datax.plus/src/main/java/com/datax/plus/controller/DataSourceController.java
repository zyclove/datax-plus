package com.datax.plus.controller;

import com.datax.plus.core.simpleDbAccess.DbAccessUtil;
import com.datax.plus.model.DataJob;
import com.datax.plus.model.DataSource;
import com.datax.plus.model.DataSourceType;
import com.datax.plus.model.User;
import com.datax.plus.model.view.*;
import com.datax.plus.service.DataSourceService;
import com.datax.plus.service.DataSourceTypeService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.*;

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


    @RequestMapping(value = "/tables/{dataSourceId}", method = RequestMethod.GET)
    public @ResponseBody
    HttpRequestResult tables(@PathVariable int dataSourceId) {
        HttpRequestResult req = new HttpRequestResult();
        req.setCode(20000);
        req.setMsg("");

        DataSource dataSource = dataSourceService.getDataSourceByDataSourceId(dataSourceId);
        List list = new ArrayList();
        try {
            DbAccessUtil dbAccessUtil = new DbAccessUtil(dataSource);
            List tempList = new ArrayList();
            tempList = dbAccessUtil.simpleQuery(DbAccessUtil.SHOW_TABLES, "");
            Iterator iterator = tempList.iterator();
            while (iterator.hasNext()) {
                Map tempMap = (Map) iterator.next();
                Iterator iteratorTemp = tempMap.values().iterator();
                while (iteratorTemp.hasNext()) {
                    list.add(iteratorTemp.next().toString());
                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        StringList stringList = new StringList();
        stringList.setList(list);

        req.setData(stringList);
        return req;
    }


    @RequestMapping(value = "/columns/{dataSourceId}/{tableName}", method = RequestMethod.GET)
    public @ResponseBody
    HttpRequestResult columns(@PathVariable int dataSourceId, @PathVariable String tableName) {
        HttpRequestResult req = new HttpRequestResult();
        req.setCode(20000);
        req.setMsg("");

        DataSource dataSource = dataSourceService.getDataSourceByDataSourceId(dataSourceId);
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        try {
            DbAccessUtil dbAccessUtil = new DbAccessUtil(dataSource);
            List tempList = new ArrayList();
            tempList = dbAccessUtil.simpleQuery(DbAccessUtil.DESC_TABLE, tableName);
            Iterator iterator = tempList.iterator();
            while (iterator.hasNext()) {
                Map tempMap = (Map) iterator.next();
                String fieldName = tempMap.get("Field").toString();
                String columnType = tempMap.get("Type").toString();
                Map newMap = new HashMap();
                newMap.put("fieldName", fieldName);
                newMap.put("columnType", columnType);
                list.add(newMap);
//                Iterator iteratorTemp = tempMap.values().iterator();
//                while( iteratorTemp.hasNext() ){
//                    list.add(iteratorTemp.next().toString());
//                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        MapList mapList = new MapList();
        mapList.setList(list);

        req.setData(mapList);
        return req;
    }


    @RequestMapping(value = "/applySql", method = RequestMethod.POST)
    public @ResponseBody
    HttpRequestResult applySql(@RequestBody DataJob dataJob) {
        HttpRequestResult req = new HttpRequestResult();
        req.setCode(20000);
        req.setMsg("");

        DataSource dataSource = dataSourceService.getDataSourceByDataSourceId(dataJob.getSource().getDataSourceId());

        SimpleSqlResult sqlResult = new SimpleSqlResult();

        List<DbColumn> columns = new ArrayList<DbColumn>();

        List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
        try {
            DbAccessUtil dbAccessUtil = new DbAccessUtil(dataSource);
            dataList = dbAccessUtil.simpleQuery(DbAccessUtil.SIMPLE_QUERY, dataJob.getSqlBody());

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        if (CollectionUtils.isNotEmpty(dataList)) {
            Map<String, Object> mapTemp = dataList.get(0);
            List<String> columnsString = new ArrayList<String>(mapTemp.keySet());
            Iterator<String> iterator = columnsString.iterator();
            while (iterator.hasNext()){
                DbColumn dbColumn = new DbColumn();
                dbColumn.setColumnName(iterator.next());
                columns.add(dbColumn);
            }
        }
        // MapList mapList = new MapList();
        //mapList.setList(list);
        sqlResult.setColumns(columns);
        sqlResult.setDataList(dataList);

        req.setData(sqlResult);
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

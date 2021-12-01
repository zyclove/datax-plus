package com.dataxservice.controller;

import com.alibaba.fastjson.JSONObject;
import com.dataxservice.constant.DataJobStatus;
import com.dataxservice.core.Worker;
import com.dataxservice.model.DataJob;
import com.dataxservice.service.DataJobService;
import com.dataxservice.service.DataJobLogService;
import com.dataxservice.util.RedisUtil;
import javafx.print.PrinterJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;


import com.alibaba.datax.core.Engine;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.xml.crypto.Data;
import java.util.LinkedHashMap;
import java.util.Map;


@RestController
public class SimpleController {

    Logger LOG = LoggerFactory.getLogger(SimpleController.class);

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private DataJobLogService jobLogsService;

    @Autowired
    private DataJobService dataJobService;


    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test() {
        java.util.LinkedHashMap object = (LinkedHashMap) redisUtil.lPopData("loging-test");

        return String.valueOf(jobLogsService.simpleCount());
    }

    @RequestMapping(value = "/testReadLog/{logId}", method = RequestMethod.GET)
    public String testReadLog(@PathVariable String logId) {
        String loginfo;
        do {
            loginfo = redisUtil.lPopData("[" + logId + "]").toString();
            System.out.println(loginfo);
        } while (loginfo == null);
        System.out.println("read");
        return "Hello world";
    }

    @RequestMapping(value = "/simple", method = RequestMethod.GET)
    public String hello() {


//        System.setProperty("datax.home", "D:\\dev\\workspaces\\DataX\\target\\datax\\datax");
        String tempDir = System.getProperty("tempfile.dir");
        String[] datxArgs = {"-job", "D:\\dev\\workspaces\\datax_java_without_python\\src\\main\\resources\\stream2stream.json", "-mode", "standalone", "-jobid", "-1"};
//
        try {
            Engine.entry(datxArgs);   //从这里启动
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return "Hello world";
    }

    // https://www.jianshu.com/p/bb9189ec523e

    @RequestMapping(value = "/mysql/{jobId}", method = RequestMethod.GET)
    public String mysql(@PathVariable long jobId) {
        String tempDir = System.getProperty("tempfile.dir");
        long startTime = System.currentTimeMillis();

        Worker worker = new Worker();
        worker.setJsonFileName("mysql2mysql.json");
        worker.setJobId(jobId);

        Thread thread1 = new Thread(worker);

        thread1.start();

        System.out.println("---???????>>>>");

        long endTime = System.currentTimeMillis();

        float seconds = (endTime - startTime) / 1000F;

        return "Hello worldeeeee ---------------" + seconds;
    }


    @RequestMapping(value = "/jobReport", method = RequestMethod.POST)
    public String jobReport(HttpServletRequest request) {
        LOG.info("HOOK REQUEST..........................");
//        JSONObject jsonObject = JSONObject.parseObject(jobConfiguration.toJSON());
//        LOG.info("jobResult: "+ request.getParameter("jobResult"));
        JSONObject jsonObject = JSONObject.parseObject(request.getParameter("jobResult"));

        Map<String, Object> jobResult = (Map<String, Object>) jsonObject;//    //json对象转Map
        Map<String, Object> core = (Map<String, Object>)jobResult.get("core");
        Map<String, Object> container = (Map<String, Object>)core.get("container");
        Map<String, Object> job = (Map<String, Object>)container.get("job");
        String id = job.get("id").toString();

        LOG.info("The hook result ID is : "+id);

        DataJob dataJob = new DataJob();
        dataJob.setDataJobId(Integer.parseInt(id));
        dataJob.setStatus(DataJobStatus.SUCCESS);
        dataJobService.updateDataJobStatus(dataJob);

        LOG.info("HOOK REQUEST--------------------------");
        //Update this job's status to successed

        return "done";
    }


    @RequestMapping(value = "/jobSubmit", method = RequestMethod.GET)
    public String jobSubmit() {

        String tempDir = System.getProperty("tempfile.dir");
        long startTime = System.currentTimeMillis();

        //Generate Job record
        //Job job =  new Job();
        DataJob dataJob = new DataJob();
        dataJob.setDataJobName("");
        dataJob.setStatus(DataJobStatus.PROCESSING);
        dataJobService.addDataJob(dataJob);
        // jobService

        Worker worker = new Worker();
        worker.setJsonFileName("mysql2mysql.json");
        worker.setJobId(dataJob.getDataJobId());

        Thread thread1 = new Thread(worker);
        thread1.start();

        long endTime = System.currentTimeMillis();
        float seconds = (endTime - startTime) / 1000F;
        return "Job submitted : " + seconds;
    }

    public static void main(String args[]) {
//        System.out.println("--->>");
//        String aaa = "{\"common\":{\"column\":{\"dateFormat\":\"yyyy-MM-dd\",\"datetimeFormat\":\"yyyy-MM-dd HH:mm:ss\",\"encoding\":\"utf-8\",\"extraFormats\":[\"yyyyMMdd\"],\"timeFormat\":\"HH:mm:ss\",\"timeZone\":\"GMT+8\"}},\"core\":{\"container\":{\"job\":{\"id\":26,\"mode\":\"standalone\",\"reportInterval\":10000},\"taskGroup\":{\"channel\":5},\"trace\":{\"enable\":\"false\"}},\"dataXServer\":{\"address\":\"http://localhost:7001/api\",\"reportDataxLog\":false,\"reportPerfLog\":false,\"timeout\":10000},\"statistics\":{\"collector\":{\"plugin\":{\"maxDirtyNumber\":10,\"taskClass\":\"com.alibaba.datax.core.statistics.plugin.task.StdoutPluginCollector\"}}},\"transport\":{\"channel\":{\"byteCapacity\":67108864,\"capacity\":512,\"class\":\"com.alibaba.datax.core.transport.channel.memory.MemoryChannel\",\"flowControlInterval\":20,\"speed\":{\"byte\":-1,\"record\":-1}},\"exchanger\":{\"bufferSize\":32,\"class\":\"com.alibaba.datax.core.plugin.BufferedRecordExchanger\"}}},\"entry\":{\"jvm\":\"-Xms1G -Xmx1G\"},\"job\":{\"content\":[{\"reader\":{\"name\":\"mysqlreader\",\"parameter\":{\"column\":\"pk_col_a,col_name,col_value\",\"columnList\":[\"pk_col_a\",\"col_name\",\"col_value\"],\"fetchSize\":-2147483648,\"isTableMode\":true,\"jdbcUrl\":\"jdbc:mysql://192.168.16.80:3366/source_db?useUnicode=true&characterEncoding=utf8&yearIsDateType=false&zeroDateTimeBehavior=convertToNull&tinyInt1isBit=false&rewriteBatchedStatements=true\",\"loadBalanceResourceMark\":\"192.168.16.80\",\"password\":\"1234\",\"pkType\":\"pkTypeLong\",\"querySql\":\"select pk_col_a,col_name,col_value from table_for_t  where  (1 <= pk_col_a AND pk_col_a < 2) \",\"splitPk\":\"pk_col_a\",\"table\":\"table_for_t\",\"tableNumber\":1,\"username\":\"root\"}},\"taskId\":0,\"writer\":{\"name\":\"mysqlwriter\",\"parameter\":{\"batchSize\":2048,\"column\":[\"col_a\",\"col_b\",\"col_c\"],\"insertOrReplaceTemplate\":\"insert INTO %s (col_a,col_b,col_c) VALUES(?,?,?)\",\"jdbcUrl\":\"jdbc:mysql://192.168.16.80:3388/target_db?useUnicode=true&characterEncoding=utf8&yearIsDateType=false&zeroDateTimeBehavior=convertToNull&rewriteBatchedStatements=true&tinyInt1isBit=false\",\"password\":\"1234\",\"session\":[\"set session sql_mode='ANSI'\"],\"table\":\"diag_8\",\"tableNumber\":1,\"username\":\"root\",\"writeMode\":\"insert\"}}},{\"reader\":{\"name\":\"mysqlreader\",\"parameter\":{\"column\":\"pk_col_a,col_name,col_value\",\"columnList\":[\"pk_col_a\",\"col_name\",\"col_value\"],\"fetchSize\":-2147483648,\"isTableMode\":true,\"jdbcUrl\":\"jdbc:mysql://192.168.16.80:3366/source_db?useUnicode=true&characterEncoding=utf8&yearIsDateType=false&zeroDateTimeBehavior=convertToNull&tinyInt1isBit=false&rewriteBatchedStatements=true\",\"loadBalanceResourceMark\":\"192.168.16.80\",\"password\":\"1234\",\"pkType\":\"pkTypeLong\",\"querySql\":\"select pk_col_a,col_name,col_value from table_for_t  where  (2 <= pk_col_a AND pk_col_a < 3) \",\"splitPk\":\"pk_col_a\",\"table\":\"table_for_t\",\"tableNumber\":1,\"username\":\"root\"}},\"taskId\":1,\"writer\":{\"name\":\"mysqlwriter\",\"parameter\":{\"batchSize\":2048,\"column\":[\"col_a\",\"col_b\",\"col_c\"],\"insertOrReplaceTemplate\":\"insert INTO %s (col_a,col_b,col_c) VALUES(?,?,?)\",\"jdbcUrl\":\"jdbc:mysql://192.168.16.80:3388/target_db?useUnicode=true&characterEncoding=utf8&yearIsDateType=false&zeroDateTimeBehavior=convertToNull&rewriteBatchedStatements=true&tinyInt1isBit=false\",\"password\":\"1234\",\"session\":[\"set session sql_mode='ANSI'\"],\"table\":\"diag_8\",\"tableNumber\":1,\"username\":\"root\",\"writeMode\":\"insert\"}}},{\"reader\":{\"name\":\"mysqlreader\",\"parameter\":{\"column\":\"pk_col_a,col_name,col_value\",\"columnList\":[\"pk_col_a\",\"col_name\",\"col_value\"],\"fetchSize\":-2147483648,\"isTableMode\":true,\"jdbcUrl\":\"jdbc:mysql://192.168.16.80:3366/source_db?useUnicode=true&characterEncoding=utf8&yearIsDateType=false&zeroDateTimeBehavior=convertToNull&tinyInt1isBit=false&rewriteBatchedStatements=true\",\"loadBalanceResourceMark\":\"192.168.16.80\",\"password\":\"1234\",\"pkType\":\"pkTypeLong\",\"querySql\":\"select pk_col_a,col_name,col_value from table_for_t  where  (3 <= pk_col_a AND pk_col_a <= 4) \",\"splitPk\":\"pk_col_a\",\"table\":\"table_for_t\",\"tableNumber\":1,\"username\":\"root\"}},\"taskId\":2,\"writer\":{\"name\":\"mysqlwriter\",\"parameter\":{\"batchSize\":2048,\"column\":[\"col_a\",\"col_b\",\"col_c\"],\"insertOrReplaceTemplate\":\"insert INTO %s (col_a,col_b,col_c) VALUES(?,?,?)\",\"jdbcUrl\":\"jdbc:mysql://192.168.16.80:3388/target_db?useUnicode=true&characterEncoding=utf8&yearIsDateType=false&zeroDateTimeBehavior=convertToNull&rewriteBatchedStatements=true&tinyInt1isBit=false\",\"password\":\"1234\",\"session\":[\"set session sql_mode='ANSI'\"],\"table\":\"diag_8\",\"tableNumber\":1,\"username\":\"root\",\"writeMode\":\"insert\"}}},{\"reader\":{\"name\":\"mysqlreader\",\"parameter\":{\"column\":\"pk_col_a,col_name,col_value\",\"columnList\":[\"pk_col_a\",\"col_name\",\"col_value\"],\"fetchSize\":-2147483648,\"isTableMode\":true,\"jdbcUrl\":\"jdbc:mysql://192.168.16.80:3366/source_db?useUnicode=true&characterEncoding=utf8&yearIsDateType=false&zeroDateTimeBehavior=convertToNull&tinyInt1isBit=false&rewriteBatchedStatements=true\",\"loadBalanceResourceMark\":\"192.168.16.80\",\"password\":\"1234\",\"pkType\":\"pkTypeLong\",\"querySql\":\"select pk_col_a,col_name,col_value from table_for_t  where  pk_col_a IS NULL\",\"splitPk\":\"pk_col_a\",\"table\":\"table_for_t\",\"tableNumber\":1,\"username\":\"root\"}},\"taskId\":3,\"writer\":{\"name\":\"mysqlwriter\",\"parameter\":{\"batchSize\":2048,\"column\":[\"col_a\",\"col_b\",\"col_c\"],\"insertOrReplaceTemplate\":\"insert INTO %s (col_a,col_b,col_c) VALUES(?,?,?)\",\"jdbcUrl\":\"jdbc:mysql://192.168.16.80:3388/target_db?useUnicode=true&characterEncoding=utf8&yearIsDateType=false&zeroDateTimeBehavior=convertToNull&rewriteBatchedStatements=true&tinyInt1isBit=false\",\"password\":\"1234\",\"session\":[\"set session sql_mode='ANSI'\"],\"table\":\"diag_8\",\"tableNumber\":1,\"username\":\"root\",\"writeMode\":\"insert\"}}}],\"setting\":{\"speed\":{\"channel\":5}}},\"plugin\":{\"reader\":{\"mysqlreader\":{\"class\":\"com.alibaba.datax.plugin.reader.mysqlreader.MysqlReader\",\"description\":\"useScene: prod. mechanism: Jdbc connection using the database, execute select sql, retrieve data from the ResultSet. warn: The more you know about the database, the less problems you encounter.\",\"developer\":\"alibaba\",\"name\":\"mysqlreader\",\"path\":\"/opt/local/datax/plugin/reader/mysqlreader\"}},\"writer\":{\"mysqlwriter\":{\"class\":\"com.alibaba.datax.plugin.writer.mysqlwriter.MysqlWriter\",\"description\":\"useScene: prod. mechanism: Jdbc connection using the database, execute insert sql. warn: The more you know about the database, the less problems you encounter.\",\"developer\":\"alibaba\",\"name\":\"mysqlwriter\",\"path\":\"/opt/local/datax/plugin/writer/mysqlwriter\"}}}}\n";
//        JSONObject jsonObject = JSONObject.parseObject(aaa);
    }


}

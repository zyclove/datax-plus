package com.datax.plus.thread;

import com.datax.plus.service.DataJobLogService;
import com.datax.plus.model.DataJob;
import com.datax.plus.model.DataJobLog;
import com.datax.plus.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;


//https://blog.csdn.net/qq_31145141/article/details/103864600

@Component
@Order(value = 1)
public class AutoLogConsummer implements CommandLineRunner {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private DataJobLogService dataJobLogService;

    @Override
    @Async
    public void run(String... args) throws Exception {
        while (true) {
            java.util.LinkedHashMap object = (LinkedHashMap) redisUtil.lPopData("loging-test");
            if (null == object) {
                continue;
            } else {
                // 将日志解析并放入相应的消息队列
                if (object.get("message") != null && object.get("message").toString().indexOf("[job-") > 0) {
                    String jobId = this.getJobIdFromLog(object.get("message").toString());
                    String removedCharsJobId = jobId.replace("[job-","").replace("]","");
                    //Write into database0
                    //Firstly, retrieve the biggest number of current log record, then add 1
                    DataJobLog dataJobLog = new DataJobLog();
                    DataJob dataJob = new DataJob();
                    dataJob.setDataJobId(Integer.parseInt(removedCharsJobId));
                    dataJobLog.setDataJob(dataJob);
                    // --------------------
                    int biggestLogId = dataJobLogService.retrieveBiggestLogIdByJobId(dataJobLog);
                    dataJobLog.setLogId(biggestLogId+1);
                    //Write log into database
                    dataJobLog.setLogBody(object.get("message").toString().replaceFirst("-",""));
                    dataJobLogService.addDataJobLog(dataJobLog);
                }
            }
        }
    }

    private String getJobIdFromLog(String logBody) {
        if (logBody != null) {
            String a = logBody.substring(logBody.indexOf("[job-"), logBody.length());
            a = a.substring(0, a.indexOf("]") + 1);
            return a;
        } else {
            return "";
        }
    }

    public static void main(String[] args) {
        String testworkds = "-[2021-11-18 14:46:30.621] ERROR 6 --- [job-99887232323237] com.alibaba.datax.common.util.RetryUtil : Exception when calling callable, 即将尝试执行第1次重试.本次重试计划等待[1000]ms,实际等待[1000]ms, 异常Msg:[DataX无法连接对应的数据库，可能原因是：1) 配置的ip/port/database/jdbc错误，无法连接。2) 配置的username/password错误，鉴权失败。请和DBA确认该数据库的连接信息是否正确。]";
        AutoLogConsummer autoLogConsummer = new AutoLogConsummer();
        System.out.println(autoLogConsummer.getJobIdFromLog(testworkds));
        String removedCharsJobId = autoLogConsummer.getJobIdFromLog(testworkds).replace("[job-","").replace("]","");
        System.out.println(removedCharsJobId);
    }
}

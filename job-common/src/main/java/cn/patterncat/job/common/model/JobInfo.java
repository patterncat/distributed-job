package cn.patterncat.job.common.model;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * Created by patterncat on 2017-11-03.
 */
@Data
@Builder
public class JobInfo {

    private String id;

    private String name;

    //根据group来调度,不同的group对应不同的handlerClz
    //分布式只是相对于各个服务的instance而言
    private String group;

    private String handlerClz;

    private Date createdTime;

    private Date dueTime;

    private Date fireTime;

    private Date finishTime;

    private JobStatus jobStatus;

    private ScheduleType scheduleType;

    private long delayInMillis;

    private String cron;

    private String error;

    private String result;

}
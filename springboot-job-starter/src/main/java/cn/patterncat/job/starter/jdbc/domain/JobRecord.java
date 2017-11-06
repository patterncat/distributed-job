package cn.patterncat.job.starter.jdbc.domain;

import cn.patterncat.job.common.model.JobStatus;
import cn.patterncat.job.common.model.ScheduleType;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.Date;

/**
 * Created by patterncat on 2017-11-06.
 */
@Entity
@Data
@Builder
public class JobRecord {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    //根据group来调度,不同的group对应不同的handlerClz
    //分布式只是相对于各个服务的instance而言
    private String jobGroup;

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

    @Tolerate
    public JobRecord(){

    }
}

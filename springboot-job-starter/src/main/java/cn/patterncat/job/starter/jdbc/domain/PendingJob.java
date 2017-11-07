package cn.patterncat.job.starter.jdbc.domain;

import cn.patterncat.job.common.model.JobStatus;
import cn.patterncat.job.common.model.ScheduleType;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by patterncat on 2017-11-07.
 */
@Entity
@Data
@Builder
public class PendingJob {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String jobGroup;

    private String handlerClz;

    private Date dueTime;

    private JobStatus jobStatus;

    private ScheduleType scheduleType;

    private long delayInMillis;

    private String createBy;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
}

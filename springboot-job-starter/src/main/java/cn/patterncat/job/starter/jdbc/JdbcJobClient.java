package cn.patterncat.job.starter.jdbc;

import cn.patterncat.job.common.api.JobClient;
import cn.patterncat.job.common.model.JobInfo;
import cn.patterncat.job.common.model.JobResp;
import cn.patterncat.job.starter.jdbc.dao.PendingJobDao;
import cn.patterncat.job.starter.jdbc.domain.PendingJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by patterncat on 2017-11-06.
 */
public class JdbcJobClient implements JobClient<Long,PendingJob>{

    @Autowired
    ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    PendingJobDao pendingJobDao;


    @Override
    public JobResp<Page<PendingJob>> fetchJobByGroup(String group, Pageable pageable) {
        return null;
    }

    @Override
    public JobResp<Page<PendingJob>> fetchJobByHandlerClz(String handlerClz, Pageable pageable) {
        return null;
    }

    @Override
    public JobResp newJob(PendingJob jobInfo) {
        return null;
    }

    @Override
    public JobResp claimJob(Long jobId) {
        return null;
    }

    @Override
    public JobResp updateJob(PendingJob jobInfo) {
        return null;
    }
}

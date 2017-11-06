package cn.patterncat.job.client.jdbc;

import cn.patterncat.job.common.api.JobClient;
import cn.patterncat.job.common.model.JobInfo;
import cn.patterncat.job.common.model.JobResp;

import java.util.List;

/**
 * Created by patterncat on 2017-11-06.
 */
public class JdbcJobClient implements JobClient{
    @Override
    public JobResp<List<JobInfo>> fetchJobByGroup(String group) {
        return null;
    }

    @Override
    public JobResp<List<JobInfo>> fetchJobByHandlerClz(String handlerClz) {
        return null;
    }

    @Override
    public JobResp newJob(JobInfo jobInfo) {
        return null;
    }

    @Override
    public JobResp claimJob(String jobId) {
        return null;
    }

    @Override
    public JobResp updateJob(JobInfo jobInfo) {
        return null;
    }
}

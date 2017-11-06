package cn.patterncat.job.common.api;

import cn.patterncat.job.common.model.JobInfo;
import cn.patterncat.job.common.model.JobResp;

import java.util.List;

/**
 * Created by patterncat on 2017-11-06.
 */
public interface JobClient {

    public JobResp<List<JobInfo>> fetchJobByGroup(String group);

    public JobResp<List<JobInfo>> fetchJobByHandlerClz(String handlerClz);

    public JobResp newJob(JobInfo jobInfo);

    public JobResp claimJob(String jobId);

    public JobResp updateJob(JobInfo jobInfo);
}

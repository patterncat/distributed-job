package cn.patterncat.job.starter.jdbc.dao;

import cn.patterncat.job.starter.jdbc.domain.JobRecord;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by patterncat on 2017-11-06.
 */
public interface JobRecordDao extends JpaRepository<JobRecord,Long> {
}

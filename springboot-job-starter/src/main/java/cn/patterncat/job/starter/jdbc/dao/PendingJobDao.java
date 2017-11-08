package cn.patterncat.job.starter.jdbc.dao;

import cn.patterncat.job.starter.jdbc.domain.PendingJob;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by patterncat on 2017-11-06.
 */
public interface PendingJobDao extends JpaRepository<PendingJob,Long> {
    Page<PendingJob> findByJobGroup(String jobGroup, Pageable pageable);
    Page<PendingJob> findByHandlerClz(String handlerClz, Pageable pageable);
}

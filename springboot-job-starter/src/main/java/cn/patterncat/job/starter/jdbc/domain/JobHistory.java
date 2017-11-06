package cn.patterncat.job.starter.jdbc.domain;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;

/**
 * Created by patterncat on 2017-11-06.
 */
@Entity
@Data
@Builder
public class JobHistory extends JobRecord{


}

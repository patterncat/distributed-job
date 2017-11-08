package cn.patterncat.job.common.model;

import lombok.Builder;
import lombok.Data;

/**
 * Created by patterncat on 2017-11-06.
 */
@Data
public class JobResp<T> {

    private boolean success;

    private T data;

    private String message;
}

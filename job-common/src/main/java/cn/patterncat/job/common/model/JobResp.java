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

    private Throwable error;

    public static <T> JobRespBuilder<T> builder(){
        return new JobRespBuilder<>();
    }

    public static class JobRespBuilder<T>{
        JobResp<T> toBuild;

        public JobRespBuilder(){
            toBuild = new JobResp<>();
        }

        public JobResp<T> build(){
            return toBuild;
        }

        public JobRespBuilder<T> success(boolean value){
            toBuild.setSuccess(value);
            return this;
        }

        public JobRespBuilder<T> data(T data){
            toBuild.setData(data);
            return this;
        }

        public JobRespBuilder<T> message(String message){
            toBuild.setMessage(message);
            return this;
        }

        public JobRespBuilder<T> throwable(Throwable error){
            toBuild.setError(error);
            return this;
        }
    }
}

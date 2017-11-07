package cn.patterncat.job.starter;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by patterncat on 2017-11-06.
 */
@ConfigurationProperties(prefix = "dxjob")
public class JobProperties {

    private boolean enabled = true;

    private String tablePrefix = "dxjob";

    private String dataSourceName = "dataSource";

    private boolean asyncEvent = true;

    public String getTablePrefix() {
        return tablePrefix;
    }

    public void setTablePrefix(String tablePrefix) {
        this.tablePrefix = tablePrefix;
    }

    public String getDataSourceName() {
        return dataSourceName;
    }

    public void setDataSourceName(String dataSourceName) {
        this.dataSourceName = dataSourceName;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isAsyncEvent() {
        return asyncEvent;
    }

    public void setAsyncEvent(boolean asyncEvent) {
        this.asyncEvent = asyncEvent;
    }
}

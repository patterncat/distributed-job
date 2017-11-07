package cn.patterncat.job.starter;

import cn.patterncat.job.starter.jdbc.JobTableNamingStrategy;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by patterncat on 2017-11-06.
 */
@Configuration
@ConditionalOnProperty(
        prefix = "dxjob",
        name = "enabled",
        havingValue = "true",
        matchIfMissing = false
)
@AutoConfigureAfter(DataSourceAutoConfiguration.class)
@ComponentScan("cn.patterncat.job.starter")
@EnableJpaRepositories(basePackages = "cn.patterncat.job.starter.jdbc.dao",entityManagerFactoryRef = "jobEntityManagerFactory",
        transactionManagerRef = "jobTransactionManager")
@EnableConfigurationProperties(JobProperties.class)
public class JobAutoConfiguration implements ApplicationContextAware {

    private static final Logger LOGGER = LoggerFactory.getLogger(JobAutoConfiguration.class);

    public static final Thread.UncaughtExceptionHandler uncaughtExceptionHandler = new Thread.UncaughtExceptionHandler() {
        @Override
        public void uncaughtException(Thread t, Throwable e) {
            LOGGER.error("{} job-async error",t,e);
        }
    };

    private ApplicationContext context;

    private final JobProperties properties;

    public JobAutoConfiguration(JobProperties properties) {
        this.properties = properties;
    }

    @Bean(name = "jobExecutor")
    @ConditionalOnProperty(value = "dxjob.asyncEvent",havingValue = "true",matchIfMissing = false)
    protected TaskExecutor jobExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(50);
        executor.setQueueCapacity(1000);
        executor.setThreadNamePrefix("job-async-");
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.setWaitForTasksToCompleteOnShutdown(true);
        ThreadFactoryBuilder threadFactoryBuilder = new ThreadFactoryBuilder();
        threadFactoryBuilder.setUncaughtExceptionHandler(uncaughtExceptionHandler);
        executor.setThreadFactory(threadFactoryBuilder.build());
        executor.initialize();
        return executor;
    }

    @Bean(name = "applicationEventMulticaster")
    @ConditionalOnMissingBean
    @ConditionalOnProperty(value = "dxjob.asyncEvent",havingValue = "true",matchIfMissing = false)
    public ApplicationEventMulticaster simpleApplicationEventMulticaster() {
        SimpleApplicationEventMulticaster eventMulticaster
                = new SimpleApplicationEventMulticaster();
        eventMulticaster.setTaskExecutor(jobExecutor());
        return eventMulticaster;
    }

    @Bean
    public DataSource jobDataSource() {
        return (DataSource) context.getBean(properties.getDataSourceName());
    }

    @Bean
    @Qualifier("jobTransactionManager")
    PlatformTransactionManager jobTransactionManager() {
        return new JpaTransactionManager(jobEntityManagerFactory().getObject());
    }

    @Bean
    LocalContainerEntityManagerFactoryBean jobEntityManagerFactory() {

        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        jpaVendorAdapter.setGenerateDdl(true);
        jpaVendorAdapter.setDatabase(Database.POSTGRESQL);
        jpaVendorAdapter.setDatabasePlatform("org.hibernate.dialect.PostgreSQLDialect");

        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(jobDataSource());
        factoryBean.setJpaVendorAdapter(jpaVendorAdapter);
        factoryBean.setPackagesToScan("cn.patterncat.job.starter.jdbc.domain");
        factoryBean.setPersistenceUnitName("jobPersistUnit");
        Map<String, Object> props = new HashMap<>();
        props.put("hibernate.physical_naming_strategy",new JobTableNamingStrategy(properties.getTablePrefix()));
        props.put("hibernate.ejb.entitymanager_factory_name", "jobEntityManagerFactory");
        props.put("hibernate.hbm2ddl.auto","update");
        factoryBean.setJpaPropertyMap(props);

        return factoryBean;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }
}

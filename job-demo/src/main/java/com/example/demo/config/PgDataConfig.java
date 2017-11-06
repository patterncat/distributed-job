package com.example.demo.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@AutoConfigureAfter(DataSourceAutoConfiguration.class)
@ComponentScan("com.example.demo.domain")
@EnableJpaRepositories(basePackages = "com.example.demo.dao",entityManagerFactoryRef = "pgEntityManagerFactory",
        transactionManagerRef = "pgTransactionManager")
public class PgDataConfig {

    @Bean
    @Primary
    @Qualifier("pgDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.pg")
    public DataSource pgDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @Qualifier("pgTransactionManager")
    PlatformTransactionManager pgTransactionManager() {
        return new JpaTransactionManager(pgEntityManagerFactory().getObject());
    }

    @Bean
    @Primary
    LocalContainerEntityManagerFactoryBean pgEntityManagerFactory() {

        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        jpaVendorAdapter.setGenerateDdl(true);
        jpaVendorAdapter.setDatabase(Database.POSTGRESQL);
        jpaVendorAdapter.setDatabasePlatform("org.hibernate.dialect.PostgreSQLDialect");

        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(pgDataSource());
        factoryBean.setJpaVendorAdapter(jpaVendorAdapter);
        factoryBean.setPackagesToScan("com.example.demo.domain");
        factoryBean.setPersistenceUnitName("pgPersistUnit");
        Map<String, Object> props = new HashMap<>();
        props.put("hibernate.physical_naming_strategy",new SpringPhysicalNamingStrategy());
        props.put("hibernate.ejb.entitymanager_factory_name", "pgEntityManagerFactory");
        props.put("hibernate.hbm2ddl.auto","update");
        factoryBean.setJpaPropertyMap(props);

        return factoryBean;
    }

}

package com.itheima.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * 事务相关配置
 */
@Configuration
public class TransactionConfig {
    /**
     * 事务管理器
     *
     * @param dataSource 数据源
     */
    @Bean(name = "transactionManager")
    public PlatformTransactionManager createTransactionManager(@Qualifier("dataSource1") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}
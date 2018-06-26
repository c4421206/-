package com.clouddo.log.server.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.io.IOException;

/**
 * 日志数据源配置
 * @author zhongming
 * @since 3.0
 * 2018/5/31下午1:22
 */
@Configuration
@MapperScan(basePackages = "com.clouddo.log.server.**.mybatis", sqlSessionTemplateRef = "logSqlSessionTemplate")
public class LogDataSourceConfig {

    /**
     * 创建日志SqlSessionFactory
     * @param dataSource 数据源
     * @return 日志SqlSessionFactory
     * @throws Exception 异常
     */
    @Bean(name = "logSqlSessionFactory")
    public SqlSessionFactory logSqlSessionFactory (DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setMapperLocations(matchMapperLocations());
        return sqlSessionFactoryBean.getObject();
    }

    private Resource[] matchMapperLocations() throws IOException {
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        return  resolver.getResources("classpath*:mybatis/*.xml");
    }

    /**
     * 创建系统数据库事物
     * @param dataSource 系统数据源
     * @return 事物类
     */
    @Bean(name = "logTransactionManager")
    public DataSourceTransactionManager logTransactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    /**
     * 创建系统库SqlSessionTemplate
     * @param sqlSessionFactory sqlSessionFactory
     * @return 系统库SqlSessionTemplate
     */
    @Bean(name = "logSqlSessionTemplate")
    public SqlSessionTemplate logSqlSessionTemplate(@Qualifier("logSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}

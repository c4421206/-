package com.clouddo.system.config;

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
 * mybatis配置
 * 配置SqlSessionFactory
 * 配置mybaties扫描路径
 * 配置事物
 * 配置SqlSessionTemplate
 * @author zhongming
 * @since 3.0
 * 2018/3/29上午8:36
 */
@Configuration
@MapperScan(basePackages = {"com.clouddo.system.**.mapper"}, sqlSessionTemplateRef = "sysSqlSessionTemplate")
public class SysDataSourceConfig {

    /**
     * 创建系统SqlSessionFactory
     * @param dataSource 系统数据源
     * @return 系统SqlSessionFactory
     * @throws Exception 异常
     */
    @Primary
    @Bean(name = "sysSqlSessionFactory")
    public SqlSessionFactory sysSqlSessionFactory(DataSource dataSource) throws Exception {
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
    @Primary
    @Bean(name = "sysTransactionManager")
    public DataSourceTransactionManager sysTransactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    /**
     * 创建系统库SqlSessionTemplate
     * @param sqlSessionFactory sqlSessionFactory
     * @return 系统库SqlSessionTemplate
     */
    @Primary
    @Bean(name = "sysSqlSessionTemplate")
    public SqlSessionTemplate sysSqlSessionTemplate(@Qualifier("sysSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}

package com.domain.api.config;

import com.domain.api.domain.Role;
import com.domain.api.domain.User;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.*;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mybatis.repository.config.EnableMybatisRepositories;
import org.springframework.data.mybatis.repository.config.MybatisMappersRegister;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Properties;

@Configuration
@EnableMybatisRepositories(
        value = "com.domain.api.**.repository",
        mapperLocations = {
                "classpath*:/mappers/*Mapper.xml",
                "classpath*:/beforemappers/*Mapper.xml"
        }
)
public class CoreDbConfig implements DataSourceConfiguration {

    private DataSourceCoreProperties dataSourceCoreProperties;

    @Autowired
    public void setDataSourceCoreProperties(DataSourceCoreProperties dataSourceCoreProperties) {
        this.dataSourceCoreProperties = dataSourceCoreProperties;
    }

    @Bean
    public DriverManagerDataSource dataSource() throws SQLException {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(dataSourceCoreProperties.getUrl());
        dataSource.setUsername(dataSourceCoreProperties.getUsername());
        dataSource.setPassword(dataSourceCoreProperties.getPassword());
        dataSource.setDriverClassName(dataSourceCoreProperties.getDriverClassName());
        Properties properties = new Properties();
        properties.put("minIdle", dataSourceCoreProperties.getMinIdle().toString());
        properties.put("maxIdle", dataSourceCoreProperties.getMaxIdle().toString());
        properties.put("maxActive", dataSourceCoreProperties.getMaxActive().toString());
        properties.put("maxWait", dataSourceCoreProperties.getMaxWait().toString());
        properties.put("testOnBorrow", dataSourceCoreProperties.getTestOnBorrow().toString());
        properties.put("validationQuery", dataSourceCoreProperties.getValidationQuery());
        properties.put("timeBetweenEvictionRunsMillis", dataSourceCoreProperties.getTimeBetweenEvictionRunsMillis().toString());
        properties.put("numTestsPerEvictionRun", dataSourceCoreProperties.getNumTestsPerEvictionRun().toString());
        properties.put("minEvictableIdleTimeMillis", dataSourceCoreProperties.getMinEvictableIdleTimeMillis().toString());
        properties.put("testWhileIdle", dataSourceCoreProperties.getTestWhileIdle().toString());
        dataSource.setConnectionProperties(properties);
        return dataSource;
    }

    @Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public SqlSessionFactoryBean sqlSessionFactory() throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setTypeHandlers(new TypeHandler[] {
                new InstantTypeHandler(),
                new LocalDateTimeTypeHandler(),
                new LocalDateTypeHandler(),
                new LocalTimeTypeHandler(),
                new OffsetDateTimeTypeHandler(),
                new OffsetTimeTypeHandler(),
                new ZonedDateTimeTypeHandler(),
                new YearTypeHandler(),
                new MonthTypeHandler(),
        });
        sessionFactory.setTypeAliases(new Class[] {
                User.class,
                Role.class,
        });
        return sessionFactory;
    }


    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
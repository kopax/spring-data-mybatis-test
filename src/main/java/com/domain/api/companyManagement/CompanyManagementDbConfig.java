package com.domain.api.companyManagement;

import org.apache.ibatis.session.SqlSessionFactory;
import org.flywaydb.core.Flyway;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mybatis.repository.config.EnableMybatisRepositories;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
@EnableTransactionManagement
@EnableMybatisRepositories(
        transactionManagerRef = "companyManagement",
        sqlSessionFactoryRef = "companyManagementSqlSessionFactory",
        considerNestedRepositories = true,
        value = {
                "com.domain.api.companyManagement.repository",
                "com.domain.api.companyManagement.service"
        }
//        mapperLocations = {
//                "classpath*:/mappers/companyManagement/*Mapper.xml",
//                "classpath*:/beforemappers/companyManagement/*Mapper.xml"
//        }
)
public class CompanyManagementDbConfig {

    @Value("${api.db.companyManagement.version}")
    private String version;

    @Bean(name = "companyManagementDataSource")
    @ConfigurationProperties(prefix = "api.db.companyManagement")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "companyManagement")
    public PlatformTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "companyManagementSqlSessionFactory")
    public SqlSessionFactoryBean sqlSessionFactory() throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        return sessionFactory;
    }


    @Bean(name = "companyManagementSqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }


    @Bean(initMethod = "migrate", name = "companyManagementFlyway")
    public Flyway flyway() throws SQLException {
        Flyway flyway = new Flyway();
        flyway.setBaselineOnMigrate(true);
        flyway.setLocations("classpath:db/migration/companyManagement");
        flyway.setSqlMigrationPrefix("V");
        flyway.setSqlMigrationSuffix(".sql");
        flyway.setEncoding("UTF-8");
        flyway.setValidateOnMigrate(false);
        flyway.setOutOfOrder(true);
        flyway.setTargetAsString(version);
        flyway.setDataSource(dataSource());
        return flyway;
    }
}

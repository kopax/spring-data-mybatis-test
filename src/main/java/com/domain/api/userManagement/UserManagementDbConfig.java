package com.domain.api.userManagement;

import org.apache.ibatis.session.SqlSessionFactory;
import org.flywaydb.core.Flyway;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mybatis.repository.config.EnableMybatisRepositories;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
@EnableTransactionManagement
@EnableMybatisRepositories(
        transactionManagerRef = "userManagementTransactionManager",
        sqlSessionFactoryRef = "userManagementSqlSessionFactory",
        value = "com.domain.api.userManagement.repository"
)
public class UserManagementDbConfig {

    @Value("${api.db.userManagement.version}")
    private String version;

    @Primary
    @Bean(name = "userManagementDataSource")
    @ConfigurationProperties(prefix = "api.db.userManagement")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "userManagementTransactionManager")
    public PlatformTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Primary
    @Bean(name = "userManagementSqlSessionFactory")
    public SqlSessionFactoryBean sqlSessionFactory() throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        return sessionFactory;
    }

    @Bean(name = "userManagementSqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean(initMethod = "migrate", name = "userManagementFlyway")
    public Flyway flyway() throws SQLException {
        Flyway flyway = new Flyway();
        flyway.setBaselineOnMigrate(true);
        flyway.setLocations("classpath:db/migration/userManagement");
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

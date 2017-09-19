package com.domain.api.config;

import org.flywaydb.core.Flyway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.SQLException;

@Configuration
public class FlywayConfig {

	private final static Logger logger = LoggerFactory.getLogger(FlywayConfig.class);

	private CoreDbConfig coreDbConfig;
	private DataSourceCoreProperties dataSourceCoreProperties;


	@Autowired
	public void setDataSourceCore(CoreDbConfig coreDbConfig) {
		this.coreDbConfig = coreDbConfig;
	}

	@Autowired
	public void setDataSourceCoreProperties(DataSourceCoreProperties dataSourceCoreProperties) {
		this.dataSourceCoreProperties = dataSourceCoreProperties;
	}


	@Bean(initMethod = "migrate")
	public Flyway flyway() throws SQLException {
		Flyway flyway = new Flyway();
		flyway.setBaselineOnMigrate(true);
		flyway.setLocations("classpath:db/migration/core");
		flyway.setSqlMigrationPrefix("V");
		flyway.setSqlMigrationSuffix(".sql");
		flyway.setEncoding("UTF-8");
		flyway.setValidateOnMigrate(false);
		flyway.setOutOfOrder(true);
		flyway.setTargetAsString(dataSourceCoreProperties.getVersion());
		flyway.setDataSource(coreDbConfig.dataSource());
		return flyway;
	}

}

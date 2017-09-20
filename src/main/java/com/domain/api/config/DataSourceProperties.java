package com.domain.api.config;

import lombok.Data;

/**
 * Created by dka on 4/9/17.
 */
@Data
public abstract class DataSourceProperties {

	private String version;
	private String url;
	private String username;
	private String password;
	private String driverClassName;
	private Integer minIdle;
	private Integer maxIdle;
	private Integer maxActive;
	private Long maxWait;
	private Boolean testOnBorrow;
	private String validationQuery;
	private Long timeBetweenEvictionRunsMillis;
	private Integer numTestsPerEvictionRun;
	private Integer minEvictableIdleTimeMillis;
	private Boolean testWhileIdle;

}


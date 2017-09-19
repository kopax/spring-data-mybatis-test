package com.domain.api.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created by dka on 4/9/17.
 */
@Configuration
@ConfigurationProperties("api.db.core")
public class DataSourceCoreProperties extends DataSourceProperties {

}


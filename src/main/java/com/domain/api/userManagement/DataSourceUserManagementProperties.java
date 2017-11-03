package com.domain.api.userManagement;

import com.domain.api.config.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created by dka on 4/9/17.
 */
@Configuration
@ConfigurationProperties("api.db.userManagement")
public class DataSourceUserManagementProperties extends DataSourceProperties {

}


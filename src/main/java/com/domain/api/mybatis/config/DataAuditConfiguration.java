/*
 * Kopax Ltd Copyright (c) 2016.
 */

package com.domain.api.mybatis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mybatis.domains.AuditDateAware;

import java.time.Instant;

/**
 * Created by dka on 12/25/16.
 */
@Configuration
public class DataAuditConfiguration {

	@Bean
	public AuditDateAware<Instant> auditDateAware() {
		return new AuditDateAware<Instant>() {
			@Override
			public Instant getCurrentDate() {
				return Instant.now();
			}
		};
	}

}

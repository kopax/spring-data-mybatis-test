package com.domain.api.domain;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.annotation.*;
import org.springframework.data.mybatis.annotations.JdbcType;
import org.springframework.data.mybatis.domains.LongId;

import java.time.Instant;

import static org.apache.ibatis.type.JdbcType.TIMESTAMP;

@Data
@EqualsAndHashCode(callSuper = false)
public abstract class VersionId extends LongId implements Cloneable{

	private static final Logger logger = LoggerFactory.getLogger(VersionId.class);

	@Version
	private Integer version;


	@CreatedDate
	@JsonUnwrapped
    @JdbcType(TIMESTAMP)
	private Instant createdDate;

	@LastModifiedDate
	@JsonUnwrapped
    @JdbcType(TIMESTAMP)
	private Instant lastModifiedDate;

	@CreatedBy
	private Long createdById;

	@LastModifiedBy
	private Long lastModifiedById;

	private Boolean deleted = true;

}

package com.domain.api.companyManagement.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.mybatis.annotations.Entity;
import org.springframework.data.mybatis.domains.LongId;
import org.springframework.hateoas.Identifiable;

/**
 * Test entity
 * It is a user of spring security
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(table = "cm_test")
@JsonIgnoreProperties("new")
@EqualsAndHashCode(callSuper=false)
public class Test extends LongId implements Identifiable<Long> {

	public static final Long ROOT_ID = 1L;

	private String name;

}

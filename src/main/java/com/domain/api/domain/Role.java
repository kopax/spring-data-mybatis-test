/*
 *
 *   Copyright 2016 the original author or authors.
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 *
 */

package com.domain.api.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.*;
import org.springframework.data.mybatis.annotations.*;

import java.time.Instant;
import java.util.List;

import static org.apache.ibatis.type.JdbcType.TIMESTAMP;
import static org.springframework.data.repository.query.parser.Part.Type.CONTAINING;


/**
 * Sample domain class representing roles. Mapped with XML.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(table = "role")
@JsonIgnoreProperties("new")
public class Role extends VersionId {

	private static final String PREFIX = "ROLE_";

	@Conditions({
			@Condition,
			@Condition(properties = "fuzzyName", type = CONTAINING)
	})
	private String name;

	@ManyToMany
	private List<User> userList;


	/**
	 * Creates a new preconfigured {@code Role}.
	 *
	 * @param name
	 */
	public Role(final String name) {
		this.name = name;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Role role = (Role) o;

		return id != null ? id.equals(role.id) : role.id == null;

	}

	@Override
	public int hashCode() {
		return id != null ? id.hashCode() : 0;
	}


	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
	@Override
	public String toString() {

		return PREFIX + name;
	}

	public void setName(String name) {
		this.name = name;
	}

}

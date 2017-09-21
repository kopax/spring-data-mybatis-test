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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.mybatis.annotations.*;

import java.util.List;

import static org.springframework.data.repository.query.parser.Part.Type.CONTAINING;
import static org.springframework.data.repository.query.parser.Part.Type.IN;


/**
 * Sample domain class representing roles. Mapped with XML.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(table = "role")
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties("new")
public class Role extends VersionId {

    private static final String PREFIX = "ROLE_";

    @Conditions({
            @Condition,
            @Condition(properties = "fuzzyName", type = CONTAINING)
    })
    private String name;


    @ManyToMany
    @Conditions({
            @Condition,
            @Condition(type = IN, properties = "userId"),
    })
    @JoinTable(name = "role_user")
    private List<User> userList;


    /**
     * Creates a new preconfigured {@code Role}.
     *
     * @param name
     */
    public Role(final String name) {
        this.name = name;
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

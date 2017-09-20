package com.domain.api.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.mybatis.annotations.Condition;
import org.springframework.data.mybatis.annotations.Conditions;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class RoleDTO extends Role {
    private String fuzzyName;
    private Long fuzzyRoleId;
    private Long fuzzyUserId;
    private String fuzzyUserList;

    private Long userId;
}

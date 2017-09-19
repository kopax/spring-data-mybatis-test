package com.domain.api.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class RoleDTO extends Role {
    private String fuzzyName;
    private String fuzzyRoleId;
    private String fuzzyUserId;
    private String fuzzyUserList;
    private Long userId;
}

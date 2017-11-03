package com.domain.api.userManagement.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class RoleDTO extends Role {
    private String fuzzyName;
    private Long fuzzyRoleId;
    private Long fuzzyUserId;
    private String fuzzyUserList;
    private String fuzzyDeleted;

//    private Long userId;
}

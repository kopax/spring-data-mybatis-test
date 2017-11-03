package com.domain.api.userManagement.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class UserDTO extends User {
    private String fuzzyUsername;
    private String fuzzyFirstName;
    private String fuzzyLastName;
    private String fuzzyMiddleName;
    private String fuzzyMobile;
    private String fuzzyEmail;
    private Long fuzzyRoleId;

    private String fuzzyDeleted;

//    private Long roleId;
//    private Role role;

}

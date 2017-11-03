package com.domain.api.userManagement.resource;

import com.domain.api.userManagement.domain.Role;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;

public class RoleResource extends Resource<Role> {

    public RoleResource(Role content, Link... links) {
        super(content, links);
    }

}

package com.domain.api.resource;

import com.domain.api.controller.RoleController;
import com.domain.api.controller.UserController;
import com.domain.api.domain.Role;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

public class RoleResourceAssembler extends ResourceAssemblerSupport<Role, RoleResource> {

    public RoleResourceAssembler() {
        super(RoleController.class, RoleResource.class);
    }

    public RoleResource createResource(Role role) {
        RoleResource roleResource = new RoleResource(role);
        Link link = linkTo(RoleController.class).slash(role.getId()).withSelfRel();
        Link userLink = linkTo(UserController.class).withRel("users");
        roleResource.add(link, userLink);
        return roleResource;
    }

    @Override
    public RoleResource toResource(Role role) {
        RoleResource resource = createResource(role);
        return resource;
    }

}
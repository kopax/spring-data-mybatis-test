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

    public RoleResource createResource(Role entity) {
        RoleResource resource = new RoleResource(entity);
        Link link = linkTo(RoleController.class).slash(entity.getId()).withSelfRel();
        Link userListLink = linkTo(UserController.class).slash("?roleId="+entity.getId()).withRel("userList");
        resource.add(link, userListLink);
        return resource;
    }

    @Override
    public RoleResource toResource(Role entity) {
        RoleResource resource = createResource(entity);
        return resource;
    }

}
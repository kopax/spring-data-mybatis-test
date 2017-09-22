package com.domain.api.resource;

import com.domain.api.controller.RoleController;
import com.domain.api.controller.UserController;
import com.domain.api.domain.User;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

public class UserResourceAssembler extends ResourceAssemblerSupport<User, UserResource> {

    public UserResourceAssembler() {
        super(UserController.class, UserResource.class);
    }

    public UserResource createResource(User entity) {
        UserResource resource = new UserResource(entity);
        Link link = linkTo(UserController.class).slash(entity.getId()).withSelfRel();
        Link roleLink = linkTo(RoleController.class).slash("?roleId="+entity.getId()).withRel("roleList");
        resource.add(link, roleLink);
        return resource;
    }

    @Override
    public UserResource toResource(User entity) {
        UserResource resource = createResource(entity);
        return resource;
    }

}
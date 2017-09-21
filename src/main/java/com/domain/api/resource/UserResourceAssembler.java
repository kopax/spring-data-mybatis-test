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

    public UserResource createResource(User user) {
        UserResource userResource = new UserResource(user);
        Link link = linkTo(UserController.class).slash(user.getId()).withSelfRel();
        Link roleLink = linkTo(RoleController.class).withRel("roles");
        userResource.add(link, roleLink);
        return userResource;
    }

    @Override
    public UserResource toResource(User user) {
        UserResource resource = createResource(user);
        return resource;
    }

}
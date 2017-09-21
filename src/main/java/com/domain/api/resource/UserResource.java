package com.domain.api.resource;

import com.domain.api.domain.User;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;

public class UserResource extends Resource<User> {

    public UserResource(User content, Link... links) {
        super(content, links);
    }

}

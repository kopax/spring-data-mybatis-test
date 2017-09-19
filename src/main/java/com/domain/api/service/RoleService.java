package com.domain.api.service;

import com.domain.api.domain.Role;
import org.springframework.data.support.CrudService;

public interface RoleService extends CrudService<Role, Long> {
    Role getByName(String name);
}

package com.domain.api;

import com.domain.api.domain.Role;
import com.domain.api.domain.User;
import com.domain.api.repository.RoleRepository;
import com.domain.api.repository.UserRepository;
import com.domain.api.service.RoleService;
import com.domain.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Component
@Profile({"default"})
public class InitialDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private Environment environment;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {

        if (null != roleService.getByName("ADMIN"))
            return;

        createRoleIfNotFound("ADMIN");
        createRoleIfNotFound("USER");
        createRoleIfNotFound("CLIENT");

        Role adminRole = roleService.getByName("ADMIN");
        Role userRole = roleService.getByName("USER");
        User admin = new User();
        admin.setUsername("admin");
        admin.setFirstName("admin");
        admin.setLastName("root");
        admin.setPassword("admin");

        List<Role> roleList = Arrays.asList(adminRole, userRole);
        admin.setRoleList(roleList);
        admin.setDeleted(false);
        userService.insert(admin);

        User admin2 = new User();
        admin2.setUsername("admin2");
        admin2.setFirstName("admin");
        admin2.setLastName("root2");
        admin2.setPassword("admin2");

        List<Role> roleList2 = Arrays.asList(adminRole);
        admin2.setRoleList(roleList2);
        admin2.setDeleted(false);
        userService.insert(admin2);
    }


    @Transactional
    protected Role createRoleIfNotFound(String name) {
        Role role = roleService.getByName(name);
        if (role == null) {
            role = new Role(name);
            roleService.save(role);
        }
        return role;
    }
}

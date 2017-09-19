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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

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
        User admin = new User();
        admin.setUsername("admin");
        admin.setFirstName("admin");
        admin.setLastName("root");
        admin.setPassword("admin");

        Set<Role> roleList = new HashSet<>(Arrays.asList(adminRole));
        admin.setRoleList(roleList);
        admin.setDeleted(false);
        userService.insert(admin);

        User admin2 = new User();
        admin2.setUsername("admin2");
        admin2.setFirstName("admin");
        admin2.setLastName("root2");
        admin2.setPassword("admin2");

        Set<Role> roleList2 = new HashSet<>(Arrays.asList(adminRole));
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

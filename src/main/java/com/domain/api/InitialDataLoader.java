package com.domain.api;

import com.domain.api.domain.Role;
import com.domain.api.domain.User;
import com.domain.api.service.RoleService;
import com.domain.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

@Component
@Profile({"default"})
public class InitialDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

//    @Autowired
//    private Environment environment;

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

        User dimitri = new User();
        dimitri.setUsername("Dmitri");
        dimitri.setFirstName("Dimitri");
        dimitri.setLastName("Kop");
        dimitri.setMiddleName("Alexander");
        dimitri.setMobile("0033655443355");
        dimitri.setPassword("notEncryptedPw");
        dimitri.setRoleList(Arrays.asList(adminRole, userRole));
        userService.insert(dimitri);

        User jarvis = new User();
        jarvis.setUsername("jarvis");
        jarvis.setFirstName("Jarvis");
        jarvis.setLastName("Song");
        jarvis.setMobile("001777443355");
        jarvis.setPassword("notEncryptedPw155");
        jarvis.setCreatedById(dimitri.getId());
        jarvis.setRoleList(Arrays.asList(adminRole, userRole));
        userService.insert(jarvis);

        for (int i = 0; i < 50; i++) {
            User user = new User();
            user.setUsername("user" + i);
            user.setFirstName("User" + i);
            user.setLastName("robot");
            user.setMobile("0101010100011" + i);
            user.setPassword("********");
            user.setRoleList(Arrays.asList(userRole));
            user.setCreatedById(dimitri.getId());
            userService.insert(user);
        }

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

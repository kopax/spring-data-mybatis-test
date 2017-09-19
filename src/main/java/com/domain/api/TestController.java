package com.domain.api;

import com.domain.api.domain.Role;
import com.domain.api.domain.RoleDTO;
import com.domain.api.domain.User;
import com.domain.api.domain.UserDTO;
import com.domain.api.service.RoleService;
import com.domain.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
public class TestController {

  @Autowired
  private RoleService roleService;
  @Autowired
  private UserService userService;

  @GetMapping
  Page<User> list(@PageableDefault(size = 20) Pageable pageable, UserDTO condition) {

    condition.setFirstName("admin");

    return userService.findAll(pageable, condition);
  }


}

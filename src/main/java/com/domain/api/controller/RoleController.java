package com.domain.api.controller;

import java.util.List;

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
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("roles")
public class RoleController {

  @Autowired
  private RoleService roleService;
  @Autowired
  private UserService userService;

  @GetMapping
  Page<Role> list(@PageableDefault(size = 20) Pageable pageable, RoleDTO condition) {
    return roleService.findAll(pageable, condition);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  Long create(@RequestBody Role role) {
    roleService.insert(role);
    return role.getId();
  }

  @GetMapping("{id}")
  Role getRole(@PathVariable("id") Long id){
    Role res = roleService.get(id);
    // we add user relation
    UserDTO userDTO = new UserDTO();
    userDTO.setRoleId(res.getId());
    List<User> userList = this.userService.findAll(userDTO);
    res.setUserList(userList);

    return res;
  }

  @PutMapping("{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  void modify(@PathVariable("id") Long id, @RequestBody Role role) {
    role.setId(id);
    roleService.updateIgnore(role);
  }

  @DeleteMapping("{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  void delete(@PathVariable("id") Long id) {
    roleService.delete(id);
  }

}

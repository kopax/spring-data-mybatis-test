package com.domain.api.controller;


import com.domain.api.domain.Role;
import com.domain.api.domain.RoleDTO;
import com.domain.api.domain.User;
import com.domain.api.domain.UserDTO;
import com.domain.api.resource.UserResource;
import com.domain.api.resource.UserResourceAssembler;
import com.domain.api.service.RoleService;
import com.domain.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.*;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping("users")
public class UserController {

  @Autowired
  private UserService userService;
  @Autowired
  private RoleService roleService;

  // WITH PAGINATED METHOD
  @RequestMapping(value = "paged", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
  public ResponseEntity<?> showAllBis(PagedResourcesAssembler<User> pageAssembler, @PageableDefault(size = 20) Pageable pageable, UserDTO condition) {
    Page<User> userList = userService.findAll(pageable, condition);
    PagedResources<?> resources = pageAssembler.toResource(userList, new UserResourceAssembler());
    return ResponseEntity.ok(resources);
  }

  @RequestMapping(value = "paged/{id}", produces= MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
  public ResponseEntity<UserResource> showOneBis(@PathVariable("id") Long id) {
    UserResource resource = new UserResourceAssembler().toResource(userService.get(id));
    return ResponseEntity.ok(resource);
  }

  // WITHOUT PAGINATED METHOD

  @GetMapping
  public Page<User> list(@PageableDefault(size = 20) Pageable pageable, UserDTO condition) {
    Page<User> page = userService.findAll(pageable, condition);
    if (page.hasContent()) {
      page.getContent().forEach(user -> {
        RoleDTO cond = new RoleDTO();
        cond.setUserId(user.getId());
        List<Role> roles = roleService.findAll(cond);
        user.setRoleList(roles);
      });
    }
    return page;
  }

  @GetMapping("list")
  List<User> list(UserDTO condition){
    return userService.findAll(condition);
  }

  @GetMapping("{id}")
//  @Auth(inner = true)
  User get(@PathVariable("id") Long id) {
    return userService.get(id);
  }
  private void preProcessUser(@RequestBody User user) {
//    if (StringUtils.hasText(user.getPassword())) {
//      user.setPassword(userService.encrypt(user.getPassword()));
//    }
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  Long create(@RequestBody User user) {
    preProcessUser(user);
    userService.insert(user);
    return user.getId();
  }


  @PutMapping("{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  void modify(@PathVariable("id") Long id, @RequestBody User user) {
    user.setId(id);
    preProcessUser(user);
    userService.updateIgnore(user);
  }

  @DeleteMapping("{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  void delete(@PathVariable("id") Long id) {
    userService.delete(id);
  }

  @DeleteMapping
  @ResponseStatus(HttpStatus.NO_CONTENT)
  void bulkDelete(@RequestBody Long[] ids) {
    userService.delete(ids);
  }
}

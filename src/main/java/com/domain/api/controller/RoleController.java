package com.domain.api.controller;

import java.util.List;

import com.domain.api.domain.Role;
import com.domain.api.domain.RoleDTO;
import com.domain.api.domain.User;
import com.domain.api.domain.UserDTO;
import com.domain.api.resource.RoleResource;
import com.domain.api.resource.RoleResourceAssembler;
import com.domain.api.resource.UserResource;
import com.domain.api.resource.UserResourceAssembler;
import com.domain.api.service.RoleService;
import com.domain.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("roles")
public class RoleController {

  @Autowired
  private RoleService roleService;
  @Autowired
  private UserService userService;

  // WITH PAGINATED METHOD
  @RequestMapping(value = "paged", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
  public ResponseEntity<?> showAllBis(PagedResourcesAssembler<Role> pageAssembler, @PageableDefault(size = 20) Pageable pageable, RoleDTO condition) {
    Page<Role> page = roleService.findAll(pageable, condition);
    PagedResources<?> resources = pageAssembler.toResource(page, new RoleResourceAssembler());
    return ResponseEntity.ok(resources);
  }

  @RequestMapping(value = "paged/{id}", produces= MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
  public ResponseEntity<RoleResource> showOneBis(@PathVariable("id") Long id) {
    RoleResource resource = new RoleResourceAssembler().toResource(roleService.get(id));
    return ResponseEntity.ok(resource);
  }

  // WITHOUT PAGINATED METHOD
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
    userDTO.setFuzzyRoleId(res.getId());
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

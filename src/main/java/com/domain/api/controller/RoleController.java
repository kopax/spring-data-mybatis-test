package com.domain.api.controller;

import com.domain.api.domain.Role;
import com.domain.api.domain.RoleDTO;
import com.domain.api.resource.RoleResource;
import com.domain.api.resource.RoleResourceAssembler;
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

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> list(PagedResourcesAssembler<Role> pageAssembler, @PageableDefault(size = 20) Pageable pageable, RoleDTO condition) {
    Page<Role> page = roleService.findAll(pageable, condition);
    PagedResources<?> resources = pageAssembler.toResource(page, new RoleResourceAssembler());
    return ResponseEntity.ok(resources);
  }

  @GetMapping(value = "{id}", produces= MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<RoleResource> get(@PathVariable("id") Long id) {
    RoleResource resource = new RoleResourceAssembler().toResource(roleService.get(id));
    return ResponseEntity.ok(resource);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  Long create(@RequestBody Role role) {
    roleService.insert(role);
    return role.getId();
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

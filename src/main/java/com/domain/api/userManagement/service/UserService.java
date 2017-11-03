package com.domain.api.userManagement.service;

import com.domain.api.userManagement.domain.User;
import org.springframework.data.support.CrudService;

public interface UserService extends CrudService<User, Long> {

  User getByUsername(String username);

  User getByMobile(String mobile);

  User getByEmail(String email);

  /**
   * bulk delete users.
   *
   * @param ids user's id
   */
  void delete(Long[] ids);
}

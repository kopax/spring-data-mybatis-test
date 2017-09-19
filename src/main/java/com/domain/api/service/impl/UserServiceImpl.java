package com.domain.api.service.impl;

import com.domain.api.domain.Role;
import com.domain.api.domain.User;
import com.domain.api.domain.UserDTO;
import com.domain.api.repository.UserRepository;
import com.domain.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.support.AbstractCrudService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl extends AbstractCrudService<UserRepository, User, Long> implements UserService {

  /**
   * 构造函数.
   *
   * @param repository 注入的Repository
   */
  @Autowired
  public UserServiceImpl(UserRepository repository) {
    super(repository);
  }

  @Override
  @Transactional(readOnly = true)
  public User getByUsername(String username) {
    UserDTO cond = new UserDTO();
    cond.setUsername(username);
    return getRepository().findOne(cond);

  }

  @Override
  @Transactional(readOnly = true)
  public User getByMobile(String mobile) {
    UserDTO cond = new UserDTO();
    cond.setMobile(mobile);
    return getRepository().findOne(cond);
  }

  @Override
  @Transactional(readOnly = true)
  public User getByEmail(String email) {
    UserDTO cond = new UserDTO();
    cond.setEmail(email);
    return getRepository().findOne(cond);
  }

  @Override
  @Transactional
  public void delete(Long id) {
    User user = new User();
    user.setId(id);
    user.setDeleted(true);
    updateIgnore(user);

    getRepository().deleteRoleUserByUserId(id);

  }

  @Override
  @Transactional
  public void delete(Long[] ids) {
//    if (ArrayUtil.isEmpty(ids)) {
//      return;
//    }
    for (Long id : ids) {
      delete(id);
    }
  }

  @Override
  @Transactional
  public void insert(User user) {
//    user.setId(AppContext.get().getIdWorker().nextIdAsString());
    user.setDeleted(false);
    super.insert(user);

    if (user.getRoleList().size() > 0) {
      for (Role role : user.getRoleList())
        getRepository().insertRoleUser(role.getId(), user.getId());
    }

  }

  @Override
  @Transactional
  public void updateIgnore(User user) {
    super.updateIgnore(user);

    getRepository().deleteRoleUserByUserId(user.getId());

    if (user.getRoleList().size() > 0) {
      for (Role role : user.getRoleList())
        getRepository().insertRoleUser(role.getId(), user.getId());
    }


  }
}

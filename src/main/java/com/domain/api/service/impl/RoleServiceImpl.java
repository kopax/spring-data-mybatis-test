package com.domain.api.service.impl;


import com.domain.api.domain.Role;
import com.domain.api.domain.RoleDTO;
import com.domain.api.repository.RoleRepository;
import com.domain.api.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.support.AbstractCrudService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RoleServiceImpl extends AbstractCrudService<RoleRepository, Role, Long> implements RoleService {


  /**
   * 构造函数.
   *
   * @param repository 注入的Repository
   */
  @Autowired
  public RoleServiceImpl(RoleRepository repository) {
    super(repository);
  }

  @Override
  @Transactional(readOnly = true)
  public Role getByName(String name) {
    RoleDTO cond = new RoleDTO();
    cond.setName(name);
    return getRepository().findOne(cond);
  }

  @Override
  @Transactional
  public void updateIgnore(Role role) {

    super.updateIgnore(role);
    getRepository().deleteLinkRoleUserByRoleId(role.getId());
//    if (CollectionUtil.isNotEmpty(role.getUsers())) {
//      role.getUsers().forEach(user -> {
//        getRepository().insertLinkRoleUser(role.getId(), user.getId());
//      });
//    }

  }


  @Override
  @Transactional
  public void insert(Role role) {
    super.insert(role);
//    //更新角色对应的用户
//    if (CollectionUtil.isNotEmpty(role.getUsers())) {
//      role.getUsers().forEach(user -> {
//        getRepository().insertLinkRoleUser(role.getId(), user.getId());
//      });
//    }

  }

  @Override
  @Transactional
  public void delete(Long id) {
    super.delete(id);
    getRepository().deleteLinkRoleUserByRoleId(id);
  }
}

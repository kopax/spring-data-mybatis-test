package com.domain.api.repository;

import static org.springframework.data.mybatis.repository.annotation.Query.Operation.delete;
import static org.springframework.data.mybatis.repository.annotation.Query.Operation.insert;

import com.domain.api.domain.Role;
import org.springframework.data.mybatis.repository.annotation.Query;
import org.springframework.data.mybatis.repository.support.MybatisRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
public interface RoleRepository extends MybatisRepository<Role, Long> {
  @Query(operation = insert)
  int insertLinkRoleUser(@Param("roleId") Long roleId, @Param("userId") Long userId);

  @Query(operation = delete)
  int deleteLinkRoleUserByRoleId(@Param("roleId") Long roleId);
}

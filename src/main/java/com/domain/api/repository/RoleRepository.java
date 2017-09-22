package com.domain.api.repository;

import static org.springframework.data.mybatis.repository.annotation.Query.Operation.delete;
import static org.springframework.data.mybatis.repository.annotation.Query.Operation.insert;
import static org.springframework.data.mybatis.repository.annotation.Query.Operation.select_list;

import com.domain.api.domain.Role;
import org.springframework.data.mybatis.repository.annotation.Query;
import org.springframework.data.mybatis.repository.support.MybatisRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(exported = false)
public interface RoleRepository extends MybatisRepository<Role, Long> {
  @Query(operation = insert)
  int insertRoleUser(@Param("roleId") Long roleId, @Param("userId") Long userId);

  @Query(operation = delete)
  int deleteRoleUserByRoleId(@Param("roleId") Long roleId);

  @Query(operation = select_list)
  List<Role> getByUserId(@Param("userId") Long userId);
}

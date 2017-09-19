package com.domain.api.repository;

import com.domain.api.domain.User;
import org.springframework.data.mybatis.repository.annotation.Query;
import org.springframework.data.mybatis.repository.support.MybatisRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.data.mybatis.repository.annotation.Query.Operation.delete;
import static org.springframework.data.mybatis.repository.annotation.Query.Operation.insert;

@RepositoryRestResource(exported = false)
public interface UserRepository extends MybatisRepository<User, Long> {

  @Query(operation = insert)
  int insertLinkRoleUser(@Param("roleId") Long roleId, @Param("userId") Long userId);

  @Query(operation = delete)
  int deleteLinkRoleUserByUserId(@Param("userId") Long userId);

}

package com.domain.api.userManagement.repository;

import com.domain.api.userManagement.domain.Test;
import org.springframework.data.mybatis.repository.support.MybatisRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * This Repository is used to map the mybatis method in the xml files
 */
@RepositoryRestResource(exported = false)
public interface TestRepository extends MybatisRepository<Test, Long> {


}

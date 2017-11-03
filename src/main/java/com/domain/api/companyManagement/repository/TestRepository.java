package com.domain.api.companyManagement.repository;

import com.domain.api.companyManagement.domain.Test;
import org.springframework.data.mybatis.repository.support.MybatisRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * This Repository is used to map the mybatis method in the xml files
 */
@RepositoryRestResource(exported = false)
public interface TestRepository extends MybatisRepository<Test, Long> {


}

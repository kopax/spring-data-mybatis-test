package com.domain.api.companyManagement.service;

import com.domain.api.companyManagement.domain.Test;
import org.springframework.data.support.CrudService;

public interface TestService extends CrudService<Test, Long> {
    public void insert(Test test);
    public void updateIgnore(Test user);
}

package com.domain.api.companyManagement.service.impl;

import com.domain.api.companyManagement.CompanyManagementTx;
import com.domain.api.companyManagement.domain.Test;
import com.domain.api.companyManagement.repository.TestRepository;
import com.domain.api.companyManagement.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.support.AbstractCrudService;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl extends AbstractCrudService<TestRepository, Test, Long> implements TestService {

    @Autowired
    public TestServiceImpl(TestRepository repository) {
        super(repository);
    }

    @Override
    @CompanyManagementTx
    public void insert(Test user) {
        super.insert(user);
    }

    @Override
    public void updateIgnore(Test user) {
        super.updateIgnore(user);
    }
}

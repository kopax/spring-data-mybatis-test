package com.domain.api.companyManagement.controller;

import com.domain.api.companyManagement.service.TestService;
import com.domain.api.companyManagement.domain.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("test")
public class TestController {

  @Autowired
  private TestService testRepository;

  @GetMapping
  Page<Test> list(@PageableDefault(size = 20) Pageable pageable, Test cond) {
    Test test = new Test();
    test.setName("Hello "+ new Date().toString());

    testRepository.insert(test);

    return testRepository.findAll(pageable, cond);
  }


}

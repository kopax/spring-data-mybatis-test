package com.domain.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@EnableAutoConfiguration
@SpringBootApplication(scanBasePackages={
        "com.domain.api",
        "com.domain.api.userManagement",
        "com.domain.api.companyManagement"
})
//@Import({ InitialDataLoader.class })
public class Application {

  public static void main(String[] args) {
      LocalDate localDate = LocalDate.now();
      System.out.println("The current local time is: " + localDate);
      SpringApplication.run(Application.class, args);
  }

}
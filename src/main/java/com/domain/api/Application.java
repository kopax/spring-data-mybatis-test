package com.domain.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@EnableAutoConfiguration
@SpringBootApplication
public class Application {

  public static void main(String[] args) {
      LocalDate localDate = LocalDate.now();
      System.out.println("The current local time is: " + localDate);
    SpringApplication.run(Application.class, args);
  }

}
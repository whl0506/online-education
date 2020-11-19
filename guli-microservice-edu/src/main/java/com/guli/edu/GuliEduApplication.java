package com.guli.edu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.guli.edu.mapper")
public class GuliEduApplication {
     public static void main(String[] args) {
           SpringApplication.run(GuliEduApplication.class, args);
      }

}

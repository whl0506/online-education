package com.guli.edu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("com.guli.edu.mapper")
@EnableTransactionManagement
public class GuliEduApplication {
     public static void main(String[] args) {
           SpringApplication.run(GuliEduApplication.class, args);
      }

}

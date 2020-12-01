package com.guli.oss;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * 在@SpringBootApplication注解上加上exclude，解除自动加载DataSourceAutoConfiguration
 */

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ComponentScan(basePackages={"com.guli.oss","com.guli.common"})
@EnableEncryptableProperties
public class AliyunOssApplication {

     public static void main(String[] args) {
           SpringApplication.run(AliyunOssApplication.class, args);
      }

}

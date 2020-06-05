package com.example.springboot_o2o;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@MapperScan("com.example.springboot_o2o/mapper")
@EnableCaching
public class SpringbootO2oApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootO2oApplication.class, args);
	}

}

package com.example.o2o;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@MapperScan("com.example.o2o/mapper")
@EnableCaching
public class SpringBootO2OApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootO2OApplication.class, args);
	}

}

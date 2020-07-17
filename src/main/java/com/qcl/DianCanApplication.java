package com.qcl;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@MapperScan(value = "com.qcl.mybatis.mapper") //使用MapperScan批量扫描所有的Mapper接口
public class DianCanApplication {

	public static void main(String[] args) {
		System.out.println("springboot项目开始启动了");
		SpringApplication.run(DianCanApplication.class, args);
	}
}

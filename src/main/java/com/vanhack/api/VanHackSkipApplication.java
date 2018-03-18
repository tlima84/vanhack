package com.vanhack.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication(scanBasePackages = "com.vanhack.api.*")
public class VanHackSkipApplication {

	public static void main(String[] args) {
		SpringApplication.run(VanHackSkipApplication.class, args);
	}
}

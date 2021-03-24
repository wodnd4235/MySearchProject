package com.mysearch.work;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
@EnableJpaAuditing
@SpringBootApplication
public class KakaoTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(KakaoTestApplication.class, args);
	}

}

package com.dgu.clubauth.global;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing // created_at, updated_at 자동 입력을 위한 annotation
public class DguCentralClubAuthenticationSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(DguCentralClubAuthenticationSystemApplication.class, args);
	}

}

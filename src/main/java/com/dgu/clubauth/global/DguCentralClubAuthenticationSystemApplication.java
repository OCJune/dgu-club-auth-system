package com.dgu.clubauth.global;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

// Ensure components in both global and domain packages are scanned
@SpringBootApplication(scanBasePackages = "com.dgu.clubauth")
@EntityScan(basePackages = "com.dgu.clubauth.domain")
@EnableJpaRepositories(basePackages = "com.dgu.clubauth.domain")
@EnableJpaAuditing // created_at, updated_at 자동 입력을 위한 annotation
@OpenAPIDefinition(
	info = @Info(
		title = "DGU Central Club Authentication System API",
		version = "1.0",
		description = "중앙동아리 회원 인증 및 관리 시스템 API 문서"
	)
)
public class DguCentralClubAuthenticationSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(DguCentralClubAuthenticationSystemApplication.class, args);
	}

}

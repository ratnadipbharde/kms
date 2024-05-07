package com.emudhra.kms;

import com.emudhra.kms.dto.UserLoginDto;
import com.emudhra.kms.services.KmsService;
import com.emudhra.kms.services.KmsServiceImpl;
import com.emudhra.kms.utility.JwtUtility;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(KmsApplication.class, args);
	}

}

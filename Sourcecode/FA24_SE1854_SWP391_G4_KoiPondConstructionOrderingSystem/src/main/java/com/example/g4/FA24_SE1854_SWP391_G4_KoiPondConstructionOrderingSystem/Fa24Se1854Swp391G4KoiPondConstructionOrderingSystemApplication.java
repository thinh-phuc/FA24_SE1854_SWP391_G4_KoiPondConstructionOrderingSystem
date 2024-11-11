package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "KoiPondAPI", version = "1.0", description = "Information"))
@SecurityScheme(name = "api", scheme = "bearer", type = SecuritySchemeType.HTTP, in = SecuritySchemeIn.HEADER)
public class 	Fa24Se1854Swp391G4KoiPondConstructionOrderingSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(Fa24Se1854Swp391G4KoiPondConstructionOrderingSystemApplication.class, args);
	}	

}

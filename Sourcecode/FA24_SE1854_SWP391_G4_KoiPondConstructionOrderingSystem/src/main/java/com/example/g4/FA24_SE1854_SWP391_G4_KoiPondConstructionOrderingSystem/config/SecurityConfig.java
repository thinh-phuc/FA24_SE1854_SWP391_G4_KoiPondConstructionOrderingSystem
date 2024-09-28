package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SecurityConfig {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}

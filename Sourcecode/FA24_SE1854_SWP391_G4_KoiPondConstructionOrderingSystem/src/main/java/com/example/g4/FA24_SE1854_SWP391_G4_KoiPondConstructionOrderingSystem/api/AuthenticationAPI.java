package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.api;

import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.Customer;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.model.CustomerResponse;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.model.LoginRequest;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.model.RegisterRequest;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.service.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationAPI {
    @Autowired
    AuthenticationService authenticationService;
    @PostMapping("/api/register")
    public ResponseEntity register(@Valid @RequestBody RegisterRequest registerRequest){
        CustomerResponse customer=authenticationService.register(registerRequest);
        return ResponseEntity.ok(customer);
    }

    @PostMapping("/api/login")
    public ResponseEntity login(@Valid @RequestBody LoginRequest loginRequest){
        CustomerResponse customer=authenticationService.login(loginRequest);
        return ResponseEntity.ok(customer);
    }
}

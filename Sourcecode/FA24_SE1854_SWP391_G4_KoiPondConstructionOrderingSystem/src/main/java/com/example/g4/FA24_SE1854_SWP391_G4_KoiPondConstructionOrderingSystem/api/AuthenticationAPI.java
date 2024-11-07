package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.api;

import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.Customer;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.model.CustomerResponse;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.model.LoginRequest;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.model.RegisterRequest;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.service.AuthenticationService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@SecurityRequirement(name="api")
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

    @GetMapping("/staff")
    public List<CustomerResponse> listAllStaff() {
        return authenticationService.listAllStaff();
    }
}

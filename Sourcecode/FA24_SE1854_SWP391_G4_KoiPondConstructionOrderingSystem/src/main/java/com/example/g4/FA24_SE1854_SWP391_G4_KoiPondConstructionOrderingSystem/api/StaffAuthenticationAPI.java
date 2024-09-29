package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.api;

import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.model.StaffLoginRequest;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.model.StaffRegisterRequest;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.model.StaffResponse;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.service.StaffAuthenticationService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SecurityRequirement(name = "api")
@RequestMapping("api")
public class StaffAuthenticationAPI {
    @Autowired
    StaffAuthenticationService staffAuthenticationService;
    @PostMapping("register")
    public ResponseEntity register(@Valid @RequestBody StaffRegisterRequest staffRegisterRequest){
        StaffResponse staffResponse = staffAuthenticationService.register(staffRegisterRequest);
        return ResponseEntity.ok(staffRegisterRequest);
    }
//    @GetMapping("account")
//    public ResponseEntity getAllAccount(){
//        List<Staff> accountList = staffAuthenticationService.getAll();
//        return ResponseEntity.ok(accountList);
//    }
    @PostMapping("login")
    public ResponseEntity login(StaffLoginRequest staffLoginRequest){
        StaffResponse newAccount = staffAuthenticationService.login(staffLoginRequest);
        return ResponseEntity.ok(newAccount);
    }
}

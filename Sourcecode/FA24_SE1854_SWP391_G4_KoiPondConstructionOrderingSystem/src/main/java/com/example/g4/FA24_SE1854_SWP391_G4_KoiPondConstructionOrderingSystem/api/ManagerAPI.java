package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.api;

import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.model.AddStaffRequest;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.model.CustomerResponse;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.service.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ManagerAPI {
    @Autowired
    AuthenticationService authenticationService;
    @PostMapping("/api/manager/add-staff")
    public ResponseEntity addStaff(@Valid @RequestBody AddStaffRequest addStaffRequest){
        CustomerResponse staff=authenticationService.addStaff(addStaffRequest);
        return ResponseEntity.ok(staff);
    }
}
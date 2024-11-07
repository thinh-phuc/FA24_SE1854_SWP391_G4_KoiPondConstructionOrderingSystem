package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.api;

import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.Customer;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.model.GetStaffByRoleResponse;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.model.UpdateProfileRequest;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.service.CustomerService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SecurityRequirement(name="api")
@RestController
@RequestMapping("/api/customer")
@CrossOrigin(origins = "*")
public class CustomerAPI {
    @Autowired
    CustomerService customerService;

    @GetMapping("{role}")
    public ResponseEntity getStaffsByRole(@PathVariable String role){
        List<GetStaffByRoleResponse> customers = customerService.getStaffsByRole(role);
        return ResponseEntity.ok(customers);
    }

    @PutMapping
    public ResponseEntity updateProfile(@Valid @RequestBody UpdateProfileRequest updateProfileRequest){
        Customer customer= customerService.updateProfile(updateProfileRequest);
        return ResponseEntity.ok(customer);
    }
}

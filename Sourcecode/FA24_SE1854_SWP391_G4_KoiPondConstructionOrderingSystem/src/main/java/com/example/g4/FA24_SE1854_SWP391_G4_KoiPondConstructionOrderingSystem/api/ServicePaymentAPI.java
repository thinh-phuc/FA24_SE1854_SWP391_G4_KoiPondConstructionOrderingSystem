package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.api;

import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.ServicePayment;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.model.ServicePaymentRequest;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.service.ServicePaymentService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@SecurityRequirement(name = "api")
@RestController

public class ServicePaymentAPI {

    @Autowired
    ServicePaymentService servicePaymentService;

    @PostMapping("/api/service-payment")
    public ResponseEntity createServicePayment(@RequestBody @Valid ServicePaymentRequest servicePaymentRequest) {
        ServicePayment servicePayment = servicePaymentService.createServicePayment(servicePaymentRequest);
        return ResponseEntity.ok(servicePayment);
    }

    @PutMapping("/api/service-payment/{servicePaymentId}")
    public ResponseEntity updateServicePayment(@RequestBody @Valid ServicePaymentRequest servicePaymentRequest, @PathVariable Integer servicePaymentId) {
        ServicePayment servicePayment = servicePaymentService.updateServicePayment(servicePaymentId, servicePaymentRequest);
        return ResponseEntity.ok(servicePayment);
    }

    @DeleteMapping("/api/service-payment/{servicePaymentId}")
    public ResponseEntity deleteServicePayment(@PathVariable Integer servicePaymentId) {
        ServicePayment servicePayment = servicePaymentService.deleteServicePayment(servicePaymentId);
        return ResponseEntity.ok(servicePayment);
    }

    @GetMapping("/api/service-payment/{servicePaymentId}")
    public ResponseEntity getServicePayment(@PathVariable Integer servicePaymentId) {
        ServicePayment servicePayment = servicePaymentService.getServicePaymentById(servicePaymentId);
        return ResponseEntity.ok(servicePayment);
    }

    @GetMapping("/api/service-payment")
    public ResponseEntity getServicePayment() {
        List<ServicePayment> servicePayments = servicePaymentService.getAllServicePayment();
        return ResponseEntity.ok(servicePayments);
    }

}

package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.api;

import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.ServiceQuotation;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.model.ServiceQuotationRequest;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.service.ServiceQuotationService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SecurityRequirement(name="api")
@RestController
@RequestMapping("/api/service-quotations")
public class ServiceQuotationAPI {
    @Autowired
    private ServiceQuotationService serviceQuotationService;
    // Create a new ServiceQuotation
    @PostMapping
    public ResponseEntity<ServiceQuotation> addServiceQuotation(@RequestBody ServiceQuotationRequest serviceQuotationRequest) {
        try {
            ServiceQuotation createdQuotation = serviceQuotationService.addServiceQuotation(serviceQuotationRequest);
            return ResponseEntity.ok(createdQuotation);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    // Get a ServiceQuotation by its ID
    @GetMapping("/{id}")
    public ResponseEntity<ServiceQuotation> getServiceQuotationById(@PathVariable Integer id) {
        try {
            ServiceQuotation serviceQuotation = serviceQuotationService.findById(id);
            return ResponseEntity.ok(serviceQuotation);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Get all ServiceQuotations
    @GetMapping
    public ResponseEntity<List<ServiceQuotation>> getAllServiceQuotations() {
        List<ServiceQuotation> serviceQuotations = serviceQuotationService.findAll();
        return ResponseEntity.ok(serviceQuotations);
    }

    // Delete a ServiceQuotation by its ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteServiceQuotationById(@PathVariable Integer id) {
        try {
            serviceQuotationService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
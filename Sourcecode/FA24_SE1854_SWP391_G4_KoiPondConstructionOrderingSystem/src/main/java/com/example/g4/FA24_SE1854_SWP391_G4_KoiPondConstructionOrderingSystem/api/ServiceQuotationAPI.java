package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.api;

import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.ServiceQuotation;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.exception.DataNotFoundException;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.model.ServiceQuotationRequest;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.model.ServiceQuotationUpdateRequest;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.service.ServiceQuotationService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*" ,methods = {RequestMethod.GET,RequestMethod.PATCH, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@SecurityRequirement(name="api")
@RestController
@RequestMapping("/api/service-quotations")
public class ServiceQuotationAPI {
    @Autowired
    private ServiceQuotationService serviceQuotationService;
    // Create a new ServiceQuotation
    @PostMapping
    public ResponseEntity<ServiceQuotation> addServiceQuotation( @Valid  @RequestBody ServiceQuotationRequest serviceQuotationRequest) {
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
            serviceQuotationService.deleteServiceQuotationById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    // Update a ServiceQuotation by ID
    @PutMapping("/{id}")
    public ResponseEntity<ServiceQuotation> updateServiceQuotation(
            @PathVariable Integer id,
            @Valid @RequestBody ServiceQuotationUpdateRequest serviceQuotationRequest) {
        try {
            ServiceQuotation updatedQuotation = serviceQuotationService.updateServiceQuotation(id, serviceQuotationRequest);
            return ResponseEntity.ok(updatedQuotation);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<ServiceQuotation>> getServiceQuotationsByCustomerId(@PathVariable Integer customerId) {
        List<ServiceQuotation> quotations = serviceQuotationService.findByCustomerId(customerId);
        if (quotations.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(quotations);
    }
    @GetMapping("/by-request/{requestId}")
    public ResponseEntity<ServiceQuotation> getServiceQuotationByRequestId(@PathVariable Integer requestId) {
        try {
            ServiceQuotation serviceQuotation = serviceQuotationService.findByRequestID(requestId);
            return ResponseEntity.ok(serviceQuotation);
        } catch (DataNotFoundException e) {
            return ResponseEntity.notFound().build(); // Return 404 if the ServiceRequest is not found
        } catch (Exception e) {
            return ResponseEntity.status(500).build(); // Return 500 for other exceptions
        }
    }

    @PatchMapping("/{quotationId}/toggle-confirm")
    public ResponseEntity<ServiceQuotation> toggleIsConfirm(@PathVariable Integer quotationId) {
        try {
            // Call the service method to toggle the confirmation status
            ServiceQuotation updatedQuotation = serviceQuotationService.updateServiceQuotationIsConfirm(quotationId);
            return ResponseEntity.ok(updatedQuotation);
        } catch (DataNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }
}

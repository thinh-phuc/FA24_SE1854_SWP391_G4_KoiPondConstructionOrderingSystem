package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.api;

import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.ServiceRequest;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.model.AddServiceRequest;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.service.ServiceRequestService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*")
@SecurityRequirement(name="api")
@RestController
@RequestMapping("/api/service-requests")
public class ServiceRequestAPI {
    @Autowired
    private ServiceRequestService serviceRequestService;

    // Create a new ServiceRequest
    @PostMapping
    public ResponseEntity<ServiceRequest> addServiceRequest( @Valid @RequestBody AddServiceRequest serviceRequest) {
        try {
            ServiceRequest createdRequest = serviceRequestService.addServiceRequest(serviceRequest);
            return ResponseEntity.ok(createdRequest);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    // Get a ServiceRequest by its ID
    @GetMapping("/{id}")
    public ResponseEntity<ServiceRequest> getServiceRequestById(@PathVariable Integer id) {
        try {
            ServiceRequest serviceRequest = serviceRequestService.getServiceRequestById(id);
            return ResponseEntity.ok(serviceRequest);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Update a ServiceRequest
    @PutMapping("/{id}")
    public ResponseEntity<ServiceRequest> updateServiceRequest(
            @PathVariable Integer id,@Valid @RequestBody ServiceRequest serviceRequest) {
        try {
            ServiceRequest updatedRequest = serviceRequestService.updateServiceRequest(id, serviceRequest);
            return ResponseEntity.ok(updatedRequest);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    // Delete a ServiceRequest by its ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteServiceRequestById(@PathVariable Integer id) {
        try {
            serviceRequestService.deleteServiceRequestById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Get all ServiceRequests
    @GetMapping
    public ResponseEntity<List<ServiceRequest>> getAllServiceRequests() {
        List<ServiceRequest> serviceRequests = serviceRequestService.getAllServiceRequests();
        return ResponseEntity.ok(serviceRequests);
    }

    // Get ServiceRequests by Customer ID
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<ServiceRequest>> findServiceRequestsByCustomerId(@PathVariable Integer customerId) {
        List<ServiceRequest> serviceRequests = serviceRequestService.findServiceRequestsByCustomerId(customerId);
        return ResponseEntity.ok(serviceRequests);
    }

}

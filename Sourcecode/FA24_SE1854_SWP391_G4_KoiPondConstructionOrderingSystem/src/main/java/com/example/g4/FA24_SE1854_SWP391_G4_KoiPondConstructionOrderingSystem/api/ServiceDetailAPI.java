package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.api;

import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.ServiceDetail;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.model.ServiceDetailRequest;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.service.ServiceDetailService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SecurityRequirement(name="api")
@RestController
@RequestMapping("/api/service-details")
public class ServiceDetailAPI {
    @Autowired
    private ServiceDetailService serviceDetailService;
    // Create a new ServiceDetail
    @PostMapping
    public ResponseEntity<ServiceDetail> createServiceDetail(@RequestBody ServiceDetailRequest serviceDetailRequest) {
        try {
            ServiceDetail createdDetail = serviceDetailService.createServiceDetail(serviceDetailRequest);
            return ResponseEntity.ok(createdDetail);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    // Get a ServiceDetail by its ID
    @GetMapping("/{id}")
    public ResponseEntity<ServiceDetail> getServiceDetailById(@PathVariable Integer id) {
        try {
            ServiceDetail serviceDetail = serviceDetailService.getServiceDetailById(id);
            return ResponseEntity.ok(serviceDetail);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Get all ServiceDetails
    @GetMapping
    public ResponseEntity<List<ServiceDetail>> getAllServiceDetails() {
        List<ServiceDetail> serviceDetails = serviceDetailService.getAllServiceDetails();
        return ResponseEntity.ok(serviceDetails);
    }

    // Update a ServiceDetail
    @PutMapping("/{id}")
    public ResponseEntity<ServiceDetail> updateServiceDetail(@PathVariable Integer id, @RequestBody ServiceDetail serviceDetail) {
        try {
            ServiceDetail updatedDetail = serviceDetailService.updateServiceDetail(serviceDetail);
            return ResponseEntity.ok(updatedDetail);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    // Delete a ServiceDetail by its ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteServiceDetailById(@PathVariable Integer id) {
        try {
            serviceDetailService.deleteServiceDetailById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}

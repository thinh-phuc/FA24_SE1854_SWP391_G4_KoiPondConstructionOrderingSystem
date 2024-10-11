package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.api;

import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.ServiceCategory;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.service.ServiceCategoryService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*")
@RestController
@SecurityRequirement(name="api")
@RequestMapping("/api/service-categories")
public class ServiceCategoryAPI {
    @Autowired
    private ServiceCategoryService serviceCategoryService;

    @PostMapping
    public ResponseEntity<ServiceCategory> addServiceCategory(@Valid @RequestBody ServiceCategory serviceCategory) {
        ServiceCategory createdCategory = serviceCategoryService.addServiceCategory(serviceCategory);
        return  ResponseEntity.ok(createdCategory);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceCategory> getServiceCategoryById(@PathVariable Integer id) {
        ServiceCategory serviceCategory = serviceCategoryService.getServiceCategoryById(id);
        return ResponseEntity.ok(serviceCategory);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServiceCategory> updateServiceCategory(
            @PathVariable Integer id,
            @RequestBody ServiceCategory serviceCategory) {
        ServiceCategory updatedCategory = serviceCategoryService.updateServiceCategory(id, serviceCategory);
        return ResponseEntity.ok(updatedCategory);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteServiceCategoryById(@PathVariable Integer id) {
       serviceCategoryService.deleteServiceCategoryById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<ServiceCategory>> getAllServiceCategories() {
        List<ServiceCategory> serviceCategories = serviceCategoryService.getAllServiceCategories();
        return new ResponseEntity<>(serviceCategories, HttpStatus.OK);
    }
}

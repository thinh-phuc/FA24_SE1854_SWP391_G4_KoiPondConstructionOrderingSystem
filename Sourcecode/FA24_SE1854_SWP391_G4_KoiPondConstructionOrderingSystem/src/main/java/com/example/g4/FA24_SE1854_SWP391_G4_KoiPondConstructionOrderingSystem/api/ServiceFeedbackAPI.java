package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.api;

import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.ServiceFeedback;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.model.ServiceFeedbackRequest;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.service.ServiceFeedbackService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SecurityRequirement(name = "api")
@RestController
public class ServiceFeedbackAPI {

    @Autowired
    ServiceFeedbackService serviceFeedbackService;

    @PostMapping("/api/service-feedback")
    public ResponseEntity createServiceFeedback(@RequestBody @Valid ServiceFeedbackRequest serviceFeedbackRequest) {
        ServiceFeedback serviceFeedback = serviceFeedbackService.createServiceFeedback(serviceFeedbackRequest);
        return ResponseEntity.ok(serviceFeedback);
    }


    @PutMapping("/api/service-feedback/{serviceFeedbackId}")
    public ResponseEntity updateServiceFeedback(@Valid @RequestBody ServiceFeedbackRequest serviceFeedbackRequest, @PathVariable Integer serviceFeedbackId) {
        ServiceFeedback serviceFeedback = serviceFeedbackService.updateServiceFeedback(serviceFeedbackId, serviceFeedbackRequest);
        return ResponseEntity.ok(serviceFeedback);
    }

    @DeleteMapping("/api/service-feedback/{serviceFeedbackId}")
    public ResponseEntity deleteServiceFeedback(@PathVariable Integer serviceFeedbackId) {
        ServiceFeedback serviceFeedback = serviceFeedbackService.deleteServiceFeedback(serviceFeedbackId);
        return ResponseEntity.ok(serviceFeedback);
    }

    @GetMapping("/api/service-feedback/{serviceFeedbackId}")
    public ResponseEntity getServiceFeedback(@PathVariable Integer serviceFeedbackId) {
        ServiceFeedback serviceFeedback = serviceFeedbackService.getServiceFeedbackById(serviceFeedbackId);
        return ResponseEntity.ok(serviceFeedback);
    }

    @GetMapping("/api/service-feedback")
    public ResponseEntity getServiceFeedback() {
        List<ServiceFeedback> serviceFeedback = serviceFeedbackService.getAllServiceFeedback();
        return ResponseEntity.ok(serviceFeedback);
    }

}

package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.api;

import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.ServiceProgressLog;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.model.ServiceProgressLogRequest;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.service.ServiceProgressLogService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@SecurityRequirement(name = "api")
@RestController
public class ServiceProgressLogAPI {
    @Autowired
    ServiceProgressLogService serviceProgressLogService;

    @PostMapping("/api/create-progress-log")
    public ResponseEntity createServiceProgressLog(@RequestBody ServiceProgressLogRequest serviceProgressLogRequest) {
        ServiceProgressLog serviceProgressLog = serviceProgressLogService.CreateServiceProgressLog(serviceProgressLogRequest);
        return ResponseEntity.ok(serviceProgressLog);
    }

    @GetMapping("/api/view-progress-logs/{serviceProgressId}")
    public ResponseEntity getServiceProgressLogByServiceProgressId(@PathVariable Integer serviceProgressId) {
        List<ServiceProgressLog> serviceProgressLogs = serviceProgressLogService.GetAllServiceProgressLogsByServiceProgressId(serviceProgressId);
        return ResponseEntity.ok(serviceProgressLogs);
    }
}

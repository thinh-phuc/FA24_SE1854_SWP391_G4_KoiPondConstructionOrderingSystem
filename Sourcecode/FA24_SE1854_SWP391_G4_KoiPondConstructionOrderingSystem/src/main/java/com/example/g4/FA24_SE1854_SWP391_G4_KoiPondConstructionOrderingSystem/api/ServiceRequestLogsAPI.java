package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.api;

import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.ServiceRequestLog;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.service.ServiceRequestLogService;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.service.ServiceRequestService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author ServiceRequestLogsAPI
 */
@CrossOrigin(origins = "*")
@SecurityRequirement(name="api")
@RestController
@RequestMapping("/api/service-requests-logs")

public class ServiceRequestLogsAPI {

    @Autowired
    private ServiceRequestLogService serviceRequestLogService;

    @Autowired
    private ServiceRequestService serviceRequestService;


    @GetMapping("/{id}")
    public ResponseEntity<List<ServiceRequestLog>> getServiceRequestLogsByServiceRequestId(@PathVariable Integer id) {
        try {
            List<ServiceRequestLog> logs = serviceRequestLogService.findServiceRequestLogsbyId(id);
            return ResponseEntity.ok(logs);
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(null); // Hoặc xử lý lỗi tùy chỉnh khác
        }
    }
}

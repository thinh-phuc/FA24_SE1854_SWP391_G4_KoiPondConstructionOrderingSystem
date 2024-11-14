package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.api;

import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.RequestLog;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.service.RequestLogService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*")
@SecurityRequirement(name="api")
@RestController
public class RequestLogAPI {
    @Autowired
    RequestLogService requestLogService;

    @GetMapping("/api/request_log/{requestId}")
    public ResponseEntity getConstructionHistoryById(@PathVariable Integer requestId){
        List<RequestLog> requestLogs = requestLogService.getRequestLogsByRequestId(requestId);
        return ResponseEntity.ok(requestLogs);
    }
}

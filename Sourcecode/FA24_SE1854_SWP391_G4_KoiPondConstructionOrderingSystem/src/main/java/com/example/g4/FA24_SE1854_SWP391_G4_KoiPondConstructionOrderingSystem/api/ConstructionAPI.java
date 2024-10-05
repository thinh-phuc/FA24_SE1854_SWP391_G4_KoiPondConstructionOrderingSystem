package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.api;

import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.ConstructionHistory;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.model.ConstructionRequest;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.model.UpdateConstructionRequest;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.service.ConstructionHistoryService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@SecurityRequirement(name="api")
@RestController
public class ConstructionAPI {
    @Autowired
    ConstructionHistoryService constructionHistoryService;

    @PostMapping("/api/construction")
    public ResponseEntity createConstructionHistory(@Valid @RequestBody ConstructionRequest constructionRequest) {
        ConstructionHistory constructionHistory = constructionHistoryService.createConstructionHistory(constructionRequest);
        return ResponseEntity.ok(constructionHistory);
    }

    @PutMapping("/api/construction/{constructionHistoryId}")
    public ResponseEntity updateConstructionHistory(@Valid @RequestBody UpdateConstructionRequest constructionRequest, @PathVariable Integer constructionHistoryId) {
        ConstructionHistory constructionHistory = constructionHistoryService.updateConstructionHistory(constructionRequest, constructionHistoryId);
        return ResponseEntity.ok(constructionHistory);
    }

    @GetMapping("/api/construction")
    public ResponseEntity getAllConstructionHistory(){
        List<ConstructionHistory> constructionHistories=constructionHistoryService.getAllConstructions();
        return ResponseEntity.ok(constructionHistories);
    }
}

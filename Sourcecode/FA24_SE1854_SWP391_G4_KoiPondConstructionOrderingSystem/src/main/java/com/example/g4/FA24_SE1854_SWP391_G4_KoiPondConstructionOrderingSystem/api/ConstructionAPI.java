package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.api;

import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.AcceptanceDocument;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.ConstructionHistory;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.model.AcceptanceRequest;
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

    @PostMapping("/api/construction/acceptance-document")
    public ResponseEntity createAcceptanceDocument(@Valid @RequestBody AcceptanceRequest acceptanceRequest) {
        AcceptanceDocument acceptanceDocument = constructionHistoryService.createAcceptanceDocument(acceptanceRequest);
        return ResponseEntity.ok(acceptanceDocument);
    }

    @PutMapping("/api/construction/{constructionHistoryId}")
    public ResponseEntity updateConstructionHistory(@Valid @RequestBody UpdateConstructionRequest constructionRequest, @PathVariable Integer constructionHistoryId) {
        ConstructionHistory constructionHistory = constructionHistoryService.updateConstructionHistory(constructionRequest, constructionHistoryId);
        return ResponseEntity.ok(constructionHistory);
    }

    @PutMapping("/api/construction/finish-construction/{constructionHistoryId}")
    public ResponseEntity finishConstruction(@PathVariable Integer constructionHistoryId) {
        ConstructionHistory constructionHistory = constructionHistoryService.finishConstruction(constructionHistoryId);
        return ResponseEntity.ok(constructionHistory);
    }

    @GetMapping("/api/construction/get-active-constructions")
    public ResponseEntity getAllConstructionHistory(){
        List<ConstructionHistory> constructionHistories=constructionHistoryService.getAllConstructions();
        return ResponseEntity.ok(constructionHistories);
    }

    @GetMapping("/api/construction/get-construction-by-id/{constructionHistoryId}")
    public ResponseEntity getConstructionHistoryById(@PathVariable Integer constructionHistoryId){
        ConstructionHistory constructionHistory=constructionHistoryService.getConstructionHistoryById(constructionHistoryId);
        return ResponseEntity.ok(constructionHistory);
    }

    @GetMapping("/api/construction/get-active-acceptance_document")
    public ResponseEntity getAllAcceptanceDocument(){
        List<AcceptanceDocument> acceptanceDocuments=constructionHistoryService.getAllAcceptanceDocuments();
        return ResponseEntity.ok(acceptanceDocuments);
    }

    @GetMapping("/api/construction/get-acceptance_document-by-id/{acceptanceDocumentId}")
    public ResponseEntity getAcceptanceDocumentById(@PathVariable Integer acceptanceDocumentId){
        AcceptanceDocument acceptanceDocument=constructionHistoryService.getAcceptanceDocumentById(acceptanceDocumentId);
        return ResponseEntity.ok(acceptanceDocument);
    }

    @DeleteMapping("/api/construction/deleteConstruction/{constructionHistoryId}")
    public ResponseEntity deleteConstructionHistory(@PathVariable Integer constructionHistoryId){
        ConstructionHistory constructionHistory=constructionHistoryService.deleteConstructionHistory(constructionHistoryId);
        return ResponseEntity.ok(constructionHistory);
    }

    @DeleteMapping("/api/construction/deleteAcceptanceDocument/{acceptanceDocumentId}")
    public ResponseEntity deleteAcceptanceDocument(@PathVariable Integer acceptanceDocumentId){
        AcceptanceDocument acceptanceDocument=constructionHistoryService.deleteAcceptanceDocument(acceptanceDocumentId);
        return ResponseEntity.ok(acceptanceDocument);
    }
}

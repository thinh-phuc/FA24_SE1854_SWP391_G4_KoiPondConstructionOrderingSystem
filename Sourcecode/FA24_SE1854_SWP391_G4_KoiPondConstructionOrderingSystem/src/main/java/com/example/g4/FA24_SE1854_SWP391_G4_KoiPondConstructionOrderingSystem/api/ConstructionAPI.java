package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.api;

import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.AcceptanceDocument;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.ConstructionHistory;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.DesignProfile;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.model.AcceptanceRequest;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.model.ConstructionRequest;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.model.UpdateConstructionRequest;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.service.ConstructionHistoryService;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.service.DesignProfileService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*")
@SecurityRequirement(name="api")
@PreAuthorize("hasAuthority('CONSTRUCTOR')")
@RestController
public class ConstructionAPI {
    @Autowired
    ConstructionHistoryService constructionHistoryService;
    @Autowired
    DesignProfileService designProfileService;

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
        List<ConstructionHistory> constructionHistories=constructionHistoryService.getAllActiveConstructionsByStaff();
        return ResponseEntity.ok(constructionHistories);
    }

    @GetMapping("/api/construction/get-construction-by-id/{designProfileId}")
    public ResponseEntity getConstructionHistoryById(@PathVariable Integer designProfileId){
        ConstructionHistory constructionHistory=constructionHistoryService.getConstructionHistoryByDesignProfileId(designProfileId);
        return ResponseEntity.ok(constructionHistory);
    }

    @GetMapping("/api/construction/get-active-acceptance_document")
    public ResponseEntity getAllAcceptanceDocument(){
        List<AcceptanceDocument> acceptanceDocuments=constructionHistoryService.getAllActiveAcceptanceDocumentsByStaff();
        return ResponseEntity.ok(acceptanceDocuments);
    }

    @GetMapping("/api/construction/get-acceptance_document-by-id/{designProfileId}")
    public ResponseEntity getAcceptanceDocumentById(@PathVariable Integer designProfileId){
        AcceptanceDocument acceptanceDocument=constructionHistoryService.getAcceptanceDocumentByDesignProfileId(designProfileId);
        return ResponseEntity.ok(acceptanceDocument);
    }

    @GetMapping("/api/construction/get-design_profiles-by-constructor")
    public ResponseEntity getDesignProfilesByConstructor(){
        List<DesignProfile> designProfiles=designProfileService.getDesignProfilesByStaff();
        return ResponseEntity.ok(designProfiles);
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

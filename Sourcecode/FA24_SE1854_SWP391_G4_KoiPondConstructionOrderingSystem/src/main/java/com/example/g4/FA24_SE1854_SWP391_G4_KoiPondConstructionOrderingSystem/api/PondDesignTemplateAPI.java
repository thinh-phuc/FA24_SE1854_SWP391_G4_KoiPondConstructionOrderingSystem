package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.api;

import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.PondDesignTemplate;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.model.PondDesignTemplateRequest;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.repository.PondDesignTemplateRepository;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.service.PondDesignTemplateService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@SecurityRequirement(name="api")
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/pondDesignTemplate")
public class PondDesignTemplateAPI {

    @Autowired
    PondDesignTemplateService pondDesignTemplateService;

    @PostMapping
    public ResponseEntity create(@Valid @RequestBody PondDesignTemplateRequest pondDesignTemplateRequest){
        PondDesignTemplate newPond = pondDesignTemplateService.createTemplate(pondDesignTemplateRequest);
        return ResponseEntity.ok(newPond);
    }

    @GetMapping
    public ResponseEntity getAllPondDesignTemplates(){
        List<PondDesignTemplate> pondDesignTemplates = pondDesignTemplateService.getAllTemplates();
        return ResponseEntity.ok(pondDesignTemplates);
    }

    @GetMapping("/get-PondDesignTemplate-by-id/{pondDesignTemplateId}")
    public ResponseEntity getPondDesignTemplatesById(@PathVariable Integer pondDesignTemplateId){
        PondDesignTemplate pondDesignTemplate = pondDesignTemplateService.getPondDesignTemplateById(pondDesignTemplateId);
        return ResponseEntity.ok(pondDesignTemplate);
    }

    @PutMapping("{pondDesignTemplateId}")
    public ResponseEntity update(@PathVariable Integer pondDesignTemplateId, @Valid @RequestBody PondDesignTemplate pondDesignTemplate){
        PondDesignTemplate updatedTemplate = pondDesignTemplateService.updateTemplate(pondDesignTemplateId, pondDesignTemplate);
        return ResponseEntity.ok(updatedTemplate);
    }

    @DeleteMapping("{pondDesignTemplateId}")
    public ResponseEntity delete(@PathVariable Integer pondDesignTemplateId){
        PondDesignTemplate deletedTemplate = pondDesignTemplateService.delete(pondDesignTemplateId);
        return ResponseEntity.ok(deletedTemplate);
    }
}

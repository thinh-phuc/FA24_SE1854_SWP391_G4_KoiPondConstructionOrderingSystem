package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.api;

import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.PondDesignTemplate;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.repository.PondDesignTemplateRepository;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.service.PondDesignTemplateService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*")
@SecurityRequirement(name="api")
@RestController
@RequestMapping("/api/template")
public class PondDesignTemplateAPI {

    @Autowired
    PondDesignTemplateService pondDesignTemplateService;

    @PostMapping
    public ResponseEntity create(@Valid @RequestBody PondDesignTemplate pondDesignTemplate){
        PondDesignTemplate newPond = pondDesignTemplateService.createTemplate(pondDesignTemplate);
        return ResponseEntity.ok(newPond);
    }

    @GetMapping
    public ResponseEntity getAllTemplates(){
        List<PondDesignTemplate> pondDesignTemplates = pondDesignTemplateService.getAllTemplates();
        return ResponseEntity.ok(pondDesignTemplates);
    }

    @PutMapping("{pondId}")
    public ResponseEntity update(@PathVariable Integer pondId, @Valid @RequestBody PondDesignTemplate pondDesignTemplate){
        PondDesignTemplate updatedTemplate = pondDesignTemplateService.updateTemplate(pondId, pondDesignTemplate);
        return ResponseEntity.ok(updatedTemplate);
    }

    @DeleteMapping("{pondId}")
    public ResponseEntity delete(Integer pondId){
        PondDesignTemplate deletedTemplate = pondDesignTemplateService.delete(pondId);
        return ResponseEntity.ok(deletedTemplate);
    }
}

package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.api;

import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.ConstructionHistory;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.Design;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.model.DesignRequest;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.model.DesignResponse;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.model.UpdateDesignRequest;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.model.UpdateDesignResponse;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.service.DesignService;
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
@RequestMapping("/api/design")
public class DesignAPI {
    @Autowired
    DesignService designService;

    @PostMapping
    @PreAuthorize("hasAuthority('DESIGNER')")
    public ResponseEntity create(@Valid @RequestBody DesignRequest designRequest) {
        DesignResponse newDesign = designService.create(designRequest);
        return ResponseEntity.ok(newDesign);
    }

    @PutMapping("{designId}")
    @PreAuthorize("hasAuthority('DESIGNER')")
    public ResponseEntity update(@PathVariable Integer designId, @Valid @RequestBody UpdateDesignRequest design) {
        UpdateDesignResponse oldDesign = designService.update(designId, design);
        return ResponseEntity.ok(oldDesign);

    }

    @DeleteMapping("{designId}")
    @PreAuthorize("hasAuthority('DESIGNER')")
    public ResponseEntity delete(@PathVariable Integer designId) {
        Design design = designService.delete(designId);
        return ResponseEntity.ok(design);
    }

    @GetMapping
    public ResponseEntity getAll() {
        List<Design> designList = designService.getAll();
        return ResponseEntity.ok(designList);

    }
    //get by id
    @GetMapping("/getDesignById/{designId}")
    public ResponseEntity getById(@PathVariable Integer designId) {
       Design designList = designService.getDesignById(designId);
        return ResponseEntity.ok(designList);

    }
    @GetMapping("/getDesignByDesignProfile/{designProfileId}")
    @PreAuthorize("hasAnyAuthority('DESIGNER','MANAGER','CUSTOMER')")
    public ResponseEntity getByDesignProfile(@PathVariable Integer designProfileId){
        List<Design> designList = designService.getDesignByProfileId(designProfileId);
        return ResponseEntity.ok(designList);
    }

    @PutMapping("/finish-design/{designId}")
    @PreAuthorize("hasAuthority('DESIGNER')")
    public ResponseEntity finishDesign(@PathVariable Integer designId) {
        Design design = designService.finishDesign(designId);
        return ResponseEntity.ok(design);
    }
}

package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.api;

import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.DesignProfile;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.model.*;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.service.DesignProfileService;
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
@RequestMapping("/api/designProfile")
public class DesignProfileAPI {
    @Autowired
    DesignProfileService designProfileService;

    @PostMapping
    @PreAuthorize("hasAuthority('MANAGER')")
    public ResponseEntity create(@Valid @RequestBody DesignProfileRequest designProfileRequest) {
            DesignProfileResponse newDesignProfile = designProfileService.create(designProfileRequest);
            return ResponseEntity.ok(newDesignProfile);
    }
    @GetMapping
    @PreAuthorize("hasAnyAuthority('DESIGNER','MANAGER')")
    public ResponseEntity getAll(){
        List<GetAllDesignProfile> designProfiles = designProfileService.getAll();
        return ResponseEntity.ok(designProfiles);
    }
    //get By Id
    @GetMapping("/getById/{designProfileId}")
    public ResponseEntity getById(@PathVariable Integer designProfileId){
        DesignProfile designProfile = designProfileService.getDesignProfileById(designProfileId);
        return ResponseEntity.ok(designProfile);
    }

    @GetMapping("/getByStaffId/{staffId}")
    public ResponseEntity getByStaffId(@PathVariable Integer staffId){
        List<DesignProfile> list = designProfileService.getDesignProfilesByStaffId(staffId);
        return ResponseEntity.ok(list);
    }

    @PutMapping("{designProfileId}")
    @PreAuthorize("hasAnyAuthority('DESIGNER','MANAGER')")
    public ResponseEntity update ( @PathVariable Integer designProfileId,@Valid @RequestBody UpdateDesignProfileRequest designProfile ) {
        UpdateDesignProfileResponse updateDesignProfile = designProfileService.update(designProfileId,designProfile);
        return ResponseEntity.ok(updateDesignProfile);
    }

    @DeleteMapping("{designProfileId}")
    @PreAuthorize("hasAnyAuthority('DESIGNER','MANAGER')")
    public ResponseEntity delete (@PathVariable Integer designProfileId){
        DesignProfile designProfile = designProfileService.delete(designProfileId);
        return  ResponseEntity.ok(designProfile);
    }
    @GetMapping("/get-design_profiles-by-design_staff")
    @PreAuthorize("hasAnyAuthority('DESIGNER','CONSTRUCTOR')")
    public ResponseEntity getDesignProfilesByDesigner(){
        List<GetAllDesignProfile> designProfiles=designProfileService.getDesignProfilesByDesignStaff();
        return ResponseEntity.ok(designProfiles);
    }


}

package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.api;

import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.DesignProfile;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.model.DesignProfileRequest;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.service.DesignProfileService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity create(@Valid @RequestBody DesignProfileRequest designProfileRequest) {
            DesignProfile newDesignProfile = designProfileService.create(designProfileRequest);
            return ResponseEntity.ok(newDesignProfile);
    }
    @GetMapping
    public ResponseEntity getAllStudent(){
        List<DesignProfile> designProfiles = designProfileService.getAll();
        return ResponseEntity.ok(designProfiles);
    }

    @PutMapping("{designProfileId}")
    public ResponseEntity update ( @PathVariable Integer designProfileId,@Valid @RequestBody DesignProfileRequest designProfile ) {
        DesignProfile updateDesignProfile = designProfileService.update(designProfileId,designProfile);
        return ResponseEntity.ok(updateDesignProfile);
    }

    @DeleteMapping("{designProfileId}")
    public ResponseEntity delete (@PathVariable Integer designProfileId){
        DesignProfile designProfile = designProfileService.delete(designProfileId);
        return  ResponseEntity.ok(designProfile);
    }
//    @GetMapping("/api/construction/get-design_profiles-by-design_staff")
//    public ResponseEntity getDesignProfilesByDesigner(){
//        List<DesignProfile> designProfiles=designProfileService.getDesignProfilesByStaff();
//        return ResponseEntity.ok(designProfiles);
//    }


}

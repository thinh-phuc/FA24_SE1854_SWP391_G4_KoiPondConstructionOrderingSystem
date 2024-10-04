package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.api;

import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.Design;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.model.DesignRequest;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.service.DesignService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/design")
public class DesignAPI {
    @Autowired
    DesignService designService;
    @PostMapping
    public ResponseEntity create (@Valid @RequestBody DesignRequest designRequest){
        Design newDesign = designService.create(designRequest);
        return ResponseEntity.ok(newDesign);
    }
    @PutMapping("{designId}")
    public ResponseEntity update(@PathVariable Integer designId, @Valid @RequestBody Design design){
        Design oldDesign = designService.update(designId,design);
        return ResponseEntity.ok(oldDesign);

    }
    @DeleteMapping("{designId}")
    public ResponseEntity delete(@PathVariable Integer designId){
        Design design = designService.delete(designId);
        return  ResponseEntity.ok(design);
    }
    @GetMapping
    public ResponseEntity getAll(){
        List<Design> designList= designService.getAll();
        return  ResponseEntity.ok(designList);

    }
}

package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.api;

import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.Consult;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.service.ConsultService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*")
@SecurityRequirement(name="api")
@RestController
@RequestMapping("/api/consult")
public class ConsultAPI {

    @Autowired
    ConsultService consultService;

    @PostMapping
    public ResponseEntity create(@Valid @RequestBody Consult consult){
        Consult newConsult = consultService.createConsult(consult);
        return ResponseEntity.ok(newConsult);
    }

    @GetMapping
    public ResponseEntity getAllConsults(){
        List<Consult> consults = consultService.getAllConsults();
        return ResponseEntity.ok(consults);
    }

    @PutMapping("{consultId}")
    public ResponseEntity update(@PathVariable Integer consultId, @Valid @RequestBody Consult consult){
        Consult updatedConsult = consultService.updateConsult(consultId, consult);
        return ResponseEntity.ok(updatedConsult);
    }

    @DeleteMapping("{consultId}")
    public ResponseEntity delete(@PathVariable Integer consultId){
        Consult deletedConsult = consultService.deleteConsult(consultId);
        return ResponseEntity.ok(deletedConsult);
    }
}

package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.api;


import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.RequestDetail;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.service.RequestDetailService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@SecurityRequirement(name="api")
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/requestDetail")
public class RequestDetailAPI {

    @Autowired
    RequestDetailService requestDetailService;

    @PostMapping
    public ResponseEntity create(@Valid @RequestBody RequestDetail requestDetail){
        RequestDetail newDetail = requestDetailService.createDetail(requestDetail);
        return ResponseEntity.ok(newDetail);
    }

    @GetMapping
    public ResponseEntity getAllDetails(){
        List<RequestDetail> requestDetails = requestDetailService.getAllDetails();
        return ResponseEntity.ok(requestDetails);
    }

    @PutMapping("{detailId}")
    public ResponseEntity updateDetail(@PathVariable Integer detailId, @Valid @RequestBody RequestDetail requestDetail){
        RequestDetail updatedDetail = requestDetailService.updateDetail(detailId, requestDetail);
        return ResponseEntity.ok(updatedDetail);
    }

    @DeleteMapping("{detailId}")
    public ResponseEntity deleteDetail(@PathVariable Integer detailId){
        RequestDetail deletedDetail = requestDetailService.deleteDetail(detailId);
        return ResponseEntity.ok(deletedDetail);
    }

}

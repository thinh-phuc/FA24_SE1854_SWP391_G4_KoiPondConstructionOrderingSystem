package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.api;


import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.Request;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.RequestDetail;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.model.RequestDetailRequest;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.model.UpdateRequestDetailRequest;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.service.RequestDetailService;
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
@RequestMapping("/api/requestDetail")

public class RequestDetailAPI {

    @Autowired
    RequestDetailService requestDetailService;

    @PostMapping
    public ResponseEntity create(@Valid @RequestBody RequestDetailRequest requestDetailRequest){
        RequestDetail newRequestDetail = requestDetailService.createRequestDetail(requestDetailRequest);
        return ResponseEntity.ok(newRequestDetail);
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('CONSULTANT','MANAGER','DESIGNER')")
    public ResponseEntity getAllDetails(){
        List<RequestDetail> requestDetails = requestDetailService.getAllDetails();
        return ResponseEntity.ok(requestDetails);
    }


    @GetMapping("/get-requestDetail-by-RequestId/{requestId}")
    public ResponseEntity getRequestDetailByRequestId(@PathVariable Integer requestId){
        List<RequestDetail> requestDetails = requestDetailService.getAllRequestDetailsByRequestId(requestId);
        return ResponseEntity.ok(requestDetails);
    }


    @PutMapping("{requestDetailId}")
    public ResponseEntity updateDetail(@PathVariable Integer requestDetailId, @Valid @RequestBody UpdateRequestDetailRequest updateRequestDetailRequest){
        RequestDetail updatedDetail = requestDetailService.updateRequestDetail(requestDetailId, updateRequestDetailRequest);
        return ResponseEntity.ok(updatedDetail);
    }

    @DeleteMapping("{requestDetailId}")
    public ResponseEntity deleteDetail(@PathVariable Integer requestDetailId){
        RequestDetail deletedDetail = requestDetailService.deleteRequestDetail(requestDetailId);
        return ResponseEntity.ok(deletedDetail);
    }

}

package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.api;

import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.Request;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.model.RequestRequest;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.model.UpdateRequestRequest;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.service.RequestService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@SecurityRequirement(name="api")
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/request")
@CrossOrigin(origins = "*")
public class RequestAPI {

    @Autowired
    RequestService requestService;

    @PostMapping
    public ResponseEntity createRequest(@Valid @RequestBody RequestRequest requestRequest){
        Request newRequest = requestService.create(requestRequest);
        return ResponseEntity.ok(newRequest);
    }

    @GetMapping
    public ResponseEntity getListRequest(){
        List<Request> requests = requestService.getAllRequests();
        return ResponseEntity.ok(requests);
    }

    @GetMapping("/get-Request-by-id/{requestId}")
    public ResponseEntity getRequestByRequestId(@PathVariable Integer requestId){
        Request request = requestService.getRequestById(requestId);
        return ResponseEntity.ok(request);
    }

    @PutMapping("{requestId}")
    public ResponseEntity update(@PathVariable Integer requestId, @Valid @RequestBody UpdateRequestRequest updateRequestRequest){
        Request updatedRequest = requestService.update(requestId, updateRequestRequest);
        return ResponseEntity.ok(updatedRequest);
    }

    @DeleteMapping("{requestId}")
    public ResponseEntity delete(@PathVariable Integer requestId){
        Request deletedRequest = requestService.delete(requestId);
        return ResponseEntity.ok(deletedRequest);
    }
}

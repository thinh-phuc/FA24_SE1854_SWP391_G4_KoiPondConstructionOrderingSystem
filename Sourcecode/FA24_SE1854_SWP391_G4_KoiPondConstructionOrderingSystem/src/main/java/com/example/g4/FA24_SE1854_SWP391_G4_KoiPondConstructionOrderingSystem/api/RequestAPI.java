package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.api;

import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.Request;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.service.RequestService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*")
@SecurityRequirement(name="api")
@RestController
@RequestMapping("/api/request")
public class RequestAPI {

    @Autowired
    RequestService requestService;

    @PostMapping
    public ResponseEntity createRequest(@Valid @RequestBody Request request){
        Request newRequest = requestService.create(request);
        return ResponseEntity.ok(newRequest);
    }

    @GetMapping
    public ResponseEntity getAllRequest(){
        List<Request> requests = requestService.getAllRequests();
        return ResponseEntity.ok(requests);
    }

    @PutMapping("{requestId}")
    public ResponseEntity update(@PathVariable Integer requestId, @Valid @RequestBody Request request){
        Request updatedRequest = requestService.update(requestId, request);
        return ResponseEntity.ok(updatedRequest);
    }

    @DeleteMapping("{requestId}")
    public ResponseEntity delete(@PathVariable Integer requestId){
        Request deletedRequest = requestService.delete(requestId);
        return ResponseEntity.ok(deletedRequest);
    }
}

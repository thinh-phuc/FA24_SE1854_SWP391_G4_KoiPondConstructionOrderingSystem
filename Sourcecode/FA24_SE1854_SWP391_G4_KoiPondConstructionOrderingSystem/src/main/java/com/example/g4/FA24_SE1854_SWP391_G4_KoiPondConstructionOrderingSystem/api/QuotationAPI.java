package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.api;

import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.Quotation;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.model.QuotationRequest;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.model.QuotationResponse;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.model.UpdateQuotationRequest;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.model.UpdateQuotationResponse;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.service.QuotationService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@SecurityRequirement(name="api")
@RestController
@RequestMapping("/api/quotation")
@CrossOrigin(origins = "*")
public class QuotationAPI {
@Autowired
    QuotationService quotationService;

    @PostMapping
    public ResponseEntity create (@Valid @RequestBody QuotationRequest quotationRequest) {
    QuotationResponse newQuotation = quotationService.create(quotationRequest);
    return ResponseEntity.ok(newQuotation);
    }

    @GetMapping
    public ResponseEntity getAll(){
            List<Quotation> quotationList = quotationService.getQuotationAll();
            return ResponseEntity.ok(quotationList);
    }
    //get by Id
    @GetMapping("/getById/{quotationId}")
    public ResponseEntity getAll(@PathVariable Integer quotationId){
        Quotation quotation = quotationService.getQuotationById(quotationId);
        return ResponseEntity.ok(quotation);
    }


    @PutMapping("{quotationId}")
    public ResponseEntity update ( @PathVariable Integer quotationId,@Valid @RequestBody UpdateQuotationRequest quotation ) {
            UpdateQuotationResponse updateQuotation = quotationService.update(quotationId,quotation);
             return ResponseEntity.ok(updateQuotation);
    }

    @DeleteMapping("{quotationId}")
    public ResponseEntity delete (@PathVariable Integer quotationId){
    Quotation deleteQuotation = quotationService.delete(quotationId);
    return  ResponseEntity.ok(deleteQuotation);
    }

    @PutMapping("/confirmQuotation/{quotationId}")
    public ResponseEntity confirmQuotation(@PathVariable Integer quotationId){
        Quotation quotation = quotationService.confirmQuotation(quotationId);
        return  ResponseEntity.ok(quotation);
    }
    }


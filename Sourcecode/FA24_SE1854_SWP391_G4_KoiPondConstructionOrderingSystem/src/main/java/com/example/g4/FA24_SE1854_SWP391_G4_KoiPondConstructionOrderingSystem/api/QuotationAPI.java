package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.api;

import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.Quotation;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.model.QuotationRequest;
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
    Quotation newQuotation = quotationService.create(quotationRequest);
    return ResponseEntity.ok(newQuotation);
    }

    @GetMapping
    public ResponseEntity getAllStudent(){
            List<Quotation> quotationList = quotationService.getQuotationAll();
            return ResponseEntity.ok(quotationList);
    }

    @PutMapping("{quotationId}")
    public ResponseEntity update ( @PathVariable Integer quotationId,@Valid @RequestBody Quotation quotation ) {
            Quotation updateQuotation = quotationService.update(quotationId,quotation);
             return ResponseEntity.ok(updateQuotation);
    }

    @DeleteMapping("{quotationId}")
    public ResponseEntity delete (@PathVariable Integer quotationId){
    Quotation deleteQuotation = quotationService.delete(quotationId);
    return  ResponseEntity.ok(deleteQuotation);
    }

    }


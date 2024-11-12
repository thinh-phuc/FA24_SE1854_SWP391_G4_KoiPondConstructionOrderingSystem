package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.api;

import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.Quotation;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.model.*;
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
            List<GetAllQuotationResponse> quotationList = quotationService.getQuotationAll();
            return ResponseEntity.ok(quotationList);
    }
    //get by id
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
    @GetMapping("/getQuotationByCustomer")
    public  ResponseEntity getQuotationByCustomer(){
        List<GetAllQuotationResponse> quotationList = quotationService.getQuotationsByCustomer();
        return ResponseEntity.ok(quotationList);
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<Quotation>> listQuotationsByCustomer(@PathVariable Integer customerId) {
        List<Quotation> quotations = quotationService.listQuotationByCustomer(customerId);
        return ResponseEntity.ok(quotations);
    }
    @GetMapping("/customer")
    public ResponseEntity<List<Quotation>> getQuotationsByCurrentCustomer() {
        List<Quotation> quotations = quotationService.QuotationsByCustomer();
        return ResponseEntity.ok(quotations);
    }
    }


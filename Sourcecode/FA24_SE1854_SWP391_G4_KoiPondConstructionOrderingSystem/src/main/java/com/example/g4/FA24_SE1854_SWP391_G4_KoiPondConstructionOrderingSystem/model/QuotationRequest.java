package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class QuotationRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer quotationId;
    private Integer customerId;
   // private Integer pondDesignTemplateId;
    private Integer consultId;
    private Boolean isConfirm = false;
    private String description = "none";
    private String url = "none";
    private float mainCost = 0.0f;
    private float subCost = 0.0f;
    private float VAT = 0.0f;

}

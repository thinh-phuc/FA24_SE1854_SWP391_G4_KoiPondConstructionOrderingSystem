package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.model;

import lombok.Data;

@Data
public class ServiceQuotationRequest {
    private Integer serviceRequestId;
    private String description;
    private float VAT;

}

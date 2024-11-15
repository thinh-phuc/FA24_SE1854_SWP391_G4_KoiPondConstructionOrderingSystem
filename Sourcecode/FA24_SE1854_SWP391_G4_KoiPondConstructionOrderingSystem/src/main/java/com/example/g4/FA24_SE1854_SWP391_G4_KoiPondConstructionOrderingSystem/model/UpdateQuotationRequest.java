package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.model;

import lombok.Data;

@Data
public class UpdateQuotationRequest {
    private String description = "none";
    private String url = "none";
    private float mainCost = 0.0f;
    private float subCost = 0.0f;
    private float VAT = 0.0f;
}

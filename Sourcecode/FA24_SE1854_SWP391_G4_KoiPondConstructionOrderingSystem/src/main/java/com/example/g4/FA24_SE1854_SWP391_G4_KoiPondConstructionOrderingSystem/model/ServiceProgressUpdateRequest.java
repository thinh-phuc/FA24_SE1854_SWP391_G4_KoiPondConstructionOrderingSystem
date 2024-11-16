package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.model;

import lombok.Data;

@Data
public class ServiceProgressUpdateRequest {
    private Integer serviceDetailID;
    private String step;
    private String description;
    private String imageUrl;
}

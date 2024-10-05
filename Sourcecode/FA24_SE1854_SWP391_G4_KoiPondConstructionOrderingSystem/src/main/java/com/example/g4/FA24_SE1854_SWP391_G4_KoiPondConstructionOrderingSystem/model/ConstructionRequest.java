package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.model;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class ConstructionRequest {
    private String step;
    private String description;
    private String note;
    private Integer designProfileId;
}
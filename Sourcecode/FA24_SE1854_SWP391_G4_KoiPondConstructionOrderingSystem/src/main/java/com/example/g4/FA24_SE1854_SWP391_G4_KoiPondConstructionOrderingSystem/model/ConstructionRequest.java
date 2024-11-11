package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.model;

import jakarta.persistence.Column;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ConstructionRequest {
    private String step;
    private String description;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String note;
    private Integer designProfileId;
}

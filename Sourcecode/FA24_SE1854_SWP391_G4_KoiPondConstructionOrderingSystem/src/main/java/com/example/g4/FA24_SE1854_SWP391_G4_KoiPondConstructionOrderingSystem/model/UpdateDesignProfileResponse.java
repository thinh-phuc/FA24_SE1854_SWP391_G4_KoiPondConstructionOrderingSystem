package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UpdateDesignProfileResponse {
    private int quotationId;
    private String address;
    private String constructionStatus;
    private String description;
    private Boolean isActive = true;
    private LocalDateTime updateDate = LocalDateTime.now();
    private String updateBy;
}

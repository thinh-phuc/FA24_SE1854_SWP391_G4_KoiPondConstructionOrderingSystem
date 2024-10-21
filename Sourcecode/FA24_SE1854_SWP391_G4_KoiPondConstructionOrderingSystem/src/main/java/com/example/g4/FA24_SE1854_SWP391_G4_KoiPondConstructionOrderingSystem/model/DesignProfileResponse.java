package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.model;

import jakarta.persistence.Column;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class DesignProfileResponse {
    private int quotationId;
    private String address;
    private String constructionStatus;
    private String description;
    private Boolean isActive = true;
    private LocalDateTime createDate = LocalDateTime.now();
    private String createBy = "none";
}

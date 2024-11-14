package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.model;

import jakarta.persistence.Column;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AcceptanceRequest {
    private String description;
    private String confirmCustomerName;
    private String confirmConstructorName;
    private Integer designProfileId;
    private LocalDateTime confirmDate;
    private String fileUrl;
}

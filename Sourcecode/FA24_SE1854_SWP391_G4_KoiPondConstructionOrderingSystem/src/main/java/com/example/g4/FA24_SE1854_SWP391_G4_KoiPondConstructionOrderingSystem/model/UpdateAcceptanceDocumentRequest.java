package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UpdateAcceptanceDocumentRequest {
    private LocalDateTime confirmDate;
    private String description;
    private String confirmCustomerName;
    private String confirmConstructorName;
    private String fileUrl;
}

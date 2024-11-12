package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.model;

import lombok.Data;

@Data
public class ServiceFeedbackRequest {
    private Integer serviceRequestId;
    private Integer customerId;
    private String feedback;
    private Integer rating;
}

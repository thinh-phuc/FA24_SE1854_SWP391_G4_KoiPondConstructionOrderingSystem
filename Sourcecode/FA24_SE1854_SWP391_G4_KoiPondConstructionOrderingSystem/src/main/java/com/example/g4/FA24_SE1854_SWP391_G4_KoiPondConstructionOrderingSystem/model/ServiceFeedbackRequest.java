package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.model;

import lombok.Data;

@Data
public class ServiceFeedbackRequest {
    private Integer serviceDetailId;
    private String feedback;
    private Integer rating;
    private String note;
}

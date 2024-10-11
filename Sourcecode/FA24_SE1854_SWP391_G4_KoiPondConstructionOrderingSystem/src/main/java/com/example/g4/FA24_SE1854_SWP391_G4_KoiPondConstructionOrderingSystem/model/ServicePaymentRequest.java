package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.model;

import lombok.Data;

@Data
public class ServicePaymentRequest {
    private Integer serviceQuotationID;
    private String paymentMethod;
    private Integer maintenanceStaffID;
    private Integer serviceProgressID;
    private String status;
}

package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.model;

import lombok.Data;

@Data
public class ServicePaymentRequest {
    private Integer serviceQuotationID;
    private String transactionID;
    private String paymentMethod;
    private Integer maintenanceStaffID;
    private String status;
    //đổi service request status thành Finish
}

package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @author PaymentVNPayResponse
 */
@Getter
@Setter
@Data
public class PaymentVNPayResponse {
    private String status;
    private String message;
    private String URL;
}

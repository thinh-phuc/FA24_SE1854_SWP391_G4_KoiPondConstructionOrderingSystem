package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @author TransactionResponse
 */
@Data
@Getter
@Setter
public class TransactionResponse {
    private String status;
    private  String message;
    private String data;
}

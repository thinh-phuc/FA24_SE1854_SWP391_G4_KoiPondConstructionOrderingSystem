package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.model;

import lombok.Data;

@Data
public class AddServiceRequest {
    private Integer categoryID;
    private String description;
    private String address;
    private String note;
}
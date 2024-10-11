package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.model;

import lombok.Data;

@Data
public class ServiceProgressResquest {
    private Integer serviceDetailID;
    private Integer step;
    private String description;
}

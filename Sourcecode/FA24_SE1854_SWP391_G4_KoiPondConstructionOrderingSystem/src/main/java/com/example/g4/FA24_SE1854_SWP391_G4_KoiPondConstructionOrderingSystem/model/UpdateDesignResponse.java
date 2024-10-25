package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UpdateDesignResponse {
    private Integer designId;
    private Integer designProfileId;
    private String designStatus = "none";
    private String design = "none";
    private Boolean isActive = true;
    private String description = "none";
    private LocalDateTime UpdateDate = LocalDateTime.now();
    private String UpdateBy = "none";

}

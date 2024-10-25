package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.model;

import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.DesignProfile;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DesignResponse {

    private Integer designId;
    private Integer designProfileId;
    private String designStatus = "none";
    private String design = "none";
    private Boolean isActive = true;
    private String description = "none";
    private LocalDateTime createDate = LocalDateTime.now();
    private String createBy = "none";





}

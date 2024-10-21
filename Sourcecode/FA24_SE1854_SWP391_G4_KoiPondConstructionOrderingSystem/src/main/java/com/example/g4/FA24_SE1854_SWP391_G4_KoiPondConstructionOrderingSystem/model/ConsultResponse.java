package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ConsultResponse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer id;
    private Integer requestDetailId;
    private String description;
    private LocalDateTime consultDate = LocalDateTime.now();
    private LocalDateTime createDate = LocalDateTime.now();
    private Boolean isCustomerConfirm = false;
    Boolean isDeleted = false;
}

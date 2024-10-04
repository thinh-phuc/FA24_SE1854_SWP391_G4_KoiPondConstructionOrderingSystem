package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DesignProfileRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(name = "design_profile_id")
    private Integer designProfileId;

//    @Column(name = "consult_id")
//    private int consultId;
//
//    @Column(name = "quotation_id")
//    private int quotationId;



//    @Column(name = "designer_id")
//    private int designerId;
//
//    @Column(name = "constructor_id")
//    private int constructorId;

    @Column(name = "address", columnDefinition = "NVARCHAR(150)")
    private String address = "none";

    @Column(name = "construction_status", columnDefinition = "NVARCHAR(100)")
    private String contructionStatus = "none";



    @Column(name = "description", columnDefinition = "NVARCHAR(500)")
    private String description = "none";



    @Column(name = "is_active", columnDefinition = "BIT DEFAULT 1")
    private Boolean isActive = true;

    @Column(name = "create_date", columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createDate = LocalDateTime.now();

    @Column(name = "create_by", columnDefinition = "NVARCHAR(100)")
    private String createBy = "none";

    @Column(name = "update_date", columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime updateDate = LocalDateTime.now();

    @Column(name = "update_by", columnDefinition = "NVARCHAR(100)")
    private String updateBy = "none";
}

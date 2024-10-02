package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class DesignProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "design_profile_id")
    private int designProfileId;

    @Column(name = "consult_id")
    private int consultId;

    @Column(name = "quotation_id")
    private int quotationId;

    @Column(name = "customer_id")
    private int customerId;

    @Column(name = "designer_id")
    private int designerId;

    @Column(name = "constructor_id")
    private int constructorId;

    @Column(name = "description", columnDefinition = "NVARCHAR(150)")
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

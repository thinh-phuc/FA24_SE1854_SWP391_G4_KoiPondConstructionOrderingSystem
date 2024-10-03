package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class AcceptanceDocument {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "acceptance_document_id")
    private Integer acceptanceDocumentId;

    @Column(name = "is_confirm", columnDefinition = "BIT DEFAULT 0")
    private boolean isConfirm = false;

    @Column(name = "confirm_date")
    private LocalDateTime confirmDate;

    @Column(name = "description", columnDefinition = "NVARCHAR(500)")
    private String description = "none";

    @Column(name = "confirm_customer_name", nullable = false, columnDefinition = "NVARCHAR(100)")
    private String confirmCustomerName = "none";

    @Column(name = "confirm_constructor_name", nullable = false, columnDefinition = "NVARCHAR(100)")
    private String confirmConstructorName = "none";

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

    @ManyToOne
    @JoinColumn(name = "design_profile_id")
    DesignProfile designProfile;
}

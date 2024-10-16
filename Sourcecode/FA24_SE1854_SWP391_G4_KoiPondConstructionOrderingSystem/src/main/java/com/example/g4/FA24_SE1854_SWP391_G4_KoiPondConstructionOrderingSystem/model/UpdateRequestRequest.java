package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.model;

import jakarta.persistence.Column;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UpdateRequestRequest {

    @Column(name = "status", length = 20)
    String status;

    @Column(name = "description", columnDefinition = "NVARCHAR(500)")
    String description;

    @Column(name = "address", columnDefinition = "NVARCHAR(255)")
    String address;

    @Column(name = "note", columnDefinition = "NVARCHAR(255)")
    String note;

    @Column(name = "update_date", columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime updateDate;

    @Column(name = "update_by", columnDefinition = "NVARCHAR(40)")
    private String updateBy = "none";
}

package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RequestRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(name = "request_id")
    Integer requestId;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(name = "customer_id", nullable = false)
    Integer customerId;

    @Column(name = "description", columnDefinition = "NVARCHAR(500)")
    String description;

    @Column(name = "address", columnDefinition = "NVARCHAR(255)")
    String address;

    @Column(name = "note", columnDefinition = "NVARCHAR(255)")
    String note;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(name = "create_date", columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createDate = LocalDateTime.now();

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(name = "create_by", columnDefinition = "NVARCHAR(40)")
    private String createBy;

}

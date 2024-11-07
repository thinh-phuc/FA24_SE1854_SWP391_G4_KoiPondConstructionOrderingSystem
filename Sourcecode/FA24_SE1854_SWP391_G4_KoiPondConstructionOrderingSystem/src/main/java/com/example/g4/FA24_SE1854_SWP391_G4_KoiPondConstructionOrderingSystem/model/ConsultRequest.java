package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ConsultRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(name = "consult_id")
    private Integer id;

    @Column(name = "customer_id")
    private Integer customerId;

//    @Column(name = "consultant_id")
//    private Integer consultantId;

    @Column(name = "description")
    private String description;

    @Column(name = "consult_date")
    private LocalDateTime consultDate;

    @Column(name = "create_date")
    private LocalDateTime createDate = LocalDateTime.now();

    @Column(name = "is_customer_confirm")
    private Boolean isCustomerConfirm = false;

    private Integer requestDetailId;
}

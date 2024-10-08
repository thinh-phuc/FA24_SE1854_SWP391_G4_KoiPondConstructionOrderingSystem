package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(name = "request_id")
    Integer id;

    //@Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    //@NotBlank(message = "Cus_id cannot be blank!")

    @Column(name = "customer_id", nullable = false)
    Integer customerId;

    @Column(name = "status", length = 20)
    String status = "Pending";

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(name = "is_active", columnDefinition = "BIT DEFAULT 1")
    Boolean isActive = true;

    @Column(name = "description", columnDefinition = "NVARCHAR(500)")
    String description = "none";

    @Column(name = "address", columnDefinition = "NVARCHAR(255)")
    String address;

    @Column(name = "note", columnDefinition = "NVARCHAR(255)")
    String note = "none";

    @Column(name = "create_date", columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createDate = LocalDateTime.now();

    @Column(name = "create_by", columnDefinition = "NVARCHAR(40)")
    private String createBy;

    @Column(name = "update_date", columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime updateDate = LocalDateTime.now();

    @Column(name = "update_by", columnDefinition = "NVARCHAR(40)")
    private String updateBy = "none";

    @JsonIgnore
    Boolean isDeleted = false;

    @OneToMany(mappedBy = "request")
    @JsonIgnore
    List<RequestDetail> requestDetailList;
}

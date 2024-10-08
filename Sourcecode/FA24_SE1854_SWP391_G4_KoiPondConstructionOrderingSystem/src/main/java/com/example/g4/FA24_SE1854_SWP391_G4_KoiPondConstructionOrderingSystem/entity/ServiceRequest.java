package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name="service_request")
public class ServiceRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(name = "service_request_id")
    Integer serviceRequestId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "customer_id")
    Customer customer;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "service_category_id")
    ServiceCategory serviceCategory;
    @Column(name = "status", columnDefinition = "NVARCHAR(100)")
    private String status = "none";
    @Column(name = "description", columnDefinition = "NVARCHAR(500)")
    String description = "none";
    @Column(name = "address", columnDefinition = "NVARCHAR(255)")
    String address;

    @Column(name = "note", columnDefinition = "NVARCHAR(255)")
    private String note = "none";

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(name = "is_active", columnDefinition = "BIT DEFAULT 1")
    Boolean isActive = true;

    @Column(name = "create_date", columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createDate = LocalDateTime.now();

    @Column(name = "create_by", columnDefinition = "NVARCHAR(40)")
    private String createBy;

    @Column(name = "update_date", columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime updateDate = LocalDateTime.now();

    @Column(name = "update_by", columnDefinition = "NVARCHAR(40)")
    private String updateBy = "none";


}

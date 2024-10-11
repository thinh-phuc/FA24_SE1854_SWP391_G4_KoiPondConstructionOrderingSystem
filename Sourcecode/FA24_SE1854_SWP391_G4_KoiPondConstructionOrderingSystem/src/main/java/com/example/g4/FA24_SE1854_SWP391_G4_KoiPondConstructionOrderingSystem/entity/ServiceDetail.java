package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="service_detail")
public class ServiceDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(name = "service_detail_id")
    Integer serviceDetailId;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "customer_id")
    Customer customer;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "service_quotation_id")
    ServiceQuotation serviceQuotation;
    @Column(name = "description", columnDefinition = "NVARCHAR(500)")
    String description = "none";
    @Column(name = "address", columnDefinition = "NVARCHAR(255)")
    String address;
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

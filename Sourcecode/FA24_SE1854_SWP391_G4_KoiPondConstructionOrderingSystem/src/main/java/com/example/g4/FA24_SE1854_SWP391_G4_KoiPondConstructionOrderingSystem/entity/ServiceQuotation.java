package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="service_quotation")
public class ServiceQuotation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)

    @Column(name = "service_quotation_id")
    Integer serviceQuotationId;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    Customer customer;

    @OneToOne()
    @JoinColumn(name = "service_request_id")
   private  ServiceRequest serviceRequest;

    @Column(name = "description", columnDefinition = "NVARCHAR(500)")
    private String description = "none";


    @Column(name = "cost", nullable = false, columnDefinition = "FLOAT")
    @Min(value = 1, message = "cost must be more than 0")
    private float cost = 0.0f;

    @Column(name = "VAT", nullable = false, columnDefinition = "FLOAT")
    @Min(value = 1, message = "VAT must be more than 0")
    private float VAT = 10;

    @Column(name = "total_cost", nullable = false, columnDefinition = "FLOAT")
    @Min(value = 1, message = "TotalCost must be more than 0")
    private float totalCost = 0.0f;
    @Column(name = "is_confirm", columnDefinition = "BIT DEFAULT 0")
    private boolean isConfirm = false;


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

    @PrePersist
    public void prePersist() {
        if (this.serviceRequest != null) {
            this.serviceRequest.setStatus("PENDING");
        }
    }
}

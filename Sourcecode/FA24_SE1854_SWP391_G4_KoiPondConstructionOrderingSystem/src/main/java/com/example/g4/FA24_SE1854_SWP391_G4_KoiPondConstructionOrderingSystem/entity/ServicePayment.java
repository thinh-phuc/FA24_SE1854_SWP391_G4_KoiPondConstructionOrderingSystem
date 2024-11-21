package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "service_payment")
public class ServicePayment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "service_payment_id")
    private Integer servicePaymentID;

//    @Column(name = "service_quotation_id")
//    private Integer serviceQuotationID;

    @Column(name = "transaction_id")
    private String transactionID;

    @ManyToOne
    @JoinColumn(name = "service_quotation_id")
    ServiceQuotation serviceQuotation;

    @Column(name = "payment_method")
    private String paymentMethod;

//    @Column(name = "maintenance_staff_id")
//    private Integer maintenanceStaffID;

    @ManyToOne
    @JoinColumn(name = "maintenance_staff")
    Customer maintenanceStaff;


//    @Column(name = "service_progress_id")
//    private Integer serviceProgressID;

//    @ManyToOne
//    @JoinColumn(name = "service_progress_id")
//    ServiceProgress serviceProgress;

    @Column(name = "status")
    private String status = "Pending";

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

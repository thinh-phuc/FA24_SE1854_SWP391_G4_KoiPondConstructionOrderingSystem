package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ServiceFeedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "service_feedback_id")
    private Integer serviceFeedbackId;

//    @Column(name = "service_detail_id")
//    private int serviceDetailId;

    @ManyToOne
    @JoinColumn(name = "service_detail_id")
    ServiceDetail serviceDetail;

//    @Column(name = "customer_id")
//    private int customerId;

    @ManyToOne
    @JoinColumn(name = "customer")
    Customer customer;

    @Column(name = "feedback")
    private String feedback;

    @Column(name = "rating")
    private Integer rating;

    @Column(name = "note")
    private String note;

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

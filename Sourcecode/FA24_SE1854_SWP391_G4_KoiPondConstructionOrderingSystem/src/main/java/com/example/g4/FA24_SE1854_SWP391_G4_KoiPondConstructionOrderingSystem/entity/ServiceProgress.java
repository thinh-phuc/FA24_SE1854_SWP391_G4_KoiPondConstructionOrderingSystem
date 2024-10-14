package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "service_progress")
public class ServiceProgress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "service_progress_id")
    private Integer serviceProgressID;

//    @Column(name = "service_detail_id")
//    private Integer serviceDetailID;


    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "service_detail_id")
    ServiceDetail serviceDetail;

    @Column(name = "start_date", columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime startDate = LocalDateTime.now();

    @Column(name = "end_date")
    private LocalDateTime endDate;

    @Column(name = "step")
    private String step;

    @Column(name = "is_comfirmed", columnDefinition = "BIT DEFAULT 0")
    private Boolean isComfirmed = false;

    @Column(name = "is_paid", columnDefinition = "BIT DEFAULT 0")
    private Boolean isPaid = false;

    @Column(name = "description")
    private String description;

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

    @JsonIgnore
    @OneToMany(mappedBy = "serviceProgress")
    List<ServicePayment> servicePayments;
}

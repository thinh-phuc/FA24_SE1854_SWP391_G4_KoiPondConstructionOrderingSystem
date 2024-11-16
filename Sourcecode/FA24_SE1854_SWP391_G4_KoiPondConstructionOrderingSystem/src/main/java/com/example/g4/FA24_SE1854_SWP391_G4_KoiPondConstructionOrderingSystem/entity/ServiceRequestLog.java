package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author ServiceRequestLog
 */
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class ServiceRequestLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "service_request_log_id")
    private Integer serviceRequestLogId;

    @Column(name = "status", columnDefinition = "NVARCHAR(75)")
    String status;

    @Column(name = "description", columnDefinition = "NVARCHAR(500)")
    private String description = "none";

    @Column(name = "update_by", columnDefinition = "NVARCHAR(100)")
    private String updateBy = "none";

    @Column(name = "create_date", columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createDate = LocalDateTime.now();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "service_request_id")
    ServiceRequest serviceRequest;
}

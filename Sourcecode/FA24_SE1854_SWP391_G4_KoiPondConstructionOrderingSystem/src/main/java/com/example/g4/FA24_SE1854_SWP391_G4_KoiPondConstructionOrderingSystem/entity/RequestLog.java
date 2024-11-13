package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class RequestLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "request_log_id")
    private Integer requestLogId;

    @Column(name = "status", columnDefinition = "NVARCHAR(75)")
    String status;

    @Column(name = "description", columnDefinition = "NVARCHAR(500)")
    private String description = "none";

    @Column(name = "update_by", columnDefinition = "NVARCHAR(100)")
    private String updateBy = "none";

    @Column(name = "create_date", columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createDate = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "request_id")
    Request request;
}

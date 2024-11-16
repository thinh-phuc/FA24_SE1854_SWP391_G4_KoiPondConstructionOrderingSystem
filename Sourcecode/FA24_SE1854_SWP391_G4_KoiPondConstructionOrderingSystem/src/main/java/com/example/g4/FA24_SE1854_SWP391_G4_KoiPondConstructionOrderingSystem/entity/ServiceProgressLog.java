package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ServiceProgressLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "service_progress_log_id")
    private Integer serviceProgressLogId;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "service_progress_id")
    private ServiceProgress serviceProgress;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "step")
    private String step;

    @Column(name = "description")
    private String description;

    @Column(name = "is_active", columnDefinition = "BIT DEFAULT 1")
    private Boolean isActive = true;

    @Column(name = "create_date", columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createDate;

    @Column(name = "create_by", columnDefinition = "NVARCHAR(100)")
    private String createBy = "none";

//    @Column(name = "update_date", columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
//    private LocalDateTime updateDate = LocalDateTime.now();
//
//    @Column(name = "update_by", columnDefinition = "NVARCHAR(100)")
//    private String updateBy = "none";
}

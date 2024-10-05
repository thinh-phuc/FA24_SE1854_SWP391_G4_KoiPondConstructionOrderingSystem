package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class PondDesignTemplate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(name = "pond_design_template_id")
    Integer id;

    @Column(name = "construction_type_id", nullable = false)
    Integer constructionTypeId;

    @Column(name = "min_size", nullable = false)
    float minSize;

    @Column(name = "max_size", nullable = false)
    float maxSize;

    @Column(name = "water_volume", nullable = false)
    float waterVolume;

    @Column(name = "min_depth", nullable = false)
    float minDepth;

    @Column(name = "max_depth", nullable = false)
    float maxDepth;

    @Column(name = "shape", length = 20, nullable = false)
    String shape;

    @Column(name = "filtration_system", length = 50, nullable = false)
    String filtrationSystem;

    @Column(name = "ph_level", nullable = false)
    float phLevel;

    @Column(name = "water_temperature", nullable = false)
    float waterTemperature;

    @Column(name = "pond_liner",length = 20, nullable = false)
    String pondLiner;

    @Column(name = "pond_bottom",length = 20, nullable = false)
    String pondBottom;

    @Column(name = "decoration",length = 255, nullable = false)
    String decoration;

    @Column(name = "min_estimated_cost", nullable = false)
    float minEstimatedCost;

    @Column(name = "max_estimated_cost", nullable = false)
    float maxEstimatedCost;

    @Column(name = "image_url",length = 255, nullable = false)
    String imageUrl;

    @Column(name = "description", columnDefinition = "NVARCHAR(500)")
    String description;

    @Column(name = "note", columnDefinition = "NVARCHAR(255)")
    String note;

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

    @JsonIgnore
    Boolean isDeleted = false;

}

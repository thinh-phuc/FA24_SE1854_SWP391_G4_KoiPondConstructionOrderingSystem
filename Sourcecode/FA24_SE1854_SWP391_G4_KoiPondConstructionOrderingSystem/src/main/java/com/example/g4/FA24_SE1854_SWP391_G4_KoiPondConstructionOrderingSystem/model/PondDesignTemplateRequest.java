package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class PondDesignTemplateRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(name = "pond_design_template_id")
    Integer id;

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
}

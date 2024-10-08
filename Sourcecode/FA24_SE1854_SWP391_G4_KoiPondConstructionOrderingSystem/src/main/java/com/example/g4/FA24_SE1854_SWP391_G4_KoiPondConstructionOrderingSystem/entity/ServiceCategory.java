package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name="service_category")
public class ServiceCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(name = "service_category_id")
    Integer serviceCategoryId;

    @Column(name = "type", columnDefinition = "NVARCHAR(255)")
    String type;
    @Column(name = "cost", nullable = false, columnDefinition = "FLOAT")
    @Min(value = 1, message = "cost must be more than 0")
    private float cost = 0.0f;

    @Column(name = "description", columnDefinition = "NVARCHAR(500)")
    String description = "none";

    @Column(name = "note", columnDefinition = "NVARCHAR(255)")
    private String note = "none";



}

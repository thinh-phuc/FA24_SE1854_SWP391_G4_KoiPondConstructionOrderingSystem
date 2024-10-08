package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
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
    @Column(name = "description", columnDefinition = "NVARCHAR(500)")
    String description = "none";

    @Column(name = "note", columnDefinition = "NVARCHAR(255)")
    private String note = "none";


}

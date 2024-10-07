package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServiceDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(name = "service_detail_id")
    Integer serviceDetailId;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "service_quotation_id")
    ServiceQuotation serviceQuotation;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "service_category_id")
    ServiceCategory serviceCategory;
    @Column(name = "description", columnDefinition = "NVARCHAR(500)")
    String description = "none";
    @Column(name = "address", columnDefinition = "NVARCHAR(255)")
    String address;
}

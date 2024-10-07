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
public class ServiceQuotation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(name = "service_quotation_id")
    Integer serviceQuotationId;
    @JsonIgnore
    @ManyToMany
    @JoinColumn(name = "customer_id")
    Customer customer;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "service_category_id")
    ServiceCategory serviceCategory;

}

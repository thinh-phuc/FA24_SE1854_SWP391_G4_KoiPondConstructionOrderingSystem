package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
public class Consult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(name = "consult_id")
    private Integer id;

    @Column(name = "customer_id")
    private Integer customerId;

    @Column(name = "consultant_id")
    private Integer consultantId;

//    @Column(name = "request_detail_id")
//    private Integer requestDetailId;

    @Column(name = "description")
    private String description;

    @Column(name = "consult_date")
    private LocalDateTime consultDate = LocalDateTime.now();

    @Column(name = "create_date")
    private LocalDateTime createDate = LocalDateTime.now();

    @Column(name = "is_customer_confirm")
    private Boolean isCustomerConfirm;

    @JsonIgnore
    Boolean isDeleted = false;

    @OneToMany(mappedBy = "consult")
    @JsonIgnore
    List<Quotation> Quotations;

    @ManyToOne
    @JoinColumn(name = "request_detail_id")
    @JsonIgnore
    RequestDetail requestDetail;

    @ManyToMany(mappedBy = "consults")
    List<Customer> customers;
}

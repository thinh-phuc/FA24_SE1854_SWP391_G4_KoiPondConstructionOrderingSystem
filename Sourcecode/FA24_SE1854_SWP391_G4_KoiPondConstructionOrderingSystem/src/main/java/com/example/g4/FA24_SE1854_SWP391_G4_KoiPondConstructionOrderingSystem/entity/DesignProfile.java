package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class DesignProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(name = "design_profile_id")
    private Integer designProfileId;

//    @Column(name = "consult_id")
//    private int consultId;
//
//    @Column(name = "quotation_id")
//    private int quotationId;

//    @Column(name = "customer_id")
//    private int customerId;

//    @Column(name = "designer_id")
//    private int designerId;
//
//    @Column(name = "constructor_id")
//    private int constructorId;

    @Column(name = "address", columnDefinition = "NVARCHAR(150)")
    private String address = "none";

    @Column(name = "construction_status", columnDefinition = "NVARCHAR(100)")
    private String contructionStatus = "PENDING";


    @Column(name = "description", columnDefinition = "NVARCHAR(500)")
    private String description = "none";


    @Column(name = "is_active", columnDefinition = "BIT DEFAULT 1")
    private Boolean isActive = true;

    @Column(name = "create_date", columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createDate = LocalDateTime.now();

    @Column(name = "create_by", columnDefinition = "NVARCHAR(100)")
    private String createBy = "none";

    @Column(name = "update_date", columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime updateDate = LocalDateTime.now();

    @Column(name = "update_by", columnDefinition = "NVARCHAR(100)")
    private String updateBy = "none";

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    // @NotBlank(message = "isDelete must be  not blank")
    @Column(name = "is_delete", columnDefinition = "BIT DEFAULT 0")
    private Boolean isDelete = false;

    @OneToMany(mappedBy = "designProfile")
    @JsonIgnore
    List<ConstructionHistory> constructionHistories;

    @OneToMany(mappedBy = "designProfile")
    @JsonIgnore
    List<AcceptanceDocument> acceptanceDocuments;

    @OneToMany(mappedBy = "designProfile")
    @JsonIgnore
    List<Design> designs;

//    @JsonIgnore
//    @ManyToOne
//    @JoinColumn(name = "customer_id")
//    Customer customer;

    @ManyToMany
    @JoinTable(name = "customer_design_profile", joinColumns = @JoinColumn(name = "design_profile_id"), inverseJoinColumns = @JoinColumn(name = "customer_id"))
    List<Customer> customers;

    @ManyToOne
    @JoinColumn(name = "quotation_id")
    Quotation quotation;

}

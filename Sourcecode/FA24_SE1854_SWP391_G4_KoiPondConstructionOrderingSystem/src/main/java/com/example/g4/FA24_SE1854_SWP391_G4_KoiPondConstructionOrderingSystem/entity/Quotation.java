package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Quotation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(name = "quotation_id")
    private Integer quotationId;



    @Column(name = "is_confirm", columnDefinition = "BIT DEFAULT 0")
    private Boolean isConfirm = false;



    @Column(name = "description", columnDefinition = "NVARCHAR(500)")
    private String description = "none";

    @Column(name = "main_cost", nullable = false, columnDefinition = "FLOAT")
    @Min(value = 1, message = "MainCost must be more than 0")
    private float mainCost = 0.0f;

    @Column(name = "sub_cost", nullable = false, columnDefinition = "FLOAT")
    @Min(value = 1, message = "SubCost must be more than 0")
    private float subCost = 0.0f;

    @Column(name = "VAT", nullable = false, columnDefinition = "FLOAT")
    @Min(value = 1, message = "VAT must be more than 0")
    private float VAT = 0.0f;

    @Column(name = "total_cost", nullable = false, columnDefinition = "FLOAT")
    @Min(value = 1, message = "TotalCost must be more than 0")
    private float totalCost = 0.0f;

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

    @ManyToOne

    @JoinColumn(name = "customer_id")
    Customer customer;

    @ManyToOne
    @JoinColumn(name = "pond_design_template_id")
    PondDesignTemplate pondDesignTemplate;


    @ManyToOne
    @JoinColumn(name = "consult_id")
    Consult consult;

}

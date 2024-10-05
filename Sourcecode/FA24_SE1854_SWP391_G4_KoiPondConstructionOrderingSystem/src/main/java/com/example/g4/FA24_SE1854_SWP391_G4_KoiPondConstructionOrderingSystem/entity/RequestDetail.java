package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class RequestDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(name = "request_detail_id")
    private Integer id;

    @Column(name = "pond_design_template_id")
    private Integer pondDesignTemplateId;

    @Column(name = "request_id")
    private Integer requestId;

    @Column(name = "note")
    private String note;

    @JsonIgnore
    Boolean isDeleted = false;
}

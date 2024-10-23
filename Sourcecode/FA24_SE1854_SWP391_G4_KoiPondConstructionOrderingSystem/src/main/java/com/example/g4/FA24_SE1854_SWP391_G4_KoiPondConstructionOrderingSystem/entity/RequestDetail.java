package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class RequestDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(name = "request_detail_id")
    private Integer requestDetailId;

//    @Column(name = "pond_design_template_id")
//    private Integer pondDesignTemplateId;

//    @Column(name = "request_id")
//    private Integer requestId;

    @Column(name = "note")
    private String note;

    @JsonIgnore
    Boolean isDeleted = false;

    @ManyToOne
    @JoinColumn(name = "request_id")
    Request request;

    @ManyToOne
    @JoinColumn(name = "pond_design_template_id")
    PondDesignTemplate pondDesignTemplate;

    @OneToMany(mappedBy = "requestDetail")
    @JsonIgnore
    List<Consult> consults;
}

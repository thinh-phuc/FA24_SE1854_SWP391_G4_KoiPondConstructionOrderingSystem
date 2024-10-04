package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Design {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(name = "design_id")
    private Integer designId;

//    @Column(name = "design_profile_id")
//    private int designProfileId;

    @Column(name = "status", columnDefinition = "NVARCHAR(100)")
    private String designStatus = "none";

    @Column(name = "design", columnDefinition = "NVARCHAR(500)")
    private String design = "none";

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(name = "is_delete", columnDefinition = "BIT DEFAULT 0")
   // @NotBlank(message = "isDelete must not be blank")
    private Boolean isDelete = false;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "design_profile_id")
    DesignProfile designProfile;
}

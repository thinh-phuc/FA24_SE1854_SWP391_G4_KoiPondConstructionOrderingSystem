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



    @Column(name = "status", columnDefinition = "NVARCHAR(100)")
    private String designStatus = "none";

    @Column(name = "design", columnDefinition = "NVARCHAR(500)")
    private String design = "none";


     @Column(name = "is_active", columnDefinition = "BIT DEFAULT 1")
     private Boolean isActive = true;

    @Column(name = "description", columnDefinition = "NVARCHAR(500)")
    private String description = "none";


    @Column(name = "create_date", columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createDate = LocalDateTime.now();

    @Column(name = "create_by", columnDefinition = "NVARCHAR(100)")
    private String createBy = "none";

    @Column(name = "update_date", columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime updateDate = LocalDateTime.now();

    @Column(name = "update_by", columnDefinition = "NVARCHAR(100)")
    private String updateBy = "none";

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "design_profile_id")
    DesignProfile designProfile;
}

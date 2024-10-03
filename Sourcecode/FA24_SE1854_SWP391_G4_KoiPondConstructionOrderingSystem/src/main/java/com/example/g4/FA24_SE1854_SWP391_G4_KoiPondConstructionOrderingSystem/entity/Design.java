package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity;

import jakarta.persistence.*;
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
    @Column(name = "design_id")
    private Integer designId;

//    @Column(name = "design_profile_id")
//    private int designProfileId;

    @Column(name = "status", columnDefinition = "NVARCHAR(100)")
    private String designStatus = "none";

    @Column(name = "design", columnDefinition = "NVARCHAR(500)")
    private String design = "none";

    @ManyToOne
    @JoinColumn(name = "design_profile_id")
    DesignProfile designProfile;
}

package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.repository;

import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.DesignProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DesignProfileRepository extends JpaRepository<DesignProfile,Integer> {
DesignProfile findDesignProfileByDesignProfileId(Integer  id);
    List<DesignProfile> findDesignProfilesByIsDeleteFalse();
}

package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.repository;

import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.DesignProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DesignProfileRepository extends JpaRepository<DesignProfile, Integer> {
    DesignProfile findDesignProfileByDesignProfileId(Integer id);

    List<DesignProfile> findDesignProfilesByIsActiveTrue();

    DesignProfile findDesignProfileByDesignProfileIdAndIsActiveTrue(Integer id);


    @Query("SELECT dp FROM DesignProfile dp JOIN dp.customers c WHERE c.customerId = :customerId")
    List<DesignProfile> findDesignProfilesByStaff(@Param("customerId") Integer customerId);
}

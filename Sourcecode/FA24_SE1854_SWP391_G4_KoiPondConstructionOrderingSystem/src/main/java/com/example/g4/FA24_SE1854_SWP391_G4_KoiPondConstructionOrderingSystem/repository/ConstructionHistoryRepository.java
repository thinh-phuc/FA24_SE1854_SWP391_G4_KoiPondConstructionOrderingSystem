package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.repository;

import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.ConstructionHistory;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.DesignProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ConstructionHistoryRepository extends JpaRepository<ConstructionHistory, Integer> {
    ConstructionHistory findConstructionHistoryByConstructionHistoryId(Integer id);
    List<ConstructionHistory> findConstructionHistorysByIsActiveTrue();
    ConstructionHistory findConstructionHistoryByDesignProfile(DesignProfile designProfile);

    @Query("SELECT ch from ConstructionHistory ch JOIN ch.designProfile dp JOIN dp.customers c WHERE c.customerId = :customerId AND ch.isActive=True")
    List<ConstructionHistory> findActiveConstructionHistoriesByStaff(@Param("customerId") Integer customerId);
}

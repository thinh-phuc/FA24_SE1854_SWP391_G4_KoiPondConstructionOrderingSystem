package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.repository;

import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.ConstructionHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConstructionHistoryRepository extends JpaRepository<ConstructionHistory, Integer> {
    ConstructionHistory findConstructionHistoryByConstructionHistoryId(Integer id);
    List<ConstructionHistory> findConstructionHistorysByIsActiveTrue();
}

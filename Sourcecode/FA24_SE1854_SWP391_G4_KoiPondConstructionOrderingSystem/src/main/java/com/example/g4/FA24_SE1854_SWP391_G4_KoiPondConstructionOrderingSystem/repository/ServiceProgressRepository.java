package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.repository;

import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.ServiceProgress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServiceProgressRepository extends JpaRepository<ServiceProgress, Integer> {
    ServiceProgress findServiceProgressByServiceProgressID(Integer serviceProgressID);

    List<ServiceProgress> findServiceProgressesByIsActiveTrue();
}

package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.repository;

import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.RequestLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RequestLogRepository extends JpaRepository<RequestLog, Integer> {
    List<RequestLog> findRequestLogsByRequestId(Integer requestId);
}

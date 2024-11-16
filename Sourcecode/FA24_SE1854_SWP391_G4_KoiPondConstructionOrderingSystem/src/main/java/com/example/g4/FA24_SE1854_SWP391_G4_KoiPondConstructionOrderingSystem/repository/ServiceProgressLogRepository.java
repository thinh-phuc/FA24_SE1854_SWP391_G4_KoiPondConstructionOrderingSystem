package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.repository;

import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.ServiceProgressLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ServiceProgressLogRepository extends JpaRepository<ServiceProgressLog, Integer> {
    @Query("SELECT spl FROM ServiceProgressLog spl " +
            "JOIN spl.serviceProgress sp " +
            "WHERE sp.serviceProgressID = :serviceProgressID " +
            "AND sp.isActive = true " +
            "ORDER BY spl.createDate DESC")
    List<ServiceProgressLog> findServiceProgressLogsByServiceProgressId(@Param("serviceProgressID") Integer maintenanceStaffId);

}

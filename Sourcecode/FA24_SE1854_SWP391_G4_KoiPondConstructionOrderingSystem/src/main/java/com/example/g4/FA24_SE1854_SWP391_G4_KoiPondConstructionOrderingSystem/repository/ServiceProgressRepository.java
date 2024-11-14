package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.repository;

import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.ConstructionHistory;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.ServiceProgress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ServiceProgressRepository extends JpaRepository<ServiceProgress, Integer> {
    ServiceProgress findServiceProgressByServiceProgressID(Integer serviceProgressID);

    @Query("SELECT sp FROM ServiceProgress sp " +
            "JOIN sp.serviceDetail sd " +
//            "JOIN sd.staff s " +
            "JOIN sd.serviceQuotation sq " +
            "WHERE sq.customer.customerId = :customerId " +
            "AND sp.isActive = true " +
            "ORDER BY sp.serviceProgressID DESC")
    List<ServiceProgress> findActiveServiceProgressByCustomerOrderByServiceProgressIDDesc(@Param("customerId") Integer customerId);



    List<ServiceProgress> findServiceProgressesByIsActiveTrueOrderByServiceProgressIDDesc();
}

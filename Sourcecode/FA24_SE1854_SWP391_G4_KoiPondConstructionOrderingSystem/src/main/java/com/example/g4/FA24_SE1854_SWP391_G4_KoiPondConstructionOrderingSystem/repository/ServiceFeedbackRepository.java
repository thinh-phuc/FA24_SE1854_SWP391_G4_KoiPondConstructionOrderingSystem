package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.repository;

import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.ServiceFeedback;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.ServicePayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ServiceFeedbackRepository extends JpaRepository<ServiceFeedback, Integer> {
    ServiceFeedback findServiceFeedbackByServiceFeedbackId(Integer serviceFeedbackId);

    @Query("SELECT sp FROM ServiceFeedback sp " +
            "JOIN sp.serviceRequest sd " +
            "WHERE sd.serviceRequestId = :serviceRequestId " +
            "AND sp.isActive = true ")
    ServiceFeedback findServiceFeedbackByServiceRequestId(@Param("serviceRequestId") Integer serviceRequestId);

    List<ServiceFeedback> findServiceFeedbacksByIsActiveTrueOrderByServiceFeedbackIdDesc();
}

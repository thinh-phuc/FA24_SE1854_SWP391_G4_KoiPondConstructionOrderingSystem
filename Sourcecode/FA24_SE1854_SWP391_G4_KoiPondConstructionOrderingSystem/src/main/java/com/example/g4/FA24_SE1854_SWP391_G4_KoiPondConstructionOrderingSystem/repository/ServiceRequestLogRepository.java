package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.repository;

import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.ServiceRequest;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.ServiceRequestLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
/**
 * @author ServiceRequestLogRepository
 */
public interface ServiceRequestLogRepository extends JpaRepository<ServiceRequestLog,Integer> {
    List<ServiceRequestLog> findServiceRequestLogsByServiceRequestOrderByCreateDateDesc(ServiceRequest serviceRequest);

}

package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.repository;

import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.Customer;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.ServiceCategory;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.ServiceRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServiceRequestRepository extends JpaRepository<ServiceRequest,Integer> {
List<ServiceRequest> findServiceRequestsByIsActiveTrue();
List<ServiceRequest> findServiceRequestsByCustomerAndIsActiveTrueOrderByCreateDateDesc(Customer customerID);
ServiceRequest findServiceRequestByServiceRequestId(Integer Id);
    List<ServiceRequest> findServiceRequestsByIsActiveTrueOrderByCreateDateDesc();
}

package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.repository;

import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.Customer;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.ServiceCategory;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.ServiceRequest;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ServiceRequestRepository extends JpaRepository<ServiceRequest,Integer> {
List<ServiceRequest> findServiceRequestsByIsActiveTrue();

    @Query("SELECT DISTINCT sr FROM ServiceRequest sr LEFT JOIN FETCH sr.serviceQuotation WHERE sr.customer = :customer ORDER BY sr.createDate DESC")
List<ServiceRequest> findServiceRequestsByCustomerOrderByCreateDateDesc(@Param("customer") Customer customer);
ServiceRequest findServiceRequestByServiceRequestId(Integer Id);

    List<ServiceRequest> findServiceRequestsByIsActiveTrueOrderByCreateDateDesc();
    @Query("SELECT DISTINCT sr FROM ServiceRequest sr LEFT JOIN FETCH sr.serviceQuotation  ORDER BY sr.createDate DESC")
    List<ServiceRequest> findServiceRequestsByOrderByCreateDateDesc();

    @Transactional
    @Modifying
    @Query("UPDATE ServiceRequest sr SET sr.isActive = false WHERE sr.serviceRequestId = :id")
    void deactivateServiceRequestById(@Param("id") Integer id);
}

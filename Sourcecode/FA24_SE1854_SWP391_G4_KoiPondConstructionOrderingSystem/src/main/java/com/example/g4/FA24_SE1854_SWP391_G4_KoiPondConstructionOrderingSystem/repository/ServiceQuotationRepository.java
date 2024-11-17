package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.repository;

import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.Customer;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.ServiceCategory;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.ServiceQuotation;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.ServiceRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ServiceQuotationRepository extends JpaRepository<ServiceQuotation,Integer> {
    @Query("SELECT DISTINCT sq FROM ServiceQuotation sq LEFT JOIN FETCH sq.serviceRequest WHERE sq.customer = :customer AND sq.isActive = true ORDER BY sq.createDate DESC")
    List<ServiceQuotation> findServiceQuotationsByCustomerAndIsActiveTrueOrderByCreateDateDesc( @Param("customer") Customer customer);

    ServiceQuotation findServiceQuotationByServiceQuotationId(Integer serviceQuotationID);
    ServiceQuotation findServiceQuotationByServiceRequest(ServiceRequest serviceRequest);

    @Query("SELECT DISTINCT sq FROM ServiceQuotation sq LEFT JOIN FETCH sq.serviceRequest WHERE sq.isActive = true ORDER BY sq.createDate DESC")
    List<ServiceQuotation> findServiceQuotationsByIsActiveTrueOrderByCreateDateDesc();
}

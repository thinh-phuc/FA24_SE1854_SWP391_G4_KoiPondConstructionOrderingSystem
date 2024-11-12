package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.repository;

import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.ServicePayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ServicePaymentRepository extends JpaRepository<ServicePayment, Integer> {
    ServicePayment findServicePaymentByServicePaymentID(Integer servicePaymentID);

    @Query("SELECT sp FROM ServicePayment sp " +
            "JOIN sp.serviceQuotation sd " +
            "WHERE sd.serviceQuotationId = :serviceQuotationId " +
            "AND sp.isActive = true ")
    ServicePayment findServicePaymentByServiceQuotationIdDESC(@Param("serviceQuotationId") Integer serviceQuotationId);



    List<ServicePayment> findServicePaymentsByIsActiveTrueOrderByServicePaymentIDDesc();
}

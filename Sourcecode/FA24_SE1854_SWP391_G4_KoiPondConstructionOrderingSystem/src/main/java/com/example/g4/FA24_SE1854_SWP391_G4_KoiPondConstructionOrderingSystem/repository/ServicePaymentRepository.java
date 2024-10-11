package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.repository;

import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.ServicePayment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServicePaymentRepository extends JpaRepository<ServicePayment, Integer> {
    ServicePayment findServicePaymentByServicePaymentID(Integer servicePaymentID);

    List<ServicePayment> findServicePaymentsByIsActiveTrue();
}

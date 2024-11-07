package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.repository;

import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.Customer;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.ServiceCategory;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.ServiceQuotation;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.ServiceRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServiceQuotationRepository extends JpaRepository<ServiceQuotation,Integer> {

    List<ServiceQuotation> findServiceQuotationsByCustomer(Customer customer);

    ServiceQuotation findServiceQuotationByServiceQuotationId(Integer serviceQuotationID);
    ServiceQuotation findServiceQuotationByServiceRequest(ServiceRequest serviceRequest);
}

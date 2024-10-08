package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.service;

import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.ServiceQuotation;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.ServiceRequest;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.exception.DataNotFoundException;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.repository.ServiceQuotationRepository;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.repository.ServiceRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ServiceQuotationService implements IServiceQuotationService{
    @Autowired
    private ServiceQuotationRepository serviceQuotationRepository;
    private ServiceRequestRepository serviceRequestRepository;

    @Override
    public ServiceQuotation addServiceQuotation(ServiceQuotation serviceQuotation) throws Exception{
        ServiceRequest request = serviceRequestRepository.findById(serviceQuotation.getServiceRequest().getServiceRequestId())
                .orElseThrow(()-> new DataNotFoundException("Cannot find service-request with id= "+ serviceQuotation.getServiceRequest().getServiceRequestId()));

//        ServiceQuotation quotation =ServiceQuotation.builder()
//                .
//                .build();


        return null;
    }

    @Override
    public ServiceQuotation findById(Integer id) {
        return null;
    }

    @Override
    public List<ServiceQuotation> findAll() {
        return List.of();
    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public List<ServiceQuotation> findByCustomerId(Integer customerId) {
        return List.of();
    }

    @Override
    public List<ServiceQuotation> findByServiceCategoryId(Integer serviceCategoryId) {
        return List.of();
    }
}

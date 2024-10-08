package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.service;

import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.ServiceQuotation;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.ServiceRequest;

import java.util.List;

public class ServiceQuotationService implements IServiceQuotationService{


    @Override
    public ServiceQuotation addServiceQuotation(ServiceQuotation serviceQuotation) {
       // ServiceRequest request =

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

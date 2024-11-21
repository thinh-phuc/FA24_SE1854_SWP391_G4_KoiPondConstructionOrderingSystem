package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.service;

import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.ServiceQuotation;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.model.ServiceQuotationRequest;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.model.ServiceQuotationUpdateRequest;

import java.util.List;

public interface IServiceQuotationService {
    // add a new ServiceQuotation
    ServiceQuotation addServiceQuotation(ServiceQuotationRequest serviceQuotation) throws Exception;
    ServiceQuotation updateServiceQuotation(Integer id , ServiceQuotationUpdateRequest serviceQuotationRequest)  throws Exception;
    // Find a ServiceQuotation by its ID
    ServiceQuotation findById(Integer id) throws Exception;

    // Retrieve all ServiceQuotations
    List<ServiceQuotation> findAll();

    // Delete a ServiceQuotation by its ID
    void deleteServiceQuotationById(Integer id) throws  Exception;

    // Additional business logic methods, e.g., finding quotations by customer or service category
    List<ServiceQuotation> findByCustomerId(Integer customerId);

    List<ServiceQuotation> findByServiceCategoryId(Integer serviceCategoryId);
    ServiceQuotation findByRequestID(Integer requestId) throws  Exception;
}

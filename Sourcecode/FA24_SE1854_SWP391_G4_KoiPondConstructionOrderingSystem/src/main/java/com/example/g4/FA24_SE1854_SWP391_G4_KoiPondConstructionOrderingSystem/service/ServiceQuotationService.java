package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.service;

import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.ServiceCategory;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.ServiceQuotation;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.ServiceRequest;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.exception.DataNotFoundException;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.model.ServiceQuotationRequest;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.repository.ServiceCategoryRepository;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.repository.ServiceQuotationRepository;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.repository.ServiceRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ServiceQuotationService implements IServiceQuotationService{
    @Autowired
    private ServiceQuotationRepository serviceQuotationRepository;
    @Autowired
    private ServiceRequestRepository serviceRequestRepository;
    @Autowired
    private ServiceCategoryRepository serviceCategoryRepository;
    @Override
    public ServiceQuotation addServiceQuotation(ServiceQuotationRequest serviceQuotation) throws Exception{
        ServiceRequest request = serviceRequestRepository.findById(serviceQuotation.getServiceRequestId())
                .orElseThrow(()-> new DataNotFoundException("Cannot find service-request with id= "+ serviceQuotation.getServiceRequestId()));

        ServiceQuotation quotation = new ServiceQuotation();
    quotation.setDescription(serviceQuotation.getDescription());
    quotation.setVAT(serviceQuotation.getVAT());
        ServiceCategory serviceCategory = serviceCategoryRepository.findById(request.getServiceCategory().getServiceCategoryId())
                .orElseThrow(()-> new DataNotFoundException("Cannot find service-category with id = "+ request.getServiceCategory().getServiceCategoryId()));
   quotation.setCost(serviceCategory.getCost());
   quotation.setTotalCost(quotation.getCost() * quotation.getVAT());

    ServiceQuotation obj = serviceQuotationRepository.save(quotation);
    return obj;
    }

    @Override
    public ServiceQuotation findById(Integer id) throws Exception{
        return serviceQuotationRepository.findById(id)
                .orElseThrow(()-> new DataNotFoundException("Cannot find service-category with id = " + id));
    }

    @Override
    public List<ServiceQuotation> findAll() {
        return serviceQuotationRepository.findAll();
    }

    @Override
    public void deleteById(Integer id) {
    serviceQuotationRepository.deleteById(id);
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

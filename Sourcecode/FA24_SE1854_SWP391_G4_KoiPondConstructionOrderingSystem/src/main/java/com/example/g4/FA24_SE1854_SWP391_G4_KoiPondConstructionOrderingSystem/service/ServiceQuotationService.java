package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.service;

import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.Customer;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.ServiceCategory;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.ServiceQuotation;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.ServiceRequest;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.exception.DataNotFoundException;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.model.ServiceQuotationRequest;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.repository.CustomerRepository;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.repository.ServiceCategoryRepository;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.repository.ServiceQuotationRepository;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.repository.ServiceRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
public class ServiceQuotationService implements IServiceQuotationService{
    @Autowired
    private ServiceQuotationRepository serviceQuotationRepository;
    @Autowired
    private ServiceRequestRepository serviceRequestRepository;
    @Autowired
    private ServiceCategoryRepository serviceCategoryRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    AuthenticationService authenticationService;
    @Override
    public ServiceQuotation addServiceQuotation(ServiceQuotationRequest serviceQuotation) throws Exception{
        ServiceRequest request = serviceRequestRepository.findById(serviceQuotation.getServiceRequestId())
                .orElseThrow(()-> new DataNotFoundException("Cannot find service-request with id= "+ serviceQuotation.getServiceRequestId()));
        Customer customer = authenticationService.getCurrentUser();
        ServiceQuotation quotation = new ServiceQuotation();
    quotation.setDescription(serviceQuotation.getDescription());
    quotation.setVAT(serviceQuotation.getVAT());
        ServiceCategory serviceCategory = serviceCategoryRepository.findById(request.getServiceCategory().getServiceCategoryId())
                .orElseThrow(()-> new DataNotFoundException("Cannot find service-category with id = "+ request.getServiceCategory().getServiceCategoryId()));
   quotation.setCost(serviceCategory.getCost());
   quotation.setTotalCost(quotation.getCost()+quotation.getCost() * quotation.getVAT()/100);
    quotation.setCreateDate(LocalDateTime.now());
    quotation.setCreateBy(customer.getName());
    quotation.setIsActive(true);
    quotation.setServiceRequest(request);
    quotation.setCustomer(request.getCustomer());
    ServiceQuotation obj = serviceQuotationRepository.save(quotation);
    return obj;
    }

    @Override
    public ServiceQuotation updateServiceQuotation(Integer Id,ServiceQuotationRequest serviceQuotationRequest) throws Exception {
        ServiceQuotation serviceQuotation = serviceQuotationRepository.findById(Id)
                .orElseThrow(()-> new DataNotFoundException("Cannot find service-quotation with id"+ Id));
        ServiceRequest request = serviceRequestRepository.findById(serviceQuotationRequest.getServiceRequestId())
                .orElseThrow(()-> new DataNotFoundException("Cannot find service-request with id= "+ serviceQuotationRequest.getServiceRequestId()));
        ServiceCategory serviceCategory = serviceCategoryRepository.findById(request.getServiceCategory().getServiceCategoryId())
                .orElseThrow(()-> new DataNotFoundException("Cannot find service-category with id = "+ request.getServiceCategory().getServiceCategoryId()));
        Customer customer = authenticationService.getCurrentUser();
        serviceQuotation.setDescription(serviceQuotationRequest.getDescription());
        serviceQuotation.setVAT(serviceQuotationRequest.getVAT());
        serviceQuotation.setCost(serviceCategory.getCost());
        serviceQuotation.setUpdateBy(customer.getRole() + " " + customer.getName());
        serviceQuotation.setUpdateDate(LocalDateTime.now());
        serviceQuotation.setConfirm(false);
        serviceQuotation.setTotalCost(serviceQuotation.getCost()+serviceQuotation.getCost() * serviceQuotation.getVAT()/100);
        return serviceQuotationRepository.save(serviceQuotation);
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
    public void deleteServiceQuotationById(Integer id) {
    serviceQuotationRepository.deleteById(id);
    }

    @Override
    public List<ServiceQuotation> findByCustomerId(Integer customerId) {
        Customer customer = customerRepository.findCustomerByCustomerId(customerId);
        return serviceQuotationRepository.findServiceQuotationsByCustomer(customer);
    }
    @Override
    public List<ServiceQuotation> findByServiceCategoryId(Integer serviceCategoryId) {
        return List.of();
    }

    @Override
    public ServiceQuotation findByRequestID(Integer requestId) throws Exception {
        ServiceRequest request = serviceRequestRepository.findServiceRequestByServiceRequestId(requestId);
           if (request == null)
           {

               throw new DataNotFoundException("ServiceRequest not found!");
           }

        return serviceQuotationRepository.findServiceQuotationByServiceRequest(request);
    }


    public ServiceQuotation updateServiceQuotationIsConfirm(Integer id) throws Exception {
        // Find the existing service quotation by ID
        ServiceQuotation existingQuotation = serviceQuotationRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Cannot find service-quotation with id= " + id));

        // Update only the isConfirm field
        existingQuotation.setConfirm(!existingQuotation.isConfirm());
        existingQuotation.setUpdateDate(LocalDateTime.now());

        // Save and return the updated service quotation
        return serviceQuotationRepository.save(existingQuotation);
    }
}

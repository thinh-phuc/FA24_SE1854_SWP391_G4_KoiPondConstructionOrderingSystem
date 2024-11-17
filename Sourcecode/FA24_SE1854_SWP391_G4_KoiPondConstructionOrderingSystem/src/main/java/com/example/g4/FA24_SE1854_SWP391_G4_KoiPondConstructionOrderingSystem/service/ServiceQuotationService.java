package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.service;

import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.Customer;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.ServiceCategory;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.ServiceQuotation;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.ServiceRequest;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.exception.DataNotFoundException;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.model.ServiceQuotationRequest;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.model.ServiceQuotationUpdateRequest;
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
    @Autowired
    private ServiceRequestLogService serviceRequestLogService;
    @Override
    public ServiceQuotation addServiceQuotation(ServiceQuotationRequest serviceQuotation) throws Exception{
        ServiceRequest request = serviceRequestRepository.findById(serviceQuotation.getServiceRequestId())
                .orElseThrow(()-> new DataNotFoundException("Cannot find service-request with id= "+ serviceQuotation.getServiceRequestId()));

        Customer customer = authenticationService.getCurrentUser();
        //System.out.println(customer);
        ServiceQuotation quotation = new ServiceQuotation();
    quotation.setDescription(serviceQuotation.getDescription());
   // quotation.setVAT(serviceQuotation.getVAT());
        ServiceCategory serviceCategory = serviceCategoryRepository.findById(request.getServiceCategory().getServiceCategoryId())
                .orElseThrow(()-> new DataNotFoundException("Cannot find service-category with id = "+ request.getServiceCategory().getServiceCategoryId()));
   quotation.setCost(serviceQuotation.getCost());
   quotation.setTotalCost(serviceQuotation.getCost() * 110/100);
    quotation.setCreateDate(LocalDateTime.now());
    quotation.setCreateBy(customer.getName());
    quotation.setVAT(10);
    quotation.setIsActive(true);
    quotation.setServiceRequest(request);
    quotation.setCustomer(request.getCustomer());
      // request.setServiceQuotation(quotation);
        request.setStatus("QUOTING");
    serviceRequestRepository.save(request);
    serviceRequestLogService.createServiceRequestLog(request,"Please check your profile to view quotation detail!","Quotation made");
    ServiceQuotation obj = serviceQuotationRepository.save(quotation);
    return obj;
    }

    @Override
    public ServiceQuotation updateServiceQuotation(Integer Id, ServiceQuotationUpdateRequest serviceQuotationRequest) throws Exception {
        ServiceQuotation serviceQuotation = serviceQuotationRepository.findById(Id)
                .orElseThrow(()-> new DataNotFoundException("Cannot find service-quotation with id"+ Id));


        Customer customer = authenticationService.getCurrentUser();
        serviceQuotation.setDescription(serviceQuotationRequest.getDescription());
        serviceQuotation.setUpdateBy(customer.getRole() + " " + customer.getName());
        serviceQuotation.setUpdateDate(LocalDateTime.now());
       // serviceQuotation.setConfirm(false);
        serviceRequestLogService.createServiceRequestLog(serviceQuotation.getServiceRequest(),serviceQuotationRequest.getDescription(),"Quotation updated!");
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
        return serviceQuotationRepository.findServiceQuotationsByIsActiveTrueOrderByCreateDateDesc();
    }

    @Override
    public void deleteServiceQuotationById(Integer id) {
    serviceQuotationRepository.deleteById(id);
    }

    @Override
    public List<ServiceQuotation> findByCustomerId(Integer customerId) {
        Customer customer = customerRepository.findCustomerByCustomerId(customerId);
        return serviceQuotationRepository.findServiceQuotationsByCustomerAndIsActiveTrueOrderByCreateDateDesc(customer);
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

       ServiceRequest request = serviceRequestRepository.findById(existingQuotation.getServiceRequest().getServiceRequestId())
               .orElseThrow(()-> new DataNotFoundException("Cannot find service-request "));


        serviceRequestLogService.createServiceRequestLog(request,"Quotation has been confirmed,please wait for staff go to your pond!","Quotation Confirmed!");
        // Update only the isConfirm field
        existingQuotation.setConfirm(!existingQuotation.isConfirm());
        existingQuotation.setUpdateDate(LocalDateTime.now());

        // Save and return the updated service quotation
        return serviceQuotationRepository.save(existingQuotation);
    }
}

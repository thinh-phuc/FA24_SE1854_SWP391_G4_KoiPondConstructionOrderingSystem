package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.service;

import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.Customer;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.ServiceCategory;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.ServiceRequest;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.exception.DataNotFoundException;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.model.AddServiceRequest;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.repository.CustomerRepository;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.repository.ServiceCategoryRepository;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.repository.ServiceRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
public class ServiceRequestService implements  IServiceRequestService{
   @Autowired
   private ServiceRequestRepository serviceRequestRepository;
   @Autowired
   private ServiceCategoryRepository serviceCategoryRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    AuthenticationService authenticationService;
    @Override
    public ServiceRequest addServiceRequest(AddServiceRequest serviceRequest) throws Exception {
        ServiceCategory existingCategory= serviceCategoryRepository
                .findById(serviceRequest.getCategoryID())
                .orElseThrow(()-> new DataNotFoundException("Cannot find category with id" +serviceRequest.getCategoryID()));
            Customer customer = authenticationService.getCurrentUser();
        ServiceRequest newRequest = new ServiceRequest();
        newRequest.setDescription(serviceRequest.getDescription());
        newRequest.setAddress(serviceRequest.getAddress());
        newRequest.setNote(serviceRequest.getNote());
        newRequest.setIsActive(true);
        newRequest.setCreateBy(customer.getName());
        newRequest.setCreateDate(LocalDateTime.now());
        newRequest.setUpdateDate(LocalDateTime.now());
        newRequest.setServiceCategory(existingCategory);
        newRequest.setCustomer(customer);
        newRequest.setStatus("PROCESSING");
        return serviceRequestRepository.save(newRequest);
    }

    @Override
    public ServiceRequest getServiceRequestById(Integer id)  throws Exception{
        return serviceRequestRepository.findById(id)
                .orElseThrow(()-> new DataNotFoundException("Cannot find service-request with id= " +id ));
    }

    @Override
    public ServiceRequest updateServiceRequest(Integer id,ServiceRequest serviceRequest) throws Exception {
           ServiceRequest existingRequest = serviceRequestRepository.findServiceRequestByServiceRequestId(id);
           if(existingRequest == null)
           {
               throw new DataNotFoundException("Cannot find service-request with id = "+id );
           }
           if(!existingRequest.getStatus().equals("PROCESSING"))
           {
               throw new DataNotFoundException("Cann't update request");
           }
        Customer customer = authenticationService.getCurrentUser();
               ServiceCategory existingCategory= serviceCategoryRepository
                       .findById(serviceRequest.getServiceCategory().getServiceCategoryId())
                       .orElseThrow(()-> new DataNotFoundException("Cannot find category with id" +serviceRequest.getServiceCategory().getServiceCategoryId() ));
               existingRequest.setDescription(serviceRequest.getDescription());
               existingRequest.setAddress(serviceRequest.getAddress());
               existingRequest.setNote(serviceRequest.getNote());
               existingRequest.setUpdateDate(LocalDateTime.now());
               existingRequest.setUpdateBy(customer.getName());
               existingRequest.setServiceCategory(existingCategory);
               return serviceRequestRepository.save(existingRequest);

        //return null;
    }

    @Override
    public void deleteServiceRequestById(Integer id) {
            serviceRequestRepository.deleteById(id);
    }

    @Override
    public List<ServiceRequest> getAllServiceRequests() {
        return serviceRequestRepository.findServiceRequestsByIsActiveTrueOrderByCreateDateDesc();
    }

    @Override
    public List<ServiceRequest> findServiceRequestsByCustomerId(Integer customerId) {
      Customer customer = customerRepository.findCustomerByCustomerId(customerId);
        return  serviceRequestRepository.findServiceRequestsByCustomerAndIsActiveTrueOrderByCreateDateDesc(customer);
    }

    @Override
    public List<ServiceRequest> findServiceRequestsByServiceCategoryId(Integer serviceCategoryId) {
        return List.of();
    }

    public ServiceRequest updateServiceRequestStatus(Integer id, String newStatus) throws Exception {
        ServiceRequest existingRequest = serviceRequestRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Cannot find service-request with id= " + id));

        existingRequest.setStatus(newStatus);
        existingRequest.setUpdateDate(LocalDateTime.now());


        return serviceRequestRepository.save(existingRequest);
    }


    @Override
    public List<ServiceRequest> getActiveServiceRequests() {
        return serviceRequestRepository.findServiceRequestsByIsActiveTrue();
    }
}

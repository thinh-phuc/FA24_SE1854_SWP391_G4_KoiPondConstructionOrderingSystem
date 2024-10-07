package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.service;

import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.ServiceCategory;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.ServiceRequest;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.exception.DataNotFoundException;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.repository.ServiceCategoryRepository;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.repository.ServiceRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ServiceRequestService implements  IServiceRequestService{
   @Autowired
   private ServiceRequestRepository serviceRequestRepository;
   @Autowired
   private ServiceCategoryRepository serviceCategoryRepository;

    @Override
    public ServiceRequest addServiceRequest(ServiceRequest serviceRequest) throws Exception {
        ServiceCategory existingCategory= serviceCategoryRepository
                .findById(serviceRequest.getServiceCategory().getServiceCategoryId())
                .orElseThrow(()-> new DataNotFoundException("Cannot find category with id" +serviceRequest.getServiceCategory().getServiceCategoryId() ));

        ServiceRequest newRequest = ServiceRequest.builder()
                .status(serviceRequest.getStatus())
                .description(serviceRequest.getDescription())
                .address(serviceRequest.getAddress())
                .note(serviceRequest.getNote())
                .build();
        return serviceRequestRepository.save(newRequest);
    }

    @Override
    public ServiceRequest getServiceRequestById(Integer id)  throws Exception{
        return serviceRequestRepository.findById(id)
                .orElseThrow(()-> new DataNotFoundException("Cannot find service-request with id= " +id ));
    }

    @Override
    public ServiceRequest updateServiceRequest(Integer id,ServiceRequest serviceRequest) throws Exception {
           ServiceRequest existingRequest = getServiceRequestById(id);
           if(existingRequest != null)
           {
               ServiceCategory existingCategory= serviceCategoryRepository
                       .findById(serviceRequest.getServiceCategory().getServiceCategoryId())
                       .orElseThrow(()-> new DataNotFoundException("Cannot find category with id" +serviceRequest.getServiceCategory().getServiceCategoryId() ));

               existingRequest.setStatus(serviceRequest.getStatus());
               existingRequest.setDescription(serviceRequest.getDescription());
               existingRequest.setAddress(serviceRequest.getAddress());
               existingRequest.setNote(serviceRequest.getNote());
               return serviceRequestRepository.save(existingRequest);
           }

        return null;
    }

    @Override
    public void deleteServiceRequestById(Integer id) {
            serviceRequestRepository.deleteById(id);
    }

    @Override
    public List<ServiceRequest> getAllServiceRequests() {
        return serviceRequestRepository.findAll();
    }

    @Override
    public List<ServiceRequest> findServiceRequestsByCustomerId(Integer customerId) {
        return List.of();
    }

    @Override
    public List<ServiceRequest> findServiceRequestsByServiceCategoryId(Integer serviceCategoryId) {
        return List.of();
    }

    @Override
    public List<ServiceRequest> getActiveServiceRequests() {
        return List.of();
    }
}

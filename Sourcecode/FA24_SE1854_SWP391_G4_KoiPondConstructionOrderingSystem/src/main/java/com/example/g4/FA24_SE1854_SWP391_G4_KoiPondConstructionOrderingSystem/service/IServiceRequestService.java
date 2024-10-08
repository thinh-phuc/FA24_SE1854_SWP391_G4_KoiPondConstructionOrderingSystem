package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.service;

import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.ServiceRequest;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.model.AddServiceRequest;

import java.util.List;

public interface IServiceRequestService {
    // Method to add a new ServiceRequest
    ServiceRequest addServiceRequest(AddServiceRequest serviceRequest) throws Exception;

    // Method to retrieve a ServiceRequest by ID
    ServiceRequest getServiceRequestById(Integer id) throws Exception;

    // Method to update an existing ServiceRequest
    ServiceRequest updateServiceRequest(Integer id,ServiceRequest serviceRequest) throws Exception;

    // Method to delete a ServiceRequest by ID
    void deleteServiceRequestById(Integer id);

    // Method to list all ServiceRequests
    List<ServiceRequest> getAllServiceRequests();

    // Method to find ServiceRequests by Customer ID
    List<ServiceRequest> findServiceRequestsByCustomerId(Integer customerId);

    // Method to find ServiceRequests by ServiceCategory ID
    List<ServiceRequest> findServiceRequestsByServiceCategoryId(Integer serviceCategoryId);

    // Method to get active ServiceRequests
    List<ServiceRequest> getActiveServiceRequests();
}

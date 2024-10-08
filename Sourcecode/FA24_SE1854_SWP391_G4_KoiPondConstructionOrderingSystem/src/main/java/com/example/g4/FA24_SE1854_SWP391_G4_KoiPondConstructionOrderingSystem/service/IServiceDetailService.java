package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.service;

import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.ServiceDetail;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.model.ServiceDetailRequest;

import java.util.List;

public interface IServiceDetailService {
    // Create a new ServiceDetail
    ServiceDetail createServiceDetail(ServiceDetailRequest serviceDetail) throws Exception;

    // Get a ServiceDetail by its ID
    ServiceDetail getServiceDetailById(Integer id)throws Exception;

    // Get all ServiceDetails
    List<ServiceDetail> getAllServiceDetails();

    // Update an existing ServiceDetail
    ServiceDetail updateServiceDetail(ServiceDetail serviceDetail);

    // Delete a ServiceDetail by its ID
    void deleteServiceDetailById(Integer id);
}

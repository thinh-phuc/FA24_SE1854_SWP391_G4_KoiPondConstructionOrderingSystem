package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.service;

import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.ServiceCategory;

import java.util.List;

public interface IServiceCategoryService {

    ServiceCategory addServiceCategory(ServiceCategory serviceCategory);

    // Method to retrieve a ServiceCategory by ID
    ServiceCategory getServiceCategoryById(Integer id);

    // Method to update an existing ServiceCategory
    ServiceCategory updateServiceCategory(Integer id, ServiceCategory serviceCategory);

    // Method to delete a ServiceCategory by ID
    void deleteServiceCategoryById(Integer id);

    // Method to list all ServiceCategories
    List<ServiceCategory> getAllServiceCategories();
}

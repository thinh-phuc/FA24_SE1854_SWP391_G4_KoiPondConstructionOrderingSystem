package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.service;

import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.ServiceCategory;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.repository.ServiceCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ServiceCategoryService implements IServiceCategoryService{
    @Autowired
    private ServiceCategoryRepository serviceCategoryRepository;
    @Override
    public ServiceCategory addServiceCategory(ServiceCategory serviceCategory) {
    ServiceCategory serviceCate = ServiceCategory
            .builder()
            .type(serviceCategory.getType())
            .cost(serviceCategory.getCost())
            .description(serviceCategory.getDescription())
            .note(serviceCategory.getNote())
            .imageUrl(serviceCategory.getImageUrl())
            .build();
        return serviceCategoryRepository.save(serviceCate);
    }

    @Override
    public ServiceCategory getServiceCategoryById(Integer id) {
        return serviceCategoryRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Service-Category not found!"));
    }

    @Override
    public ServiceCategory updateServiceCategory(Integer id, ServiceCategory serviceCategory) {
        ServiceCategory existingCategory = getServiceCategoryById(id);
        existingCategory.setType(serviceCategory.getType());
        existingCategory.setCost(serviceCategory.getCost());
        existingCategory.setDescription(serviceCategory.getDescription());
        existingCategory.setNote(serviceCategory.getNote());
        existingCategory.setImageUrl(serviceCategory.getImageUrl());
        serviceCategoryRepository.save(existingCategory);
        return existingCategory;
    }

    @Override
    public void deleteServiceCategoryById(Integer id) {
            serviceCategoryRepository.deleteById(id);
    }

    @Override
    public List<ServiceCategory> getAllServiceCategories() {
        return serviceCategoryRepository.findAll();
    }
}

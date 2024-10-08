package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.service;

import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.ServiceDetail;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.ServiceQuotation;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.exception.DataNotFoundException;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.model.ServiceDetailRequest;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.repository.ServiceCategoryRepository;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.repository.ServiceDetailRepository;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.repository.ServiceQuotationRepository;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.repository.ServiceRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ServiceDetailService implements IServiceDetailService{
    @Autowired
    private ServiceQuotationRepository serviceQuotationRepository;
    @Autowired
    private ServiceRequestRepository serviceRequestRepository;
    @Autowired
    private ServiceCategoryRepository serviceCategoryRepository;
    @Autowired
    private ServiceDetailRepository serviceDetailRepository;
    @Override
    public ServiceDetail createServiceDetail(ServiceDetailRequest serviceDetail) throws Exception {
        ServiceQuotation serviceQuotation = serviceQuotationRepository.findById(serviceDetail.getServiceQuotationId())
                .orElseThrow(()-> new DataNotFoundException("Cannot find service-quotation with id = " + serviceDetail.getServiceQuotationId()));

        ServiceDetail newDetail = new ServiceDetail();
        newDetail.setDescription(serviceDetail.getDescription());
        newDetail.setServiceQuotation(serviceQuotation);
        newDetail.setAddress(serviceDetail.getAddress());
        ServiceDetail obj = serviceDetailRepository.save(newDetail);
        return obj;
    }

    @Override
    public ServiceDetail getServiceDetailById(Integer id) throws Exception {
        return serviceDetailRepository.findById(id)
                .orElseThrow(()-> new DataNotFoundException("Cannot find service-detail with id = " + id));
    }

    @Override
    public List<ServiceDetail> getAllServiceDetails() {
        return serviceDetailRepository.findAll();
    }

    @Override
    public ServiceDetail updateServiceDetail(ServiceDetail serviceDetail) {
        return null;
    }

    @Override
    public void deleteServiceDetailById(Integer id) {

        serviceDetailRepository.deleteById(id);
    }
}

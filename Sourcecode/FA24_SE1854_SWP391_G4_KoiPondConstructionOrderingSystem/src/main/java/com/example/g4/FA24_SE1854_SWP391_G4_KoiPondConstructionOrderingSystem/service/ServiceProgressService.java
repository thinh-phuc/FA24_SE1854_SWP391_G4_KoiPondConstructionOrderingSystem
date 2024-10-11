package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.service;

import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.Customer;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.ServiceDetail;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.ServiceProgress;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.exception.NotFoundException;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.model.ServiceProgressResquest;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.repository.ServiceDetailRepository;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.repository.ServiceProgressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ServiceProgressService {

    @Autowired
    ServiceDetailRepository serviceDetailRepository;
    @Autowired
    ServiceProgressRepository serviceProgressRepository;
    @Autowired
    AuthenticationService authenticationService;

    public ServiceProgress createServiceProgress(ServiceProgressResquest serviceProgressResquest) {
        try {
            ServiceProgress serviceProgress = new ServiceProgress();
            serviceProgress.setStartDate(LocalDateTime.now());
            serviceProgress.setStep(serviceProgressResquest.getStep());
            serviceProgress.setIsPaid(false);
            serviceProgress.setDescription(serviceProgressResquest.getDescription());

            ServiceDetail serviceDetail = serviceDetailRepository.findServiceDetailByServiceDetailId(serviceProgressResquest.getServiceDetailID());
            serviceProgress.setServiceDetail(serviceDetail);

            Customer staff = authenticationService.getCurrentUser();
            serviceProgress.setCreateBy(staff.getName());

            ServiceProgress newServiceProgress = serviceProgressRepository.save(serviceProgress);

            return newServiceProgress;
        } catch (Exception e) {
            throw new NotFoundException("Something is wrong!");
        }
    }


    public ServiceProgress updateServiceProgress(Integer id, ServiceProgressResquest updateServiceProgress) {
        try {
            ServiceProgress oldServiceProgress = serviceProgressRepository.findServiceProgressByServiceProgressID(id);
            if (oldServiceProgress == null)
                throw new NotFoundException("Not found!");
            oldServiceProgress.setStep(updateServiceProgress.getStep());
            oldServiceProgress.setDescription(updateServiceProgress.getDescription());

            Customer staff = authenticationService.getCurrentUser();
            oldServiceProgress.setUpdateBy(staff.getName());

            ServiceProgress updatedServiceProgress = serviceProgressRepository.save(oldServiceProgress);
            return updatedServiceProgress;
        } catch (Exception e) {
            throw new NotFoundException("Something is wrong!");
        }
    }


    public ServiceProgress deleteServiceProgress(Integer id) {
        try {
            ServiceProgress oldServiceProgress = serviceProgressRepository.findServiceProgressByServiceProgressID(id);
            if (oldServiceProgress == null)
                throw new NotFoundException("Not found!");
            oldServiceProgress.setIsActive(false);
            ServiceProgress deletedServiceProgress = serviceProgressRepository.save(oldServiceProgress);
            return deletedServiceProgress;
        } catch (Exception e) {
            throw new NotFoundException("Something is wrong!");
        }
    }


    public List<ServiceProgress> getAllServiceProgress() {
        List<ServiceProgress> serviceProgresses = serviceProgressRepository.findServiceProgressesByIsActiveTrue();
        return serviceProgresses;
    }
}

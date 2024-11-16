package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.service;

import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.ServiceProgress;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.ServiceProgressLog;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.exception.NotFoundException;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.model.ServiceProgressLogRequest;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.repository.ServiceProgressLogRepository;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.repository.ServiceProgressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceProgressLogService {
    @Autowired
    ServiceProgressLogRepository serviceProgressLogRepository;
    @Autowired
    ServiceProgressRepository serviceProgressRepository;

    public ServiceProgressLog CreateServiceProgressLog(ServiceProgressLogRequest serviceProgressLogRequest) {
        try {
            ServiceProgressLog serviceProgressLog = new ServiceProgressLog();
            ServiceProgress serviceProgress = serviceProgressRepository.findServiceProgressByServiceProgressID(serviceProgressLogRequest.getServiceProgressId());
            if (serviceProgress == null) throw new NotFoundException("Service Progress Not Found");
            serviceProgressLog.setServiceProgress(serviceProgress);
            serviceProgressLog.setImageUrl(serviceProgress.getImageUrl());
            serviceProgressLog.setStep(serviceProgress.getStep());
            serviceProgressLog.setDescription(serviceProgress.getDescription());
            serviceProgressLog.setCreateDate(serviceProgress.getCreateDate());
            serviceProgressLog.setCreateBy(serviceProgress.getCreateBy());

            ServiceProgressLog savedServiceProgressLog = serviceProgressLogRepository.save(serviceProgressLog);
            return savedServiceProgressLog;
        } catch (Exception e) {
            throw new NotFoundException("Something is wrong!");
        }
    }

    public List<ServiceProgressLog> GetAllServiceProgressLogsByServiceProgressId(Integer serviceProgressId) {
        List<ServiceProgressLog> serviceProgressLogs = serviceProgressLogRepository.findServiceProgressLogsByServiceProgressId(serviceProgressId);
        return serviceProgressLogs;
    }
}

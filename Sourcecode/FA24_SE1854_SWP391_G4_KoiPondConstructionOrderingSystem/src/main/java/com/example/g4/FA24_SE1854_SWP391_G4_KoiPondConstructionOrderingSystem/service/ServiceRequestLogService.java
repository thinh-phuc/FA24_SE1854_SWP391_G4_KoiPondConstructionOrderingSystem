package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.service;

import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.ServiceRequest;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.ServiceRequestLog;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.exception.DataNotFoundException;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.exception.NotFoundException;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.repository.ServiceRequestLogRepository;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.repository.ServiceRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author ServiceRequestLogService
 */
@Service
public class ServiceRequestLogService {

    @Autowired
    private ServiceRequestLogRepository serviceRequestLogRepository;
    @Autowired
    private ServiceRequestRepository serviceRequestRepository;
    @Autowired
    private AuthenticationService authenticationService;
    public ServiceRequestLog createServiceRequestLog(ServiceRequest serviceRequest,String description,String status) {
        try {
            ServiceRequestLog serviceRequestLog = new ServiceRequestLog();
            serviceRequestLog.setServiceRequest(serviceRequest);
            serviceRequestLog.setStatus(status);
            serviceRequestLog.setDescription(description);
            serviceRequestLog.setCreateDate(LocalDateTime.now());
            serviceRequestLog.setUpdateBy(authenticationService.getCurrentUser().getName() +"-"+ authenticationService.getCurrentUser().getRole() );
            return serviceRequestLogRepository.save(serviceRequestLog);

        } catch (Exception e) {
            throw new NotFoundException(e.getMessage());

        }
    }

    public List<ServiceRequestLog> findServiceRequestLogsbyId(Integer id)
    {
        try{
            ServiceRequest request = serviceRequestRepository.findServiceRequestByServiceRequestId(id);
            return serviceRequestLogRepository.findServiceRequestLogsByServiceRequestOrderByCreateDateDesc(request);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


}

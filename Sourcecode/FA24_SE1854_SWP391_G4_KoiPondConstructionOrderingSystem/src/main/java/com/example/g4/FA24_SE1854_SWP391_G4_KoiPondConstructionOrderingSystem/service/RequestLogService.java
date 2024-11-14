package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.service;

import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.Request;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.RequestLog;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.exception.NotFoundException;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.repository.RequestLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RequestLogService {
    @Autowired
    RequestLogRepository requestLogRepository;
    @Autowired
    AuthenticationService authenticationService;

    public RequestLog createRequestLog(String status, String description, Request request){
        try {
            RequestLog requestLog = new RequestLog();
            requestLog.setStatus(status);
            requestLog.setDescription(description);
            requestLog.setRequest(request);
            requestLog.setCreateDate(LocalDateTime.now());
            requestLog.setUpdateBy(authenticationService.getCurrentUser().getName());
            return requestLogRepository.save(requestLog);
        } catch (Exception e){
            throw new NotFoundException(e.getMessage());
        }
    }
    public List<RequestLog> getRequestLogsByRequestId(Integer id){
        try {
            return requestLogRepository.findRequestLogsByRequestId(id);
        } catch (Exception e){
            throw new NotFoundException("No logs found!");
        }
    }
}

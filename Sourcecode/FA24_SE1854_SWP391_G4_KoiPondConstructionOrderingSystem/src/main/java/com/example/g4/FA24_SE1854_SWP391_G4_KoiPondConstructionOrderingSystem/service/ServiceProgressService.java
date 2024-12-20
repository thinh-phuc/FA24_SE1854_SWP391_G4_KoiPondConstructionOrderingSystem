package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.service;

import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.Customer;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.ServiceDetail;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.ServiceProgress;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.ServiceRequest;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.exception.DataNotFoundException;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.exception.NotFoundException;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.model.ServiceProgressRequest;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.model.ServiceProgressUpdateRequest;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.repository.ServiceDetailRepository;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.repository.ServiceProgressRepository;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.repository.ServiceRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ServiceProgressService {
    @Autowired
    private ServiceRequestRepository serviceRequestRepository;
    @Autowired
    ServiceDetailRepository serviceDetailRepository;
    @Autowired
    ServiceProgressRepository serviceProgressRepository;
    @Autowired
    AuthenticationService authenticationService;
    @Autowired
    ServiceRequestLogService serviceRequestLogService;

    public ServiceProgress createServiceProgress(ServiceProgressRequest serviceProgressResquest) {
        try {
            ServiceProgress serviceProgress = new ServiceProgress();
            serviceProgress.setStartDate(LocalDateTime.now());
            serviceProgress.setStep(serviceProgressResquest.getStep());
            serviceProgress.setDescription(serviceProgressResquest.getDescription());

            ServiceDetail serviceDetail = serviceDetailRepository.findServiceDetailByServiceDetailId(serviceProgressResquest.getServiceDetailID());
            if (serviceDetail == null) {
                throw new NotFoundException("Service Detail Not Found");
            }
            //save lại status
            serviceProgress.setServiceDetail(serviceDetail);
            ServiceRequest request = serviceRequestRepository.findServiceRequestByServiceRequestId(serviceDetail.getServiceQuotation().getServiceRequest().getServiceRequestId());
            request.setStatus("PROGRESSING");
            serviceRequestRepository.save(request);

            Customer staff = authenticationService.getCurrentUser();
            serviceProgress.setCreateBy(staff.getName());

            ServiceProgress newServiceProgress = serviceProgressRepository.save(serviceProgress);
            serviceRequestLogService.createServiceRequestLog(request,"Please check view your progress!","Progress made");
            return newServiceProgress;
        } catch (Exception e) {
            throw new NotFoundException("Something is wrong!");
        }
    }



    public ServiceProgress updateServiceProgress(Integer id, ServiceProgressUpdateRequest updateServiceProgressRequest) {
        try {
            ServiceProgress oldServiceProgress = serviceProgressRepository.findServiceProgressByServiceProgressID(id);
            if (oldServiceProgress == null)
                throw new NotFoundException("Not found!");
            oldServiceProgress.setStep(updateServiceProgressRequest.getStep());
            oldServiceProgress.setDescription(updateServiceProgressRequest.getDescription());
            if ("COMPLETED".equals(updateServiceProgressRequest.getStep()))
                oldServiceProgress.setEndDate(LocalDateTime.now());
            if ("REJCTED".equals(updateServiceProgressRequest.getStep()) && "REJCTED".equals(oldServiceProgress.getStep()))
                throw new Exception("You can't reject because you rejected this progress");
            oldServiceProgress.setImageUrl(updateServiceProgressRequest.getImageUrl());
            Customer staff = authenticationService.getCurrentUser();
            oldServiceProgress.setUpdateBy(staff.getName());
            serviceRequestLogService.createServiceRequestLog(oldServiceProgress.getServiceDetail().getServiceQuotation().getServiceRequest(),"The progress is updated,view check progress!","Progress updated");
            ServiceProgress updatedServiceProgress = serviceProgressRepository.save(oldServiceProgress);
            return updatedServiceProgress;
        } catch (Exception e) {
            throw new NotFoundException("Something is wrong!");
        }
    }

    public ServiceProgress acceptServiceProgress(Integer id) {
        try {
            ServiceProgress serviceProgress = serviceProgressRepository.findServiceProgressByServiceProgressID(id);
            if (serviceProgress == null)
                throw new NotFoundException("Not found!");
            if (!serviceProgress.getStep().equals("COMPLETED"))
                throw new NotFoundException("Step is not complete");
            serviceProgress.setIsComfirmed(true);

            Customer customer = authenticationService.getCurrentUser();
            serviceProgress.setUpdateBy(customer.getName());

            ServiceProgress acceptedServiceProgress = serviceProgressRepository.save(serviceProgress);
            return acceptedServiceProgress;
        } catch (Exception e) {
            throw new NotFoundException("Something is wrong!");
        }
    }

    public ServiceProgress deleteServiceProgress(Integer id) {
        try {
            ServiceProgress oldServiceProgress = serviceProgressRepository.findServiceProgressByServiceProgressID(id);
            if (oldServiceProgress == null)
                throw new NotFoundException("Not found!");

            ServiceRequest request = serviceRequestRepository.findServiceRequestByServiceRequestId(oldServiceProgress.getServiceDetail().getServiceQuotation().getServiceRequest().getServiceRequestId());
            if (request == null)
            {

                throw new DataNotFoundException("ServiceRequest not found!");
            }
            request.setStatus("PROCESSING");
            serviceRequestRepository.save(request);
            serviceRequestLogService.createServiceRequestLog(request,"Please wait for our next response","Progress deleted");
            oldServiceProgress.setIsActive(false);
            ServiceProgress deletedServiceProgress = serviceProgressRepository.save(oldServiceProgress);
            return deletedServiceProgress;
        } catch (Exception e) {
            throw new NotFoundException("Something is wrong!");
        }
    }

    public List<ServiceProgress> getServiceProgressesForCustomer(Integer customerID) {
        List<ServiceProgress> serviceProgresses = serviceProgressRepository.findActiveServiceProgressByCustomerOrderByServiceProgressIDDesc(customerID);
        return serviceProgresses;
    }

    public List<ServiceProgress> getServiceProgressesForMaintenanceStaff(Integer maintenanceStaffID) {
        List<ServiceProgress> serviceProgresses = serviceProgressRepository.findActiveServiceProgressByCustomerOrderByMaintenanceStaffIdDesc(maintenanceStaffID);
        return serviceProgresses;
    }

    public ServiceProgress getServiceProgress(Integer id) {
        ServiceProgress serviceProgress = serviceProgressRepository.findServiceProgressByServiceProgressID(id);
        return serviceProgress;
    }

    public List<ServiceProgress> getAllServiceProgress() {
        List<ServiceProgress> serviceProgresses = serviceProgressRepository.findServiceProgressesByIsActiveTrueOrderByServiceProgressIDDesc();
        return serviceProgresses;
    }
}

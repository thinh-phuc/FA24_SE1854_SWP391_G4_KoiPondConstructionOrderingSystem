package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.service;

import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.*;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.exception.NotFoundException;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.model.ServiceFeedbackRequest;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ServiceFeedbackService {

    @Autowired
    ServiceRequestRepository serviceRequestRepository;
    @Autowired
    AuthenticationService authenticationService;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    private ServiceFeedbackRepository serviceFeedbackRepository;

    public ServiceFeedback createServiceFeedback(ServiceFeedbackRequest serviceFeedbackRequest) {
        try {
            ServiceFeedback serviceFeedback = new ServiceFeedback();

            ServiceRequest serviceRequest = serviceRequestRepository.findServiceRequestByServiceRequestId(
                    serviceFeedbackRequest.getServiceRequestId());
            if (serviceRequest == null) {
                throw new NotFoundException("Service request not found");
            }
            serviceFeedback.setServiceRequest(serviceRequest);

            Customer staff = authenticationService.getCurrentUser();
            serviceFeedback.setCreateBy(staff.getName());
            serviceFeedback.setCustomer(staff);

            serviceFeedback.setFeedback(serviceFeedbackRequest.getFeedback());

            // Validate rating between 1 and 5
            if (serviceFeedbackRequest.getRating() >= 1 && serviceFeedbackRequest.getRating() <= 5) {
                serviceFeedback.setRating(serviceFeedbackRequest.getRating());
            } else {
                serviceFeedback.setRating(5); // Default to 5 if rating is out of bounds
            }

            serviceFeedback.setCreateDate(LocalDateTime.now());

            return serviceFeedbackRepository.save(serviceFeedback);
        } catch (NotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while creating service feedback: " + e.getMessage(), e);
        }
    }

    public ServiceFeedback updateServiceFeedback(Integer id, ServiceFeedbackRequest serviceFeedbackRequest) {
        try {
            ServiceFeedback oldServiceFeedback = serviceFeedbackRepository.findServiceFeedbackByServiceFeedbackId(id);
            if (oldServiceFeedback == null)
                throw new NotFoundException("Not found!");

            oldServiceFeedback.setFeedback(serviceFeedbackRequest.getFeedback());
            oldServiceFeedback.setRating(serviceFeedbackRequest.getRating());

            Customer customer = authenticationService.getCurrentUser();
            oldServiceFeedback.setUpdateBy(customer.getName());

            oldServiceFeedback.setUpdateDate(LocalDateTime.now());

            ServiceFeedback updatedServiceFeedback = serviceFeedbackRepository.save(oldServiceFeedback);
            return updatedServiceFeedback;
        } catch (Exception e) {
            throw new NotFoundException("Something is wrong!");
        }
    }

    public ServiceFeedback deleteServiceFeedback(Integer id) {
        try {
            ServiceFeedback oldServiceFeedback = serviceFeedbackRepository.findServiceFeedbackByServiceFeedbackId(id);
            if (oldServiceFeedback == null)
                throw new NotFoundException("Not found!");
            oldServiceFeedback.setIsActive(false);
            ServiceFeedback deletedServiceFeedback = serviceFeedbackRepository.save(oldServiceFeedback);
            return deletedServiceFeedback;
        } catch (Exception e) {
            throw new NotFoundException("Something is wrong!");
        }
    }

    public ServiceFeedback getServiceFeedbackByServiceCategoryId(Integer id) {
        ServiceFeedback serviceFeedback = serviceFeedbackRepository.findServiceFeedbackByServiceRequestId(id);
        return serviceFeedback;
    }

    public ServiceFeedback getServiceFeedbackById(Integer id) {
        ServiceFeedback serviceFeedback = serviceFeedbackRepository.findServiceFeedbackByServiceFeedbackId(id);
        return serviceFeedback;
    }

    public List<ServiceFeedback> getAllServiceFeedback() {
        List<ServiceFeedback> serviceFeedbackList = serviceFeedbackRepository.findServiceFeedbacksByIsActiveTrueOrderByServiceFeedbackIdDesc();
        return serviceFeedbackList;
    }
}

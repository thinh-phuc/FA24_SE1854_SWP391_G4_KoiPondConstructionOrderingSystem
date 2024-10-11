package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.service;

import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.Customer;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.ServiceDetail;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.ServiceFeedback;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.exception.NotFoundException;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.model.ServiceFeedbackRequest;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.repository.CustomerRepository;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.repository.ServiceDetailRepository;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.repository.ServiceFeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceFeedbackService {

    @Autowired
    ServiceDetailRepository serviceDetailRepository;
    @Autowired
    AuthenticationService authenticationService;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    private ServiceFeedbackRepository serviceFeedbackRepository;

    public ServiceFeedback createServiceFeedback(ServiceFeedbackRequest serviceFeedbackRequest) {
        try {
            ServiceFeedback serviceFeedback = new ServiceFeedback();

            ServiceDetail serviceDetail = serviceDetailRepository.findServiceDetailByServiceDetailId(serviceFeedbackRequest.getServiceDetailId());
            serviceFeedback.setServiceDetail(serviceDetail);

            Customer customer = customerRepository.findCustomerByCustomerId(serviceFeedbackRequest.getCustomerId());
            serviceFeedback.setCustomer(customer);

            serviceFeedback.setFeedback(serviceFeedbackRequest.getFeedback());
            serviceFeedback.setRating(serviceFeedbackRequest.getRating());
            serviceFeedback.setNote(serviceFeedback.getNote());

            Customer staff = authenticationService.getCurrentUser();
            serviceFeedback.setCreateBy(staff.getName());

            ServiceFeedback newServiceFeedback = serviceFeedbackRepository.save(serviceFeedback);
            return newServiceFeedback;
        } catch (Exception e) {
            throw new NotFoundException("Something is wrong!");
        }
    }

    public ServiceFeedback updateServiceFeedback(Integer id, ServiceFeedbackRequest serviceFeedbackRequest) {
        try {
            ServiceFeedback oldServiceFeedback = serviceFeedbackRepository.findServiceFeedbackByServiceFeedbackId(id);
            if (oldServiceFeedback == null)
                throw new NotFoundException("Not found!");

            oldServiceFeedback.setFeedback(serviceFeedbackRequest.getFeedback());
            oldServiceFeedback.setRating(serviceFeedbackRequest.getRating());
            oldServiceFeedback.setNote(serviceFeedbackRequest.getNote());

            Customer customer = authenticationService.getCurrentUser();
            oldServiceFeedback.setUpdateBy(customer.getName());

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

    public List<ServiceFeedback> getAllServiceFeedback() {
        List<ServiceFeedback> serviceFeedbackList = serviceFeedbackRepository.findServiceFeedbacksByIsActiveTrue();
        return serviceFeedbackList;
    }
}

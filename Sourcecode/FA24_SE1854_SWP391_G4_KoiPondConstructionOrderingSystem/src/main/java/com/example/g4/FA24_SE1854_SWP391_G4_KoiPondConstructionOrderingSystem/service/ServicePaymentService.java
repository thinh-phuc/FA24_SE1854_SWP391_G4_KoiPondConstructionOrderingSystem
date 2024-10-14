package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.service;

import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.Customer;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.ServicePayment;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.ServiceProgress;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.ServiceQuotation;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.exception.NotFoundException;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.model.ServicePaymentRequest;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.repository.CustomerRepository;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.repository.ServicePaymentRepository;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.repository.ServiceProgressRepository;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.repository.ServiceQuotationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicePaymentService {
    @Autowired
    private ServiceQuotationRepository serviceQuotationRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ServiceProgressRepository serviceProgressRepository;
    @Autowired
    private ServicePaymentRepository servicePaymentRepository;
    @Autowired
    AuthenticationService authenticationService;

    public ServicePayment createServicePayment(ServicePaymentRequest servicePaymentRequest) {
        try {
            ServicePayment servicePayment = new ServicePayment();

            ServiceQuotation serviceQuotation = serviceQuotationRepository.findServiceQuotationByServiceQuotationId(servicePaymentRequest.getServiceQuotationID());
            servicePayment.setServiceQuotation(serviceQuotation);

            servicePayment.setPaymentMethod(servicePaymentRequest.getPaymentMethod());

            Customer maintenanceStaff = customerRepository.findCustomerByCustomerId(servicePaymentRequest.getMaintenanceStaffID());
            servicePayment.setMaintenanceStaff(maintenanceStaff);

            ServiceProgress serviceProgress = serviceProgressRepository.findServiceProgressByServiceProgressID(servicePaymentRequest.getServiceProgressID());
            servicePayment.setServiceProgress(serviceProgress);

            servicePayment.setStatus(servicePayment.getStatus());

            Customer staff = authenticationService.getCurrentUser();
            servicePayment.setCreateBy(staff.getName());

            ServicePayment newServicePayment = servicePaymentRepository.save(servicePayment);
            return newServicePayment;
        } catch (Exception e) {
            throw new NotFoundException("Something is wrong!");
        }
    }

    public ServicePayment updateServicePayment(Integer id, ServicePaymentRequest servicePaymentRequest) {
        try {
            ServicePayment oldServicePayment = servicePaymentRepository.findServicePaymentByServicePaymentID(id);
            if (oldServicePayment == null)
                throw new NotFoundException("Not found!");

            oldServicePayment.setPaymentMethod(servicePaymentRequest.getPaymentMethod());
            oldServicePayment.setStatus(servicePaymentRequest.getStatus());

            if ("COMPLETED".equalsIgnoreCase(servicePaymentRequest.getStatus()))
                oldServicePayment.getServiceProgress().setIsPaid(true);

            Customer staff = authenticationService.getCurrentUser();
            oldServicePayment.setUpdateBy(staff.getName());

            ServicePayment updatedServicePayment = servicePaymentRepository.save(oldServicePayment);
            return updatedServicePayment;
        } catch (Exception e) {
            throw new NotFoundException("Something is wrong!");
        }
    }

    public ServicePayment deleteServicePayment(Integer id) {
        try {
            ServicePayment oldServicePayment = servicePaymentRepository.findServicePaymentByServicePaymentID(id);
            if (oldServicePayment == null)
                throw new NotFoundException("Not found!");
            oldServicePayment.setIsActive(false);
            ServicePayment deletedServicePayment = servicePaymentRepository.save(oldServicePayment);
            return deletedServicePayment;
        } catch (Exception e) {
            throw new NotFoundException("Something is wrong!");
        }
    }

    public ServicePayment getServicePaymentById(Integer id) {
        ServicePayment servicePayment = servicePaymentRepository.findServicePaymentByServicePaymentID(id);
        return servicePayment;
    }

    public List<ServicePayment> getAllServicePayment() {
        List<ServicePayment> servicePayments = servicePaymentRepository.findServicePaymentsByIsActiveTrue();
        return servicePayments;
    }
}

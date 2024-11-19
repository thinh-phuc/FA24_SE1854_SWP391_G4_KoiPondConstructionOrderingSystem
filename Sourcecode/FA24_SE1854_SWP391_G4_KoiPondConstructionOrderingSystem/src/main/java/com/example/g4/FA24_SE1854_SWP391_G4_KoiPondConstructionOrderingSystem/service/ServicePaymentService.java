package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.service;

import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.Customer;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.ServicePayment;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.ServiceQuotation;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.ServiceRequest;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.exception.NotFoundException;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.model.ServicePaymentRequest;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.repository.*;
import jakarta.transaction.Transactional;
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
    private ServicePaymentRepository servicePaymentRepository;
    @Autowired
    AuthenticationService authenticationService;
    @Autowired
    private ServiceRequestLogService serviceRequestLogService;
    @Autowired
    private ServiceRequestRepository serviceRequestRepository;
    public ServicePayment createServicePayment(ServicePaymentRequest servicePaymentRequest) {
        try {
            ServicePayment servicePayment = new ServicePayment();

            ServiceQuotation serviceQuotation = serviceQuotationRepository.findServiceQuotationByServiceQuotationId(servicePaymentRequest.getServiceQuotationID());
            if (serviceQuotation == null)
                throw new NotFoundException("Service Quotation Not Found");
            ServiceRequest serviceRequest = serviceRequestRepository.findServiceRequestByServiceRequestId(serviceQuotation.getServiceRequest().getServiceRequestId());
            serviceRequest.setStatus("Payment in progress");
            serviceRequestRepository.save(serviceRequest);


            servicePayment.setServiceQuotation(serviceQuotation);
            servicePayment.setPaymentMethod(servicePaymentRequest.getPaymentMethod());

            Customer maintenanceStaff = customerRepository.findCustomerByCustomerId(servicePaymentRequest.getMaintenanceStaffID());
            servicePayment.setMaintenanceStaff(maintenanceStaff);

            servicePayment.setStatus(servicePayment.getStatus());

            Customer staff = authenticationService.getCurrentUser();
            servicePayment.setCreateBy(staff.getName());
            serviceRequestLogService.createServiceRequestLog(serviceRequest,"Please check your view Payment!","Payment created!");
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
            if (servicePaymentRequest.getStatus().equals("Paid")) {
                    oldServicePayment.getServiceQuotation().getServiceRequest().setStatus("Finish");
            }
            oldServicePayment.setStatus(servicePaymentRequest.getStatus());
            oldServicePayment.setPaymentMethod(servicePaymentRequest.getPaymentMethod());
            serviceRequestLogService.createServiceRequestLog(oldServicePayment.getServiceQuotation().getServiceRequest(),"The payment is updated","Payment updated");
            ServicePayment updatedServicePayment = servicePaymentRepository.save(oldServicePayment);
            return updatedServicePayment;
        } catch (Exception e) {
            throw new NotFoundException("Something is wrong!");
        }
    }

    public ServicePayment updateServicePaymentByCustomer(Integer id, ServicePaymentRequest servicePaymentRequest) {
        try {
            ServicePayment oldServicePayment = servicePaymentRepository.findServicePaymentByServicePaymentID(id);
            if (oldServicePayment == null)
                throw new NotFoundException("Not found!");
            if (servicePaymentRequest.getStatus().equals("Paid")) {
                if (oldServicePayment.getServiceQuotation() != null && oldServicePayment.getServiceQuotation().getServiceRequest() != null) {
                    oldServicePayment.getServiceQuotation().getServiceRequest().setStatus("Finish");

                } else {
                    throw new NotFoundException("Associated ServiceRequest not found!");
                }
            }
            oldServicePayment.setStatus(servicePaymentRequest.getStatus());

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

    public ServicePayment getServicePaymentByServiceQuotationId(Integer id) {
        ServicePayment servicePayment = servicePaymentRepository.findServicePaymentByServiceQuotationIdDESC(id);
        if (servicePayment == null)
            return null;
        return servicePayment;
    }

    public ServicePayment getServicePaymentById(Integer id) {
        ServicePayment servicePayment = servicePaymentRepository.findServicePaymentByServicePaymentID(id);
        return servicePayment;
    }

    public List<ServicePayment> getAllServicePayment() {
        List<ServicePayment> servicePayments = servicePaymentRepository.findServicePaymentsByIsActiveTrueOrderByServicePaymentIDDesc();
        return servicePayments;
    }


    @Transactional
    public ServicePayment updateStatusServicePaymentByCustomer(Integer id, String status) {
        try {
            ServicePayment oldServicePayment = servicePaymentRepository.findServicePaymentByServicePaymentID(id);
            if (oldServicePayment == null) {
                throw new NotFoundException("Payment not found!");
            }

            // Cập nhật trạng thái payment
            oldServicePayment.setStatus(status);
            //oldServicePayment.setPaymentMethod(servicePaymentRequest.getPaymentMethod());

            // Nếu thanh toán thành công, cập nhật trạng thái request
            if (status.equals("Paid")) {
                ServiceRequest request = oldServicePayment.getServiceQuotation().getServiceRequest();
                if (request != null) {
                    request.setStatus("Finish");
                    serviceRequestRepository.save(request);

                    // Tạo log
                    serviceRequestLogService.createServiceRequestLog(
                            request,
                            "Payment completed" ,
                            "Payment successful"
                    );
                }
            }

            return servicePaymentRepository.save(oldServicePayment);
        } catch (Exception e) {
            throw new NotFoundException("Failed to update payment: " + e.getMessage());
        }
    }
}

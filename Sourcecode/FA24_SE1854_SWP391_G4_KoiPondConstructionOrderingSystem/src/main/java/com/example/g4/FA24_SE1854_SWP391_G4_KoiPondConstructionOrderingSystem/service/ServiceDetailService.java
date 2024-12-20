package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.service;

import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.Customer;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.ServiceDetail;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.ServiceQuotation;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.ServiceRequest;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.exception.DataNotFoundException;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.model.ServiceDetailRequest;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.model.ServiceDetailUpdateRequest;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    AuthenticationService authenticationService;
    @Autowired
    private ServiceRequestLogService serviceRequestLogService;
    @Override
    public ServiceDetail createServiceDetail(ServiceDetailRequest serviceDetail) throws Exception {
        ServiceQuotation serviceQuotation = serviceQuotationRepository.findById(serviceDetail.getServiceQuotationId())
                .orElseThrow(()-> new DataNotFoundException("Cannot find service-quotation with id = " + serviceDetail.getServiceQuotationId()));

        if(serviceQuotation.isConfirm()==false)
        {
            throw new DataNotFoundException("Can not create service-detail!");
        }

        ServiceRequest request = serviceRequestRepository.findById(serviceQuotation.getServiceRequest().getServiceRequestId())
                .orElseThrow(()-> new DataNotFoundException("Cannot find service-request"));
            request.setStatus("PROCESSING");
            serviceRequestRepository.save(request);
        Customer staff = customerRepository.findCustomerByCustomerId(serviceDetail.getStaffId());
        if(staff ==null)
        {
            throw new DataNotFoundException("Cannot find staff with id = " +serviceDetail.getStaffId());
        }
        Customer manager = authenticationService.getCurrentUser();
        ServiceDetail newDetail = new ServiceDetail();
        newDetail.setDescription(serviceDetail.getDescription());
        newDetail.setStaff(staff);
        newDetail.setServiceQuotation(serviceQuotation);
        newDetail.setAddress(serviceQuotation.getServiceRequest().getAddress());
        newDetail.setCreateDate(LocalDateTime.now());
        newDetail.setCreateBy(manager.getRole() + ":" +manager.getName() );
        newDetail.setIsActive(true);
        serviceRequestLogService.createServiceRequestLog(serviceQuotation.getServiceRequest(),"We are assigning employees to do the work","Assigned staff!");
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
        return serviceDetailRepository.findServiceDetailByIsActiveTrueOrderByCreateDateDesc();
    }

    @Override
    public ServiceDetail updateServiceDetail(Integer Id, ServiceDetailUpdateRequest serviceDetail)throws Exception {
        ServiceDetail detail = serviceDetailRepository.findById(Id)
                .orElseThrow(()-> new DataNotFoundException("Cannot fin service-detail with id= "+ Id) );
        Customer manager = authenticationService.getCurrentUser();
        Customer staff = customerRepository.findCustomerByCustomerId(serviceDetail.getStaffId());
        if(staff ==null)
        {
            throw new DataNotFoundException("Cannot find staff with id = " +serviceDetail.getStaffId());
        }
        detail.setDescription(serviceDetail.getDescription());
        detail.setStaff(staff);
        detail.setUpdateDate(LocalDateTime.now());
        detail.setUpdateBy(manager.getRole() + ":" +manager.getName() );
        serviceRequestLogService.createServiceRequestLog(detail.getServiceQuotation().getServiceRequest(),"We have a change in assignment!","Assign staff updated!");
        return serviceDetailRepository.save(detail);
    }

    @Override
    public void deleteServiceDetailById(Integer id)throws Exception {
        ServiceDetail detail = serviceDetailRepository.findById(id)
                .orElseThrow(()-> new DataNotFoundException("Cannot fin service-detail") );
        ServiceRequest request = serviceRequestRepository.findServiceRequestByServiceRequestId(detail.getServiceQuotation().getServiceRequest().getServiceRequestId());
        if (request == null)
        {

            throw new DataNotFoundException("ServiceRequest not found!");
        }
        request.setStatus("QUOTING");
        serviceRequestRepository.save(request);
        serviceRequestLogService.createServiceRequestLog(request,"Please wait for our next response","Assign staff deleted");
        serviceDetailRepository.deleteById(id);
    }
}

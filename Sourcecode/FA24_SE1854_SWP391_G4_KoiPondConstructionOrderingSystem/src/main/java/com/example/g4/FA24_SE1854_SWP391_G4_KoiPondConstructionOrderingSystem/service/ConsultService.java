package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.service;

import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.*;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.exception.NotFoundException;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.model.ConsultRequest;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.model.ConsultResponse;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.repository.ConsultRepository;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.repository.CustomerRepository;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.repository.RequestDetailRepository;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.repository.RequestRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ConsultService {

    @Autowired
    ConsultRepository consultRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    RequestDetailRepository requestDetailRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    RequestDetailService requestDetailService;

    @Autowired
    RequestRepository requestRepository;

    @Autowired
    RequestLogService requestLogService;

    //response Consult
    public ConsultResponse toResponseConsult (Consult consult){
        ConsultResponse consultResponse = new ConsultResponse();
        consultResponse.setId(consult.getId());
        consultResponse.setDescription(consult.getDescription());
        consultResponse.setConsultDate(consult.getConsultDate());
        consultResponse.setCreateDate(consult.getCreateDate());
        consultResponse.setIsCustomerConfirm(false);
        consultResponse.setRequestDetailId(consult.getRequestDetail().getRequestDetailId());
        return consultResponse;
    }


    //create
    public ConsultResponse createConsult(ConsultRequest consultRequest){
        Consult consult = new Consult();

        consult.setCreateDate(LocalDateTime.now());
        consult.setDescription(consultRequest.getDescription());
        consult.setConsultDate(consultRequest.getConsultDate());
        consult.setIsCustomerConfirm(false);

        List<Customer> customers = new ArrayList<>();
        Customer staff = authenticationService.getCurrentUser();
        customers.add(staff);
        Customer customer1 = customerRepository.findCustomerByCustomerId(consultRequest.getCustomerId());
        if(customer1 == null){
            throw new EntityNotFoundException("Customer's ID not found!");
        }
        customers.add(customer1);
        consult.setCustomers(customers);

        RequestDetail requestDetail = requestDetailRepository.findRequestDetailByRequestDetailId(consultRequest.getRequestDetailId());
        Request request = requestDetail.getRequest();
        request.setStatus("CONSULTED");
        requestDetail.setNote("DONE");
        requestLogService.createRequestLog("CONSULTED", "Your request has been consulted, quotation will be made soon!", request);
        requestRepository.save(request);
        requestDetailRepository.save(requestDetail);
        if(requestDetail == null){
            throw new EntityNotFoundException("Request Detail not found!");
        }
        consult.setRequestDetail(requestDetail);

        Consult newConsult = consultRepository.save(consult);
        return toResponseConsult(newConsult);
    }

    //read
    public List<Consult> getAllConsults(){
        List<Consult> consults = consultRepository.findConsultsByIsDeletedFalse();
        return consults;
    }

    //update
    public Consult updateConsult(Integer id, ConsultRequest consultRequest){
        Consult oldConsult = consultRepository.findConsultById(id);

        if(oldConsult == null){
            throw new EntityNotFoundException("Consult Not Found!");
        }
//-------Update customer's ID-------
        List<Customer> customers = new ArrayList<>();
        Customer staff = authenticationService.getCurrentUser();
        customers.add(staff);
        Customer customer1 = customerRepository.findCustomerByCustomerId(consultRequest.getCustomerId());
        if(customer1 == null){
            throw new EntityNotFoundException("Customer's ID not found!");
        }
        customers.add(customer1);
        oldConsult.setCustomers(customers);
//-----------------------------------
        oldConsult.setDescription(consultRequest.getDescription());
        oldConsult.setConsultDate(consultRequest.getConsultDate());
        oldConsult.setIsCustomerConfirm(consultRequest.getIsCustomerConfirm());

        RequestDetail requestDetail = requestDetailRepository.findRequestDetailByRequestDetailId(consultRequest.getRequestDetailId());
        if(requestDetail == null){
            throw new EntityNotFoundException("Request Detail not found!");
        }
        oldConsult.setRequestDetail(requestDetail);

        return consultRepository.save(oldConsult);
    }

    //delete
    public Consult deleteConsult(Integer id){
        Consult oldConsult = getConsultById(id);

        if(oldConsult == null){
            throw new EntityNotFoundException("Consult does not exist!");
        }

        oldConsult.setIsDeleted(true);

        return consultRepository.save(oldConsult);
    }

    public Consult getConsultById(Integer id){ //lớp này để check xem có thông tin sẵn trong list ko để update hoặc delete. Mục đích tạo ra lớp này để sau này cần thì gọi ra cho dễ
        Consult oldConsult = consultRepository.findConsultById(id);

        if(oldConsult == null){
            throw new EntityNotFoundException("Consult does not exist!");
        }
        // if user.status == "BLOCK" => throw new EntityNotFoundException("Koi not found!");
        // nếu thông tin của user nào bị BLOCK thì quăng ra lỗi (throw new...) vì bị BLOCK rồi thì sẽ ko update hoặc delete được

        return oldConsult;
    }

    public Consult confirmConsult(Integer id){
        Consult consult = getConsultById(id);
        consult.setIsCustomerConfirm(true);
        return consultRepository.save(consult);
    }
}

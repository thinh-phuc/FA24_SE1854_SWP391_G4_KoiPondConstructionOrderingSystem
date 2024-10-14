package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.service;

import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.Consult;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.Customer;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.PondDesignTemplate;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.RequestDetail;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.exception.NotFoundException;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.model.ConsultRequest;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.repository.ConsultRepository;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.repository.CustomerRepository;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.repository.RequestDetailRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    //create
    public Consult createConsult(ConsultRequest consultRequest){
        Consult consult = new Consult();

        consult.setConsultantId(consultRequest.getConsultantId());

        Customer customer = customerRepository.findCustomerByCustomerId(consultRequest.getCustomerId());
        if(customer == null){
            throw new EntityNotFoundException("Customer ID not found!");
        }
        consult.setCustomerId(consultRequest.getCustomerId());
        consult.setCreateDate(LocalDateTime.now());
        consult.setDescription(consultRequest.getDescription());
        consult.setConsultDate(LocalDateTime.now());
        consult.setIsCustomerConfirm(false);

        RequestDetail requestDetail = requestDetailRepository.findRequestDetailById(consultRequest.getRequestDetailId());
        if(requestDetail == null){
            throw new EntityNotFoundException("Request Detail not found!");
        }
        consult.setRequestDetail(requestDetail);

        Consult newConsult = consultRepository.save(consult);
        return newConsult;
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

        Customer customer = customerRepository.findCustomerByCustomerId(consultRequest.getCustomerId());
        if(customer == null){
            throw new EntityNotFoundException("Customer ID not found!");
        }
        oldConsult.setCustomerId(consultRequest.getCustomerId());

        oldConsult.setConsultantId(consultRequest.getConsultantId());
        //oldConsult.setRequestDetailId(consult.getRequestDetailId());
        oldConsult.setDescription(consultRequest.getDescription());
        //oldConsult.setCreateDate(consult.getCreateDate());
        oldConsult.setConsultDate(consultRequest.getConsultDate());
        oldConsult.setIsCustomerConfirm(consultRequest.getIsCustomerConfirm());

        RequestDetail requestDetail = requestDetailRepository.findRequestDetailById(consultRequest.getRequestDetailId());
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


}

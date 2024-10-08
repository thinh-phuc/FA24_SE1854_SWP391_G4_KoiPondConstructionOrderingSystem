package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.service;

import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.Consult;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.PondDesignTemplate;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.repository.ConsultRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultService {

    @Autowired
    ConsultRepository consultRepository;

    //create
    public Consult createConsult(Consult consult){
        Consult newConsult = consultRepository.save(consult);
        return newConsult;
    }

    //read
    public List<Consult> getAllConsults(){
        List<Consult> consults = consultRepository.findConsultsByIsDeletedFalse();
        return consults;
    }

    //update
    public Consult updateConsult(Integer id, Consult consult){
        Consult oldConsult = consultRepository.findConsultById(id);

        if(oldConsult == null){
            throw new EntityNotFoundException("Consult Not Found!");
        }

        oldConsult.setCustomerId(consult.getCustomerId());
        oldConsult.setConsultantId(consult.getConsultantId());
        //oldConsult.setRequestDetailId(consult.getRequestDetailId());
        oldConsult.setDescription(consult.getDescription());
        oldConsult.setCreateDate(consult.getCreateDate());
        oldConsult.setConsultDate(consult.getConsultDate());
        oldConsult.setIsCustomerConfirm(consult.getIsCustomerConfirm());

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

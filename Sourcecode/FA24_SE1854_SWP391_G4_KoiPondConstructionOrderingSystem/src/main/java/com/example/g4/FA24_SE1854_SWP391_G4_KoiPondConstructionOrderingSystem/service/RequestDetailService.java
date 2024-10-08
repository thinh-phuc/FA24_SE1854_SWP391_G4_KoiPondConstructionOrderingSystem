package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.service;

import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.PondDesignTemplate;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.RequestDetail;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.exception.NotFoundException;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.repository.RequestDetailRepository;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.repository.RequestRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequestDetailService {

    @Autowired
    RequestDetailRepository requestDetailRepository;

    //create
    public RequestDetail createDetail(RequestDetail requestDetail){
        RequestDetail newRequestDetail = requestDetailRepository.save(requestDetail);
        return newRequestDetail;
    }

    //read
    public List<RequestDetail> getAllDetails(){
        List<RequestDetail>  requestDetails = requestDetailRepository.findDetailsByIsDeletedFalse();
        return requestDetails;
    }

    //update
    public RequestDetail updateDetail(Integer id, RequestDetail requestDetail){
        RequestDetail oldDetail = requestDetailRepository.findDetailById(id);

        if(oldDetail == null){
            throw new EntityNotFoundException("Detail Not Found!");
        }

        //oldDetail.setPondDesignTemplateId(requestDetail.getPondDesignTemplateId());
        //oldDetail.setRequestId(requestDetail.getRequestId());
        oldDetail.setNote(requestDetail.getNote());

        return requestDetailRepository.save(oldDetail);
    }

    public RequestDetail deleteDetail(Integer id){
        RequestDetail oldDetail = getDetailById(id);

        if(oldDetail == null){
            throw new EntityNotFoundException("Detail does not exist!");
        }

        oldDetail.setIsDeleted(true);

        return requestDetailRepository.save(oldDetail);

    }

    public RequestDetail getDetailById(Integer id){ //lớp này để check xem có thông tin sẵn trong list ko để update hoặc delete. Mục đích tạo ra lớp này để sau này cần thì gọi ra cho dễ
        RequestDetail oldRequestDetail = requestDetailRepository.findDetailById(id);

        if(oldRequestDetail == null){
            throw new EntityNotFoundException("Detail does not exist!");
        }
        // if user.status == "BLOCK" => throw new EntityNotFoundException("Koi not found!");
        // nếu thông tin của user nào bị BLOCK thì quăng ra lỗi (throw new...) vì bị BLOCK rồi thì sẽ ko update hoặc delete được

        return oldRequestDetail;
    }
}

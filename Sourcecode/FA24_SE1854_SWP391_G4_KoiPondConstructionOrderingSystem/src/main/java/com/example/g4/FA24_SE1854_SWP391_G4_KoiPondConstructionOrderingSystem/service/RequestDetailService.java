package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.service;

import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.PondDesignTemplate;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.Request;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.RequestDetail;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.exception.NotFoundException;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.model.RequestDetailRequest;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.model.UpdateRequestDetailRequest;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.repository.PondDesignTemplateRepository;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.repository.RequestDetailRepository;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.repository.RequestRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cassandra.CassandraProperties;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RequestDetailService {

    @Autowired
    RequestDetailRepository requestDetailRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    PondDesignTemplateRepository pondDesignTemplateRepository;

    @Autowired
    RequestRepository requestRepository;


    //create
    public RequestDetail createRequestDetail(RequestDetailRequest requestDetailRequest){
        RequestDetail requestDetail = new RequestDetail();

        Request request = requestRepository.findRequestById(requestDetailRequest.getRequestId());
        if(request == null){
            throw new EntityNotFoundException("Request not found!");
        }
        requestDetail.setRequest(request);

        PondDesignTemplate pondDesignTemplate = pondDesignTemplateRepository.findPondDesignTemplateByPondDesignTemplateId(requestDetailRequest.getPondDesignTemplateId());
        if(pondDesignTemplate == null){
            throw new EntityNotFoundException("Pond Design Template not found!");
        }
        requestDetail.setPondDesignTemplate(pondDesignTemplate);

        requestDetail.setNote("Consult is not started!");

        RequestDetail newRequestDetail = requestDetailRepository.save(requestDetail);
        return newRequestDetail;
    }

    //read
    public List<RequestDetail> getAllDetails(){
        List<RequestDetail>  requestDetails = requestDetailRepository.findRequestDetailsByIsDeletedFalse();
        return requestDetails;
    }

    public List<RequestDetail> getAllRequestDetailsByRequestId(Integer requestId){
        Request request = requestRepository.findRequestById(requestId);

        if(request == null){
            throw new EntityNotFoundException("Request not found!");
        }
        List<RequestDetail> requestDetails = requestDetailRepository.findRequestDetailsByRequestId(requestId);
        return requestDetails;
    }

    //update
    public RequestDetail updateRequestDetail(Integer id, UpdateRequestDetailRequest updateRequestDetailRequest){
        RequestDetail oldDetail = requestDetailRepository.findRequestDetailByRequestDetailId(id);

        if(oldDetail == null){
            throw new EntityNotFoundException("Request Detail Not Found!");
        }

        oldDetail.setNote(updateRequestDetailRequest.getNote());

        PondDesignTemplate pondDesignTemplate = pondDesignTemplateRepository.findPondDesignTemplateByPondDesignTemplateId(updateRequestDetailRequest.getPondDesignTemplateId());
        if(pondDesignTemplate == null){
            throw new EntityNotFoundException("Pond design template not found!");
        }
        oldDetail.setPondDesignTemplate(pondDesignTemplate);

        return requestDetailRepository.save(oldDetail);
    }

    public RequestDetail deleteRequestDetail(Integer id){
        RequestDetail oldDetail = getRequestDetailById(id);

        if(oldDetail == null){
            throw new EntityNotFoundException("Request Detail does not exist!");
        }

        oldDetail.setIsDeleted(true);

        return requestDetailRepository.save(oldDetail);
    }

    public RequestDetail getRequestDetailById(Integer id){ //lớp này để check xem có thông tin sẵn trong list ko để update hoặc delete. Mục đích tạo ra lớp này để sau này cần thì gọi ra cho dễ
        RequestDetail oldRequestDetail = requestDetailRepository.findRequestDetailByRequestDetailId(id);

        if(oldRequestDetail == null){
            throw new EntityNotFoundException("Request Detail does not exist!");
        }
        // if user.status == "BLOCK" => throw new EntityNotFoundException("Koi not found!");
        // nếu thông tin của user nào bị BLOCK thì quăng ra lỗi (throw new...) vì bị BLOCK rồi thì sẽ ko update hoặc delete được

        return oldRequestDetail;
    }
}

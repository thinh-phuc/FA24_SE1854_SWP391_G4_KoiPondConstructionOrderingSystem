package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.service;

import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.Request;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.repository.RequestRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequestService {
    @Autowired
    RequestRepository requestRepository;

    //create
    public Request create(Request request){
        Request newRequest = requestRepository.save(request);
        return newRequest;
    }

    //read
    public List<Request> getAllRequests(){
        List<Request> requests = requestRepository.findRequestsByIsDeletedFalse();
        return requests;
    }

    //update
    public Request update(Integer id, Request request){
        Request oldRequest = requestRepository.findRequestById(id);

        if(oldRequest == null){
            throw new EntityNotFoundException("Request not found!");
        }

        oldRequest.setAddress(request.getAddress());
        oldRequest.setNote(request.getNote());
        oldRequest.setIsActive(request.getIsActive());
        oldRequest.setStatus(request.getStatus());
        oldRequest.setDescription(request.getDescription());
        oldRequest.setCustomerId(request.getCustomerId());
        oldRequest.setIsDeleted(request.getIsDeleted());
        //set createDate, createBy, updateDate, updateBy

        return requestRepository.save(oldRequest);
    }

    //delete
    public Request delete(Integer id){
        Request oldRequest = getRequestById(id);

        if(oldRequest == null){
            throw new EntityNotFoundException("Request not found!");
        }

        oldRequest.setIsDeleted(true);
        return requestRepository.save(oldRequest);
    }

    //để check request nào bị block thì khi delete sẽ báo lỗi
    public Request getRequestById(Integer id){ //lớp này để check xem có thông tin sẵn trong list ko để update hoặc delete. Mục đích tạo ra lớp này để sau này cần thì gọi ra cho dễ
        Request oldRequest = requestRepository.findRequestById(id);

        if(oldRequest == null){
            throw new EntityNotFoundException("Request not found!");
        }
        // if user.status == "BLOCK" => throw new EntityNotFoundException("Koi not found!");
        // nếu thông tin của user nào bị BLOCK thì quăng ra lỗi (throw new...) vì bị BLOCK rồi thì sẽ ko update hoặc delete được

        return oldRequest;
    }
}

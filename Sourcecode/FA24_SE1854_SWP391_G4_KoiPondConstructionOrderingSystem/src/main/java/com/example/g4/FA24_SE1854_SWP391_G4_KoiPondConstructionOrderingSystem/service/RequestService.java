package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.service;

import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.Customer;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.Request;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.RequestDetail;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.exception.NotFoundException;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.model.RequestRequest;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.model.UpdateRequestRequest;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.repository.CustomerRepository;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.repository.RequestDetailRepository;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.repository.RequestRepository;
import jakarta.persistence.EntityNotFoundException;
import org.hibernate.usertype.internal.AbstractTimeZoneStorageCompositeUserType;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RequestService {
    @Autowired
    RequestRepository requestRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    RequestLogService requestLogService;

    //create
    public Request create(RequestRequest requestRequest){
        Customer user = authenticationService.getCurrentUser();
        requestRequest.setCustomerId(user.getCustomerId());


        Request request = new Request();
        request.setStatus("PENDING");
        request.setCreateDate(LocalDateTime.now());
        request.setDescription(requestRequest.getDescription());
        request.setAddress(requestRequest.getAddress());
        request.setNote(requestRequest.getNote());
        request.setCreateBy(user.getName());


        Customer customer = customerRepository.findCustomerByCustomerId(requestRequest.getCustomerId());
        request.setCustomer(customer);

        Request newRequest = requestRepository.save(request);

        requestLogService.createRequestLog("Request sent", "Please wait, we will contact you soon!", request);
        return newRequest;
    }

    //read
    public List<Request> getAllRequests(){
        List<Request> requests = requestRepository.findRequestsByIsActiveTrue();
        return requests;
    }

    //update
    public Request update(Integer id, UpdateRequestRequest updateRequestRequest){
        Request oldRequest = requestRepository.findRequestById(id);

        if(oldRequest == null){
            throw new EntityNotFoundException("Request not found!");
        }

        oldRequest.setStatus(updateRequestRequest.getStatus());
        oldRequest.setAddress(updateRequestRequest.getAddress());
        oldRequest.setDescription(updateRequestRequest.getDescription());
        oldRequest.setNote(updateRequestRequest.getNote());
        oldRequest.setUpdateDate(LocalDateTime.now());
        oldRequest.setUpdateBy(updateRequestRequest.getUpdateBy());

        return requestRepository.save(oldRequest);
    }

    //delete
    public Request delete(Integer id){
        Request oldRequest = getRequestById(id);

        if(oldRequest == null){
            throw new EntityNotFoundException("Request not found!");
        }

        oldRequest.setIsActive(false);
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
    public List<Request> getRequestsByCustomerIdDesc(Integer customerId) {
        Customer customer = customerRepository.findCustomerByCustomerId(customerId);
        List<Request> requests = requestRepository.findRequestsByCustomerAndIsActiveTrueOrderByCreateDateDesc(customer);

        if (requests.isEmpty()) {
            throw new EntityNotFoundException("No requests found for customer with ID: " + customerId);
        }

        return requests;
    }
}

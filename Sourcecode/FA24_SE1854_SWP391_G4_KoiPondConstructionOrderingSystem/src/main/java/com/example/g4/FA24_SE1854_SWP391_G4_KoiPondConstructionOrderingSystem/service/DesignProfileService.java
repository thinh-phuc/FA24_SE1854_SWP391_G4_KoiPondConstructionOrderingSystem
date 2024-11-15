package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.service;

import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.Customer;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.DesignProfile;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.Quotation;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.Request;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.exception.DuplicateException;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.exception.NotFoundException;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.model.*;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.repository.CustomerRepository;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.repository.DesignProfileRepository;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.repository.QuotationRepository;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.repository.RequestRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class DesignProfileService {
    @Autowired
    DesignProfileRepository designProfileRepository;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    AuthenticationService authenticationService;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    QuotationRepository quotationRepository;
    @Autowired
    QuotationService quotationService;
    @Autowired
    RequestLogService requestLogService;
    @Autowired
    RequestRepository requestRepository;

    public DesignProfile getDesignProfileById(Integer id) {
        DesignProfile oldDesignProfile = designProfileRepository.findDesignProfileByDesignProfileId(id);
        if (oldDesignProfile == null) {
            throw new NotFoundException("Not found");
        }
        return oldDesignProfile;
    }
    public DesignProfileResponse toCreateResponse(DesignProfile designProfile){
        DesignProfileResponse designProfileResponse = new DesignProfileResponse();
        designProfileResponse.setQuotationId(designProfile.getQuotation().getQuotationId());
        designProfileResponse.setDescription(designProfile.getDescription());
        designProfileResponse.setAddress(designProfile.getAddress());
        designProfileResponse.setCreateDate(designProfile.getCreateDate());
        designProfileResponse.setCreateBy(designProfile.getCreateBy());
        designProfileResponse.setIsActive(false);
        designProfileResponse.setConstructionStatus(designProfileResponse.getConstructionStatus());
        return designProfileResponse;
    }
    public UpdateDesignProfileResponse toUpdateResponse(DesignProfile designProfile) {
        UpdateDesignProfileResponse updateDesignProfileResponse = new UpdateDesignProfileResponse();
        updateDesignProfileResponse.setQuotationId(designProfile.getQuotation().getQuotationId());
        updateDesignProfileResponse.setDescription(designProfile.getDescription());
        updateDesignProfileResponse.setAddress(designProfile.getAddress());
        updateDesignProfileResponse.setUpdateDate(designProfile.getUpdateDate());
        updateDesignProfileResponse.setUpdateBy(designProfile.getUpdateBy());
        updateDesignProfileResponse.setIsActive(false);
        updateDesignProfileResponse.setConstructionStatus(designProfile.getContructionStatus());
        return updateDesignProfileResponse;
    }

//moi lam
    public DesignProfileResponse create(DesignProfileRequest designProfileRequest) {
        Quotation quotation = quotationRepository.findQuotationByQuotationId(designProfileRequest.getQuotationId());
        QuotationResponse quotationResponse = quotationService.toResponse(quotation);
        Request request = requestRepository.findRequestByQuotationId(quotation.getQuotationId());
        if(quotationResponse == null){
            throw new NotFoundException("Quotation not found");
        }
        DesignProfile designProfile = new DesignProfile();
        Customer staff = authenticationService.getCurrentUser();
        designProfile.setAddress(request.getAddress());
        designProfile.setCreateBy(staff.getName());
        designProfile.setCreateDate(LocalDateTime.now());
        designProfile.setIsActive(true);
        designProfile.setUpdateDate(null);
        designProfile.setUpdateBy(null);
        designProfile.setQuotation(quotation);
        designProfile.setDescription(designProfileRequest.getDescription());
        DesignProfile newDesignProfile = designProfileRepository.save(designProfile);

        requestLogService.createRequestLog("Design profile made", "Please check your profile to view detail!", quotation.getConsult().getRequestDetail().getRequest());
        return toCreateResponse(newDesignProfile);
    }
// moi lam
    public UpdateDesignProfileResponse update(Integer id, UpdateDesignProfileRequest designProfile) {
        DesignProfile oldDesignProfile = getDesignProfileById(id);
        Customer staff = authenticationService.getCurrentUser();
        oldDesignProfile.setAddress(designProfile.getAddress());
        oldDesignProfile.setDescription(designProfile.getDescription());
        oldDesignProfile.setUpdateDate(LocalDateTime.now());
        oldDesignProfile.setUpdateBy(staff.getName());

        DesignProfile updateDesignProfile = designProfileRepository.save(oldDesignProfile);
        return toUpdateResponse(updateDesignProfile);
    }

    public DesignProfile delete(Integer id) {
        DesignProfile designProfile = designProfileRepository.findDesignProfileByDesignProfileId(id);
        //designProfile.setIsDelete(true);
        designProfile.setIsActive(false);
        return designProfileRepository.save(designProfile);
    }

    public List<GetAllDesignProfile> getAll() {
        List<DesignProfile> designProfiles = designProfileRepository.findDesignProfilesByIsActiveTrue();
        List<GetAllDesignProfile> list = new ArrayList<>();

        for(DesignProfile dp : designProfiles){
            GetAllDesignProfile designProfile = new GetAllDesignProfile();
            designProfile.setDesignProfileId(dp.getDesignProfileId());
            designProfile.setQuotationId(dp.getQuotation().getQuotationId());
            designProfile.setAddress(dp.getAddress());
            designProfile.setDescription(dp.getDescription());
            designProfile.setConstructionStatus(dp.getContructionStatus());
            designProfile.setCreateDate(dp.getCreateDate());
            designProfile.setCreateBy(dp.getCreateBy());
            designProfile.setUpdateDate(dp.getUpdateDate());
            designProfile.setUpdateBy(dp.getUpdateBy());
            designProfile.setIsActive(dp.getIsActive());
            list.add(designProfile);
        }

        return list;
    }

    //get design profile by staff (used by both CONSTRUCTOR AND DESIGNER)
    public List<GetAllDesignProfile> getDesignProfilesByDesignStaff() {
        Customer staff = authenticationService.getCurrentUser();
        List<DesignProfile> designProfiles = designProfileRepository.findDesignProfilesByStaff(staff.getCustomerId());
//        if (designProfiles == null) {
//            throw new NotFoundException("No design profiles found!");
//        }
        List<GetAllDesignProfile> list = new ArrayList<>();
        for (DesignProfile dp : designProfiles) {
            GetAllDesignProfile designProfile = new GetAllDesignProfile();
            designProfile.setDesignProfileId(dp.getDesignProfileId());
            designProfile.setQuotationId(dp.getQuotation().getQuotationId());
            designProfile.setAddress(dp.getAddress());
            designProfile.setDescription(dp.getDescription());
            designProfile.setConstructionStatus(dp.getContructionStatus());
            designProfile.setCreateDate(dp.getCreateDate());
            designProfile.setCreateBy(dp.getCreateBy());
            designProfile.setUpdateDate(dp.getUpdateDate());
            designProfile.setUpdateBy(dp.getUpdateBy());
            designProfile.setIsActive(dp.getIsActive());
            list.add(designProfile);

        }
        return list;
    }


    public List<DesignProfile> getDesignProfilesByStaff() {
        Customer staff = authenticationService.getCurrentUser();
        List<DesignProfile> designProfiles = designProfileRepository.findDesignProfilesByStaff(staff.getCustomerId());
        if (designProfiles == null) {
            throw new NotFoundException("No design profiles found!");
        }
        return designProfiles;
    }

    public List<DesignProfile> getDesignProfilesByStaffAndAddress(String address){
        Customer staff = authenticationService.getCurrentUser();
        List<DesignProfile> designProfiles = designProfileRepository.findDesignProfilesByStaffAndAddress(staff.getCustomerId(), address);
        if (designProfiles == null) {
            throw new NotFoundException("No design profiles found!");
        }
        return designProfiles;
    }

    public List<DesignProfile> getDesignProfilesByCustomer(){
        Customer customer = authenticationService.getCurrentUser();
        List<DesignProfile> designProfiles = designProfileRepository.findDesignProfilesByCustomer(customer.getCustomerId());
        if (designProfiles == null) {
            throw new NotFoundException("No design profiles found!");
        }
        return designProfiles;
    }

//moi lam
    public DesignProfile assignCustomersToDesignProfile(Integer designProfileId, List<Integer> staffIds) {
        DesignProfile designProfile = designProfileRepository.findDesignProfileByDesignProfileIdAndIsActiveTrue(designProfileId);
        if(designProfile == null){
            throw new NotFoundException("DesignProfile not found");
        }
        List<Customer> existStaffs = designProfile.getCustomers();
        List<Customer> newStaffs = new ArrayList<>();
        for(Integer staffId : staffIds){
                boolean IsExist = false;
                for(Customer staff : existStaffs){
                    if(staff.getCustomerId().equals(staffId)){
                        IsExist = true;

                    }
                }
                if(!IsExist){
                    Customer staff = customerRepository.findCustomerByCustomerId(staffId);
                    if(staff!=null){
                        String role = staff.getRole();
                        if(role.equalsIgnoreCase("DESIGNER") || role.equalsIgnoreCase("CONSTRUCTOR") ){
                            newStaffs.add(staff);
                        }else {
                            throw new IllegalArgumentException("Staff with id "+staffId+" not valid");
                        }
                    }else{
                        throw new NotFoundException("Staff not found");
                    }
                }else{
                    throw new DuplicateException("Staff with id "+ staffId + " is exist");
                }
                existStaffs.addAll(newStaffs);
                designProfile.setCustomers(existStaffs);


        }
        return designProfileRepository.save(designProfile);

//        List<Customer> customers = new ArrayList<>();
//        for (Integer customerId : customerIds) {
//            Customer customer = customerRepository.findCustomerByCustomerId(customerId);
//            if (customer != null) {
//                customers.add(customer);
//            } else {
//                throw new NotFoundException("Customer not found");
//            }
//        }
//        designProfile.setCustomers(customers);
//
//        return designProfileRepository.save(designProfile);
    }

}

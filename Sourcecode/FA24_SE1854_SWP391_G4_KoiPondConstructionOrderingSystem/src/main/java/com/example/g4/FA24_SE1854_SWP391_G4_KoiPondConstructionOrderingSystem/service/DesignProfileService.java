package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.service;

import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.Customer;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.DesignProfile;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.Quotation;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.exception.DuplicateException;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.exception.NotFoundException;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.model.DesignProfileRequest;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.model.QuotationResponse;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.repository.CustomerRepository;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.repository.DesignProfileRepository;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.repository.QuotationRepository;
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

    public DesignProfile getDesignProfileById(Integer id) {
        DesignProfile oldDesignProfile = designProfileRepository.findDesignProfileByDesignProfileId(id);
        if (oldDesignProfile == null) {
            throw new NotFoundException("Not found");
        }
        return oldDesignProfile;
    }

//moi lam
    public DesignProfile create(DesignProfileRequest designProfileRequest) {
        Quotation quotation = quotationRepository.findQuotationByQuotationId(designProfileRequest.getQuotationId());
        QuotationResponse quotationResponse = quotationService.toResponse(quotation);
        if(quotationResponse == null){
            throw new NotFoundException("Quotation not found");
        }
        DesignProfile designProfile = new DesignProfile();
        Customer staff = authenticationService.getCurrentUser();
        designProfile.setAddress(designProfileRequest.getAddress());
        designProfile.setCreateBy(staff.getName());
        designProfile.setCreateDate(LocalDateTime.now());
        designProfile.setIsActive(true);
        designProfile.setUpdateDate(null);
        designProfile.setUpdateBy(null);
        designProfile.setQuotation(quotation);

        DesignProfile newDesignProfile = designProfileRepository.save(designProfile);
        return newDesignProfile;
    }
// moi lam
    public DesignProfile update(Integer id, DesignProfileRequest designProfile) {
        DesignProfile oldDesignProfile = getDesignProfileById(id);
        Customer staff = authenticationService.getCurrentUser();
        oldDesignProfile.setAddress(designProfile.getAddress());
        oldDesignProfile.setDescription(designProfile.getDescription());
        oldDesignProfile.setUpdateDate(LocalDateTime.now());
        oldDesignProfile.setUpdateBy(staff.getName());
        return designProfileRepository.save(oldDesignProfile);
    }

    public DesignProfile delete(Integer id) {
        DesignProfile designProfile = designProfileRepository.findDesignProfileByDesignProfileId(id);
        //designProfile.setIsDelete(true);
        designProfile.setIsActive(false);
        return designProfileRepository.save(designProfile);
    }

    public List<DesignProfile> getAll() {
        List<DesignProfile> designProfiles = designProfileRepository.findDesignProfilesByIsActiveTrue();
        return designProfiles;
    }

    //get design profile by staff (used by both CONSTRUCTOR AND DESIGNER)
    public List<DesignProfile> getDesignProfilesByStaff() {
        Customer staff = authenticationService.getCurrentUser();
        List<DesignProfile> designProfiles = designProfileRepository.findDesignProfilesByStaff(staff.getCustomerId());
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
                        if(role.equalsIgnoreCase("Design Staff") || role.equalsIgnoreCase("Construction Staff") ){
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

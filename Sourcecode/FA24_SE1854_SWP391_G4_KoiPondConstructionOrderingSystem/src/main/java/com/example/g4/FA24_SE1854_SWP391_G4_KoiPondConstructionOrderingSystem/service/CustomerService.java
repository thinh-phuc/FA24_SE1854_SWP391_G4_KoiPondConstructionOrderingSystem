package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.service;

import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.Customer;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.exception.NotFoundException;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.model.CustomerResponse;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.model.GetStaffByRoleResponse;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.model.UpdateProfileRequest;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    AuthenticationService authenticationService;

    public List<GetStaffByRoleResponse> response(Customer customer){
        List<GetStaffByRoleResponse> staffs = new ArrayList<>();
        GetStaffByRoleResponse staff = new GetStaffByRoleResponse();
        staff.setCustomerId(customer.getCustomerId());
        staff.setName(customer.getName());
        staff.setRole(customer.getRole());
        staff.setEmail(customer.getEmail());
        staff.setPhoneNumber(customer.getPhoneNumber());
        staffs.add(staff);
        return  staffs;
    }

    public List<GetStaffByRoleResponse> getStaffsByRole(String role){
        List<Customer> staffs = customerRepository.findCustomersByRole(role);
        List<GetStaffByRoleResponse> list = new ArrayList<>();
        for(Customer customer : staffs){
            GetStaffByRoleResponse getStaffByRoleResponse = new GetStaffByRoleResponse();
            getStaffByRoleResponse.setCustomerId(customer.getCustomerId());
            getStaffByRoleResponse.setName(customer.getName());
            getStaffByRoleResponse.setRole(customer.getRole());
            getStaffByRoleResponse.setPhoneNumber(customer.getPhoneNumber());
            getStaffByRoleResponse.setEmail(customer.getEmail());
            list.add(getStaffByRoleResponse);
        }
        return  list;
    }

    public Customer updateProfile(UpdateProfileRequest updateProfileRequest){
        Customer current = authenticationService.getCurrentUser();
        if(current == null){
            throw new NotFoundException("Please log in first!");
        }
        current.setName(updateProfileRequest.getName());
        current.setEmail(updateProfileRequest.getEmail());
        current.setPhoneNumber(updateProfileRequest.getPhoneNumber());
        return customerRepository.save(current);
    }
    public void deleteCustomerById(Integer customerId){
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new NotFoundException("Customer with ID " + customerId + " not found."));
        customerRepository.delete(customer);
    }
    public Customer updateProfile(Integer id, UpdateProfileRequest updateProfileRequest) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Customer with ID " + id + " not found."));
        customer.setName(updateProfileRequest.getName());
        customer.setEmail(updateProfileRequest.getEmail());
        customer.setPhoneNumber(updateProfileRequest.getPhoneNumber());
        return customerRepository.save(customer);
    }
}

package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.service;

import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.Customer;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.exception.NotFoundException;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.model.GetStaffByRoleResponse;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    public List<GetStaffByRoleResponse> response(Customer customer){
        List<GetStaffByRoleResponse> staffs = new ArrayList<>();
        GetStaffByRoleResponse staff = new GetStaffByRoleResponse();
        staff.setCustomerId(customer.getCustomerId());
        staff.setName(customer.getName());
        staff.setRole(customer.getRole());
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
            list.add(getStaffByRoleResponse);
        }
        return  list;
    }
}

package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.service;

import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.Customer;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.exception.NotFoundException;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.model.CustomerResponse;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.model.LoginRequest;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.model.RegisterRequest;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.repository.CustomerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements UserDetailsService {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;

    public CustomerResponse register(RegisterRequest registerRequest) {
        Customer customer = modelMapper.map(registerRequest, Customer.class);
        try {
            customer.setPassword(passwordEncoder.encode(customer.getPassword()));
            Customer newCustomer = customerRepository.save(customer);
            return modelMapper.map(newCustomer, CustomerResponse.class);
        } catch (Exception e) {
            if (e.getMessage().contains(customer.getName())) {
                throw new NotFoundException("Invalid name!");
            } else if (e.getMessage().contains(customer.getEmail())) {
                throw new NotFoundException("Invalid email!");
            } else if (e.getMessage().contains(customer.getPassword())) {
                throw new NotFoundException("Invalid password!!");
            } else if (e.getMessage().contains(customer.getPhoneNumber())) {
                throw new NotFoundException("Invalid phone number!");
            } else {
                e.printStackTrace();
                throw new NotFoundException("Something is wrong!");
            }
        }
    }

    public CustomerResponse login(LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(new
                    UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
            Customer customer = (Customer) authentication.getPrincipal();
            return modelMapper.map(customer, CustomerResponse.class);
        } catch (Exception e) {
            throw new NotFoundException("Email or password is invalid!");
        }

    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return customerRepository.findCustomerByEmail(email);
    }
}

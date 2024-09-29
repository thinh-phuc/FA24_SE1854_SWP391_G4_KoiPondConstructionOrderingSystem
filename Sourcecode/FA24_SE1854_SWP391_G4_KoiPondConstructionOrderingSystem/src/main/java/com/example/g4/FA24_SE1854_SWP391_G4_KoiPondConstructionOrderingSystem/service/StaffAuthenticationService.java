package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.service;

import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.Staff;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.exception.DuplicateEntity;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.model.StaffLoginRequest;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.model.StaffRegisterRequest;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.model.StaffResponse;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.repository.StaffRepository;
import jakarta.persistence.EntityNotFoundException;
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

import java.util.List;

@Service
public class StaffAuthenticationService implements UserDetailsService {
    @Autowired
    StaffRepository staffRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    PasswordEncoder passwordEncoder;



    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    TokenService tokenService;


    public StaffResponse register(StaffRegisterRequest staffRegisterRequest) {
        Staff staff = modelMapper.map(staffRegisterRequest, Staff.class);
        try {
            String originPassword = staff.getPassword();
            staff.setPassword(passwordEncoder.encode(originPassword));
            Staff newAccount = staffRepository.save(staff);
            return modelMapper.map(newAccount, StaffResponse.class);
        } catch (Exception e) {


            if (e.getMessage().contains(staff.getEmail())) {
                throw new DuplicateEntity("Duplicate email");

            } else {
                throw new DuplicateEntity("Duplicated phone");

            }
        }
    }


    public List<Staff> getAll(){
        List<Staff> staff = staffRepository.findAll();
        return  staff;
    }

    public StaffResponse login(StaffLoginRequest staffLoginRequest){
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    staffLoginRequest.getUsername(),
                    staffLoginRequest.getPassword()

            ));
            Staff staff = (Staff) authentication.getPrincipal();
            StaffResponse staffResponse = modelMapper.map(staff, StaffResponse.class);
            staffResponse.setToken(tokenService.generateToken(staff));
            return staffResponse;
        }catch (Exception e){
            throw new EntityNotFoundException("Username or password invalid!!");
        }

    }

    @Override
    public UserDetails loadUserByUsername(String phone) throws UsernameNotFoundException {
        return staffRepository.findStaffByPhoneNumber(phone);
    }
}


package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.service;

import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.Customer;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.Design;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.DesignProfile;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.exception.NotFoundException;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.model.DesignRequest;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.repository.DesignProfileRepository;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.repository.DesignRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DesignService {
    @Autowired
    DesignRepository designRepository;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    AuthenticationService authenticationService;
    @Autowired
    DesignProfileRepository designProfileRepository;


    public Design getDesignById(Integer id){
        Design design = designRepository.findDesignByDesignId(id);
        if(design == null){
            throw new NotFoundException("Not found");
        }
        return design;
    }
    // moi sua lai
    public Design create(DesignRequest designRequest){
        DesignProfile designProfile = designProfileRepository.findDesignProfileByDesignProfileId(designRequest.getDesignProfileId());
        if(designProfile == null){
            throw new NotFoundException("Can not found design profile with id: "+ designRequest.getDesignId());
        }
        Design design = new Design();
        design.setDesign(designRequest.getDesign());
        design.setDesignStatus("PROCESSING");
        design.setDesignProfile(designProfile);
        Customer manager = authenticationService.getCurrentUser();
        design.setCreateBy(manager.getName());
        design.setCreateDate(LocalDateTime.now());
        design.setDescription(designRequest.getDescription());
        design.setIsActive(true);
        design.setUpdateDate(null);
        design.setUpdateBy(null);
        Design newDesign = designRepository.save(design);
        return newDesign;
    }
    public List<Design> getAll(){
        List<Design> designs = designRepository.findDesignsByIsActiveTrue();
        return designs;
    }
    public Design update(Integer id, Design design){
        Design oldDesign = getDesignById(id);

        oldDesign.setDesign(design.getDesign());
        oldDesign.setDesignStatus(design.getDesignStatus());
        Customer staff = authenticationService.getCurrentUser();
        oldDesign.setUpdateBy(staff.getName());
        oldDesign.setUpdateDate(LocalDateTime.now());
        return designRepository.save(oldDesign);
    }
    public Design delete(Integer id){
        Design design = getDesignById(id);

        design.setIsActive(false);
        return designRepository.save(design);
    }
    // moi lam
    public Design finishDesign(Integer id){
        Design design = getDesignById(id);
        design.setDesignStatus("Complete");
        design.setDescription("Design is completed");
        return designRepository.save(design);
    }
}

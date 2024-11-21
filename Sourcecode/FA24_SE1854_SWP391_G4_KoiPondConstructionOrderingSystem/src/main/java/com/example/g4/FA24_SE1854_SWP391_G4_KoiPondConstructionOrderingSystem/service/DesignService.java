package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.service;

import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.Customer;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.Design;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.DesignProfile;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.Request;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.exception.NotFoundException;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.model.*;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.repository.DesignProfileRepository;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.repository.DesignRepository;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.repository.RequestRepository;
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
    @Autowired
    RequestLogService requestLogService;
    @Autowired
    RequestRepository requestRepository;

    public Design getDesignById(Integer id){
        Design design = designRepository.findDesignByDesignId(id);
        if(design == null){
            throw new NotFoundException("Not found");
        }
        return design;
    }

    public DesignResponse toResponse(Design design){
        DesignResponse designResponse = new DesignResponse();
        designResponse.setDesign(design.getDesign());
        designResponse.setDesignId(design.getDesignId());
        designResponse.setDesignStatus(design.getDesignStatus());
        designResponse.setDescription(design.getDescription());
        designResponse.setDesignProfileId(design.getDesignProfile().getDesignProfileId());
        designResponse.setIsActive(true);
        designResponse.setCreateBy(design.getCreateBy());
        designResponse.setCreateDate(design.getCreateDate());
        return designResponse;
    }

    public UpdateDesignResponse toUpdateResponse(Design design){
        UpdateDesignResponse designResponse = new UpdateDesignResponse();
        designResponse.setDesign(design.getDesign());
        designResponse.setDesignId(design.getDesignId());
        designResponse.setDesignStatus(design.getDesignStatus());
        designResponse.setDescription(design.getDescription());
        designResponse.setDesignProfileId(design.getDesignProfile().getDesignProfileId());
        designResponse.setIsActive(true);
        designResponse.setUpdateBy(design.getUpdateBy());
        designResponse.setUpdateDate(design.getUpdateDate());
        return designResponse;
    }
    // moi sua lai
    public DesignResponse create(DesignRequest designRequest){
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
        requestLogService.createRequestLog("Design made", "Please check your profile to view design detail!", designProfile.getQuotation().getConsult().getRequestDetail().getRequest());
        Request request = designProfile.getQuotation().getConsult().getRequestDetail().getRequest();
        request.setStatus("Designed");
        requestRepository.save(request);
        return toResponse(newDesign);
    }
    public List<Design> getAll(){
        List<Design> designs = designRepository.findDesignsByIsActiveTrue();
        return designs;
    }
    public UpdateDesignResponse update(Integer id, UpdateDesignRequest updateDesignRequest){
        Design oldDesign = getDesignById(id);

        oldDesign.setDesign(updateDesignRequest.getDesign());

        Customer staff = authenticationService.getCurrentUser();
        oldDesign.setUpdateBy(staff.getName());
        oldDesign.setUpdateDate(LocalDateTime.now());
        Design design = designRepository.save(oldDesign);
        requestLogService.createRequestLog("Design updated", "Please check your profile for detail!", oldDesign.getDesignProfile().getQuotation().getConsult().getRequestDetail().getRequest());
        return toUpdateResponse(design);
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
    public List<Design> getDesignByProfileId(Integer designProfileId){
        DesignProfile designProfile = designProfileRepository.findDesignProfileByDesignProfileIdAndIsActiveTrue(designProfileId);
        List<Design> designList = designRepository.findDesignsByDesignProfileAndIsActiveTrue(designProfile);
        if(designProfile == null){
            throw new NotFoundException("Not found");
        }
        return designList;
    }
}

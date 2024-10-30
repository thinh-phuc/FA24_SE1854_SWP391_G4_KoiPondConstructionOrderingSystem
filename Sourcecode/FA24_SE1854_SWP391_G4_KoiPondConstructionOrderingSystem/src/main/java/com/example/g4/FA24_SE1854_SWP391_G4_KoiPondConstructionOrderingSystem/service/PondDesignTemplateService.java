package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.service;

import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.Customer;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.PondDesignTemplate;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.Request;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.model.PondDesignTemplateRequest;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.repository.PondDesignTemplateRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PondDesignTemplateService {

    @Autowired
    PondDesignTemplateRepository pondDesignTemplateRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    AuthenticationService authenticationService;

    //create
    public PondDesignTemplate createTemplate(PondDesignTemplateRequest pondDesignTemplateRequest){
        PondDesignTemplate pondDesignTemplate = modelMapper.map(pondDesignTemplateRequest, PondDesignTemplate.class);

        PondDesignTemplate newPond = pondDesignTemplateRepository.save(pondDesignTemplate);
        return newPond;
    }

    //read
    public List<PondDesignTemplate> getAllTemplates(){
        List<PondDesignTemplate> templates = pondDesignTemplateRepository.findPondDesignTemplatesByIsActiveTrue();
        return templates;
    }

    //update
    public PondDesignTemplate updateTemplate(Integer id, PondDesignTemplate pondDesignTemplate){
        PondDesignTemplate oldPondDesignTemplate = pondDesignTemplateRepository.findPondDesignTemplateByPondDesignTemplateId(id);

        if(oldPondDesignTemplate == null){
            throw new EntityNotFoundException("Pond Design Template does not exist!");
        }

        oldPondDesignTemplate.setMinSize(pondDesignTemplate.getMinSize());
        oldPondDesignTemplate.setMaxSize(pondDesignTemplate.getMaxSize());
        oldPondDesignTemplate.setWaterVolume(pondDesignTemplate.getWaterVolume());
        oldPondDesignTemplate.setMinDepth(pondDesignTemplate.getMinDepth());
        oldPondDesignTemplate.setMaxDepth(pondDesignTemplate.getMaxDepth());
        oldPondDesignTemplate.setShape(pondDesignTemplate.getShape());
        oldPondDesignTemplate.setFiltrationSystem(pondDesignTemplate.getFiltrationSystem());
        oldPondDesignTemplate.setPhLevel(pondDesignTemplate.getPhLevel());
        oldPondDesignTemplate.setWaterTemperature(pondDesignTemplate.getWaterTemperature());
        oldPondDesignTemplate.setPondLiner(pondDesignTemplate.getPondLiner());
        oldPondDesignTemplate.setPondBottom(pondDesignTemplate.getPondBottom());
        oldPondDesignTemplate.setDecoration(pondDesignTemplate.getDecoration());
        oldPondDesignTemplate.setMinEstimatedCost(pondDesignTemplate.getMinEstimatedCost());
        oldPondDesignTemplate.setMaxEstimatedCost(pondDesignTemplate.getMaxEstimatedCost());
        oldPondDesignTemplate.setImageUrl(pondDesignTemplate.getImageUrl());
        oldPondDesignTemplate.setDescription(pondDesignTemplate.getDescription());
        oldPondDesignTemplate.setNote(pondDesignTemplate.getNote());
        //oldPondDesignTemplate.setIsActive(pondDesignTemplate.getIsActive());
        //oldPondDesignTemplate.setIsDeleted(pondDesignTemplate.getIsDeleted());
        oldPondDesignTemplate.setUpdateDate(LocalDateTime.now());
        oldPondDesignTemplate.setCreateBy(pondDesignTemplate.getCreateBy());
        oldPondDesignTemplate.setUpdateBy(pondDesignTemplate.getUpdateBy());

        return pondDesignTemplateRepository.save(oldPondDesignTemplate);
    }

    //delete
    public PondDesignTemplate delete(Integer id){
        PondDesignTemplate oldPondDesignTemplate = getPondDesignTemplateById(id);
        if(oldPondDesignTemplate == null){
            throw new EntityNotFoundException("Template does not exist!");
        }

        oldPondDesignTemplate.setIsActive(false);

        return pondDesignTemplateRepository.save(oldPondDesignTemplate);
    }


    public PondDesignTemplate getPondDesignTemplateById(Integer id){ //lớp này để check xem có thông tin sẵn trong list ko để update hoặc delete. Mục đích tạo ra lớp này để sau này cần thì gọi ra cho dễ
        PondDesignTemplate oldPondDesignTemplate = pondDesignTemplateRepository.findPondDesignTemplateByPondDesignTemplateId(id);

        if(oldPondDesignTemplate == null){
            throw new EntityNotFoundException("Pond Design Template does not exist!");
        }
        // if user.status == "BLOCK" => throw new EntityNotFoundException("Koi not found!");
        // nếu thông tin của user nào bị BLOCK thì quăng ra lỗi (throw new...) vì bị BLOCK rồi thì sẽ ko update hoặc delete được

        return oldPondDesignTemplate;
    }
}

package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.service;

import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.Design;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.exception.NotFoundException;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.model.DesignRequest;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.repository.DesignRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DesignService {
    @Autowired
    DesignRepository designRepository;
    @Autowired
    ModelMapper modelMapper;


    public Design getDesignById(Integer id){
        Design design = designRepository.findDesignByDesignId(id);
        if(design == null){
            throw new NotFoundException("Not found");
        }
        return design;
    }

    public Design create(DesignRequest designRequest){
        Design design = modelMapper.map(designRequest,Design.class);
        Design newDesign = designRepository.save(design);
        return newDesign;
    }
    public List<Design> getAll(){
        List<Design> designs = designRepository.findDesignsByIsDeleteFalse();
        return designs;
    }
    public Design update(Integer id, Design design){
        Design oldDesign = getDesignById(id);

        oldDesign.setDesign(design.getDesign());
        oldDesign.setDesignStatus(design.getDesignStatus());
       // oldDesign.setDesignProfileId(design.getDesignProfileId());
       // oldDesign.setIsDelete(design.getIsDelete());

        return designRepository.save(oldDesign);
    }
    public Design delete(Integer id){
        Design design = getDesignById(id);
        design.setIsDelete(true);
        return designRepository.save(design);
    }
}

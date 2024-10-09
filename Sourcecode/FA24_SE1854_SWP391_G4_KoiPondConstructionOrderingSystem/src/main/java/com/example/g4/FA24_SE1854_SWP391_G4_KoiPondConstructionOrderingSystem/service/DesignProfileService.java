package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.service;

import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.Customer;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.DesignProfile;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.exception.NotFoundException;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.model.DesignProfileRequest;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.repository.DesignProfileRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DesignProfileService {
    @Autowired
    DesignProfileRepository designProfileRepository;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    AuthenticationService authenticationService;

    public DesignProfile getDesignProfileById(Integer id) {
        DesignProfile oldDesignProfile = designProfileRepository.findDesignProfileByDesignProfileId(id);
        if (oldDesignProfile == null) {
            throw new NotFoundException("Not found");
        }
        return oldDesignProfile;
    }

    public DesignProfile create(DesignProfileRequest designProfileRequest) {
        DesignProfile designProfile = modelMapper.map(designProfileRequest, DesignProfile.class);
        DesignProfile newDesignProfile = designProfileRepository.save(designProfile);
        return newDesignProfile;
    }

    public DesignProfile update(Integer id, DesignProfile designProfile) {
        DesignProfile oldDesignProfile = getDesignProfileById(id);
//            oldDesignProfile.setConsultId(designProfile.getConsultId());
//            oldDesignProfile.setQuotationId(designProfile.getQuotationId());
        //oldDesignProfile.setCustomerId();
//            oldDesignProfile.setDesignerId(designProfile.getDesignerId());
//            oldDesignProfile.setConstructorId(designProfile.getConstructorId());
        oldDesignProfile.setAddress(designProfile.getAddress());
        oldDesignProfile.setContructionStatus(designProfile.getContructionStatus());
        oldDesignProfile.setDescription(designProfile.getDescription());
        oldDesignProfile.setIsActive(designProfile.getIsActive());
        oldDesignProfile.setUpdateDate(designProfile.getUpdateDate());
        oldDesignProfile.setUpdateBy(designProfile.getUpdateBy());
        oldDesignProfile.setCreateBy(designProfile.getCreateBy());
        oldDesignProfile.setCreateDate(designProfile.getCreateDate());

        return designProfileRepository.save(oldDesignProfile);
    }

    public DesignProfile delete(Integer id) {
        DesignProfile designProfile = designProfileRepository.findDesignProfileByDesignProfileId(id);
        designProfile.setIsDelete(true);
        return designProfileRepository.save(designProfile);
    }

    public List<DesignProfile> getAll() {
        List<DesignProfile> designProfiles = designProfileRepository.findDesignProfilesByIsDeleteFalse();
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

}

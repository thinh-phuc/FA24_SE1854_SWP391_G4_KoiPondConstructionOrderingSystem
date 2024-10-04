package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.service;

import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.ConstructionHistory;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.DesignProfile;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.exception.NotFoundException;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.model.ConstructionRequest;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.model.UpdateConstructionRequest;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.repository.ConstructionHistoryRepository;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.repository.DesignProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ConstructionHistoryService {
    @Autowired
    ConstructionHistoryRepository constructionHistoryRepository;
    @Autowired
    DesignProfileRepository designProfileRepository;

    public ConstructionHistory createConstructionHistory(ConstructionRequest constructionRequest) {
        try {
            ConstructionHistory constructionHistory = new ConstructionHistory();
            constructionHistory.setStartDate(LocalDateTime.now());
            constructionHistory.setStep(constructionRequest.getStep());
            constructionHistory.setDescription(constructionRequest.getDescription());
            DesignProfile designProfile = designProfileRepository.findDesignProfileByDesignProfileId(constructionRequest.getDesignProfileId());
            constructionHistory.setDesignProfile(designProfile);
            ConstructionHistory newConstructionHistory = constructionHistoryRepository.save(constructionHistory);
            return newConstructionHistory;
        } catch (Exception e) {
            throw new NotFoundException("Something is wrong!");
        }
    }

    public ConstructionHistory updateConstructionHistory(UpdateConstructionRequest constructionRequest, Integer id) {
        try {
            ConstructionHistory old = constructionHistoryRepository.findConstructionHistoryByConstructionHistoryId(id);
            if (old == null) {
                throw new NotFoundException("Not found!");
            }
            old.setStep(constructionRequest.getStep());
            old.setDescription(constructionRequest.getDescription());
            old.setNote(constructionRequest.getNote());
            return constructionHistoryRepository.save(old);
        } catch (Exception e) {
            throw new NotFoundException("Something is wrong!");
        }
    }

    public List<ConstructionHistory> getAllConstructions() {
        List<ConstructionHistory> constructionHistories = constructionHistoryRepository.findConstructionHistorysByIsActiveTrue();
        return constructionHistories;
    }
}

package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.service;

import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.AcceptanceDocument;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.ConstructionHistory;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.Customer;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.DesignProfile;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.exception.NotFoundException;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.model.AcceptanceRequest;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.model.ConstructionRequest;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.model.UpdateConstructionRequest;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.repository.AcceptanceDocumentRepository;
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
    @Autowired
    AuthenticationService authenticationService;
    @Autowired
    AcceptanceDocumentRepository acceptanceDocumentRepository;

    public ConstructionHistory createConstructionHistory(ConstructionRequest constructionRequest) {
        try {
            ConstructionHistory constructionHistory = new ConstructionHistory();
            constructionHistory.setStartDate(LocalDateTime.now());
            constructionHistory.setStep(constructionRequest.getStep());
            constructionHistory.setDescription(constructionRequest.getDescription());
            DesignProfile designProfile = designProfileRepository.findDesignProfileByDesignProfileId(constructionRequest.getDesignProfileId());
            designProfile.setContructionStatus("PROGRESSING");
            designProfileRepository.save(designProfile);
            Customer staff = authenticationService.getCurrentUser();
            constructionHistory.setCreateBy(staff.getName());
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
            Customer staff = authenticationService.getCurrentUser();
            old.setUpdateBy(staff.getName());
            return constructionHistoryRepository.save(old);
        } catch (Exception e) {
            throw new NotFoundException("Something is wrong!");
        }
    }

    public ConstructionHistory finishConstruction(Integer id) {
        try {
            ConstructionHistory old = constructionHistoryRepository.findConstructionHistoryByConstructionHistoryId(id);
            if (old == null) {
                throw new NotFoundException("Not found!");
            }
            old.setStep("FINAL");
            old.setDescription("The construction has finished.");
            old.setEndDate(LocalDateTime.now());
            Customer staff = authenticationService.getCurrentUser();
            old.setUpdateBy(staff.getName());
            return constructionHistoryRepository.save(old);
        } catch (Exception e) {
            throw new NotFoundException("Something is wrong!");
        }
    }

    public List<ConstructionHistory> getAllConstructions() {
        List<ConstructionHistory> constructionHistories = constructionHistoryRepository.findConstructionHistorysByIsActiveTrue();
        return constructionHistories;
    }

    public List<AcceptanceDocument> getAllAcceptanceDocuments() {
        List<AcceptanceDocument> acceptanceDocuments = acceptanceDocumentRepository.findAcceptanceDocumentsByIsActiveTrue();
        return acceptanceDocuments;
    }

    public AcceptanceDocument createAcceptanceDocument(AcceptanceRequest acceptanceRequest) {
        try {
            AcceptanceDocument acceptanceDocument = new AcceptanceDocument();
            acceptanceDocument.setConfirm(true);
            acceptanceDocument.setDescription(acceptanceRequest.getDescription());
            acceptanceDocument.setConfirmDate(LocalDateTime.now());
            acceptanceDocument.setConfirmConstructorName(acceptanceRequest.getConfirmConstructorName());
            acceptanceDocument.setConfirmCustomerName(acceptanceRequest.getConfirmCustomerName());
            acceptanceDocument.setCreateDate(LocalDateTime.now());
            Customer staff = authenticationService.getCurrentUser();
            acceptanceDocument.setCreateBy(staff.getName());
            DesignProfile designProfile = designProfileRepository.findDesignProfileByDesignProfileId(acceptanceRequest.getDesignProfileId());
            acceptanceDocument.setDesignProfile(designProfile);
            AcceptanceDocument newAcceptanceDocument = acceptanceDocumentRepository.save(acceptanceDocument);
            return newAcceptanceDocument;
        } catch (Exception e) {
            throw new NotFoundException("Something is wrong!");
        }
    }

    public ConstructionHistory deleteConstructionHistory(Integer id) {
        ConstructionHistory constructionHistory = constructionHistoryRepository.findConstructionHistoryByConstructionHistoryId(id);
        if (constructionHistory == null) {
            throw new NotFoundException("Not found!");
        }
        constructionHistory.setIsActive(false);
        return constructionHistoryRepository.save(constructionHistory);
    }

    public AcceptanceDocument deleteAcceptanceDocument(Integer id){
        AcceptanceDocument acceptanceDocument=acceptanceDocumentRepository.findAcceptanceDocumentByAcceptanceDocumentId(id);
        if(acceptanceDocument==null){
            throw new NotFoundException("Not found!");
        }
        acceptanceDocument.setIsActive(false);
        return acceptanceDocumentRepository.save(acceptanceDocument);
    }

    public ConstructionHistory getConstructionHistoryById(Integer id){
        ConstructionHistory constructionHistory=constructionHistoryRepository.findConstructionHistoryByConstructionHistoryId(id);
        if(constructionHistory==null){
            throw new NotFoundException("Not found!");
        }
        return constructionHistory;
    }

    public AcceptanceDocument getAcceptanceDocumentById(Integer id){
        AcceptanceDocument acceptanceDocument=acceptanceDocumentRepository.findAcceptanceDocumentByAcceptanceDocumentId(id);
        if(acceptanceDocument==null){
            throw new NotFoundException("Not found!");
        }
        return acceptanceDocument;
    }
}

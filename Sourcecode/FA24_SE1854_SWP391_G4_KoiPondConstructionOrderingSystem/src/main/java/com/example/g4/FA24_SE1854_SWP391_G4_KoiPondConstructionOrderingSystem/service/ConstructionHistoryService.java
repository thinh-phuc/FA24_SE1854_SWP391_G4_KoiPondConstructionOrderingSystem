package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.service;

import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.*;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.exception.DuplicateException;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.exception.NotFoundException;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.model.AcceptanceRequest;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.model.ConstructionRequest;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.model.UpdateAcceptanceDocumentRequest;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.model.UpdateConstructionRequest;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.repository.AcceptanceDocumentRepository;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.repository.ConstructionHistoryRepository;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.repository.DesignProfileRepository;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.repository.RequestRepository;
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
    @Autowired
    RequestLogService requestLogService;
    @Autowired
    RequestRepository requestRepository;

    public ConstructionHistory createConstructionHistory(ConstructionRequest constructionRequest) {
        try {
            DesignProfile designProfile = designProfileRepository.findDesignProfileByDesignProfileId(constructionRequest.getDesignProfileId());
            if(designProfile.getContructionStatus().equals("COMPLETED")){
                throw new NotFoundException("This design profile is already completed!");
            }
//            ConstructionHistory existing = constructionHistoryRepository.findConstructionHistoryByDesignProfile(designProfile);
//            if (existing != null) {
//                throw new DuplicateException("This profile already has a construction history!");
//            }
            ConstructionHistory constructionHistory = new ConstructionHistory();
            constructionHistory.setStep(constructionRequest.getStep());
            constructionHistory.setDescription(constructionRequest.getDescription());
            constructionHistory.setStartDate(constructionRequest.getStartDate());
            constructionHistory.setEndDate(constructionRequest.getEndDate());
            constructionHistory.setNote(constructionRequest.getNote());
            designProfile.setContructionStatus("PROGRESSING");
            designProfileRepository.save(designProfile);
            Customer staff = authenticationService.getCurrentUser();
            constructionHistory.setCreateBy(staff.getName());
            constructionHistory.setDesignProfile(designProfile);
            ConstructionHistory newConstructionHistory = constructionHistoryRepository.save(constructionHistory);

            requestLogService.createRequestLog("Constructing", "Please check your profile to view construction progress!", designProfile.getQuotation().getConsult().getRequestDetail().getRequest());
            Request request = designProfile.getQuotation().getConsult().getRequestDetail().getRequest();
            request.setStatus("Constructing");
            requestRepository.save(request);
            return newConstructionHistory;
        } catch (Exception e) {
            throw new NotFoundException(e.getMessage());
        }
    }

    public ConstructionHistory updateConstructionHistory(UpdateConstructionRequest constructionRequest, Integer id) {
        try {
            ConstructionHistory old = constructionHistoryRepository.findConstructionHistoryByConstructionHistoryId(id);
            if (old == null) {
                throw new NotFoundException("Not found!");
            }
            if(old.getDesignProfile().getContructionStatus().equals("COMPLETED")){
                throw new NotFoundException("This design profile is already completed!");
            }
            old.setStep(constructionRequest.getStep());
            old.setDescription(constructionRequest.getDescription());
            old.setNote(constructionRequest.getNote());
            Customer staff = authenticationService.getCurrentUser();
            old.setUpdateBy(staff.getName());
            return constructionHistoryRepository.save(old);
        } catch (Exception e) {
            throw new NotFoundException(e.getMessage());
        }
    }

    public AcceptanceDocument updateAcceptanceDocument(UpdateAcceptanceDocumentRequest documentRequest, Integer id) {
        try {
            AcceptanceDocument old = acceptanceDocumentRepository.findAcceptanceDocumentByAcceptanceDocumentId(id);
            if (old == null) {
                throw new NotFoundException("Not found!");
            }
            old.setFileUrl(documentRequest.getFileUrl());
            old.setConfirmConstructorName(documentRequest.getConfirmConstructorName());
            old.setConfirmCustomerName(documentRequest.getConfirmCustomerName());
            old.setDescription(documentRequest.getDescription());
            old.setConfirmDate(documentRequest.getConfirmDate());
            old.setUpdateDate(LocalDateTime.now());
            Customer staff = authenticationService.getCurrentUser();
            old.setUpdateBy(staff.getName());
            return acceptanceDocumentRepository.save(old);
        } catch (Exception e) {
            throw new NotFoundException(e.getMessage());
        }
    }

    public ConstructionHistory finishConstruction(Integer id) {
        try {
            DesignProfile designProfile = designProfileRepository.findDesignProfileByDesignProfileId(id);
            if(designProfile.getContructionStatus().equals("COMPLETED")){
                throw new NotFoundException("This design profile has already completed!");
            }
            ConstructionHistory complete = new ConstructionHistory();
            complete.setStep("FINAL");
            complete.setDescription("The construction has finished.");
            complete.setStartDate(LocalDateTime.now());
            complete.setEndDate(LocalDateTime.now());
            Customer staff = authenticationService.getCurrentUser();
            complete.setCreateBy(staff.getName());
            complete.setUpdateBy(staff.getName());
            complete.setDesignProfile(designProfile);
            designProfile.setContructionStatus("COMPLETED");
            designProfileRepository.save(designProfile);

            requestLogService.createRequestLog("Construction finished", "Testing for acceptance.", designProfile.getQuotation().getConsult().getRequestDetail().getRequest());
            Request requestStatus = designProfile.getQuotation().getConsult().getRequestDetail().getRequest();
            requestStatus.setStatus("Construction done");
            requestRepository.save(requestStatus);
            return constructionHistoryRepository.save(complete);
        } catch (Exception e) {
            throw new NotFoundException(e.getMessage());
        }
    }

    public List<ConstructionHistory> getAllActiveConstructionsByStaff() {
        List<ConstructionHistory> constructionHistories = constructionHistoryRepository.findActiveConstructionHistoriesByStaff(authenticationService.getCurrentUser().getCustomerId());
        return constructionHistories;
    }

    public List<AcceptanceDocument> getAllActiveAcceptanceDocumentsByStaff() {
        List<AcceptanceDocument> acceptanceDocuments = acceptanceDocumentRepository.findActiveAcceptanceDocumentsByStaff(authenticationService.getCurrentUser().getCustomerId());
        return acceptanceDocuments;
    }

    public AcceptanceDocument createAcceptanceDocument(AcceptanceRequest acceptanceRequest) {
        try {
            DesignProfile designProfile = designProfileRepository.findDesignProfileByDesignProfileId(acceptanceRequest.getDesignProfileId());
            if(designProfile==null){
                throw new NotFoundException("No design profile found!");
            }
            AcceptanceDocument existing = acceptanceDocumentRepository.findAcceptanceDocumentByDesignProfile(designProfile);
            if (existing != null) {
                throw new DuplicateException("This profile already has an acceptance document!");
            }
            AcceptanceDocument acceptanceDocument = new AcceptanceDocument();
            acceptanceDocument.setConfirm(true);
            acceptanceDocument.setDescription(acceptanceRequest.getDescription());
            acceptanceDocument.setConfirmDate(acceptanceRequest.getConfirmDate());
            acceptanceDocument.setConfirmConstructorName(acceptanceRequest.getConfirmConstructorName());
            acceptanceDocument.setConfirmCustomerName(acceptanceRequest.getConfirmCustomerName());
            acceptanceDocument.setCreateDate(LocalDateTime.now());
            acceptanceDocument.setFileUrl(acceptanceRequest.getFileUrl());
            Customer staff = authenticationService.getCurrentUser();
            acceptanceDocument.setCreateBy(staff.getName());
            acceptanceDocument.setDesignProfile(designProfile);
            AcceptanceDocument newAcceptanceDocument = acceptanceDocumentRepository.save(acceptanceDocument);

            requestLogService.createRequestLog("Acceptance document created", "Please check your profile for acceptance test detail!", designProfile.getQuotation().getConsult().getRequestDetail().getRequest());
            Request request = designProfile.getQuotation().getConsult().getRequestDetail().getRequest();
            request.setStatus("Tested");
            requestRepository.save(request);
            return newAcceptanceDocument;
        } catch (Exception e) {
            throw new NotFoundException(e.getMessage());
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

    public AcceptanceDocument deleteAcceptanceDocument(Integer id) {
        AcceptanceDocument acceptanceDocument = acceptanceDocumentRepository.findAcceptanceDocumentByAcceptanceDocumentId(id);
        if (acceptanceDocument == null) {
            throw new NotFoundException("Not found!");
        }
        acceptanceDocument.setIsActive(false);
        return acceptanceDocumentRepository.save(acceptanceDocument);
    }

    public List<ConstructionHistory> getConstructionHistoryByDesignProfileId(Integer id) {
        DesignProfile designProfile = designProfileRepository.findDesignProfileByDesignProfileId(id);
        List<ConstructionHistory> constructionHistories = constructionHistoryRepository.findConstructionHistorysByDesignProfileAndIsActiveTrue(designProfile);
        if (constructionHistories == null) {
            throw new NotFoundException("Not found!");
        }
        return constructionHistories;
    }

    public AcceptanceDocument getAcceptanceDocumentByDesignProfileId(Integer id) {
        DesignProfile designProfile = designProfileRepository.findDesignProfileByDesignProfileId(id);
        AcceptanceDocument acceptanceDocument = acceptanceDocumentRepository.findAcceptanceDocumentByDesignProfile(designProfile);
        if (acceptanceDocument == null) {
            throw new NotFoundException("Not found!");
        }
        return acceptanceDocument;
    }
}

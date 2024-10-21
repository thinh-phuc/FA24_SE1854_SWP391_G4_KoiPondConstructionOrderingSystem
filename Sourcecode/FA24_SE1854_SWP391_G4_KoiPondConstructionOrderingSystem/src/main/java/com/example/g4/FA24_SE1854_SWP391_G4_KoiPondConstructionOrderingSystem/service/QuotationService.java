package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.service;

import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.*;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.exception.NotFoundException;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.model.QuotationRequest;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.model.QuotationResponse;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.model.UpdateQuotationRequest;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.model.UpdateQuotationResponse;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.repository.ConsultRepository;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.repository.CustomerRepository;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.repository.PondDesignTemplateRepository;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.repository.QuotationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class QuotationService {
    @Autowired
    QuotationRepository quotationRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    PondDesignTemplateRepository pondDesignTemplateRepository;
    @Autowired
    ConsultRepository consultRepository;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    AuthenticationService authenticationService;

            public Quotation getQuotationById(Integer id){
                Quotation oldQuotation = quotationRepository.findQuotationByQuotationId(id);
                if(oldQuotation == null){
                    throw new NotFoundException("not found");
                }
                return oldQuotation;
            }
            // moi lam
    public QuotationResponse toResponse(Quotation quotation) {
        QuotationResponse response = new QuotationResponse();
        response.setQuotationId(quotation.getQuotationId());
        response.setIsConfirm(quotation.getIsConfirm());
        response.setDescription(quotation.getDescription());
        response.setMainCost(quotation.getMainCost());
        response.setSubCost(quotation.getSubCost());
        response.setVAT(quotation.getVAT());
        response.setTotal(quotation.getTotalCost());
        response.setIsActive(quotation.getIsActive());
        response.setCreateDate(quotation.getCreateDate());
        response.setCreateBy(quotation.getCreateBy());
        response.setConsultId(quotation.getConsult().getId());
        response.setCustomerId(quotation.getCustomer().getCustomerId());
        //response.setPondDesignTemplateId(quotation.getPondDesignTemplate().getPondDesignTemplateId());
        return response;
    }
    public UpdateQuotationResponse toUpdateResponse(Quotation quotation) {
        UpdateQuotationResponse response = new UpdateQuotationResponse();
        response.setQuotationId(quotation.getQuotationId());
        response.setIsConfirm(quotation.getIsConfirm());
        response.setDescription(quotation.getDescription());
        response.setMainCost(quotation.getMainCost());
        response.setSubCost(quotation.getSubCost());
        response.setVAT(quotation.getVAT());
        response.setTotal(quotation.getTotalCost());
        response.setIsActive(quotation.getIsActive());
        response.setUpdateDate(quotation.getUpdateDate());
        response.setUpdateBy(quotation.getUpdateBy());
        response.setConsultId(quotation.getConsult().getId());
        response.setCustomerId(quotation.getCustomer().getCustomerId());
        //response.setPondDesignTemplateId(quotation.getPondDesignTemplate().getPondDesignTemplateId());
        return response;
    }

            // moi lam
            public QuotationResponse  create (QuotationRequest quotationRequest){

                Customer customer = customerRepository.findCustomerByCustomerId(quotationRequest.getCustomerId());
                if(customer == null){
                    throw  new NotFoundException("Customer not found with ");
                }
//                PondDesignTemplate pondDesignTemplate = pondDesignTemplateRepository.findPondDesignTemplateByPondDesignTemplateId(quotationRequest.getPondDesignTemplateId());
//                if(pondDesignTemplate == null){
//                    throw new NotFoundException("Pond design template not found");
//                }
                Consult consult = consultRepository.findConsultById(quotationRequest.getConsultId());
                if (consult == null){
                    throw new NotFoundException("consult not found");
                }
                Quotation quotation = new Quotation();
                Customer staff = authenticationService.getCurrentUser();
                quotation.setCreateBy(staff.getName());
                quotation.setCreateDate(LocalDateTime.now());
                quotation.setVAT(quotationRequest.getVAT());
                quotation.setMainCost(quotationRequest.getMainCost());
                quotation.setSubCost(quotationRequest.getSubCost());
                quotation.setTotalCost((quotation.getMainCost()+ quotation.getSubCost())+(quotation.getMainCost()+ quotation.getSubCost())*quotation.getVAT()/100);
                quotation.setIsActive(true);
                quotation.setDescription(quotationRequest.getDescription());
                quotation.setConsult(consult);
                //quotation.setPondDesignTemplate(pondDesignTemplate);
                quotation.setCustomer(customer);
                quotation.setIsConfirm(false);
                quotation.setUpdateDate(null);
                quotation.setUpdateBy(null);
                Quotation newQuotation = quotationRepository.save(quotation);
                return toResponse(newQuotation);

            }
            //moi lam
            public UpdateQuotationResponse update (Integer id, UpdateQuotationRequest quotationRequest){
               try {
                   Customer staff = authenticationService.getCurrentUser();
                   Quotation oldQuotation = getQuotationById(id);
                   oldQuotation.setDescription(quotationRequest.getDescription());
                   oldQuotation.setMainCost(quotationRequest.getMainCost());
                   oldQuotation.setSubCost(quotationRequest.getSubCost());
                   oldQuotation.setVAT(quotationRequest.getVAT());
                   oldQuotation.setUpdateDate(LocalDateTime.now());
                   oldQuotation.setUpdateBy(staff.getName());
                   oldQuotation.setTotalCost((oldQuotation.getMainCost() + oldQuotation.getSubCost()) + (oldQuotation.getMainCost() + oldQuotation.getSubCost()) * oldQuotation.getVAT() / 100);
                   Quotation updateQuotation = quotationRepository.save(oldQuotation);
                   return  toUpdateResponse(updateQuotation);
               } catch (Exception e) {
                   throw new NotFoundException(e.getMessage());
               }

            }

            public List<Quotation> getQuotationAll(){
                    List<Quotation> quotationList = quotationRepository.findQuotationsByIsActiveTrue();
                    return quotationList;
            }

            public Quotation delete (Integer id){
                   Quotation oldQuotation = getQuotationById(id);
                   oldQuotation.setIsActive(false);
                   return quotationRepository.save(oldQuotation);
            }


    public Quotation confirmQuotation(Integer id){
       Quotation quotation =  getQuotationById(id);
       quotation.setIsConfirm(true);
       return quotationRepository.save(quotation);
    }

}

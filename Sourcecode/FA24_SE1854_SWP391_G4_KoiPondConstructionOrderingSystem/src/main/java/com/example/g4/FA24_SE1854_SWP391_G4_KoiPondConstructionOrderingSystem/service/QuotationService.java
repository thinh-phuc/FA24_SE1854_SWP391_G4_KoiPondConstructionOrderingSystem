package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.service;

import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.Quotation;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.exception.NotFoundException;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.model.QuotationRequest;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.repository.QuotationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuotationService {
    @Autowired
    QuotationRepository quotationRepository;

    @Autowired
    ModelMapper modelMapper;

            public Quotation getQuotationById(Integer id){
                Quotation oldQuotation = quotationRepository.findQuotationByQuotationId(id);
                if(oldQuotation == null){
                    throw new NotFoundException("not found");
                }
                return oldQuotation;
            }

            public Quotation create (QuotationRequest quotationRequest){
                Quotation quotation = modelMapper.map(quotationRequest,Quotation
                        .class);
                Quotation newQuotation = quotationRepository.save(quotation);
                return  newQuotation;

            }

            public Quotation update (Integer id,Quotation quotation){
                Quotation oldQuotation = getQuotationById(id);
              //  oldQuotation.setConsultId(quotation.getConsultId());
               // oldQuotation.setCustomerId(quotation.getCustomerId());
              //  oldQuotation.setPondDesignTemplateId(quotation.getPondDesignTemplateId());
                oldQuotation.setDescription(quotation.getDescription());
                oldQuotation.setMainCost(quotation.getMainCost());
                oldQuotation.setSubCost(quotation.getSubCost());
                oldQuotation.setVAT(quotation.getVAT());
                oldQuotation.setIsConfirm(quotation.getIsConfirm());
                oldQuotation.setIsActive(quotation.getIsActive());
                oldQuotation.setCreateBy(quotation.getCreateBy());
                oldQuotation.setUpdateDate(quotation.getUpdateDate());
                oldQuotation.setCreateDate(quotation.getCreateDate());
                oldQuotation.setUpdateBy(quotation.getUpdateBy());

                return quotationRepository.save(oldQuotation);

            }

            public List<Quotation> getQuotationAll(){
                    List<Quotation> quotationList = quotationRepository.findQuotationsByIsDeleteFalse();
                    return quotationList;
            }
            public Quotation delete (Integer id){
                   Quotation oldQuotation = getQuotationById(id);
                   oldQuotation.setIsDelete(true);
                   return quotationRepository.save(oldQuotation);
            }


}

package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.repository;

import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.Quotation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuotationRepository extends JpaRepository<Quotation, Integer> {
    Quotation findQuotationByQuotationId(Integer quotationId);
    List<Quotation> findQuotationsByIsDeleteFalse();
}
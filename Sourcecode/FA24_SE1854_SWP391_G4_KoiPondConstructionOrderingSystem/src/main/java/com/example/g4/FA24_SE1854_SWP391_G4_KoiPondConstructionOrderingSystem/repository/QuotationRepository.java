package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.repository;

import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.Customer;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.DesignProfile;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.Quotation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuotationRepository extends JpaRepository<Quotation, Integer> {
    Quotation findQuotationByQuotationId(Integer quotationId);
    List<Quotation> findQuotationsByIsActiveTrue();
    List<Quotation> findQuotationsByCustomerAndIsActiveTrueOrderByCreateDateDesc(Customer customer);

//    @Query("SELECT dp FROM Quotation dp JOIN dp.customers c WHERE c.customerId = :customerId")
//    List<Quotation> findQuotationsByStaff(@Param("customerId") Integer customerId);
@Query(value = "SELECT q.* " +
        "FROM quotation q " +
        "JOIN consult c ON q.consult_id = c.consult_id " +
        "JOIN customer_consult cc ON c.consult_id = cc.consult_id " +
        "WHERE cc.customer_id = :customerId",
        nativeQuery = true)
List<Quotation> findQuotationsByStaff(@Param("customerId") Integer customerId);
}
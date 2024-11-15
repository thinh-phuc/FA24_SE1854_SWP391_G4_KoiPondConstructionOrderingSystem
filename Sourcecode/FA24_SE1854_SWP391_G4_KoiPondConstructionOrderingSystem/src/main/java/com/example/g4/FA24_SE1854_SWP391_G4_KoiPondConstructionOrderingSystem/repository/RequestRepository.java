package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.repository;

import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.Customer;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.Request;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.RequestDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RequestRepository extends JpaRepository<Request, Integer> {
    Request findRequestById(Integer id);

    List<Request> findRequestsByIsActiveTrue();
    List<Request> findRequestsByCustomerAndIsActiveTrueOrderByCreateDateDesc(Customer customer);

    @Query(value = """
        SELECT r.*
        FROM request r
        JOIN request_detail rd ON r.request_id = rd.request_id
        JOIN consult c ON rd.request_detail_id = c.request_detail_id
        JOIN quotation q ON c.consult_id = q.consult_id
        WHERE q.quotation_id = :quotationId
        """, nativeQuery = true)
    Request findRequestByQuotationId(@Param("quotationId") Integer quotationId);
}

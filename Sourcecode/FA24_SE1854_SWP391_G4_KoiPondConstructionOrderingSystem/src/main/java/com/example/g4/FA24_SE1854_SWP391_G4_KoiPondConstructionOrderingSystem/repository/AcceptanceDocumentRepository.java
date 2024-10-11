package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.repository;

import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.AcceptanceDocument;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.DesignProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AcceptanceDocumentRepository extends JpaRepository<AcceptanceDocument, Integer> {
    List<AcceptanceDocument> findAcceptanceDocumentsByIsActiveTrue();
    AcceptanceDocument findAcceptanceDocumentByAcceptanceDocumentId(Integer id);
    AcceptanceDocument findAcceptanceDocumentByDesignProfile(DesignProfile designProfile);

    @Query("SELECT ad from AcceptanceDocument ad JOIN ad.designProfile dp JOIN dp.customers c WHERE c.customerId = :customerId AND ad.isActive=True")
    List<AcceptanceDocument> findActiveAcceptanceDocumentsByStaff(@Param("customerId") Integer customerId);
}

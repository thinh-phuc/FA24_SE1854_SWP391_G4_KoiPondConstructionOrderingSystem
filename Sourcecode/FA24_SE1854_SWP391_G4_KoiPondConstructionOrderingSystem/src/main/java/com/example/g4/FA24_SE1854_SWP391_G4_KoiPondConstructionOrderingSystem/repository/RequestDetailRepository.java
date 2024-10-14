package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.repository;

import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.Request;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.RequestDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RequestDetailRepository extends JpaRepository<RequestDetail, Integer> {
    RequestDetail findRequestDetailById(Integer id);


    List<RequestDetail> findRequestDetailsByIsDeletedFalse();

    List<RequestDetail> findRequestDetailsByRequestId(Integer requestId);
}

package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.repository;

import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.ServiceDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServiceDetailRepository extends JpaRepository<ServiceDetail,Integer> {
    ServiceDetail findServiceDetailByServiceDetailId(Integer ServiceDetailId);
    void deleteServiceDetailByServiceDetailId(Integer serviceDetailId);

    List<ServiceDetail> findServiceDetailByIsActiveTrueOrderByCreateDateDesc();
}

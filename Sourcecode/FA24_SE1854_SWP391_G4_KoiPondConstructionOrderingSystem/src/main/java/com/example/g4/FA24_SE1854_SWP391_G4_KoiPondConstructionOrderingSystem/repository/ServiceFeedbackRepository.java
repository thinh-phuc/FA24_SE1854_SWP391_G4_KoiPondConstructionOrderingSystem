package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.repository;

import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.ServiceFeedback;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServiceFeedbackRepository extends JpaRepository<ServiceFeedback, Integer> {
    ServiceFeedback findServiceFeedbackByServiceFeedbackId(Integer serviceFeedbackId);

    List<ServiceFeedback> findServiceFeedbacksByIsActiveTrue();
}

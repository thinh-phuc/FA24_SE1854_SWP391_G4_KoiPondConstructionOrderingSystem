package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.repository;

import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.PondDesignTemplate;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.RequestDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PondDesignTemplateRepository extends JpaRepository<PondDesignTemplate, Integer> {
    PondDesignTemplate findPondDesignTemplateById(Integer id);

    List<PondDesignTemplate> findPondDesignTemplatesByIsActiveTrue();

    //PondDesignTemplate findPondDesignTemplateByRequestDetailId(RequestDetail requestDetail);

}
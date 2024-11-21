package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.repository;

import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.PondDesignTemplate;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.RequestDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PondDesignTemplateRepository extends JpaRepository<PondDesignTemplate, Integer> {
    PondDesignTemplate findPondDesignTemplateByPondDesignTemplateId(Integer id);

    List<PondDesignTemplate> findPondDesignTemplatesByIsActiveTrue();

    //PondDesignTemplate findPondDesignTemplateByRequestDetailId(RequestDetail requestDetail);

    @Query(value = "SELECT pdt.* " +
            "FROM design_profile dp " +
            "JOIN quotation q ON dp.quotation_id = q.quotation_id " +
            "JOIN consult c ON c.consult_id = q.consult_id " +
            "JOIN request_detail rd ON rd.request_detail_id = c.request_detail_id " +
            "JOIN pond_design_template pdt ON pdt.pond_design_template_id = rd.pond_design_template_id " +
            "WHERE dp.design_profile_id = :designProfileId", nativeQuery = true)


    List<PondDesignTemplate> findPondDesignTemplateByDesignProfileId(@Param("designProfileId") Integer designProfileId);

}
package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.repository;

import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Customer findCustomerByEmail(String email);
    Customer findCustomerByCustomerId(Integer customerId);

    List<Customer> findAllByRole(String role);

    List<Customer> findCustomersByRole(String role);

    @Query("SELECT c FROM Customer c JOIN c.designProfiles dp WHERE dp.id = :designProfileId")
    List<Customer> findStaffsByDesignProfileId(@Param("designProfileId") Integer designProfileId);

    @Query("SELECT c FROM Customer c WHERE c.role = 'CONSTRUCTOR' AND c.name LIKE %:name% ORDER BY SIZE(c.designProfiles) ASC")
    List<Customer> findConstructorsOrderedByDesignProfileCount(@Param("name") String name);

    @Query("SELECT c FROM Customer c WHERE c.role = 'DESIGNER' AND c.name LIKE %:name% ORDER BY SIZE(c.designProfiles) ASC")
    List<Customer> findDesignersOrderedByDesignProfileCount(@Param("name") String name);
}

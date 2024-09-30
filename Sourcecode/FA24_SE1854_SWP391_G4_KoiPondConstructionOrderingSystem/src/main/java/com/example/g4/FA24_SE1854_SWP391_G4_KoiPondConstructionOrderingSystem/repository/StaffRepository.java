package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.repository;

import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StaffRepository extends JpaRepository<Staff,Integer> {
Staff findStaffByPhoneNumber(String phone);
Staff findStaffByStaffId(int id);
List<Staff> findStaffsByIsDeleteFalse();
}

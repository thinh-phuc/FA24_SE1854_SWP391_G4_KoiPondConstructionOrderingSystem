package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.api;

import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.ServiceProgress;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.model.ServiceProgressRequest;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.model.ServiceProgressUpdateRequest;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.service.ServiceProgressService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@SecurityRequirement(name = "api")
@RestController
public class ServiceProgressAPI {
    @Autowired
    ServiceProgressService serviceProgressService;

    @PostMapping("/api/service-progress")
    public ResponseEntity createServiceProgress(@Valid @RequestBody ServiceProgressRequest serviceProgressResquest) {
        ServiceProgress serviceProgress = serviceProgressService.createServiceProgress(serviceProgressResquest);
        return ResponseEntity.ok(serviceProgress);
    }

    //Để cho nhân viên thay đổi trạng thái trong quá trình thi công
    @PutMapping("/api/service-progress/{serviceProgressId}")
    public ResponseEntity updateServiceProgress(@Valid @RequestBody ServiceProgressUpdateRequest updatedServiceProgressResquest, @PathVariable Integer serviceProgressId) {
        ServiceProgress serviceProgress = serviceProgressService.updateServiceProgress(serviceProgressId, updatedServiceProgressResquest);
        return ResponseEntity.ok(serviceProgress);
    }

    //Để người dùng nhấn nút xác nhận sau khi mà nhân viên hoàn thành dịch vụ
    @PutMapping("/api/acceptance-service-progress/{serviceProgressId}")
    public ResponseEntity updateServiceProgress(@PathVariable Integer serviceProgressId) {
        ServiceProgress serviceProgress = serviceProgressService.acceptServiceProgress(serviceProgressId);
        return ResponseEntity.ok(serviceProgress);
    }

    //Để xóa khi muốn xóa
    @DeleteMapping("/api/service-progress/{serviceProgressId}")
    public ResponseEntity deleteServiceProgress(@PathVariable Integer serviceProgressId) {
        ServiceProgress serviceProgress = serviceProgressService.deleteServiceProgress(serviceProgressId);
        return ResponseEntity.ok(serviceProgress);
    }

    //Lấy ra một bản ghi cụ thể
    @GetMapping("/api/service-progress/{serviceProgressId}")
    public ResponseEntity getServiceProgress(@PathVariable Integer serviceProgressId) {
        ServiceProgress serviceProgress = serviceProgressService.getServiceProgress(serviceProgressId);
        return ResponseEntity.ok(serviceProgress);
    }

    //Lấy ra tất cả các bản ghi
    @GetMapping("/api/service-progress")
    public ResponseEntity getServiceProgress() {
        List<ServiceProgress> serviceProgresses = serviceProgressService.getAllServiceProgress();
        return ResponseEntity.ok(serviceProgresses);
    }

    @GetMapping("/api/service-progress/customer/{customerID}")
    public ResponseEntity getServiceProgressForCustomer(@PathVariable Integer customerID) {
        List<ServiceProgress> serviceProgresses = serviceProgressService.getServiceProgressesForCustomer(customerID);
        return ResponseEntity.ok(serviceProgresses);
    }

    @GetMapping("/api/service-progress/maintenance-staff/{maintenanceStaffId}")
    public ResponseEntity getServiceProgressForMaintenanceStaff(@PathVariable Integer maintenanceStaffId) {
        List<ServiceProgress> serviceProgresses = serviceProgressService.getServiceProgressesForMaintenanceStaff(maintenanceStaffId);
        return ResponseEntity.ok(serviceProgresses);
    }
}

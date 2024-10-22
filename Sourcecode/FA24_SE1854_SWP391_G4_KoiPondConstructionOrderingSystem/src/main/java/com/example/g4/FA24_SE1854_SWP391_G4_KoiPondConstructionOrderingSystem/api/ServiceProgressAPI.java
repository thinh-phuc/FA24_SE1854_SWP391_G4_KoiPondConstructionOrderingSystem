package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.api;

import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.ServiceProgress;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.model.ServiceProgressResquest;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.service.ServiceProgressService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@SecurityRequirement(name = "api")
@RestController
public class ServiceProgressAPI {
    @Autowired
    ServiceProgressService serviceProgressService;

    @PostMapping("/api/service-progress")
    public ResponseEntity createServiceProgress(@Valid @RequestBody ServiceProgressResquest  serviceProgressResquest) {
        ServiceProgress serviceProgress = serviceProgressService.createServiceProgress(serviceProgressResquest);
        return ResponseEntity.ok(serviceProgress);
    }

    //Để cho nhân viên thay đổi trạng thái trong quá trình thi công
    @PutMapping("/api/service-progress/{serviceProgressId}")
    public ResponseEntity updateServiceProgress(@Valid @RequestBody ServiceProgressResquest  serviceProgressResquest, @PathVariable Integer serviceProgressId) {
        ServiceProgress serviceProgress = serviceProgressService.updateServiceProgress(serviceProgressId, serviceProgressResquest);
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
}

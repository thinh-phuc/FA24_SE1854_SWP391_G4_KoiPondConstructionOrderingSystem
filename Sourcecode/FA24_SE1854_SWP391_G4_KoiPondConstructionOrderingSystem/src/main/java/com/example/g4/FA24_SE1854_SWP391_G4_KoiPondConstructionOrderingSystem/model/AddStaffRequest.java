package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.model;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AddStaffRequest {
    private String name;
    @Email(message = "Invalid email!")
    private String email;
    @Size(min = 6, message = "Password must be at least 6 characters!")
    private String password;
    @Pattern(regexp = "(84|0[3|5|7|8|9])+(\\d{8})", message = "Invalid phone number!")
    private String phoneNumber;
    private String role = "CUSTOMER";
}

package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class Customer implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Integer customerId;

    @Column(name = "name", nullable = false, columnDefinition = "NVARCHAR(100)")
    private String name;

    @Email(message = "Invalid email!")
    @Column(name = "email", nullable = false, length = 50, unique = true)
    private String email;

    @Size(min = 6, message = "Password must be at least 6 characters!")
    @Column(name = "password", nullable = false, length = 255)
    private String password;

    @Column(name = "phone_number", nullable = false, length = 20, unique = true)
    @Pattern(regexp = "(84|0[3|5|7|8|9])+(\\d{8})", message = "Invalid phone number!")
    private String phoneNumber;

    @Column(name = "role", columnDefinition = "VARCHAR(30)")
    private String role = "CUSTOMER";

    @Column(name = "description", columnDefinition = "NVARCHAR(500)")
    private String description = "none";

    @Column(name = "address", columnDefinition = "NVARCHAR(200)")
    private String address = "none";

    @Column(name = "note", columnDefinition = "NVARCHAR(255)")
    private String note = "none";

    @Column(name = "is_active", columnDefinition = "BIT DEFAULT 1")
    private Boolean isActive = true;

    @Column(name = "create_date", columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createDate = LocalDateTime.now();

//    @Column(name = "create_by", columnDefinition = "NVARCHAR(100)")
//    private String createBy;

    @Column(name = "update_date", columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime updateDate = LocalDateTime.now();

    @Column(name = "update_by", columnDefinition = "NVARCHAR(100)")
    private String updateBy = "none";

//    @OneToMany(mappedBy = "customer")
//    @JsonIgnore
//    List<DesignProfile> designProfiles;

    @ManyToMany(mappedBy = "customers")
    @JsonIgnore
    List<DesignProfile> designProfiles;

    @OneToMany(mappedBy = "customer")
    @JsonIgnore
    List<Quotation> Quotations;

    @ManyToMany(mappedBy = "customers")
    //@JoinTable(name = "customer_consult", joinColumns = @JoinColumn(name = "customer_id"), inverseJoinColumns = @JoinColumn(name = "consult_id"))
    @JsonIgnore
    List<Consult> consults;

    @OneToMany(mappedBy = "maintenanceStaff")
    @JsonIgnore
    List<ServicePayment> servicePayments;

    @OneToMany(mappedBy = "customer")
    @JsonIgnore
    List<ServiceFeedback> serviceFeedbacks;

    @OneToMany(mappedBy = "customer")
    @JsonIgnore
    List<Request> requests;

    @OneToMany(mappedBy = "customer")
    @JsonIgnore
    List<ServiceRequest> serviceRequest;

    @OneToMany(mappedBy = "customer")
    @JsonIgnore
    List<ServiceQuotation> serviceQuotations;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        if (this.role != null) authorities.add(new SimpleGrantedAuthority(this.role.toString()));
        return authorities;
    }

    @Override

    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity;

import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.repository.CustomerRepository;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(name = "request_id")
    Integer id;

    //@Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    //@NotBlank(message = "Cus_id cannot be blank!")

//    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
//    @Column(name = "customer_id", nullable = false)
//    Integer customerId;

    @Column(name = "status", length = 20)
    String status;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(name = "is_active", columnDefinition = "BIT DEFAULT 1")
    Boolean isActive = true;

    @Column(name = "description", columnDefinition = "NVARCHAR(500)")
    String description = "none";

    @Column(name = "address", columnDefinition = "NVARCHAR(255)")
    String address;

    @Column(name = "note", columnDefinition = "NVARCHAR(255)")
    String note = "none";

    @Column(name = "create_date", columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createDate = LocalDateTime.now();

    @Column(name = "create_by", columnDefinition = "NVARCHAR(40)")
    private String createBy;

    @Column(name = "update_date", columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime updateDate;

    @Column(name = "update_by", columnDefinition = "NVARCHAR(40)")
    private String updateBy = "none";

//    @JsonIgnore
//    Boolean isDeleted = false;

    @OneToMany(mappedBy = "request")
    @JsonIgnore
    List<RequestDetail> requestDetails;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    Customer customer;
}

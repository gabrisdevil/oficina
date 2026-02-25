package com.santana.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class CustomerRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String customerName;
    private String plate;
    private String phone;
    private String carModel;
    private String requestedService;
    private String status;
    private LocalDateTime createdAt;

    public CustomerRequest() {}

    public CustomerRequest(String customerName, String plate, String phone, String carModel, String requestedService) {
        this.customerName = customerName;
        this.plate = plate;
        this.phone = phone;
        this.carModel = carModel;
        this.requestedService = requestedService;
    }

    @PrePersist
    public void onCreate() {
        if (status == null || status.isBlank()) {
            status = "Recebido";
        }
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
    }

    public Long getId() { return id; }
    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }
    public String getPlate() { return plate; }
    public void setPlate(String plate) { this.plate = plate; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getCarModel() { return carModel; }
    public void setCarModel(String carModel) { this.carModel = carModel; }
    public String getRequestedService() { return requestedService; }
    public void setRequestedService(String requestedService) { this.requestedService = requestedService; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}

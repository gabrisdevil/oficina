package com.santana.model;

import javax.persistence.*;

@Entity
public class ServiceItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(length = 2000)
    private String description;
    private String category;
    private Double price;

    public ServiceItem() {}

    public ServiceItem(String name, String description, String category, Double price) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.price = price;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }
}

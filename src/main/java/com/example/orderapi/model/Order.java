package com.example.orderapi.model;

import jakarta.persistence.*;

@Entity
@Table(name = "orders")  
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String category;
    private String payment;
    private String description;
    private Integer amount;

    // Default constructor
    public Order() {}

    // Parameterized constructor
    public Order(String category, String payment, String description, Integer amount) {
        this.category = category;
        this.payment = payment;
        this.description = description;
        this.amount = amount;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}

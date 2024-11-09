package com.example.orderapi.repository;

import com.example.orderapi.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    // JPA will provide all the necessary methods for CRUD
}


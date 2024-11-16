package com.example.orderapi.controller;

import com.example.orderapi.model.Order;
import com.example.orderapi.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    // GET all orders
    @GetMapping
    @CrossOrigin
      public List<Order> getAllOrders() {
        return orderService.findAll();
    }

    // GET order by ID
    @GetMapping("/{id}")
    @CrossOrigin
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        Optional<Order> order = orderService.findById(id);
        return order.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // POST create a new order
    @PostMapping
    @CrossOrigin
    public Order createOrder(@RequestBody Order order) {
        return orderService.save(order);
    }

    // PUT update an order
    @PutMapping("/{id}")
    @CrossOrigin
    public ResponseEntity<Order> updateOrder(@PathVariable Long id, @RequestBody Order updatedOrder) {
        return orderService.findById(id)
                .map(existingOrder -> {
                    existingOrder.setCategory(updatedOrder.getCategory());
                    existingOrder.setPayment(updatedOrder.getPayment());
                    existingOrder.setDescription(updatedOrder.getDescription());
                    existingOrder.setAmount(updatedOrder.getAmount());
                    orderService.save(existingOrder);
                    return ResponseEntity.ok(existingOrder);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // DELETE an order by ID
    @DeleteMapping("/{id}")
    @CrossOrigin
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        if (orderService.findById(id).isPresent()) {
            orderService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}


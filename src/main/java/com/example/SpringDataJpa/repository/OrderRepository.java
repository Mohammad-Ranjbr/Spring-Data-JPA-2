package com.example.SpringDataJpa.repository;

import com.example.SpringDataJpa.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    Order findByOrderTrackingNumber(String orderTrackingNumber);

}

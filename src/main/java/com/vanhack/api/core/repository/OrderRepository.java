package com.vanhack.api.core.repository;

import com.vanhack.api.core.repository.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, String> {

    Optional<Order> findById(UUID id);
}

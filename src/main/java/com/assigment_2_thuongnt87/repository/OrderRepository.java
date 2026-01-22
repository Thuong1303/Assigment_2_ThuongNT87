package com.assigment_2_thuongnt87.repository;


import java.util.Optional;
import java.util.UUID;

import com.assigment_2_thuongnt87.entities.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, UUID> {
  Optional<Order> findByTrackingToken(UUID trackingToken);
  Optional<Order> findByOrderCode(String orderCode);
  Optional<Order> findByReservationToken(java.util.UUID reservationToken);
}

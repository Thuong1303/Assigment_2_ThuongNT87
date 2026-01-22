package com.assigment_2_thuongnt87.dto.order;


import com.assigment_2_thuongnt87.entities.order.OrderStatus;
import com.assigment_2_thuongnt87.entities.order.PaymentMethod;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

public record TrackingOrderResponse(
    String orderCode,
    OrderStatus status,
    PaymentMethod paymentMethod,
    BigDecimal totalAmount,
    Instant createdAt,
    List<OrderItemDto> items
) {}

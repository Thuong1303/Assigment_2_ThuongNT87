package com.assigment_2_thuongnt87.dto.admin;


import com.assigment_2_thuongnt87.entities.order.OrderStatus;
import com.assigment_2_thuongnt87.entities.order.PaymentMethod;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public record AdminOrderSummaryResponse(
    UUID id,
    String orderCode,
    OrderStatus status,
    PaymentMethod paymentMethod,
    BigDecimal totalAmount,
    Instant createdAt,
    String customerName,
    String phone
) {}

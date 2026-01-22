package com.assigment_2_thuongnt87.dto.admin;

import com.assigment_2_thuongnt87.entities.order.OrderStatus;
import jakarta.validation.constraints.NotNull;

public record UpdateOrderStatusRequest(@NotNull OrderStatus status) {}

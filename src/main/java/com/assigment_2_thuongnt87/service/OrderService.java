package com.assigment_2_thuongnt87.service;

import com.assigment_2_thuongnt87.dto.PageResponse;
import com.assigment_2_thuongnt87.dto.admin.AdminOrderSummaryResponse;
import com.assigment_2_thuongnt87.dto.order.TrackingOrderResponse;
import com.assigment_2_thuongnt87.entities.order.Order;
import com.assigment_2_thuongnt87.entities.order.OrderStatus;
import com.assigment_2_thuongnt87.entities.order.PaymentMethod;

import java.util.UUID;

public interface OrderService {
    Order placeOrder(
            UUID reservationToken,
            String email,
            String fullName,
            String phone,
            String addressLine1,
            String addressLine2,
            String city,
            PaymentMethod paymentMethod
    );
    TrackingOrderResponse track(UUID trackingToken);
    PageResponse<AdminOrderSummaryResponse> adminList(int page, int size);
    AdminOrderSummaryResponse adminUpdateStatus(UUID orderId, OrderStatus target);
    Order markPaidByOrderCode(String orderCode);

}

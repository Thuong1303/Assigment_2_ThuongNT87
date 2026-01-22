package com.assigment_2_thuongnt87.dto.checkout;

import java.util.UUID;

public record PlaceOrderResponse(
    String orderCode,
    UUID trackingToken
) {}

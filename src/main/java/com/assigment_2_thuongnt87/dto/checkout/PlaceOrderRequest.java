package com.assigment_2_thuongnt87.dto.checkout;

import java.util.UUID;

import com.assigment_2_thuongnt87.entities.order.PaymentMethod;
import jakarta.validation.constraints.*;

public record PlaceOrderRequest(
    @NotNull UUID reservationToken,

    @Email @NotBlank String email,
    @NotBlank String fullName,
    @NotBlank String phone,

    @NotBlank String addressLine1,
    String addressLine2,
    @NotBlank String city,

    @NotNull PaymentMethod paymentMethod
) {}

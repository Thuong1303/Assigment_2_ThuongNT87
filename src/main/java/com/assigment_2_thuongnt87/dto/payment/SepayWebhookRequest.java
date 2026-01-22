package com.assigment_2_thuongnt87.dto.payment;

import jakarta.validation.constraints.NotBlank;

public record SepayWebhookRequest(
    @NotBlank String orderCode
) {}

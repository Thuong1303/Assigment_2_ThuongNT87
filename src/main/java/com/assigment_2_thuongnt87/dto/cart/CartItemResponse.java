package com.assigment_2_thuongnt87.dto.cart;
import java.math.BigDecimal;
import java.util.UUID;

public record CartItemResponse(
    UUID variantId,
    String skuCode,
    String productName,
    String variantName,
    BigDecimal unitPrice,
    int quantity,
    BigDecimal lineTotal,
    int availableStock
) {}

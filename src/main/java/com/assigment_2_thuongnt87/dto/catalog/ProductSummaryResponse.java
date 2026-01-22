package com.assigment_2_thuongnt87.dto.catalog;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductSummaryResponse(
    UUID id,
    String name,
    String slug,
    String categorySlug,
    BigDecimal minPrice,
    BigDecimal maxPrice
) {}

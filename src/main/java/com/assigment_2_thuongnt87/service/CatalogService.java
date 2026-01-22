package com.assigment_2_thuongnt87.service;

import com.assigment_2_thuongnt87.dto.PageResponse;
import com.assigment_2_thuongnt87.dto.catalog.ProductDetailResponse;
import com.assigment_2_thuongnt87.dto.catalog.ProductSummaryResponse;

import java.math.BigDecimal;

public interface CatalogService {
    PageResponse<ProductSummaryResponse> listProducts(
            int page, int size,
            String categorySlug,
            BigDecimal minPrice,
            BigDecimal maxPrice,
            String keyword
    );
    public ProductDetailResponse getProductDetail(String slug);

}

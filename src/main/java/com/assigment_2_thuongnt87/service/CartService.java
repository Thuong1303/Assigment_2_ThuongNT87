package com.assigment_2_thuongnt87.service;

import com.assigment_2_thuongnt87.dto.cart.CartResponse;
import com.assigment_2_thuongnt87.dto.cart.CreateCartResponse;
import com.assigment_2_thuongnt87.entities.cart.Cart;

import java.util.UUID;

public interface CartService {

    CreateCartResponse createCart();
    CartResponse getCart(UUID cartToken);
    CartResponse addItem(UUID cartToken, UUID variantId, int quantity);
    CartResponse updateItem(UUID cartToken, UUID variantId, int quantity);
    CartResponse removeItem(UUID cartToken, UUID variantId);

}

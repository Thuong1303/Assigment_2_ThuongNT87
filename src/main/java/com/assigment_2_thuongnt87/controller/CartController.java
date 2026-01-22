package com.assigment_2_thuongnt87.controller;


import java.util.UUID;

import com.assigment_2_thuongnt87.dto.cart.AddCartItemRequest;
import com.assigment_2_thuongnt87.dto.cart.CartResponse;
import com.assigment_2_thuongnt87.dto.cart.CreateCartResponse;
import com.assigment_2_thuongnt87.dto.cart.UpdateCartItemRequest;
import com.assigment_2_thuongnt87.service.impl.CartServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/public/carts")
public class CartController {

  private final CartServiceImpl cartServiceImpl;

  @PostMapping
  public CreateCartResponse create() {
    return cartServiceImpl.createCart();
  }

  @GetMapping("/{cartToken}")
  public CartResponse get(@PathVariable UUID cartToken) {
    return cartServiceImpl.getCart(cartToken);
  }

  @PostMapping("/{cartToken}/items")
  public CartResponse addItem(@PathVariable UUID cartToken, @Valid @RequestBody AddCartItemRequest req) {
    return cartServiceImpl.addItem(cartToken, req.variantId(), req.quantity());
  }

  @PutMapping("/{cartToken}/items/{variantId}")
  public CartResponse updateItem(@PathVariable UUID cartToken, @PathVariable UUID variantId, @Valid @RequestBody UpdateCartItemRequest req) {
    return cartServiceImpl.updateItem(cartToken, variantId, req.quantity());
  }

  @DeleteMapping("/{cartToken}/items/{variantId}")
  public CartResponse removeItem(@PathVariable UUID cartToken, @PathVariable UUID variantId) {
    return cartServiceImpl.removeItem(cartToken, variantId);
  }
}

package com.assigment_2_thuongnt87.controller;


import com.assigment_2_thuongnt87.dto.checkout.PlaceOrderRequest;
import com.assigment_2_thuongnt87.dto.checkout.PlaceOrderResponse;
import com.assigment_2_thuongnt87.dto.checkout.ReserveRequest;
import com.assigment_2_thuongnt87.dto.checkout.ReserveResponse;
import com.assigment_2_thuongnt87.service.impl.InventoryReservationServiceImpl;
import com.assigment_2_thuongnt87.service.impl.OrderServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/public/checkout")
public class CheckoutController {

  private final InventoryReservationServiceImpl reservationService;
  private final OrderServiceImpl orderServiceImpl;

  @PostMapping("/reserve")
  public ReserveResponse reserve(@Valid @RequestBody ReserveRequest req) {
    return reservationService.reserveFromCart(req.cartToken(), req.holdMinutes());
  }

  @PostMapping("/orders")
  public PlaceOrderResponse placeOrder(@Valid @RequestBody PlaceOrderRequest req) {
    var order = orderServiceImpl.placeOrder(
        req.reservationToken(),
        req.email(),
        req.fullName(),
        req.phone(),
        req.addressLine1(),
        req.addressLine2(),
        req.city(),
        req.paymentMethod()
    );
    return new PlaceOrderResponse(order.getOrderCode(), order.getTrackingToken());
  }

  @PostMapping("/reserve/{reservationToken}/cancel")
  public void cancel(@PathVariable java.util.UUID reservationToken) {
    reservationService.cancelReservation(reservationToken);
  }
}

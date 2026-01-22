package com.assigment_2_thuongnt87.controller;

import java.util.UUID;

import com.assigment_2_thuongnt87.dto.order.TrackingOrderResponse;
import com.assigment_2_thuongnt87.service.impl.OrderServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/public/orders")
public class TrackingController {

  private final OrderServiceImpl orderServiceImpl;

  @GetMapping("/track/{trackingToken}")
  public TrackingOrderResponse track(@PathVariable UUID trackingToken) {
    return orderServiceImpl.track(trackingToken);
  }
}

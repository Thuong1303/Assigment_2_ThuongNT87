package com.assigment_2_thuongnt87.controller;


import com.assigment_2_thuongnt87.dto.payment.SepayWebhookRequest;
import com.assigment_2_thuongnt87.service.impl.OrderServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/public/payments")
public class PaymentController {

  private final OrderServiceImpl orderServiceImpl;

  // Phase 1 stub: call this endpoint to simulate SePay webhook
  @PostMapping("/sepay/webhook")
  public void sepayWebhook(@Valid @RequestBody SepayWebhookRequest req) {
    orderServiceImpl.markPaidByOrderCode(req.orderCode());
  }
}

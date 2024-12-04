package com.zam.StripePay.controller;

import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.zam.StripePay.payload.request.PaymentIntentRequest;
import com.zam.StripePay.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(PayController.API_PATH)
public class PayController {

    public static final String API_PATH = "/api/pay";

    private final PaymentService paymentService;

    @Autowired
    public PayController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public ResponseEntity<?> createPaymentIntent(@RequestBody PaymentIntentRequest paymentIntentRequest) throws StripeException {
        PaymentIntent paymentIntent = paymentService.createPaymentIntent(paymentIntentRequest);
        String paymentStr = paymentIntent.toJson();
        return new ResponseEntity<>(paymentStr, HttpStatus.CREATED);
    }

    @PostMapping("/confirm/{id}")
    public ResponseEntity<?> confirmPaymentIntent(@PathVariable("id") String paymentId) throws StripeException {
        PaymentIntent paymentIntent = paymentService.confirmPaymentIntent(paymentId);
        String paymentStr = paymentIntent.toJson();
        return new ResponseEntity<>(paymentStr, HttpStatus.CREATED);
    }

    @PostMapping("/cancel/{id}")
    public ResponseEntity<?> cancelPaymentIntent(@PathVariable("id") String paymentId) throws StripeException {
        PaymentIntent paymentIntent = paymentService.cancelPaymentIntent(paymentId);
        String paymentStr = paymentIntent.toJson();
        return new ResponseEntity<>(paymentStr, HttpStatus.CREATED);
    }

}

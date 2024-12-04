package com.zam.StripePay.service.impl;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentConfirmParams;
import com.stripe.param.PaymentIntentCreateParams;
import com.zam.StripePay.payload.request.PaymentIntentRequest;
import com.zam.StripePay.service.PaymentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Value("${stripe.key}")
    private String stripeKey;

    @Override
    public PaymentIntent createPaymentIntent(PaymentIntentRequest paymentIntentRequest) throws StripeException {
        Stripe.apiKey = stripeKey;

        PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                .setAmount(paymentIntentRequest.getAmount())
                .setCurrency(paymentIntentRequest.getCurrency())
                .setDescription(paymentIntentRequest.getDescription())
                .setAutomaticPaymentMethods(
                        PaymentIntentCreateParams.AutomaticPaymentMethods.builder()
                                .setEnabled(true)
                                .build()
                )
                .build();
        return PaymentIntent.create(params);
    }

    @Override
    public PaymentIntent confirmPaymentIntent(String paymentId) throws StripeException {
        Stripe.apiKey = stripeKey;
        PaymentIntent resource = PaymentIntent.retrieve("pi_3MtweELkdIwHu7ix0Dt0gF2H");
        PaymentIntentConfirmParams params =
                PaymentIntentConfirmParams.builder()
                        .setPaymentMethod("pm_card_visa")
                        .setReturnUrl("http://localhost:8080")
                        .build();
        return resource.confirm(params);
    }

    @Override
    public PaymentIntent cancelPaymentIntent(String paymentId) throws StripeException {
        Stripe.apiKey = stripeKey;
        PaymentIntent paymentIntent = PaymentIntent.retrieve(paymentId);
        paymentIntent.cancel();
        return paymentIntent;
    }

}

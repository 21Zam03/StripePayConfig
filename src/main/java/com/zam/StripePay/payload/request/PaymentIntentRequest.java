package com.zam.StripePay.payload.request;

import com.stripe.param.SetupIntentCreateParams;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentIntentRequest {

    private String description;
    private Long amount;
    private String currency;
    private String cardNumber;
    private Long cardExpiryMonth;
    private Long cardExpiryYear;
    private String cvcCard;

}

package com.example.CreditCardLimitOffer.DTO;

import lombok.NonNull;

import java.time.LocalDateTime;

public class GetActiveOffersDTO {
    @NonNull
    Long accountId;
    LocalDateTime activationDate;
}

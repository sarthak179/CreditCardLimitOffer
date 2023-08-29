package com.example.CreditCardLimitOffer.DTO;

import com.example.CreditCardLimitOffer.Enum.LimitType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class OfferCreationDTO {
    @NonNull
    Long accountId;
    @NonNull
    LimitType limitType;
    @NonNull
    Double newLimit;
    @NonNull
    String offerActivationTime;
    @NonNull
    String offerExpiryTime;
}

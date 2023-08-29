package com.example.CreditCardLimitOffer.DTO;

import com.example.CreditCardLimitOffer.Enum.OfferStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UpdateOfferStatusDTO {
    @NonNull
    Long offerId;
    @NonNull
    OfferStatus offerStatus;
}

package com.example.SarthakGupta_Assignment_VegaPay.DTO;

import com.example.SarthakGupta_Assignment_VegaPay.Enum.OfferStatus;
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

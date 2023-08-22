package com.example.SarthakGupta_Assignment_VegaPay.DTO;

import com.example.SarthakGupta_Assignment_VegaPay.Enum.LimitType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.time.LocalDate;

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

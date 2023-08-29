package com.example.CreditCardLimitOffer.DTO;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class AccountCreationDTO {
    @NonNull
    String firstName;
    String lastName;
    @NonNull
    String cardNumber;
    @NonNull
    Double accountLimit;
    @NonNull
    Double perTransactionLimit;
}

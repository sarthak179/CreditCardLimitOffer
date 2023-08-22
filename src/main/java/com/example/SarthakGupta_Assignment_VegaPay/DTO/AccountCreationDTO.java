package com.example.SarthakGupta_Assignment_VegaPay.DTO;

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

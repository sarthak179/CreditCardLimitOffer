package com.example.CreditCardLimitOffer.DTO;

import com.example.CreditCardLimitOffer.Enum.LimitType;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountLimitUpdateDTO {
    @NonNull
    Long accountId;
    Double newLimit;
    LimitType limitType;
}

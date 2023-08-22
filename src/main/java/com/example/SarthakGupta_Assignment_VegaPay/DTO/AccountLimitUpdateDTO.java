package com.example.SarthakGupta_Assignment_VegaPay.DTO;

import com.example.SarthakGupta_Assignment_VegaPay.Enum.LimitType;
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

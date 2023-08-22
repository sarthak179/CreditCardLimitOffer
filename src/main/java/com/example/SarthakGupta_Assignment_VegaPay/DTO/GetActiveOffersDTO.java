package com.example.SarthakGupta_Assignment_VegaPay.DTO;

import lombok.NonNull;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class GetActiveOffersDTO {
    @NonNull
    Long accountId;
    LocalDateTime activationDate;
}

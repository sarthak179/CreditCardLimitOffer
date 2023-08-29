package com.example.CreditCardLimitOffer.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Accounts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Account extends BaseEntity {
    @NonNull
    String customerId;
    @NonNull
    String firstName;
    String lastName;
    @NonNull
    String cardNumber;
    @NonNull
    Double accountLimit;
    @NonNull
    Double perTransactionLimit;
    @NonNull
    Double lastAccountLimit;
    @NonNull
    Double lastPerTransactionLimit;
    @NonNull
    LocalDateTime accountLimitUpdateTime;
    @NonNull
    LocalDateTime perTransactionLimitUpdateTime;
}

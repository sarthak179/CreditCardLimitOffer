package com.example.SarthakGupta_Assignment_VegaPay.Entity;

import com.example.SarthakGupta_Assignment_VegaPay.Enum.LimitType;
import com.example.SarthakGupta_Assignment_VegaPay.Enum.OfferStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Offers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Offer extends BaseEntity {
    @NonNull
    LimitType limitType;
    @NonNull
    Double newLimit;
    @NonNull
    LocalDateTime offerActivationTime;
    @NonNull
    LocalDateTime offerExpiry;
    @NonNull
    OfferStatus offerStatus;
    @ManyToOne
    Account account;
}

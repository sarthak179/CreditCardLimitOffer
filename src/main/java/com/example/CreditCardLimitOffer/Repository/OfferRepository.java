package com.example.CreditCardLimitOffer.Repository;

import com.example.CreditCardLimitOffer.Entity.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {
    public List<Offer> findByAccountId(Long id);
    public Optional<Offer> findById(Long id);
}

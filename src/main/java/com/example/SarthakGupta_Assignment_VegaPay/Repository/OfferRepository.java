package com.example.SarthakGupta_Assignment_VegaPay.Repository;

import com.example.SarthakGupta_Assignment_VegaPay.Entity.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {
    public List<Offer> findByAccountId(Long id);
    public Optional<Offer> findById(Long id);
}

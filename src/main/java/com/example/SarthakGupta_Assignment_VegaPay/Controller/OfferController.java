package com.example.SarthakGupta_Assignment_VegaPay.Controller;

import com.example.SarthakGupta_Assignment_VegaPay.DTO.OfferCreationDTO;
import com.example.SarthakGupta_Assignment_VegaPay.DTO.UpdateOfferStatusDTO;
import com.example.SarthakGupta_Assignment_VegaPay.Entity.Offer;
import com.example.SarthakGupta_Assignment_VegaPay.Exception.*;
import com.example.SarthakGupta_Assignment_VegaPay.Service.OfferService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/offers")
public class OfferController {
    private final OfferService offerService;
    public OfferController(@Autowired OfferService offerService) {
        this.offerService = offerService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<Offer>> getOffersByAccountId(@PathVariable Long id) throws NoOffersFoundException {
        return ResponseEntity.ok().body(offerService.getActiveOffersByAccountId(id));
    }

    @PostMapping("")
    public ResponseEntity<Offer> createOffer(@RequestBody OfferCreationDTO offerCreationDTO) throws InvalidDateFormatException, NoAccountFoundException, LimitExceededException {
        return ResponseEntity.ok().body(offerService.createOffer(offerCreationDTO));
    }

    @PatchMapping("")
    public ResponseEntity<Offer> updateOfferStatus(@RequestBody UpdateOfferStatusDTO updateOfferStatusDTO) throws NoOffersFoundException, NoAccountFoundException, OfferAlreadyExpiredException {
        return ResponseEntity.ok().body(offerService.updateOffer(updateOfferStatusDTO));
    }
}

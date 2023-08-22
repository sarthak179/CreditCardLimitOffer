package com.example.SarthakGupta_Assignment_VegaPay.Service;

import com.example.SarthakGupta_Assignment_VegaPay.DTO.AccountLimitUpdateDTO;
import com.example.SarthakGupta_Assignment_VegaPay.DTO.OfferCreationDTO;
import com.example.SarthakGupta_Assignment_VegaPay.DTO.UpdateOfferStatusDTO;
import com.example.SarthakGupta_Assignment_VegaPay.Entity.Account;
import com.example.SarthakGupta_Assignment_VegaPay.Entity.Offer;
import com.example.SarthakGupta_Assignment_VegaPay.Enum.LimitType;
import com.example.SarthakGupta_Assignment_VegaPay.Enum.OfferStatus;
import com.example.SarthakGupta_Assignment_VegaPay.Exception.*;
import com.example.SarthakGupta_Assignment_VegaPay.Repository.OfferRepository;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OfferService {
    private final OfferRepository offerRepository;
    private final AccountService accountService;
    public OfferService(@Autowired OfferRepository offerRepository,
                        @Autowired AccountService accountService) {
        this.offerRepository = offerRepository;
        this.accountService = accountService;
    }

    public List<Offer> getActiveOffersByAccountId(Long id) throws NoOffersFoundException {

        List<Offer> receivedOffers = offerRepository.findByAccountId(id);

        LocalDateTime ldt = LocalDateTime.now();
        String dateNow = (DateTimeFormatter.ISO_DATE_TIME).format(ldt);
        LocalDateTime finalLdt = LocalDateTime.parse(dateNow);

        receivedOffers = receivedOffers.stream()
                        .filter(i -> (i.getOfferActivationTime().isBefore(finalLdt)) && (i.getOfferExpiry().isAfter(finalLdt)) && (i.getOfferStatus().equals(OfferStatus.PENDING)))
                        .collect(Collectors.toList());

        if(receivedOffers.isEmpty()) {
            throw new NoOffersFoundException("No active Offers exist for this account Id");
        }
        return receivedOffers;
    }

    /*
    creates and offer for an account with validation checks on limits
     */
    public Offer createOffer(OfferCreationDTO offerCreationDTO) throws InvalidDateFormatException, NoAccountFoundException, LimitExceededException {

        String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";

        try {
            DateFormat df = new SimpleDateFormat(DATE_FORMAT);
            df.setLenient(false);
            df.parse(offerCreationDTO.getOfferActivationTime());
            df.parse(offerCreationDTO.getOfferExpiryTime());
        } catch (ParseException e) {
            throw new InvalidDateFormatException();
        }


        LocalDateTime offerActivationLdt = LocalDateTime.parse(offerCreationDTO.getOfferActivationTime());
        LocalDateTime offerExpiryLdt = LocalDateTime.parse(offerCreationDTO.getOfferExpiryTime());

        Account receivedAccount = accountService.getAccountById(offerCreationDTO.getAccountId());

        //validation for new per transaction limit exceeding the account limit
        if(offerCreationDTO.getLimitType() == LimitType.PER_TRANSACTION_LIMIT) {
            if(receivedAccount.getAccountLimit() < offerCreationDTO.getNewLimit()) {
                throw new LimitExceededException("Per Transaction limit cannot be more than the account limit");
            }
        }

        //validation for new account limit lower than the per transaction limit
        if(offerCreationDTO.getLimitType() == LimitType.ACCOUNT_LIMIT) {
            if(receivedAccount.getPerTransactionLimit() > offerCreationDTO.getNewLimit()) {
                throw new LimitExceededException("Per Transaction limit cannot be more than the account limit");
            }
        }

        Offer offer = Offer.builder()
                .limitType(offerCreationDTO.getLimitType())
                .newLimit(offerCreationDTO.getNewLimit())
                .offerActivationTime(offerActivationLdt)
                .offerExpiry(offerExpiryLdt)
                .offerStatus(OfferStatus.PENDING)
                .account(receivedAccount).build();

        return offerRepository.save(offer);
    }

    /*
    Updates the offer and account after accepting an active offer
     */
    public Offer updateOffer(UpdateOfferStatusDTO offerStatusDTO) throws NoAccountFoundException, NoOffersFoundException, OfferAlreadyExpiredException {
        LocalDateTime ldt = LocalDateTime.now();
        String dateNow = (DateTimeFormatter.ISO_DATE_TIME).format(ldt);
        LocalDateTime finalLdt = LocalDateTime.parse(dateNow);

        Optional<Offer> offerOptional = offerRepository.findById(offerStatusDTO.getOfferId());
        if(offerOptional.isEmpty()) {
            throw  new NoOffersFoundException("No Offer exists with this ID");
        }
        Offer receivedOffer = offerOptional.get();

        //check for offer expiry date before updating the offer
        if(finalLdt.isAfter(receivedOffer.getOfferExpiry())) {
            throw new OfferAlreadyExpiredException("Offer already expired.");
        }

        //reject offer without updating account
        if(offerStatusDTO.getOfferStatus() == OfferStatus.REJECTED) {
            receivedOffer.setOfferStatus(OfferStatus.REJECTED);
            return offerRepository.save(receivedOffer);
        }

        receivedOffer.setOfferStatus(OfferStatus.ACCEPTED);
        AccountLimitUpdateDTO accountLimitUpdateDTO = new AccountLimitUpdateDTO();
        accountLimitUpdateDTO.setAccountId(receivedOffer.getAccount().getId());
        accountLimitUpdateDTO.setLimitType(receivedOffer.getLimitType());
        accountLimitUpdateDTO.setNewLimit(receivedOffer.getNewLimit());

        Account updatedAccount = accountService.updateAccountLimit(accountLimitUpdateDTO);
        return offerRepository.save(receivedOffer);
    }
}

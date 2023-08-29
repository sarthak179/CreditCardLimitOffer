package com.example.CreditCardLimitOffer.Service;

import com.example.CreditCardLimitOffer.Commons.Variables;
import com.example.CreditCardLimitOffer.DTO.AccountCreationDTO;
import com.example.CreditCardLimitOffer.DTO.AccountLimitUpdateDTO;
import com.example.CreditCardLimitOffer.Entity.Account;
import com.example.CreditCardLimitOffer.Enum.LimitType;
import com.example.CreditCardLimitOffer.Exception.NoAccountFoundException;
import com.example.CreditCardLimitOffer.Repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AccountService {
    private final AccountRepository accountRepository;
    public AccountService(@Autowired AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public Account getAccountById(Long id) throws NoAccountFoundException {
        Optional<Account> receivedAccount = accountRepository.findById(id);
        if (receivedAccount.isEmpty()) {
            throw new NoAccountFoundException("No account exists with this ID");
        }
        return receivedAccount.get();
    }

    public Account createAccount(AccountCreationDTO accountCreationDTO) {
        LocalDateTime ldt = LocalDateTime.now();
        String dateNow = (DateTimeFormatter.ISO_DATE_TIME).format(ldt);
        ldt = LocalDateTime.parse(dateNow);

        Account account = Account.builder()
                .firstName(accountCreationDTO.getFirstName())
                .lastName(accountCreationDTO.getLastName())
                .cardNumber(accountCreationDTO.getCardNumber())
                .accountLimit(accountCreationDTO.getAccountLimit())
                .perTransactionLimit(accountCreationDTO.getPerTransactionLimit())
                .lastAccountLimit(Variables.ZERO)
                .lastPerTransactionLimit(Variables.ZERO)
                .accountLimitUpdateTime(ldt)
                .perTransactionLimitUpdateTime(ldt)
                .customerId(UUID.randomUUID().toString().replace("-", "")).build();

        return accountRepository.save(account);
    }

    /*
    Updates account limit after accepting an active offer
     */
    public Account updateAccountLimit(AccountLimitUpdateDTO accountLimitUpdateDTO) throws NoAccountFoundException {
        Optional<Account> receivedAccount = accountRepository.findById(accountLimitUpdateDTO.getAccountId());
        if(receivedAccount.isEmpty()) {
            throw new NoAccountFoundException("No account exists with this ID");
        }

        Account account = receivedAccount.get();

        if(accountLimitUpdateDTO.getLimitType() == LimitType.PER_TRANSACTION_LIMIT) {
            account.setPerTransactionLimit(accountLimitUpdateDTO.getNewLimit());
            account.setAccountLimitUpdateTime(LocalDateTime.now());
        }
        else {
            account.setPerTransactionLimit(accountLimitUpdateDTO.getNewLimit());
            account.setPerTransactionLimitUpdateTime(LocalDateTime.now());
        }

        return accountRepository.save(account);
    }
}

package com.example.SarthakGupta_Assignment_VegaPay.Service;

import com.example.SarthakGupta_Assignment_VegaPay.Commons.Variables;
import com.example.SarthakGupta_Assignment_VegaPay.DTO.AccountCreationDTO;
import com.example.SarthakGupta_Assignment_VegaPay.DTO.AccountLimitUpdateDTO;
import com.example.SarthakGupta_Assignment_VegaPay.Entity.Account;
import com.example.SarthakGupta_Assignment_VegaPay.Enum.LimitType;
import com.example.SarthakGupta_Assignment_VegaPay.Exception.NoAccountFoundException;
import com.example.SarthakGupta_Assignment_VegaPay.Repository.AccountRepository;
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

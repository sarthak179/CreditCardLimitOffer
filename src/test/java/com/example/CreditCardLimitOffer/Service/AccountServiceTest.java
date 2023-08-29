package com.example.CreditCardLimitOffer.Service;

import com.example.CreditCardLimitOffer.Commons.Variables;
import com.example.CreditCardLimitOffer.Controller.AccountController;
import com.example.CreditCardLimitOffer.DTO.AccountCreationDTO;
import com.example.CreditCardLimitOffer.Entity.Account;
import com.example.CreditCardLimitOffer.Exception.NoAccountFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class AccountServiceTest {
    @MockBean
    private AccountController accountController;
    @MockBean
    private AccountService accountService;

    private List<Account> accountList = new ArrayList<>();
    @BeforeEach
    public void setup() {
        Account account_user1 = Account.builder()
                .firstName("user1")
                .lastName("test")
                .cardNumber("123456789101234")
                .accountLimit(10000.0)
                .perTransactionLimit(1000.0)
                .customerId(String.valueOf(UUID.randomUUID()))
                .lastAccountLimit(Variables.ZERO)
                .lastPerTransactionLimit(Variables.ZERO)
                .accountLimitUpdateTime(LocalDateTime.now())
                .perTransactionLimitUpdateTime(LocalDateTime.now()).build();

        Account account_user2 = Account.builder()
                .firstName("user2")
                .lastName("test")
                .cardNumber("123456789101456")
                .accountLimit(20000.0)
                .perTransactionLimit(2000.0)
                .customerId(String.valueOf(UUID.randomUUID()))
                .lastAccountLimit(Variables.ZERO)
                .lastPerTransactionLimit(Variables.ZERO)
                .accountLimitUpdateTime(LocalDateTime.now())
                .perTransactionLimitUpdateTime(LocalDateTime.now()).build();

        accountList.add(account_user1);
        accountList.add(account_user2);
    }

    @Test
    public void save_Account_Success() {

        AccountCreationDTO accountCreationDTO = new AccountCreationDTO();
        Account account1 = accountList.get(0);

        accountCreationDTO.setFirstName(account1.getFirstName());
        accountCreationDTO.setLastName(account1.getLastName());
        accountCreationDTO.setAccountLimit(account1.getAccountLimit());
        accountCreationDTO.setPerTransactionLimit(account1.getPerTransactionLimit());
        accountCreationDTO.setCardNumber(account1.getCardNumber());

        when(accountService.createAccount(accountCreationDTO)).thenReturn(accountList.get(0));

        Account account_1 = accountService.createAccount(accountCreationDTO);
        assertNotEquals(account_1, null);
    }

    @Test
    public void find_Account_By_Id_Success() throws NoAccountFoundException {

        AccountCreationDTO accountCreationDTO = new AccountCreationDTO();
        Account account1 = accountList.get(0);

        when(accountService.getAccountById(account1.getId())).thenReturn(accountList.get(0));

        Account account_1 = accountService.getAccountById(account1.getId());
        assertSame(account_1, accountList.get(0));
    }
}

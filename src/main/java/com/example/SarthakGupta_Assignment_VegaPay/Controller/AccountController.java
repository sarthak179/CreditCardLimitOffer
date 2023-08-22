package com.example.SarthakGupta_Assignment_VegaPay.Controller;

import com.example.SarthakGupta_Assignment_VegaPay.DTO.AccountCreationDTO;
import com.example.SarthakGupta_Assignment_VegaPay.Entity.Account;
import com.example.SarthakGupta_Assignment_VegaPay.Exception.NoAccountFoundException;
import com.example.SarthakGupta_Assignment_VegaPay.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    private final AccountService accountService;
    public AccountController(@Autowired AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("")
    ResponseEntity<List<Account>> getAllAccounts() {
        return ResponseEntity.ok().body(accountService.getAllAccounts());
    }

    @GetMapping("/{id}")
    ResponseEntity<Account> getAccountById(@PathVariable Long id) throws NoAccountFoundException {
        return ResponseEntity.ok().body(accountService.getAccountById(id));
    }

    @PostMapping("")
    ResponseEntity<Account> createAccount(@RequestBody AccountCreationDTO accountCreationDTO) {
        return ResponseEntity.ok().body(accountService.createAccount(accountCreationDTO));
    }

}

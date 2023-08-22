package com.example.SarthakGupta_Assignment_VegaPay.Repository;

import com.example.SarthakGupta_Assignment_VegaPay.Entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    public List<Account> findAll();
    public Optional<Account> findById(Long id);
}

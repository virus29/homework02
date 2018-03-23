package com.i.homework02.repository;

import com.i.homework02.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
    public Account findAccountByActivationCode(String activationCode);
    public Account findAccountByLogin(String login);
}

package com.i.homework02.account;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
    public Account findAccountByActivationCode(String activationCode);
    public Account findAccountByLogin(String login);
}

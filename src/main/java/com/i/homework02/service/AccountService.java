package com.i.homework02.service;

import com.i.homework02.entity.Account;
import com.i.homework02.exeption.CustomAccountException;

public interface AccountService {

    /**
     * Регистрация аккаунта
     * @param account - обЪект, который содержит login, password, name аккаунта
     */
    public void registration (Account account) throws CustomAccountException;

    /**
     * Активация аккаунта по коду
     * @param code - код активации высланный на почту
     */
    public void activation(String code) throws CustomAccountException;

    /**
     * Вход в систему, через верификация аккаунта
     * @param account обЪект, который содержит login, password аккаунта
     * @return подтверждение входа true или false
     */
    public boolean logIn(Account account) throws CustomAccountException;

    /**
     * Иммитация получение активационного кода по электронной почте, для нужд тестирования
     * @param account -содержит login аккаунта
     * @return возвращает активационный код аккаунта
     */
    public String getActivationCode(Account account);
}

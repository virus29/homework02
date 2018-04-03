package com.i.homework02.service;

import com.i.homework02.exeption.CustomAccountException;
import com.i.homework02.view.AccountViewRequest;

import java.text.ParseException;

public interface AccountService {

    /**
     * Регистрация аккаунта
     * @param accountViewRequest - обЪект, который содержит login, password, name аккаунта
     */
    public void registration (AccountViewRequest accountViewRequest) throws CustomAccountException, ParseException;

    /**
     * Активация аккаунта по коду
     * @param code - код активации высланный на почту
     */
    public void activation(String code) throws CustomAccountException;

    /**
     * Вход в систему, через верификация аккаунта
     * @param accountViewRequest обЪект, который содержит login, password аккаунта
     * @return подтверждение входа true или false
     */
    public boolean logIn(AccountViewRequest accountViewRequest) throws CustomAccountException, ParseException;

    /**
     * Иммитация получение активационного кода по электронной почте, для нужд тестирования
     * @param accountViewRequest -содержит login аккаунта
     * @return возвращает активационный код аккаунта
     */
    public String getActivationCode(AccountViewRequest accountViewRequest) throws ParseException;
}

package com.i.homework02.service;

import com.i.homework02.exeption.CustomAccountException;
import com.i.homework02.view.AccountView;

public interface AccountService {

    /**
     * Регистрация аккаунта
     * @param accountView - обЪект, который содержит login, password, name аккаунта
     */
    public void registration (AccountView accountView) throws CustomAccountException;

    /**
     * Активация аккаунта по коду
     * @param code - код активации высланный на почту
     */
    public void activation(String code) throws CustomAccountException;

    /**
     * Вход в систему, через верификация аккаунта
     * @param accountView обЪект, который содержит login, password аккаунта
     * @return подтверждение входа true или false
     */
    public boolean logIn(AccountView accountView) throws CustomAccountException;

    /**
     * Иммитация получение активационного кода по электронной почте, для нужд тестирования
     * @param accountView -содержит login аккаунта
     * @return возвращает активационный код аккаунта
     */
    public String getActivationCode(AccountView accountView);
}

package com.i.homework02.servise;

import com.i.homework02.exeption.CustomAccountException;
import com.i.homework02.view.AccountView;

public interface AccountService {

    /**
     * Регистрация аккаунта
     * @param accountView
     */
    public void registration (AccountView accountView) throws CustomAccountException;

    /**
     * Активация аккаунта по коду
     * @param code - код активации высланный на почту
     */
    public void activation(String code) throws CustomAccountException;

    /**
     * Вход в систему, через верификация аккаунта
     * @param accountView
     * @return
     */
    public boolean logIn(AccountView accountView) throws CustomAccountException;

    /**
     * Иммитация получение активационного кода по электронной почте, для нужд тестирования
     * @param accountView
     * @return
     */
    public String getActivationCode(AccountView accountView);
}

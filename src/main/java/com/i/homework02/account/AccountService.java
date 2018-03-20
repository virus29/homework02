package com.i.homework02.account;

import view.AccountView;

public interface AccountService {
    //Регистрация аккаунта
    public void registration (AccountView accountView);

    //Активация аккаунта по коду
    public void activation(String code);

    //Вход в систему, через верификация аккаунта
    public boolean logIn(AccountView accountView);

    //Иммитация получение активационного кода по электронной почте, для нужд тестирования
    public String getActivationCode(AccountView accountView);
}

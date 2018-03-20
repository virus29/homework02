package com.i.homework02.account;

import com.i.homework02.exeption.CustomAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import view.AccountView;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.stream.Collectors;

@Service
@Repository
@Transactional
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountRepository accountRepository;

    //Генерация рандомной строки
    public static String randomCode() {
        int codeLength = 25;
        String id = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstvwxyz123456789";
        return new SecureRandom()
                .ints(codeLength, 0, id.length())
                .mapToObj(id::charAt)
                .map(Object::toString)
                .collect(Collectors.joining());
    }

    //Кодировщик
    public String codingValue(String value) {
        try {
            MessageDigest encoder = MessageDigest.getInstance("SHA-256");
            byte[] digest = encoder.digest(value.getBytes("UTF-8"));
            return new String(Base64.getEncoder().encode(digest));
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
            throw new CustomAccountException("Ошибка верификации логина: ошибка кодирования пароля", e);
        }
    }

    //Иммитация получение активационного кода по электронной почте, для нужд тестирования
    @Override
    public String getActivationCode(AccountView accountView) {
        return accountRepository.findAccountByLogin(accountView.getLogin()).getCode();
    }

    //Иммитация метода, для отправка активационного кода на на электронную почту(здесь же сохраняем отдельным полем в таблице)
    public void sendMessageWithCodeToEmail(String code,Account account){
        account.setCode(code);
        accountRepository.save(account);

    }

    //Регистрация Аккаунта
    @Override
    public void registration(AccountView accountView) {
        if (accountRepository.findAccountByLogin(accountView.getLogin()) == null) {
            String code = randomCode();
            Account sAccount = new Account();
            sAccount.setPassword(codingValue(accountView.getPassword()));
            sAccount.setLogin(accountView.getLogin());
            sAccount.setName(accountView.getName());
            sAccount.setActivationCode(codingValue(code));
            accountRepository.save(sAccount);
            //отправляем активационный код на электронную почту
            sendMessageWithCodeToEmail(code, sAccount);
        } else {
            throw new CustomAccountException("Аккаунт с таким Логином уже сущетвует! Для регистрации введите уникальный Логин.");
        }
    }

    //Активация кода, присланного на электронную почту
    @Override
    public void activation(String code) {
        if (accountRepository.findAccountByActivationCode(codingValue(code))!= null) {
            Account account = accountRepository.findAccountByActivationCode(codingValue(code));
            account.setActive(true);
            accountRepository.save(account);
        } else {
            throw new CustomAccountException("Аккаунта с переданным активационным кодом не существует!");
        }
    }

    //Вход в систему(если прошло верификацию возвращаем true, если нет, то false)
    @Override
    public boolean logIn(AccountView accountView) {
        if (accountRepository.findAccountByLogin(accountView.getLogin()) != null) {
            if (accountRepository.findAccountByLogin(accountView.getLogin()).getActive() == true) {
                accountView.setPassword(codingValue(accountView.getPassword()));
                if (accountRepository.findAccountByLogin(accountView.getLogin()).getPassword().equals(accountView.getPassword())) {
                    return true;
                } else {
                    new CustomAccountException("Введен неверный пароль!");
                }
            } else {
                new CustomAccountException("Аккаунт не активирован, для входа в систему активируте код присланный на вашу электронную почту, который вы указали во время регистрации!");
            }
        } else {
            new CustomAccountException("Аккаунта с таким Логином не сущетвует! Введите свой Логин.");
        }
        return false;
    }
}

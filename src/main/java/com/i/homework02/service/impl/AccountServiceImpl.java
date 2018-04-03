package com.i.homework02.service.impl;

import com.i.homework02.entity.Account;
import com.i.homework02.repository.AccountRepository;
import com.i.homework02.exeption.CustomAccountException;
import com.i.homework02.service.AccountService;
import com.i.homework02.view.AccountViewRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.ParseException;
import java.util.Base64;
import java.util.stream.Collectors;

@Service
@Repository

public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountRepository accountRepository;

    /**
     * Генерация рандомной строки
     * @return рандомная строка
     */
    public static String randomCode() {
        int codeLength = 25;
        String id = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstvwxyz0123456789";
        return new SecureRandom()
                .ints(codeLength, 0, id.length())
                .mapToObj(id::charAt)
                .map(Object::toString)
                .collect(Collectors.joining());
    }

    /**
     * Кодировщик
     * @param value - строка для хеширования и кодирования
     * @return захешированная и закодированная строка
     */
    public String codingValue(String value) throws CustomAccountException {
        try {
            MessageDigest encoder = MessageDigest.getInstance("SHA-256");
            byte[] digest = encoder.digest(value.getBytes("UTF-8"));
            return new String(Base64.getEncoder().encode(digest));
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
            throw new CustomAccountException("Ошибка верификации логина: ошибка кодирования пароля", e);
        }
    }


    /**
     * Иммитация получение активационного кода по электронной почте, для нужд тестирования
     * @param accountViewRequest
     * @return активационный код
     */
    @Override
    @Transactional(readOnly = true)
    public String getActivationCode(AccountViewRequest accountViewRequest) throws ParseException {
        Account account = convertToEntity(accountViewRequest);
        return accountRepository.findAccountByLogin(account.getLogin()).getCode();
    }

    /**
     * Иммитация метода, для отправка активационного кода на на электронную почту(здесь же сохраняем отдельным полем в таблице)
     * @param code    - активационный код
     * @param account - данные аккаунта пользователя
     */
    @Transactional(readOnly = true)
    public void sendMessageWithCodeToEmail(String code, Account account) {
        account.setCode(code);
        accountRepository.save(account);

    }

    /**
     * Регистрация Аккаунта
     * @param accountViewRequest
     */
    @Override
    @Transactional
    public void registration(AccountViewRequest accountViewRequest) throws CustomAccountException, ParseException {
        Account account = convertToEntity(accountViewRequest);
        if (accountRepository.findAccountByLogin(account.getLogin()) == null) {
            String code = randomCode();
            Account sAccount = new Account();
            sAccount.setPassword(codingValue(account.getPassword()));
            sAccount.setLogin(account.getLogin());
            sAccount.setName(account.getName());
            sAccount.setActivationCode(codingValue(code));
            accountRepository.save(sAccount);
            //отправляем активационный код на электронную почту
            sendMessageWithCodeToEmail(code, sAccount);
        } else {
            throw new CustomAccountException("Аккаунт с таким Логином уже сущетвует! Для регистрации введите уникальный Логин.");
        }
    }

    /**
     * Активация кода, присланного на электронную почту
     * @param code - код активации высланный на почту
     */
    @Override
    @Transactional
    public void activation(String code) throws CustomAccountException {
        if (accountRepository.findAccountByActivationCode(codingValue(code)) != null) {
            Account account = accountRepository.findAccountByActivationCode(codingValue(code));
            account.setActive(true);
            accountRepository.save(account);
        } else {
            throw new CustomAccountException("Аккаунта с переданным активационным кодом не существует!");
        }
    }

    /**
     * Вход в систему(если прошло верификацию возвращаем true, если нет, то false)
     * @param accountViewRequest - аккаунт с передоваемыми параметрами login и password
     * @return если true, то вошли. если false, то не вошли
     */
    @Override
    @Transactional(readOnly = true)
    public boolean logIn(AccountViewRequest accountViewRequest) throws CustomAccountException, ParseException {
        Account account = convertToEntity(accountViewRequest);
        String login= account.getLogin();
        String password = account.getPassword();
        if (accountRepository.findAccountByLogin(login)!= null) {
            com.i.homework02.entity.Account acc=accountRepository.findAccountByLogin(login);
            if (acc.getActive()) {
                String codingPassword = codingValue(password);
                account.setPassword(codingPassword);
                if (acc.getPassword().equals(password)) {
                    return true;
                } else {
                   throw new CustomAccountException("Введен неверный пароль!");
                }
            } else {
                throw new CustomAccountException("Аккаунт не активирован, для входа в систему активируте код присланный на вашу электронную почту, который вы указали во время регистрации!");
            }
        } else {
            throw new CustomAccountException("Аккаунта с таким Логином не сущетвует! Введите свой Логин ли зарегистрируйтесь.");
        }
    }
    @Autowired
    private ModelMapper modelMapper;

    public Account convertToEntity(AccountViewRequest accountViewRequest) throws ParseException {
        Account account=modelMapper.map(accountViewRequest, Account.class);
        return account;
    }
}



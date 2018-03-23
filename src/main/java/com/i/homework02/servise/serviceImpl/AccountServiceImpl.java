package com.i.homework02.servise.serviceImpl;

import com.i.homework02.repository.AccountRepository;
import com.i.homework02.entity.Account;
import com.i.homework02.exeption.CustomAccountException;
import com.i.homework02.servise.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.i.homework02.view.AccountView;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.stream.Collectors;

@Service
@Repository

public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountRepository accountRepository;

    /**
     * Генерация рандомной строки
     *
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
     *
     * @param value - строка для хеширования и кодирования
     * @return захешированная и закодированная строка
     */
    @Transactional(readOnly = true)
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
     *
     * @param accountView
     * @return активационный код
     */
    @Override
    @Transactional(readOnly = true)
    public String getActivationCode(AccountView accountView) {
        return accountRepository.findAccountByLogin(accountView.getLogin()).getCode();
    }

    /**
     * Иммитация метода, для отправка активационного кода на на электронную почту(здесь же сохраняем отдельным полем в таблице)
     *
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
     *
     * @param accountView
     */
    @Override
    @Transactional
    public void registration(AccountView accountView) throws CustomAccountException {
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

    /**
     * Активация кода, присланного на электронную почту
     *
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
     *
     * @param accountView - аккаунт с передоваемыми параметрами login и password
     * @return если true, то вошли. если false, то не вошли
     */
    @Override
    @Transactional(readOnly = true)
    public boolean logIn(AccountView accountView) throws CustomAccountException {
        String loginAcc=accountView.getLogin();
        String password = accountView.getPassword();
        if (accountRepository.findAccountByLogin(loginAcc)!= null) {
            Account acc=accountRepository.findAccountByLogin(loginAcc);
            if (acc.getActive()) {
                String codingPassword = codingValue(password);
                accountView.setPassword(codingPassword);
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
}



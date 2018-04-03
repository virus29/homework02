package com.i.homework02.controller;

import com.i.homework02.entity.Account;
import com.i.homework02.exeption.CustomAccountException;
import com.i.homework02.service.impl.AccountServiceImpl;
import com.i.homework02.view.AccountViewRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import com.i.homework02.view.PositiveResponseView;

import javax.validation.Valid;
import java.text.ParseException;




@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class AccountController {


    @Autowired
    AccountServiceImpl accountServiceImpl;

    /**
     * Регистрация аккаунта
     * @param accountViewRequest - обЪект, который содержит login, password, name аккаунта
     */
    @PostMapping(value = "/register")
    public ResponseEntity registrationAccount(@RequestBody @Valid AccountViewRequest accountViewRequest) throws CustomAccountException, ParseException {
        Account account=accountServiceImpl.convertToEntity(accountViewRequest);
        accountServiceImpl.registration(accountViewRequest);
        return new ResponseEntity<>(new PositiveResponseView(), HttpStatus.CREATED);
    }

    /**
     * Активация аккаунта по коду
     * @param code - код активации высланный на почту
     */
    @GetMapping(path = "/activation/{code}")
    public ResponseEntity findOfficeById(@PathVariable String code) throws CustomAccountException {
        accountServiceImpl.activation(code);
        return new ResponseEntity<>(new PositiveResponseView(), HttpStatus.OK);
    }

    /**
     * Вход в систему, через верификация аккаунта
     * @param accountViewRequest обЪект, который содержит login, password аккаунта
     * @return подтверждение входа true или false
     */
    @PostMapping(value = "/login")
    public ResponseEntity logInAccount(@RequestBody @Valid AccountViewRequest accountViewRequest) throws CustomAccountException, ParseException {
        Account account=accountServiceImpl.convertToEntity(accountViewRequest);
        accountServiceImpl.logIn(accountViewRequest);
        return new ResponseEntity<>(new PositiveResponseView(), HttpStatus.OK);
    }

    /**
     * Иммитация получение активационного кода по электронной почте, для нужд тестирования
     * @param accountViewRequest -содержит login аккаунта
     * @return возвращает активационный код аккаунта
     */
    @PostMapping(value = "/getactivationcode")
    public ResponseEntity getActivationCode(@RequestBody AccountViewRequest accountViewRequest) throws ParseException {
        Account account=accountServiceImpl.convertToEntity(accountViewRequest);
        String s = accountServiceImpl.getActivationCode(accountViewRequest);
        return new ResponseEntity<>(s, HttpStatus.OK);
    }
}

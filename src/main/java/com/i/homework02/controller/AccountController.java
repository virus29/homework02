package com.i.homework02.controller;

import com.i.homework02.exeption.CustomAccountException;
import com.i.homework02.servise.serviceImpl.AccountServiceImpl;
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
import com.i.homework02.view.AccountView;
import com.i.homework02.view.PositiveResponseView;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class AccountController {

    @Autowired
    AccountServiceImpl accountServiceImpl;

    //Регистрация аккаунта
    /**
     *
     * @param accountView
     * @return
     */
    @PostMapping(value = "/register")
    public ResponseEntity registrationAccount(@RequestBody @Valid AccountView accountView) throws CustomAccountException {
        accountServiceImpl.registration(accountView);
        return new ResponseEntity<>(new PositiveResponseView(), HttpStatus.CREATED);
    }
    //Активация аккаунта по коду

    /**
     *
     * @param code
     * @return
     */
    @GetMapping(path = "/activation/{code}")
    public ResponseEntity findOfficeById(@PathVariable String code) throws CustomAccountException {
        accountServiceImpl.activation(code);
        return new ResponseEntity<>(new PositiveResponseView(), HttpStatus.OK);
    }

    //Вход в систему, через верификация аккаунта

    /**
     *
     * @param accountView
     * @return
     */
    @PostMapping(value = "/login")
    public ResponseEntity logInAccount(@RequestBody @Valid AccountView accountView) throws CustomAccountException {
        accountServiceImpl.logIn(accountView);
        return new ResponseEntity<>(new PositiveResponseView(), HttpStatus.OK);
    }

    //Иммитация получение активационного кода по электронной почте, для нужд тестирования

    /**
     *
     * @param accountView
     * @return
     */
    @PostMapping(value = "/getactivationcode")
    public ResponseEntity getActivationCode(@RequestBody AccountView accountView) {
        String s = accountServiceImpl.getActivationCode(accountView);
        return new ResponseEntity<>(s, HttpStatus.OK);
    }
}

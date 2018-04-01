package com.i.homework02.controller;

import com.i.homework02.Homework02Application;
import com.i.homework02.entity.Account;
import com.i.homework02.exeption.CustomAccountException;
import com.i.homework02.exeption.MainExceptionHandler;
import com.i.homework02.repository.AccountRepository;
import com.i.homework02.service.impl.AccountServiceImpl;
import com.i.homework02.view.AccountViewRequest;
import com.i.homework02.view.NegativeResponseView;
import com.i.homework02.view.PositiveResponseView;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestClientException;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = Homework02Application.class)
public class AccountControllerITest {
    @Autowired
    TestRestTemplate restTemplate;

    HttpHeaders headers = new HttpHeaders();

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    AccountServiceImpl accountServiceImpl;


    @Before
    public void init() {
        headers.setContentType(MediaType.APPLICATION_JSON);
        accountRepository.deleteAll();
    }

    public void after() {
        accountRepository.deleteAll();
    }

    @Test
    public void registrationAccountPositiveTest() throws Exception {
        accountRepository.deleteAll();

        AccountViewRequest accountViewRequest = new AccountViewRequest();
        accountViewRequest.setLogin("vasya@mail.ru");
        accountViewRequest.setPassword("123");
        accountViewRequest.setName("Тест");
        HttpEntity entity = new HttpEntity<>(accountViewRequest, headers);

        ResponseEntity<PositiveResponseView> response = restTemplate.exchange("/api/register", HttpMethod.POST, entity, PositiveResponseView.class);
        PositiveResponseView result = response.getBody();
        PositiveResponseView expected = new PositiveResponseView();
        Assert.assertEquals(expected, result);
        assertNotNull(accountRepository.findAccountByLogin("vasya@mail.ru"));
    }

    @Test
    public void registrationAccountNegativeTest() throws Exception {
        com.i.homework02.entity.Account account = new com.i.homework02.entity.Account();
        account.setLogin("test@mail.ru");
        account.setPassword("1234");
        accountRepository.save(account);

        AccountViewRequest accountViewRequest = new AccountViewRequest();
        accountViewRequest.setLogin("test@mail.ru");
        accountViewRequest.setPassword("123");
        accountViewRequest.setName("Тест");
        HttpEntity entity = new HttpEntity<>(accountViewRequest, headers);

        ResponseEntity<NegativeResponseView> response = restTemplate.exchange("/api/register", HttpMethod.POST, entity, NegativeResponseView.class);
        NegativeResponseView result = response.getBody();

        NegativeResponseView expected = new NegativeResponseView("Аккаунт с таким Логином уже сущетвует! Для регистрации введите уникальный Логин.");

        Assert.assertEquals(expected, result);
    }

    @Test
    public void getActivationCodePositiveTest() throws Exception {
        Account account = new Account();
        account.setLogin("test@mail.ru");
        account.setPassword("123");
        account.setName("Test");
        accountServiceImpl.registration(account);
        String code = accountServiceImpl.getActivationCode(account);

        HttpEntity entity = new HttpEntity<>(headers);
        ResponseEntity<PositiveResponseView> response = restTemplate.exchange("/api/activation/" + code, HttpMethod.GET, entity, PositiveResponseView.class);
        PositiveResponseView result = response.getBody();
        PositiveResponseView expected = new PositiveResponseView();
        Assert.assertEquals(expected, result);
        assertNotNull(accountRepository.findAccountByLogin("test@mail.ru"));
        assertTrue(accountRepository.findAccountByLogin("test@mail.ru").getActive());
    }

    @Test
    public void getActivationCodeNegativeTest() throws Exception {
        Account account = new Account();
        account.setLogin("test@mail.ru");
        account.setPassword("123");
        account.setName("Test");
        accountServiceImpl.registration(account);
        String code = accountServiceImpl.getActivationCode(account);
        String fakeCode = "khhfdjdkdthrsfdrexfcgvfdfg345678";

        HttpEntity entity = new HttpEntity<>(headers);
        ResponseEntity<NegativeResponseView> response = restTemplate.exchange("/api/activation/" + fakeCode, HttpMethod.GET, entity, NegativeResponseView.class);
        NegativeResponseView result = response.getBody();
        NegativeResponseView expected = new  NegativeResponseView("Аккаунта с переданным активационным кодом не существует!");
        Assert.assertEquals(expected, result);

    }

    @Test
    public void logInAccountPositiveTest() throws Exception {
        com.i.homework02.entity.Account account = new com.i.homework02.entity.Account();
        account.setLogin("admin@mail.ru");
        account.setPassword("123456");
        account.setActive(true);
        accountRepository.save(account);

        AccountViewRequest accountViewRequest=new AccountViewRequest();
        accountViewRequest.setLogin("admin@mail.ru");
        accountViewRequest.setPassword("123456");
        HttpEntity entity = new HttpEntity<>(accountViewRequest, headers);

        ResponseEntity<PositiveResponseView> response = restTemplate.exchange("/api/login", HttpMethod.POST, entity, PositiveResponseView.class);

        PositiveResponseView result = response.getBody();
        PositiveResponseView expected = new PositiveResponseView();
        Assert.assertEquals(expected, result);
    }

    @Test
    public void logInAccountNegativeTest() throws Exception {
        com.i.homework02.entity.Account account = new com.i.homework02.entity.Account();
        account.setLogin("test@mail.ru");
        account.setPassword("123456");
        account.setActive(true);
        accountRepository.save(account);

        AccountViewRequest accountViewRequest=new AccountViewRequest();
        accountViewRequest.setLogin("test@mail.ru");
        accountViewRequest.setPassword("jh;h;");
        HttpEntity entity = new HttpEntity<>(accountViewRequest, headers);

        ResponseEntity<NegativeResponseView> response = restTemplate.exchange("/api/login", HttpMethod.POST, entity, NegativeResponseView.class);

        NegativeResponseView result = response.getBody();
        NegativeResponseView expected = new  NegativeResponseView("Введен неверный пароль!");
        Assert.assertEquals(expected, result);

        assertNotNull(accountRepository.findAccountByLogin("test@mail.ru"));
        assertTrue(accountRepository.findAccountByLogin("test@mail.ru").getActive());
    }
}
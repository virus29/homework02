package com.i.homework02.controller;

import com.i.homework02.Homework02Application;
import com.i.homework02.entity.Account;
import com.i.homework02.repository.AccountRepository;
import com.i.homework02.servise.serviceImpl.AccountServiceImpl;
import com.i.homework02.view.AccountView;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

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

    @Test
    public void registrationAccountPositiveTest() throws Exception {
        String body = "{\"login\": \"vasya@mail.ru\"," +
                "\"password\": \"123\"," +
                "\"name\": \"Тест\"}";
        HttpEntity entity = new HttpEntity<>(body, headers);

        ResponseEntity<String> response = restTemplate.exchange("/api/register", HttpMethod.POST, entity, String.class);
        String result = response.getBody();
        String expected = "{\"data\":{\"result\":\"success\"}}";
        assertEquals(expected, result, true);
        assertNotNull(accountRepository.findAccountByLogin("vasya@mail.ru"));
    }

    @Test
    public void registrationAccountNegativeTest() throws Exception {
        Account account=new Account();
        account.setLogin("test@mail.ru");
        account.setPassword("1234");
        accountRepository.save(account);

        String body = "{\"login\": \"test@mail.ru\"," +
                "\"password\": \"123\"," +
                "\"name\": \"Тест\"}";
        HttpEntity entity = new HttpEntity<>(body, headers);

        ResponseEntity<String> response = restTemplate.exchange("/api/register", HttpMethod.POST, entity, String.class);
        String result = response.getBody();
        String expected = "{\"error\":\"Аккаунт с таким Логином уже сущетвует! Для регистрации введите уникальный Логин.\"}";
        assertEquals(expected, result, true);
    }

    @Test
    public void getActivationCodePositiveTest() throws Exception {
        AccountView accountView=new AccountView();
        accountView.setLogin("test@mail.ru");
        accountView.setPassword("123");
        accountView.setName("Test");
        accountServiceImpl.registration(accountView);
        String code=accountServiceImpl.getActivationCode(accountView);

        HttpEntity entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange("/api/activation/"+code, HttpMethod.GET, entity, String.class);
        String result = response.getBody();
        String expected = "{\"data\":{\"result\":\"success\"}}";
        assertEquals(expected, result, true);
        assertNotNull(accountRepository.findAccountByLogin("test@mail.ru"));
        assertTrue(accountRepository.findAccountByLogin("test@mail.ru").getActive());
    }

    @Test
    public void getActivationCodeNegativeTest() throws Exception {
        AccountView accountView=new AccountView();
        accountView.setLogin("test@mail.ru");
        accountView.setPassword("123");
        accountView.setName("Test");
        accountServiceImpl.registration(accountView);
        String code=accountServiceImpl.getActivationCode(accountView);
        String fakeCode="khhfdjdkdthrsfdrexfcgvfdfg345678";

        HttpEntity entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange("/api/activation/"+fakeCode, HttpMethod.GET, entity, String.class);
        String result = response.getBody();
        String expected = "{\"error\": \"Аккаунта с переданным активационным кодом не существует!\"}";
        assertEquals(expected, result, true);

    }

    @Test
    public void logInAccountPositiveTest() throws Exception {
        AccountView accountView=new AccountView();
        accountView.setLogin("test@mail.ru");
        accountView.setPassword("123");
        accountView.setName("Test");
        accountServiceImpl.registration(accountView);
        String code=accountServiceImpl.getActivationCode(accountView);
        accountServiceImpl.activation(code);

        String body="{\"login\":\"test@mail.ru\"," +
                "\"password\":\"123\"}";
        HttpEntity entity = new HttpEntity<>(body,headers);
        ResponseEntity<String> response = restTemplate.exchange("/api/login", HttpMethod.POST, entity, String.class);
        String result = response.getBody();
        String expected = "{\"data\":{\"result\":\"success\"}}";
        assertEquals(expected, result, true);
    }

    @Test
    public void logInAccountNegativeTest() throws Exception {
        AccountView accountView=new AccountView();
        accountView.setLogin("test@mail.ru");
        accountView.setPassword("123");
        accountView.setName("Test");
        accountServiceImpl.registration(accountView);
        String code=accountServiceImpl.getActivationCode(accountView);
        accountServiceImpl.activation(code);

        String body="{\"login\":\"test@mail.ru\"," +
                "\"password\":\"jh;h;\"}";
        HttpEntity entity = new HttpEntity<>(body,headers);
        ResponseEntity<String> response = restTemplate.exchange("/api/login", HttpMethod.POST, entity, String.class);
        String result = response.getBody();
        String expected = "{\"error\":\"Введен неверный пароль!\"}";
        assertEquals(expected, result, true);
//        assertNotNull(accountRepository.findAccountByLogin("test@mail.ru"));
//        assertTrue(accountRepository.findAccountByLogin("test@mail.ru").getActive());
    }
}
package com.i.homework02.controller;

import com.i.homework02.Homework02Application;
import com.i.homework02.entity.*;
import com.i.homework02.repository.*;
import com.i.homework02.view.DataView;
import com.i.homework02.view.UserListViewRequest;
import com.i.homework02.view.UserListViewResponse;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = Homework02Application.class)
public class UserControllerITest {
    @Autowired
    TestRestTemplate restTemplate;

    HttpHeaders headers = new HttpHeaders();

    @Autowired
    OrganizationRepository organizationRepository;

    @Autowired
    OfficeRepository officeRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CountryRepository countryRepository;

    @Autowired
    DocTypeRepository docTypeRepository;

    @Before
    public void init() {
        headers.setContentType(MediaType.APPLICATION_JSON);


        Organization organization = new Organization("Тестовая организация");
        organizationRepository.save(organization);

        Office office = new Office("Тестовый офис", "Киров, ул. Серова, д.2", "+7(8532)45-45-45", true, organizationRepository.findOrganizationByName("Тестовая организация"));
        officeRepository.save(office);

        Country country = new Country("643", "Российская Федерация");
        countryRepository.save(country);

        DocType docType = new DocType("21", "Паспорт гражданина Российской Федерации");
        docTypeRepository.save(docType);

        User user = new User();
        user.setFirstName("Тест");
        user.setSecondName("Тестов");
        user.setMiddleName("Тестович");
        user.setPosition("Менеджер");
        user.setPhone("+7(8532)45-45-45");
        user.setDocNumber("4554");
        user.setDocDate(new Date(1435190400000L));/*06/25/2015 @ 12:00am (UTC)*/
        user.setOffice(officeRepository.findOfficeByName(office.getName()));
        user.setCountry(countryRepository.findCountryByCode(country.getCode()));
        user.setDocType(docTypeRepository.findDocTypeByCode(docType.getCode()));
        userRepository.save(user);
    }

    @After
    public void after() {
        organizationRepository.deleteAll();
        officeRepository.deleteAll();
        userRepository.deleteAll();
        countryRepository.deleteAll();
        docTypeRepository.deleteAll();
    }

    @Test
    public void searchUserPositiveTest() throws Exception {
        Long userId = userRepository.findUserByFirstName("Тест").getId();
        Long officeId = officeRepository.findOfficeByName("Тестовый офис").getId();

        UserListViewRequest userListViewRequest = new UserListViewRequest();

        userListViewRequest.setOfficeId(officeId);
        userListViewRequest.setFirstName("Тест");
        userListViewRequest.setSecondName("Тестов");
        userListViewRequest.setMiddleName("Тестович");
        userListViewRequest.setPosition("Менеджер");
        userListViewRequest.setDocCode("21");
        userListViewRequest.setCitizenshipCode("643");

        HttpEntity entity = new HttpEntity<>(userListViewRequest, headers);
        ResponseEntity<DataView<List<UserListViewResponse>>> response = restTemplate.exchange("/api/user/list", HttpMethod.POST, entity, new ParameterizedTypeReference<DataView<List<UserListViewResponse>>>() {});
        DataView<List<UserListViewResponse>> result = response.getBody();

        UserListViewResponse userListViewResponse = new UserListViewResponse();
        userListViewResponse.setId(userId);
        userListViewResponse.setFirstName("Тест");
        userListViewResponse.setSecondName("Тестов");
        userListViewResponse.setMiddleName("Тестович");
        userListViewResponse.setPosition("Менеджер");
        List<UserListViewResponse> listUsers = new ArrayList<>();
        listUsers.add(userListViewResponse);
        DataView<List<UserListViewResponse>> expected = new DataView(listUsers);

        Assert.assertEquals(expected, result);
    }


    @Test
    public void searchUserNegativeTest() throws Exception {

        UserListViewRequest userListViewRequest = new UserListViewRequest();

        userListViewRequest.setOfficeId(5000L);
        userListViewRequest.setFirstName("Тест");

        HttpEntity entity = new HttpEntity<>(userListViewRequest, headers);
        ResponseEntity<String> response = restTemplate.exchange("/api/user/list", HttpMethod.POST, entity, String.class);
        String expected = "{\"error\": \"По заданным параметрам ничего не найдено\"}";
        String result = response.getBody();
        assertEquals(expected, result, true);
        assertNotNull(userRepository.findUserByFirstName("Тест"));
    }

    @Test
    public void findUserByIdPositiveTest() throws Exception {
        Long userId = userRepository.findUserByFirstName("Тест").getId();

        HttpEntity entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange("/api/user/" + userId, HttpMethod.GET, entity, String.class);
        String expected = "{\"data\":{\"id\": " + userId + "," +
                "\"firstName\": \"Тест\"," +
                "\"secondName\": \"Тестов\"," +
                "\"middleName\": \"Тестович\"," +
                "\"position\": \"Менеджер\"," +
                "\"phone\": \"+7(8532)45-45-45\"," +
                "\"docName\": \"Паспорт гражданина Российской Федерации\"," +
                "\"docCode\": \"21\"," +
                "\"docNumber\": \"4554\"," +
                "\"docDate\": \"2015-06-25\"," +/*06/24/2015 @ 12:00am (UTC)*/
                "\"citizenshipName\": \"Российская Федерация\"," +
                "\"citizenshipCode\": \"643\"," +
                "\"isIdentified\": null}}";
        String result = response.getBody();
        assertEquals(expected, result, true);
    }

    @Test
    public void findUserByIdNegativeTest() throws Exception {

        Long userId = 5000L;
        HttpEntity entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange("/api/user/" + userId, HttpMethod.GET, entity, String.class);
        String expected = "{\"error\": \"С Id = " + userId + ", сотрудники не найдены! Введите существующий Id.\"}";
        String result = response.getBody();
        assertEquals(expected, result, true);
    }

    @Test
    public void updaterUserPositiveTest() throws Exception {
        Long userId = userRepository.findUserByFirstName("Тест").getId();
        String body = "{\"id\":" + userId + "," +
                "\"firstName\": \"Тест1\"," +
                "\"secondName\": \"Тестов1\"," +
                "\"middleName\": \"Тестович1\"," +
                "\"position\": \"Менеджер1\"," +
                "\"phone\": \"+7(8532)45-45-46\"," +
                "\"docName\": \"Паспорт гражданина Российской Федерации\"," +
                "\"docNumber\": \"4555\"," +
                "\"docDate\": \"2015-06-30\"," +/*06/24/2015 @ 12:00am (UTC)*/
                "\"citizenshipName\": \"Российская Федерация\"," +
                "\"citizenshipCode\": \"643\"," +
                "\"isIdentified\": true}}";
        HttpEntity entity = new HttpEntity<>(body, headers);

        ResponseEntity<String> response = restTemplate.exchange("/api/user/update", HttpMethod.POST, entity, String.class);
        String result = response.getBody();
        String expected = "{\"data\":{\"result\":\"success\"}}";
        assertEquals(expected, result, true);
        assertNotNull(userRepository.findUserByFirstName("Тест1"));
    }

    @Test
    public void updaterUserNegativeTest() throws Exception {
        Long userId = 5000L;
        String body = "{\"id\":" + userId + "," +
                "\"firstName\": \"Тест1\"," +
                "\"secondName\": \"Тестов1\"," +
                "\"middleName\": \"Тестович1\"," +
                "\"position\": \"Менеджер1\"," +
                "\"phone\": \"+7(8532)45-45-46\"," +
                "\"docName\": \"Паспорт гражданина Российской Федерации\"," +
                "\"docNumber\": \"4555\"," +
                "\"docDate\": \"2015-06-30\"," +/*06/24/2015 @ 12:00am (UTC)*/
                "\"citizenshipName\": \"Российская Федерация\"," +
                "\"citizenshipCode\": \"643\"," +
                "\"isIdentified\": true}}";
        HttpEntity entity = new HttpEntity<>(body, headers);

        ResponseEntity<String> response = restTemplate.exchange("/api/user/update", HttpMethod.POST, entity, String.class);
        String result = response.getBody();
        String expected = "{\"error\" : \"Сотрудник с Id: " + userId + " не найден!\"}";
        assertEquals(expected, result, true);
        assertNull(userRepository.findUserByFirstName("Тест1"));
    }

    @Test
    public void saveUserPositiveTest() throws Exception {
        Long officeId = officeRepository.findOfficeByName("Тестовый офис").getId();
        String body = "{\"officeId\":" + officeId + "," +
                "\"firstName\": \"Тест1\"," +
                "\"secondName\": \"Тестов1\"," +
                "\"middleName\": \"Тестович1\"," +
                "\"position\": \"Менеджер1\"," +
                "\"phone\": \"+7(8532)45-45-46\"," +
                "\"docCode\": \"21\"," +
                "\"docNumber\": \"4555\"," +
                "\"docDate\": \"2015-06-30\"," +
                "\"citizenshipCode\": \"643\"," +
                "\"isIdentified\": true}";
        HttpEntity entity = new HttpEntity<>(body, headers);

        ResponseEntity<String> response = restTemplate.exchange("/api/user/save", HttpMethod.POST, entity, String.class);
        String result = response.getBody();
        String expected = "{\"data\":{\"result\":\"success\"}}";
        assertEquals(expected, result, true);
        assertNotNull(userRepository.findUserByFirstName("Тест1"));
    }

    @Test
    public void saveUserNegativeTest() throws Exception {
        Long officeId = 5000L;
        String body = "{\"officeId\":" + officeId + "," +
                "\"firstName\": \"Тест1\"," +
                "\"secondName\": \"Тестов1\"," +
                "\"middleName\": \"Тестович1\"," +
                "\"position\": \"Менеджер1\"," +
                "\"phone\": \"+7(8532)45-45-46\"," +
                "\"docCode\": \"21\"," +
                "\"docNumber\": \"4555\"," +
                "\"docDate\": \"2015-06-30\"," +/*06/24/2015 @ 12:00am (UTC)*/
                "\"citizenshipCode\": \"643\"," +
                "\"isIdentified\": true}}";
        HttpEntity entity = new HttpEntity<>(body, headers);

        ResponseEntity<String> response = restTemplate.exchange("/api/user/save", HttpMethod.POST, entity, String.class);
        String result = response.getBody();
        String expected = "{\"error\" : \"По officeId: " + officeId + " офис не найден! Для заведения нового сотрудника, нужно прикрепить сотрудника к существующему офису!\"}";
        assertEquals(expected, result, true);
//        assertNull(organizationRepository.findOrganizationByName(null));
    }

    @Test
    public void deleteUserPositiveTest() throws Exception {
        Long userId = userRepository.findUserByFirstName("Тест").getId();
        String body = "{\"id\" : " + userId + "}";
        HttpEntity entity = new HttpEntity<>(body, headers);

        ResponseEntity<String> response = restTemplate.exchange("/api/user/delete", HttpMethod.POST, entity, String.class);
        String expected = "{\"data\":{\"result\":\"success\"}}";
        String result = response.getBody();
        assertEquals(expected, result, true);
        assertNull(userRepository.findUserByFirstName("Тест"));
    }

    @Test
    public void deleteUserNegativeTest() throws Exception {
        String body = "{\"id\" : 5000}";
        HttpEntity entity = new HttpEntity<>(body, headers);

        ResponseEntity<String> response = restTemplate.exchange("/api/user/delete", HttpMethod.POST, entity, String.class);

        String expected = "{\"error\": \"С данным Id, сотрудник не найден!\"}";
        String result = response.getBody();
        assertEquals(expected, result, true);
        assertNotNull(userRepository.findUserByFirstName("Тест"));
    }


}
package com.i.homework02.controller;

import com.i.homework02.Homework02Application;
import com.i.homework02.entity.*;
import com.i.homework02.repository.*;
import com.i.homework02.view.*;
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
        ResponseEntity<NegativeResponseView> response = restTemplate.exchange("/api/user/list", HttpMethod.POST, entity, NegativeResponseView.class);

        NegativeResponseView result = response.getBody();
        NegativeResponseView expected=new NegativeResponseView("По заданным параметрам ничего не найдено");
        Assert.assertEquals(expected, result);
        assertNotNull(userRepository.findUserByFirstName("Тест"));
    }

    @Test
    public void findUserByIdPositiveTest() throws Exception {
        Long userId = userRepository.findUserByFirstName("Тест").getId();

        HttpEntity entity = new HttpEntity<>(headers);

        ResponseEntity<DataView<UserIdViewResponse>> response = restTemplate.exchange("/api/user/" + userId, HttpMethod.GET, entity, new ParameterizedTypeReference<DataView<UserIdViewResponse>>() {});

        DataView<UserIdViewResponse> result = response.getBody();

        UserIdViewResponse userIdViewResponse = new UserIdViewResponse();
        userIdViewResponse.setId(userId);
        userIdViewResponse.setFirstName("Тест");
        userIdViewResponse.setSecondName("Тестов");
        userIdViewResponse.setMiddleName("Тестович");
        userIdViewResponse.setPosition("Менеджер");
        userIdViewResponse.setPhone("+7(8532)45-45-45");
        userIdViewResponse.setDocName("Паспорт гражданина Российской Федерации");
        userIdViewResponse.setDocCode("21");
        userIdViewResponse.setDocNumber("4554");
        userIdViewResponse.setDocDate(new Date(1435190400000L));/*06/25/2015 @ 12:00am (UTC)*/
        userIdViewResponse.setCitizenshipName("Российская Федерация");
        userIdViewResponse.setCitizenshipCode("643");
        userIdViewResponse.setIdentified(null);

        DataView<UserIdViewResponse> expected=new DataView<UserIdViewResponse>(userIdViewResponse);

        Assert.assertEquals(expected, result);
    }

    @Test
    public void findUserByIdNegativeTest() throws Exception {

        Long userId = 5000L;
        HttpEntity entity = new HttpEntity<>(headers);
        ResponseEntity<NegativeResponseView> response = restTemplate.exchange("/api/user/" + userId, HttpMethod.GET, entity, NegativeResponseView.class);

        NegativeResponseView result = response.getBody();
        NegativeResponseView expected=new NegativeResponseView("С Id = " + userId + ", сотрудники не найдены! Введите существующий Id.");
        Assert.assertEquals(expected, result);
    }

    @Test
    public void updaterUserPositiveTest() throws Exception {
        Long userId = userRepository.findUserByFirstName("Тест").getId();

        UserUpdateViewRequest userUpdateViewRequest = new UserUpdateViewRequest();
        userUpdateViewRequest.setId(userId);
        userUpdateViewRequest.setFirstName("Тест1");
        userUpdateViewRequest.setSecondName("Тестов1");
        userUpdateViewRequest.setMiddleName("Тестович1");
        userUpdateViewRequest.setPosition("Менеджер1");
        userUpdateViewRequest.setPhone("+7(8532)45-45-45");
        userUpdateViewRequest.setDocName("Паспорт гражданина Российской Федерации");
        userUpdateViewRequest.setDocNumber("4554");
        userUpdateViewRequest.setDocDate(new Date(1435190400000L));/*06/25/2015 @ 12:00am (UTC)*/
        userUpdateViewRequest.setCitizenshipName("Российская Федерация");
        userUpdateViewRequest.setCitizenshipCode("643");
        userUpdateViewRequest.setIdentified(null);

        HttpEntity entity = new HttpEntity<>(userUpdateViewRequest, headers);

        ResponseEntity<PositiveResponseView> response = restTemplate.exchange("/api/user/update", HttpMethod.POST, entity, PositiveResponseView.class);

        PositiveResponseView result = response.getBody();
        PositiveResponseView expected = new PositiveResponseView();

        Assert.assertEquals(expected, result);
        assertNotNull(userRepository.findUserByFirstName("Тест1"));
    }

    @Test
    public void updaterUserNegativeTest() throws Exception {
        Long userId = 5000L;

        UserUpdateViewRequest userUpdateViewRequest = new UserUpdateViewRequest();
        userUpdateViewRequest.setId(userId);
        userUpdateViewRequest.setFirstName("Тест1");
        userUpdateViewRequest.setSecondName("Тестов1");
        userUpdateViewRequest.setMiddleName("Тестович1");
        userUpdateViewRequest.setPosition("Менеджер1");
        userUpdateViewRequest.setPhone("+7(8532)45-45-45");
        userUpdateViewRequest.setDocName("Паспорт гражданина Российской Федерации");
        userUpdateViewRequest.setDocNumber("4554");
        userUpdateViewRequest.setDocDate(new Date(1435190400000L));/*06/25/2015 @ 12:00am (UTC)*/
        userUpdateViewRequest.setCitizenshipName("Российская Федерация");
        userUpdateViewRequest.setCitizenshipCode("643");
        userUpdateViewRequest.setIdentified(null);

        HttpEntity entity = new HttpEntity<>(userUpdateViewRequest, headers);

        ResponseEntity<NegativeResponseView> response = restTemplate.exchange("/api/user/update", HttpMethod.POST, entity, NegativeResponseView.class);

        NegativeResponseView result = response.getBody();
        NegativeResponseView expected=new NegativeResponseView("Сотрудник с Id: " + userId + " не найден!");
        Assert.assertEquals(expected, result);

        assertNull(userRepository.findUserByFirstName("Тест1"));
    }

    @Test
    public void saveUserPositiveTest() throws Exception {
        Long officeId = officeRepository.findOfficeByName("Тестовый офис").getId();

        UserSaveViewRequest userSaveViewRequest = new UserSaveViewRequest();
        userSaveViewRequest.setOfficeId(officeId);
        userSaveViewRequest.setFirstName("Тест1");
        userSaveViewRequest.setSecondName("Тестов1");
        userSaveViewRequest.setMiddleName("Тестович1");
        userSaveViewRequest.setPosition("Менеджер1");
        userSaveViewRequest.setPhone("+7(8532)45-45-45");
        userSaveViewRequest.setDocCode("21");
        userSaveViewRequest.setDocNumber("4554");
        userSaveViewRequest.setDocDate(new Date(1435190400000L));/*06/25/2015 @ 12:00am (UTC)*/
        userSaveViewRequest.setCitizenshipCode("643");
        userSaveViewRequest.setIdentified(true);

        HttpEntity entity = new HttpEntity<>(userSaveViewRequest, headers);

        ResponseEntity<PositiveResponseView> response = restTemplate.exchange("/api/user/save", HttpMethod.POST, entity, PositiveResponseView.class);

        PositiveResponseView result = response.getBody();
        PositiveResponseView expected = new PositiveResponseView();

        Assert.assertEquals(expected, result);
        assertNotNull(userRepository.findUserByFirstName("Тест1"));
    }

    @Test
    public void saveUserNegativeTest() throws Exception {
        Long officeId = 5000L;
        UserSaveViewRequest userSaveViewRequest = new UserSaveViewRequest();
        userSaveViewRequest.setOfficeId(officeId);
        userSaveViewRequest.setFirstName("Тест1");
        userSaveViewRequest.setSecondName("Тестов1");
        userSaveViewRequest.setMiddleName("Тестович1");
        userSaveViewRequest.setPosition("Менеджер1");
        userSaveViewRequest.setPhone("+7(8532)45-45-45");
        userSaveViewRequest.setDocCode("21");
        userSaveViewRequest.setDocNumber("4554");
        userSaveViewRequest.setDocDate(new Date(1435190400000L));/*06/25/2015 @ 12:00am (UTC)*/
        userSaveViewRequest.setCitizenshipCode("643");
        userSaveViewRequest.setIdentified(true);

        HttpEntity entity = new HttpEntity<>(userSaveViewRequest, headers);

        ResponseEntity<NegativeResponseView> response = restTemplate.exchange("/api/user/save", HttpMethod.POST, entity, NegativeResponseView.class);

        NegativeResponseView result = response.getBody();
        NegativeResponseView expected=new NegativeResponseView("По officeId: " + officeId + " офис не найден! Для заведения нового сотрудника, нужно прикрепить сотрудника к существующему офису!");
        Assert.assertEquals(expected, result);
//        assertNull(organizationRepository.findOrganizationByName(null));
    }

    @Test
    public void deleteUserPositiveTest() throws Exception {
        Long userId = userRepository.findUserByFirstName("Тест").getId();

        UserDeleteViewRequest userDeleteViewRequest = new UserDeleteViewRequest();
        userDeleteViewRequest.setId(userId);

        HttpEntity entity = new HttpEntity<>(userDeleteViewRequest, headers);

        ResponseEntity<PositiveResponseView> response = restTemplate.exchange("/api/user/delete", HttpMethod.POST, entity, PositiveResponseView.class);

        PositiveResponseView result = response.getBody();
        PositiveResponseView expected = new PositiveResponseView();

        Assert.assertEquals(expected, result);
        assertNull(userRepository.findUserByFirstName("Тест"));
    }

    @Test
    public void deleteUserNegativeTest() throws Exception {
        Long userId = 5000L;

        UserDeleteViewRequest userDeleteViewRequest = new UserDeleteViewRequest();
        userDeleteViewRequest.setId(userId);

        HttpEntity entity = new HttpEntity<>(userDeleteViewRequest, headers);

        ResponseEntity<NegativeResponseView> response = restTemplate.exchange("/api/user/delete", HttpMethod.POST, entity, NegativeResponseView.class);

        NegativeResponseView result = response.getBody();
        NegativeResponseView expected=new NegativeResponseView("С данным Id, сотрудник не найден!");

        Assert.assertEquals(expected, result);
        assertNotNull(userRepository.findUserByFirstName("Тест"));
    }


}
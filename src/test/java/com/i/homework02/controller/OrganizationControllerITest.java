package com.i.homework02.controller;

import com.i.homework02.Homework02Application;
import com.i.homework02.repository.OrganizationRepository;
import com.i.homework02.entity.Organization;
import com.i.homework02.view.DataView;
import com.i.homework02.view.OrgListViewRequest;
import com.i.homework02.view.OrgListViewResponse;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = Homework02Application.class)
public class OrganizationControllerITest {

    @Autowired
    TestRestTemplate restTemplate;

    HttpHeaders headers = new HttpHeaders();

    @Autowired
    OrganizationRepository organizationRepository;

    @Before
    public void init() {
        headers.setContentType(MediaType.APPLICATION_JSON);
    }

    @After
    public void after() {
        organizationRepository.deleteAll();
    }

    @Test
    public void searchOrganizationPositiveTest() throws Exception {
        organizationRepository.deleteAll();
        Organization organisation = new Organization("АО Тестовая Организация",
                "Акционерное Общество Тестовая Организация", "774565646", "777897978979",
                "Киров, ул. Серова, д.2", "+7(8532)45-45-45", true);
        organizationRepository.save(organisation);
        Long id = organizationRepository.findOrganizationByName(organisation.getName()).getId();

        OrgListViewRequest orgListViewRequest=new OrgListViewRequest();
        orgListViewRequest.setName("АО Тестовая Организация");
        orgListViewRequest.setInn("774565646");
        orgListViewRequest.setActive(true);

        HttpEntity entity = new HttpEntity<>(orgListViewRequest, headers);
        ResponseEntity<DataView<List<OrgListViewResponse>>> response = restTemplate.exchange("/api/organization/list", HttpMethod.POST, entity, new ParameterizedTypeReference<DataView<List<OrgListViewResponse>>>() {});
        DataView result = response.getBody();

        OrgListViewResponse orgListViewResponse=new OrgListViewResponse();
        orgListViewResponse.setId(id);
        orgListViewResponse.setName("АО Тестовая Организация");
        orgListViewResponse.setActive(true);
        List<OrgListViewResponse> orgList=new ArrayList<>();
        orgList.add(orgListViewResponse);
        DataView<List<OrgListViewResponse>> expected = new DataView(orgList);

        Assert.assertEquals(expected, result);
    }


    @Test
    public void searchOrganizationNegativeTest() throws Exception {
        Organization organisation = new Organization("АО Тестовая Организация",
                "Акционерное Общество Тестовая Организация", "774565646", "777897978979",
                "Киров, ул. Серова, д.2", "+7(8532)45-45-45", true);
        organizationRepository.save(organisation);
        Long id = organizationRepository.findOrganizationByName(organisation.getName()).getId();
        String body =
                "{\"name\":\"АО Тестовая Организация1\"," +
                        "\"inn\":\"777777777\"," +
                        "\"isActive\":false}";

        HttpEntity entity = new HttpEntity<>(body, headers);
        ResponseEntity<String> response = restTemplate.exchange("/api/organization/list", HttpMethod.POST, entity, String.class);
        String expected = "{\n" +
                "\"error\": \"По заданным параметрам ничего не найдено\"\n" +
                "}";
        String result = response.getBody();
        assertEquals(expected, result, true);
        assertNull(organizationRepository.findOrganizationByName("АО Тестовая Организация1"));
    }

    @Test
    public void findOrganizationByIdPositiveTest() throws Exception {
        Organization organisation = new Organization("АО Тестовая Организация",
                "Акционерное Общество Тестовая Организация", "774565646", "777897978979",
                "Киров, ул. Серова, д.2", "+7(8532)45-45-45", true);
        organizationRepository.save(organisation);
        Long id = organizationRepository.findOrganizationByName(organisation.getName()).getId();

        HttpEntity entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange("/api/organization/" + id, HttpMethod.GET, entity, String.class);
        String expected = "{\"data\":" +
                "{\"id\":" + id + "," +
                "\"name\":\"АО Тестовая Организация\"," +
                "\"fullName\":\"Акционерное Общество Тестовая Организация\"," +
                "\"inn\":\"774565646\"," +
                "\"kpp\":\"777897978979\"," +
                "\"address\":\"Киров, ул. Серова, д.2\"," +
                "\"phone\":\"+7(8532)45-45-45\"," +
                "\"isActive\":true}}";
        String result = response.getBody();
        assertEquals(expected, result, true);
    }

    @Test
    public void findOrganizationByIdNegativeTest() throws Exception {
        Organization organisation = new Organization("АО Тестовая Организация",
                "Акционерное Общество Тестовая Организация", "774565646", "777897978979",
                "Киров, ул. Серова, д.2", "+7(8532)45-45-45", true);
        organizationRepository.save(organisation);
        Long id = 5000L;

        HttpEntity entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange("/api/organization/" + id, HttpMethod.GET, entity, String.class);
        String expected = "{\n" +
                "\"error\": \"С данным Id, организация не найдена!\"\n" +
                "}";
        String result = response.getBody();
        assertEquals(expected, result, true);
    }

    @Test
    public void updaterOrganizationPositiveTest() throws Exception {
        Organization organisation = new Organization("АО Тестовая Организация",
                "Акционерное Общество Тестовая Организация", "774565646", "777897978979",
                "Киров, ул. Серова, д.2", "+7(8532)45-45-45", true);
        organizationRepository.save(organisation);
        Long id = organizationRepository.findOrganizationByName(organisation.getName()).getId();
        String body = "{\"id\":" + id + "," +
                "\"name\": \"БЕЛЛ ИНТЕГРАТОР1\"," +
                "\"fullName\": \"АКЦИОНЕРНОЕ ОБЩЕСТВО БЕЛЛ ИНТЕГРАТОР1\"}";
        HttpEntity entity = new HttpEntity<>(body, headers);

        ResponseEntity<String> response = restTemplate.exchange("/api/organization/update", HttpMethod.POST, entity, String.class);
        String result = response.getBody();
        String expected = "{\"data\":{\"result\":\"success\"}}";
        assertEquals(expected, result, true);
        assertNotNull(organizationRepository.findOrganizationByName("БЕЛЛ ИНТЕГРАТОР1"));
        assertNotNull(organizationRepository.findOrganizationByInn("774565646"));
    }

    @Test
    public void updaterOrganizationNegativeTest() throws Exception {
        Organization organisation = new Organization("АО Тестовая Организация",
                "Акционерное Общество Тестовая Организация", "774565646", "777897978979",
                "Киров, ул. Серова, д.2", "+7(8532)45-45-45", true);
        organizationRepository.save(organisation);
        Long id = 5000L;
        String body = "{\"id\":" + id + "," +
                "\"name\": \"БЕЛЛ ИНТЕГРАТОР1\"," +
                "\"fullName\": \"АКЦИОНЕРНОЕ ОБЩЕСТВО БЕЛЛ ИНТЕГРАТОР1\"}";
        HttpEntity entity = new HttpEntity<>(body, headers);

        ResponseEntity<String> response = restTemplate.exchange("/api/organization/update", HttpMethod.POST, entity, String.class);
        String result = response.getBody();
        String expected = "{\"error\" : \"Организация с Id: " + id + " не найдена!\"}";
        assertEquals(expected, result, true);
        assertNull(organizationRepository.findOrganizationByName("БЕЛЛ ИНТЕГРАТОР1"));
        assertNotNull(organizationRepository.findOrganizationByInn("774565646"));
    }

    @Test
    public void saveOrganisationPositiveTest() throws Exception {
        String body = "{\"name\": \"БЕЛЛ ИНТЕГРАТОР1\"," +
                "\"fullName\": \"АКЦИОНЕРНОЕ ОБЩЕСТВО БЕЛЛ ИНТЕГРАТОР\"," +
                "\"inn\": \"7714923230\"," +
                "\"kpp\": \"771401001\"," +
                "\"address\": \"125167, ГОРОД МОСКВА, УЛИЦА ПЛАНЕТНАЯ УЛИЦА, ДОМ 11, ПОМЕЩЕНИЕ 9/10 РМ-4\"," +
                "\"phone\": \"+7 (495) 980-61-81\"," +
                "\"isActive\": true}";
        HttpEntity entity = new HttpEntity<>(body, headers);

        ResponseEntity<String> response = restTemplate.exchange("/api/organization/save", HttpMethod.POST, entity, String.class);
        String result = response.getBody();
        String expected = "{\"data\":{\"result\":\"success\"}}";
        assertEquals(expected, result, true);
        assertNotNull(organizationRepository.findOrganizationByName("БЕЛЛ ИНТЕГРАТОР1"));
    }

    @Test
    public void saveOrganisationNegativeTest() throws Exception {
        String body = "{\"name\": null," +
                "\"fullName\": null," +
                "\"inn\": null," +
                "\"kpp\": null}";
        HttpEntity entity = new HttpEntity<>(body, headers);

        ResponseEntity<String> response = restTemplate.exchange("/api/organization/save", HttpMethod.POST, entity, String.class);
        String result = response.getBody();
        String expected = "{\"error\" : \"Поля не заполнены! Для сохранения, необходимо заполнить, хотя бы, одно поле!\"}";
        assertEquals(expected, result, true);
//        assertNull(organizationRepository.findOrganizationByName(null));
    }

    @Test
    public void deleteOrganizationPositiveTest() throws Exception {
        Organization organisation = new Organization("АО Тестовая Организация",
                "Акционерное Общество Тестовая Организация", "774565646", "777897978979",
                "Киров, ул. Серова, д.2", "+7(8532)45-45-45", true);
        organizationRepository.save(organisation);
        Long id = organizationRepository.findOrganizationByName(organisation.getName()).getId();

        String body = "{\"id\" : " + id + "}";
        HttpEntity entity = new HttpEntity<>(body, headers);

        ResponseEntity<String> response = restTemplate.exchange("/api/organization/delete", HttpMethod.POST, entity, String.class);

        String expected = "{\"data\":{\"result\":\"success\"}}";
        String result = response.getBody();
        assertEquals(expected, result, true);
        assertNull(organizationRepository.findOrganizationByName(organisation.getName()));
    }

    @Test
    public void deleteOrganizationNegativeTest() throws Exception {
        Organization organisation = new Organization("АО Тестовая Организация",
                "Акционерное Общество Тестовая Организация", "774565646", "777897978979",
                "Киров, ул. Серова, д.2", "+7(8532)45-45-45", true);
        organizationRepository.save(organisation);

        String body = "{\"id\" : 5000}";
        HttpEntity entity = new HttpEntity<>(body, headers);

        ResponseEntity<String> response = restTemplate.exchange("/api/organization/delete", HttpMethod.POST, entity, String.class);

        String expected = "{\"error\": \"С данным Id, организация не найдена!\"}";
        String result = response.getBody();
        assertEquals(expected, result, true);
        assertNotNull(organizationRepository.findOrganizationByName(organisation.getName()));
    }

}
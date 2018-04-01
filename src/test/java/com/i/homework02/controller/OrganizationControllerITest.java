package com.i.homework02.controller;

import com.i.homework02.Homework02Application;
import com.i.homework02.repository.OrganizationRepository;
import com.i.homework02.entity.Organization;
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
        OrgListViewRequest orgListViewRequest=new OrgListViewRequest();
        orgListViewRequest.setName("АО Тестовая Организация");
        orgListViewRequest.setInn("777777777");
        orgListViewRequest.setActive(false);

        HttpEntity entity = new HttpEntity<>(orgListViewRequest, headers);
        ResponseEntity<NegativeResponseView> response = restTemplate.exchange("/api/organization/list", HttpMethod.POST, entity, NegativeResponseView.class);

        NegativeResponseView result = response.getBody();
        NegativeResponseView expected = new  NegativeResponseView("По заданным параметрам ничего не найдено");
        Assert.assertEquals(expected, result);

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
        ResponseEntity<DataView<OrgViewResponse>> response = restTemplate.exchange("/api/organization/" + id, HttpMethod.GET, entity, new ParameterizedTypeReference<DataView<OrgViewResponse>>() {});
        DataView<OrgViewResponse> result=response.getBody();


        OrgViewResponse orgViewResponse=new OrgViewResponse();
        orgViewResponse.setId(id);
        orgViewResponse.setName("АО Тестовая Организация");
        orgViewResponse.setFullName("Акционерное Общество Тестовая Организация");
        orgViewResponse.setInn("774565646");
        orgViewResponse.setKpp("777897978979");
        orgViewResponse.setAddress("Киров, ул. Серова, д.2");
        orgViewResponse.setPhone("+7(8532)45-45-45");
        orgViewResponse.setActive(true);
        DataView<OrgViewResponse> expected = new DataView(orgViewResponse);


        Assert.assertEquals(expected, result);
    }

    @Test
    public void findOrganizationByIdNegativeTest() throws Exception {
        Organization organisation = new Organization("АО Тестовая Организация",
                "Акционерное Общество Тестовая Организация", "774565646", "777897978979",
                "Киров, ул. Серова, д.2", "+7(8532)45-45-45", true);
        organizationRepository.save(organisation);
        Long id = 5000L;

        HttpEntity entity = new HttpEntity<>(headers);
        ResponseEntity<NegativeResponseView> response = restTemplate.exchange("/api/organization/" + id, HttpMethod.GET, entity, NegativeResponseView.class);

        NegativeResponseView result = response.getBody();
        NegativeResponseView expected = new  NegativeResponseView("С данным Id, организация не найдена!");
        Assert.assertEquals(expected, result);
    }

    @Test
    public void updaterOrganizationPositiveTest() throws Exception {
        Organization organisation = new Organization("АО Тестовая Организация",
                "Акционерное Общество Тестовая Организация", "774565646", "777897978979",
                "Киров, ул. Серова, д.2", "+7(8532)45-45-45", true);
        organizationRepository.save(organisation);
        Long id = organizationRepository.findOrganizationByName(organisation.getName()).getId();

        OrgViewRequest orgViewRequest = new OrgViewRequest();
        orgViewRequest.setId(id);
        orgViewRequest.setName("БЕЛЛ ИНТЕГРАТОР1");
        orgViewRequest.setFullName("АКЦИОНЕРНОЕ ОБЩЕСТВО БЕЛЛ ИНТЕГРАТОР1");

        HttpEntity entity = new HttpEntity<>(orgViewRequest, headers);

        ResponseEntity<PositiveResponseView> response = restTemplate.exchange("/api/organization/update", HttpMethod.POST, entity, PositiveResponseView.class);

        PositiveResponseView result = response.getBody();
        PositiveResponseView expected = new PositiveResponseView();
        Assert.assertEquals(expected, result);

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

        OrgViewRequest orgViewRequest = new OrgViewRequest();
        orgViewRequest.setId(id);
        orgViewRequest.setName("БЕЛЛ ИНТЕГРАТОР1");
        orgViewRequest.setFullName("АКЦИОНЕРНОЕ ОБЩЕСТВО БЕЛЛ ИНТЕГРАТОР1");

        HttpEntity entity = new HttpEntity<>(orgViewRequest, headers);

        ResponseEntity<NegativeResponseView> response = restTemplate.exchange("/api/organization/update", HttpMethod.POST, entity, NegativeResponseView.class);
        NegativeResponseView result = response.getBody();
        NegativeResponseView expected = new  NegativeResponseView("Организация с Id: " + id + " не найдена!");
        Assert.assertEquals(expected, result);
        assertNull(organizationRepository.findOrganizationByName("БЕЛЛ ИНТЕГРАТОР1"));
        assertNotNull(organizationRepository.findOrganizationByInn("774565646"));
    }

    @Test
    public void saveOrganisationPositiveTest() throws Exception {

        OrgViewRequest orgViewRequest = new OrgViewRequest();
        orgViewRequest.setName("БЕЛЛ ИНТЕГРАТОР1");
        orgViewRequest.setFullName("АКЦИОНЕРНОЕ ОБЩЕСТВО БЕЛЛ ИНТЕГРАТОР1");
        orgViewRequest.setInn("7714923230");
        orgViewRequest.setKpp("771401001");
        orgViewRequest.setAddress("125167, ГОРОД МОСКВА, УЛИЦА ПЛАНЕТНАЯ УЛИЦА, ДОМ 11, ПОМЕЩЕНИЕ 9/10 РМ-4");
        orgViewRequest.setPhone("+7 (495) 980-61-81");
        orgViewRequest.setActive(true);

        HttpEntity entity = new HttpEntity<>(orgViewRequest, headers);

        ResponseEntity<PositiveResponseView> response = restTemplate.exchange("/api/organization/save", HttpMethod.POST, entity, PositiveResponseView.class);
        PositiveResponseView result = response.getBody();
        PositiveResponseView expected = new PositiveResponseView();
        Assert.assertEquals(expected, result);
        assertNotNull(organizationRepository.findOrganizationByName("БЕЛЛ ИНТЕГРАТОР1"));
    }

    @Test
    public void saveOrganisationNegativeTest() throws Exception {

        OrgViewRequest orgViewRequest = new OrgViewRequest();
        orgViewRequest.setName(null);
        orgViewRequest.setFullName(null);
        orgViewRequest.setInn(null);
        orgViewRequest.setKpp(null);

        HttpEntity entity = new HttpEntity<>(orgViewRequest, headers);

        ResponseEntity<NegativeResponseView> response = restTemplate.exchange("/api/organization/save", HttpMethod.POST, entity, NegativeResponseView.class);

        NegativeResponseView result = response.getBody();
        NegativeResponseView expected = new  NegativeResponseView("Поля не заполнены! Для сохранения, необходимо заполнить, хотя бы, одно поле!");
        Assert.assertEquals(expected, result);
//        assertNull(organizationRepository.findOrganizationByName(null));
    }

    @Test
    public void deleteOrganizationPositiveTest() throws Exception {
        Organization organisation = new Organization("АО Тестовая Организация",
                "Акционерное Общество Тестовая Организация", "774565646", "777897978979",
                "Киров, ул. Серова, д.2", "+7(8532)45-45-45", true);
        organizationRepository.save(organisation);
        Long id = organizationRepository.findOrganizationByName(organisation.getName()).getId();

        OrgViewRequest orgViewRequest = new OrgViewRequest();
        orgViewRequest.setId(id);

        HttpEntity entity = new HttpEntity<>(orgViewRequest, headers);

        ResponseEntity<PositiveResponseView> response = restTemplate.exchange("/api/organization/delete", HttpMethod.POST, entity, PositiveResponseView.class);

        PositiveResponseView result = response.getBody();
        PositiveResponseView expected = new PositiveResponseView();
        Assert.assertEquals(expected, result);
        assertNull(organizationRepository.findOrganizationByName(organisation.getName()));
    }

    @Test
    public void deleteOrganizationNegativeTest() throws Exception {
        Organization organisation = new Organization("АО Тестовая Организация",
                "Акционерное Общество Тестовая Организация", "774565646", "777897978979",
                "Киров, ул. Серова, д.2", "+7(8532)45-45-45", true);
        organizationRepository.save(organisation);

        OrgViewRequest orgViewRequest = new OrgViewRequest();
        orgViewRequest.setId(5000L);

        HttpEntity entity = new HttpEntity<>(orgViewRequest, headers);

        ResponseEntity<NegativeResponseView> response = restTemplate.exchange("/api/organization/delete", HttpMethod.POST, entity, NegativeResponseView.class);

        NegativeResponseView result = response.getBody();
        NegativeResponseView expected = new  NegativeResponseView("С данным Id, организация не найдена!");

        Assert.assertEquals(expected, result);
        assertNotNull(organizationRepository.findOrganizationByName(organisation.getName()));
    }

}
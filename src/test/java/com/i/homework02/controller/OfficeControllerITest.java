package com.i.homework02.controller;

import com.i.homework02.Homework02Application;
import com.i.homework02.entity.Office;
import com.i.homework02.entity.Organization;
import com.i.homework02.repository.OfficeRepository;
import com.i.homework02.repository.OrganizationRepository;
import com.i.homework02.view.DataView;
import com.i.homework02.view.OfficeListViewRequest;
import com.i.homework02.view.OfficeListViewResponse;
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
import java.util.List;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertNull;
import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = Homework02Application.class)
public class OfficeControllerITest {
    @Autowired
    TestRestTemplate restTemplate;

    HttpHeaders headers = new HttpHeaders();

    @Autowired
    OfficeRepository officeRepository;

    @Autowired
    OrganizationRepository organizationRepository;

    @Before
    public void init() {

        headers.setContentType(MediaType.APPLICATION_JSON);
        organizationRepository.deleteAll();
        officeRepository.deleteAll();
    }

    @Test
    public void searchOfficePositiveTest() throws Exception {
        Organization organization = new Organization("Тестовая организация");
        organizationRepository.save(organization);
        Office office = new Office("Тестовый офис", "Киров, ул. Серова, д.2", "+7(8532)45-45-45", true, organizationRepository.findOrganizationByName("Тестовая организация"));
        officeRepository.save(office);
        Long officeId = officeRepository.findOfficeByName(office.getName()).getId();
        Long orgId = organizationRepository.findOrganizationByName("Тестовая организация").getId();
        OfficeListViewRequest officeListViewRequest = new OfficeListViewRequest();
        officeListViewRequest.setOrgId(orgId);
        officeListViewRequest.setName("Тестовый офис");
        officeListViewRequest.setPhone("+7(8532)45-45-45");
        officeListViewRequest.setActive(true);

        HttpEntity entity = new HttpEntity<>(officeListViewRequest, headers);
        ResponseEntity<DataView<List<OfficeListViewResponse>>> response = restTemplate.exchange("/api/office/list", HttpMethod.POST, entity, new ParameterizedTypeReference<DataView<List<OfficeListViewResponse>>>() {});
        DataView<List<OfficeListViewResponse>> result = response.getBody();

        OfficeListViewResponse officeListViewResponse=new OfficeListViewResponse();
        officeListViewResponse.setId(officeId);
        officeListViewResponse.setName("Тестовый офис");
        officeListViewResponse.setActive(true);

        List<OfficeListViewResponse> officeList = new ArrayList<>();
        officeList.add(officeListViewResponse);

        DataView<List<OfficeListViewResponse>> expected = new DataView<>(officeList);

        Assert.assertEquals(expected, result);
    }

    @Test
    public void searchOfficeNegativeTest() throws Exception {
        Organization organization = new Organization("Тестовая организация");
        organizationRepository.save(organization);
        Office office = new Office("Тестовый офис", "Киров, ул. Серова, д.2", "+7(8532)45-45-45", true, organizationRepository.findOrganizationByName("Тестовая организация"));
        officeRepository.save(office);
        String body = "{\"orgId\":" + 5000 + "}";

        HttpEntity entity = new HttpEntity<>(body, headers);
        ResponseEntity<String> response = restTemplate.exchange("/api/office/list", HttpMethod.POST, entity, String.class);
        String expected = "{\"error\": \"По заданным параметрам ничего не найдено\"}";
        String result = response.getBody();
        assertEquals(expected, result, true);
        assertNotNull(officeRepository.findOfficeByName("Тестовый офис"));
    }

    @Test
    public void findOfficeByIdPositiveTest() throws Exception {
        Organization organization = new Organization("Тестовая организация");
        organizationRepository.save(organization);
        Office office = new Office("Тестовый офис", "Киров, ул. Серова, д.2", "+7(8532)45-45-45", true, organizationRepository.findOrganizationByName("Тестовая организация"));
        officeRepository.save(office);
        Long officeId = officeRepository.findOfficeByName(office.getName()).getId();

        HttpEntity entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange("/api/office/" + officeId, HttpMethod.GET, entity, String.class);
        String expected = "{\"data\":" +
                "{\"id\":" + officeId + "," +
                "\"name\":\"Тестовый офис\"," +
                "\"address\":\"Киров, ул. Серова, д.2\"," +
                "\"phone\":\"+7(8532)45-45-45\"," +
                "\"isActive\":true}}";
        String result = response.getBody();
        assertEquals(expected, result, true);
    }

    @Test
    public void findOfficeByIdNegativeTest() throws Exception {
        Organization organization = new Organization("Тестовая организация");
        organizationRepository.save(organization);
        Office office = new Office("Тестовый офис", "Киров, ул. Серова, д.2", "+7(8532)45-45-45", true, organizationRepository.findOrganizationByName("Тестовая организация"));
        officeRepository.save(office);

        HttpEntity entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange("/api/office/" + 5000, HttpMethod.GET, entity, String.class);
        String expected = "{\"error\": \"С Id = " + 5000 + ", офисов не найдено! Введите существующий Id.\"}";
        String result = response.getBody();
        assertEquals(expected, result, true);
    }

    @Test
    public void updaterOfficePositiveTest() throws Exception {
        Organization organization = new Organization("Тестовая организация");
        organizationRepository.save(organization);
        Office office = new Office("Тестовый офис", "Киров, ул. Серова, д.2", "+7(8532)45-45-45", true, organizationRepository.findOrganizationByName("Тестовая организация"));
        officeRepository.save(office);
        Long officeId = officeRepository.findOfficeByName(office.getName()).getId();
        String body = "{\"id\":" + officeId + "," +
                "\"name\":\"Новый Тестовый офис\"," +
                "\"address\":\"Самара, ул. Серова, д.3\"," +
                "\"phone\":\"+7(8579)45-45-97\"," +
                "\"isActive\":false}";
        HttpEntity entity = new HttpEntity<>(body, headers);

        ResponseEntity<String> response = restTemplate.exchange("/api/office/update", HttpMethod.POST, entity, String.class);
        String result = response.getBody();
        String expected = "{\"data\":{\"result\":\"success\"}}";
        assertEquals(expected, result, true);
        assertNotNull(officeRepository.findOfficeByName("Новый Тестовый офис"));
    }

    @Test
    public void updaterOfficeNegativeTest() throws Exception {
        Organization organization = new Organization("Тестовая организация");
        organizationRepository.save(organization);
        Office office = new Office("Тестовый офис", "Киров, ул. Серова, д.2", "+7(8532)45-45-45", true, organizationRepository.findOrganizationByName("Тестовая организация"));
        officeRepository.save(office);
        Long officeId = Long.valueOf(5000);
        String body = "{\"id\":" + officeId + "," +
                "\"name\": \"Новый Тестовый офис\"," +
                "\"address\":\"Самара, ул. Серова, д.3\"," +
                "\"phone\":\"+7(8579)45-45-97\"," +
                "\"isActive\":false}";
        HttpEntity entity = new HttpEntity<>(body, headers);

        ResponseEntity<String> response = restTemplate.exchange("/api/office/update", HttpMethod.POST, entity, String.class);
        String result = response.getBody();
        String expected = "{\"error\" : \"Не найден офис с ID: " + officeId + "\"}";
        assertEquals(expected, result, true);
        assertNull(organizationRepository.findOrganizationByName("Новый Тестовый офис"));
    }

    @Test
    public void saveOrganisationPositiveTest() throws Exception {
        Organization organization = new Organization("Тестовая организация");
        organizationRepository.save(organization);
        Long orgId = organizationRepository.findOrganizationByName("Тестовая организация").getId();
        String body = "{\"orgId\":" + orgId + "," +
                "\"name\":\"Тестовый офис\"," +
                "\"phone\":\"+7(8532)45-45-45\"," +
                "\"isActive\":true}";
        HttpEntity entity = new HttpEntity<>(body, headers);

        ResponseEntity<String> response = restTemplate.exchange("/api/office/save", HttpMethod.POST, entity, String.class);
        String result = response.getBody();
        String expected = "{\"data\":{\"result\":\"success\"}}";
        assertEquals(expected, result, true);
        assertNotNull(officeRepository.findOfficeByName("Тестовый офис"));
    }

    @Test
    public void saveOfficeNegativeTest() throws Exception {
        Organization organization = new Organization("Тестовая организация");
        organizationRepository.save(organization);
        Long orgId = Long.valueOf(5000);
        String body = "{\"orgId\":" + orgId + "}";
        HttpEntity entity = new HttpEntity<>(body, headers);

        ResponseEntity<String> response = restTemplate.exchange("/api/office/save", HttpMethod.POST, entity, String.class);
        String result = response.getBody();
        String expected = "{\"error\" : \"Не возможно добавить офис, принадлежащий организациии, с ID: " + orgId + ", отсутствующим в базе\"}";
        assertEquals(expected, result, true);
//        assertNull(organizationRepository.findOrganizationByName(null));
    }

    @Test
    public void deleteOfficePositiveTest() throws Exception {
        Organization organization = new Organization("Тестовая организация");
        organizationRepository.save(organization);
        Office office = new Office("Тестовый офис", "Киров, ул. Серова, д.2", "+7(8532)45-45-45", true, organizationRepository.findOrganizationByName("Тестовая организация"));
        officeRepository.save(office);
        Long officeId = officeRepository.findOfficeByName(office.getName()).getId();
        String body = "{\"id\" : " + officeId + "}";
        HttpEntity entity = new HttpEntity<>(body, headers);

        ResponseEntity<String> response = restTemplate.exchange("/api/office/delete", HttpMethod.POST, entity, String.class);
        String expected = "{\"data\":{\"result\":\"success\"}}";
        String result = response.getBody();
        assertEquals(expected, result, true);
        assertNull(officeRepository.findOfficeByName(office.getName()));
    }

    @Test
    public void deleteOfficeNegativeTest() throws Exception {
        Organization organization = new Organization("Тестовая организация");
        organizationRepository.save(organization);
        Office office = new Office("Тестовый офис", "Киров, ул. Серова, д.2", "+7(8532)45-45-45", true, organizationRepository.findOrganizationByName("Тестовая организация"));
        officeRepository.save(office);

        String body = "{\"id\" : 5000}";
        HttpEntity entity = new HttpEntity<>(body, headers);

        ResponseEntity<String> response = restTemplate.exchange("/api/office/delete", HttpMethod.POST, entity, String.class);

        String expected = "{\"error\": \"С данным Id, офис не найден!\"}";
        String result = response.getBody();
        assertEquals(expected, result, true);
        assertNotNull(officeRepository.findOfficeByName(office.getName()));
    }

}
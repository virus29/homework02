package com.i.homework02.controller;

import com.i.homework02.Homework02Application;
import com.i.homework02.entity.Office;
import com.i.homework02.entity.Organization;
import com.i.homework02.repository.OfficeRepository;
import com.i.homework02.repository.OrganizationRepository;
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
    }

    @After
    public void after(){
        organizationRepository.deleteAll();
        officeRepository.deleteAll();
    }

    @Test
    public void searchOfficePositiveTest() throws Exception {
        organizationRepository.deleteAll();
        officeRepository.deleteAll();
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

        OfficeListViewRequest officeListViewRequest = new OfficeListViewRequest();
        officeListViewRequest.setOrgId(5000L);

        HttpEntity entity = new HttpEntity<>(officeListViewRequest, headers);
        ResponseEntity<NegativeResponseView> response = restTemplate.exchange("/api/office/list", HttpMethod.POST, entity, NegativeResponseView.class);

        NegativeResponseView result = response.getBody();
        NegativeResponseView expected = new  NegativeResponseView("По заданным параметрам ничего не найдено");

        Assert.assertEquals(expected, result);
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
        ResponseEntity<DataView<OfficeViewResponse>> response = restTemplate.exchange("/api/office/" + officeId, HttpMethod.GET, entity, new ParameterizedTypeReference<DataView<OfficeViewResponse>>() {});

        OfficeViewResponse officeViewResponse = new OfficeViewResponse();
        officeViewResponse.setId(officeId);
        officeViewResponse.setName("Тестовый офис");
        officeViewResponse.setAddress("Киров, ул. Серова, д.2");
        officeViewResponse.setPhone("+7(8532)45-45-45");
        officeViewResponse.setActive(true);
        DataView<OfficeViewResponse> expected=new DataView(officeViewResponse);

        DataView<OfficeViewResponse> result = response.getBody();
        Assert.assertEquals(expected, result);
    }

    @Test
    public void findOfficeByIdNegativeTest() throws Exception {
        Organization organization = new Organization("Тестовая организация");
        organizationRepository.save(organization);
        Office office = new Office("Тестовый офис", "Киров, ул. Серова, д.2", "+7(8532)45-45-45", true, organizationRepository.findOrganizationByName("Тестовая организация"));
        officeRepository.save(office);

        HttpEntity entity = new HttpEntity<>(headers);
        ResponseEntity<NegativeResponseView> response = restTemplate.exchange("/api/office/" + 5000, HttpMethod.GET, entity, NegativeResponseView.class);

        NegativeResponseView result = response.getBody();
        NegativeResponseView expected = new  NegativeResponseView("С Id = " + 5000 + ", офисов не найдено! Введите существующий Id.");

        Assert.assertEquals(expected, result);
    }

    @Test
    public void updaterOfficePositiveTest() throws Exception {
        Organization organization = new Organization("Тестовая организация");
        organizationRepository.save(organization);
        Office office = new Office("Тестовый офис", "Киров, ул. Серова, д.2", "+7(8532)45-45-45", true, organizationRepository.findOrganizationByName("Тестовая организация"));
        officeRepository.save(office);
        Long officeId = officeRepository.findOfficeByName(office.getName()).getId();

        OfficeUpdateViewRequest officeUpdateViewRequest =new OfficeUpdateViewRequest();
        officeUpdateViewRequest.setId(officeId);
        officeUpdateViewRequest.setName("Новый Тестовый офис");
        officeUpdateViewRequest.setAddress("Самара, ул. Серова, д.3");
        officeUpdateViewRequest.setPhone("+7(8579)45-45-97");
        officeUpdateViewRequest.setActive(false);

        HttpEntity entity = new HttpEntity<>(officeUpdateViewRequest, headers);

        ResponseEntity<PositiveResponseView> response = restTemplate.exchange("/api/office/update", HttpMethod.POST, entity, PositiveResponseView.class);
        PositiveResponseView result = response.getBody();
        PositiveResponseView expected = new PositiveResponseView();
        Assert.assertEquals(expected, result);
        assertNotNull(officeRepository.findOfficeByName("Новый Тестовый офис"));
    }

    @Test
    public void updaterOfficeNegativeTest() throws Exception {
        Organization organization = new Organization("Тестовая организация");
        organizationRepository.save(organization);
        Office office = new Office("Тестовый офис", "Киров, ул. Серова, д.2", "+7(8532)45-45-45", true, organizationRepository.findOrganizationByName("Тестовая организация"));
        officeRepository.save(office);
        Long officeId = Long.valueOf(5000);

        OfficeUpdateViewRequest officeUpdateViewRequest =new OfficeUpdateViewRequest();
        officeUpdateViewRequest.setId(officeId);
        officeUpdateViewRequest.setName("Новый Тестовый офис");
        officeUpdateViewRequest.setAddress("Самара, ул. Серова, д.3");
        officeUpdateViewRequest.setPhone("+7(8579)45-45-97");
        officeUpdateViewRequest.setActive(false);

        HttpEntity entity = new HttpEntity<>(officeUpdateViewRequest, headers);

        ResponseEntity<NegativeResponseView> response = restTemplate.exchange("/api/office/update", HttpMethod.POST, entity, NegativeResponseView.class);

        NegativeResponseView result = response.getBody();
        NegativeResponseView expected = new  NegativeResponseView("Не найден офис с ID: " + officeId);

        Assert.assertEquals(expected, result);
        assertNull(organizationRepository.findOrganizationByName("Новый Тестовый офис"));
    }

    @Test
    public void saveOfficePositiveTest() throws Exception {
        Organization organization = new Organization("Тестовая организация");
        organizationRepository.save(organization);
        Long orgId = organizationRepository.findOrganizationByName("Тестовая организация").getId();

        OfficeSaveViewRequest officeSaveViewRequest=new OfficeSaveViewRequest();
        officeSaveViewRequest.setOrgId(orgId);
        officeSaveViewRequest.setName("Тестовый офис");
        officeSaveViewRequest.setPhone("+7(8532)45-45-45");
        officeSaveViewRequest.setActive(true);

        HttpEntity entity = new HttpEntity<>(officeSaveViewRequest, headers);

        ResponseEntity<PositiveResponseView> response = restTemplate.exchange("/api/office/save", HttpMethod.POST, entity, PositiveResponseView.class);
        PositiveResponseView result = response.getBody();
        PositiveResponseView expected = new PositiveResponseView();
        Assert.assertEquals(expected, result);
        assertNotNull(officeRepository.findOfficeByName("Тестовый офис"));
    }

    @Test
    public void saveOfficeNegativeTest() throws Exception {
        Organization organization = new Organization("Тестовая организация");
        organizationRepository.save(organization);
        Long orgId = Long.valueOf(5000);
        OfficeSaveViewRequest officeSaveViewRequest=new OfficeSaveViewRequest();
        officeSaveViewRequest.setOrgId(orgId);
        HttpEntity entity = new HttpEntity<>(officeSaveViewRequest, headers);

        ResponseEntity<NegativeResponseView> response = restTemplate.exchange("/api/office/save", HttpMethod.POST, entity, NegativeResponseView.class);

        NegativeResponseView result = response.getBody();
        NegativeResponseView expected = new  NegativeResponseView("Не возможно добавить офис, принадлежащий организациии, с ID: " + orgId + ", отсутствующим в базе");

        Assert.assertEquals(expected, result);

//        assertNull(organizationRepository.findOrganizationByName(null));
    }

    @Test
    public void deleteOfficePositiveTest() throws Exception {
        Organization organization = new Organization("Тестовая организация");
        organizationRepository.save(organization);
        Office office = new Office("Тестовый офис", "Киров, ул. Серова, д.2", "+7(8532)45-45-45", true, organizationRepository.findOrganizationByName("Тестовая организация"));
        officeRepository.save(office);
        Long officeId = officeRepository.findOfficeByName(office.getName()).getId();
        OfficeDeleteViewRequest officeDeleteViewRequest=new OfficeDeleteViewRequest();
        officeDeleteViewRequest.setId(officeId);
        HttpEntity entity = new HttpEntity<>(officeDeleteViewRequest, headers);

        ResponseEntity<PositiveResponseView> response = restTemplate.exchange("/api/office/delete", HttpMethod.POST, entity, PositiveResponseView.class);
        PositiveResponseView result = response.getBody();
        PositiveResponseView expected = new PositiveResponseView();
        Assert.assertEquals(expected, result);
        assertNull(officeRepository.findOfficeByName(office.getName()));
    }

    @Test
    public void deleteOfficeNegativeTest() throws Exception {
        Organization organization = new Organization("Тестовая организация");
        organizationRepository.save(organization);
        Office office = new Office("Тестовый офис", "Киров, ул. Серова, д.2", "+7(8532)45-45-45", true, organizationRepository.findOrganizationByName("Тестовая организация"));
        officeRepository.save(office);

        OfficeDeleteViewRequest officeDeleteViewRequest = new OfficeDeleteViewRequest();
        officeDeleteViewRequest.setId(5000L);

        HttpEntity entity = new HttpEntity<>(officeDeleteViewRequest, headers);

        ResponseEntity<NegativeResponseView> response = restTemplate.exchange("/api/office/delete", HttpMethod.POST, entity, NegativeResponseView.class);

        NegativeResponseView result = response.getBody();
        NegativeResponseView expected = new  NegativeResponseView("С данным Id, офис не найден!");

        Assert.assertEquals(expected, result);

        assertNotNull(officeRepository.findOfficeByName(office.getName()));
    }
}
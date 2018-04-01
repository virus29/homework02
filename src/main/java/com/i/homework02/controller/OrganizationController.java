package com.i.homework02.controller;


import com.i.homework02.entity.Organization;
import com.i.homework02.exeption.CustomOrganizationException;
import com.i.homework02.service.impl.OrganizationServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.i.homework02.view.*;


import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;


@RestController
@RequestMapping(value = "/api/organization")
public class OrganizationController {

    private final Logger logger = LoggerFactory.getLogger(OrganizationController.class);

    @Autowired
    private OrganizationServiceImpl organizationServiceImpl;

    /**
     * Поиск организации по нескольким параметрам
     * @param orgListViewRequest - объект содержащий параметры для поиска
     * @return список организаций подходящие критериям поиска
     */
    @PostMapping(value = "/list")
    public ResponseEntity searchOrganization(@RequestBody @Valid OrgListViewRequest orgListViewRequest) throws CustomOrganizationException, ParseException {
        Organization organization=organizationServiceImpl.convertToEntity(orgListViewRequest);
        List<OrgListViewResponse> listOrganizations = organizationServiceImpl.search(organization);
        DataView<List<OrgListViewResponse>> dataView = new DataView<>(listOrganizations);
        return new ResponseEntity<>(dataView, HttpStatus.FOUND);
    }

    /**
     * Поиск по Id организации
     * @param id - Id организации
     * @return - Организация найденная по id
     */
    @GetMapping(path = "/{id}")
    public ResponseEntity findOrganizationById(@PathVariable Long id) throws CustomOrganizationException {
        OrgViewResponse orgViewResponse = organizationServiceImpl.findById(id);
        DataView<OrgViewResponse> dataView = new DataView<>(orgViewResponse);
        return new ResponseEntity<>(dataView, HttpStatus.FOUND);
    }

    /**
     * Изменение(обновление) организации
     * @param orgViewRequest - объект содержащий параметры для обновления
     */
    @PostMapping(value = "/update")
    public ResponseEntity updaterOrganization(@RequestBody @Valid OrgViewRequest orgViewRequest) throws CustomOrganizationException, ParseException {
        Organization organization=organizationServiceImpl.convertToEntity(orgViewRequest);
        organizationServiceImpl.update(organization);
        return new ResponseEntity<>(new PositiveResponseView(), HttpStatus.OK);
    }

    /**
     * Сохранение организации
     * @param orgViewRequest - объект содержащий параметры для сохранения
     */
    @PostMapping(value = "/save")
    public ResponseEntity saveOrganization(@RequestBody @Valid OrgViewRequest orgViewRequest) throws CustomOrganizationException, ParseException {
        Organization organization=organizationServiceImpl.convertToEntity(orgViewRequest);
        organizationServiceImpl.save(organization);
        return new ResponseEntity<>(new PositiveResponseView(), HttpStatus.CREATED);
    }

    /**
     * Удаление организации
     * @param orgViewRequest - объект содержащий id организации
     */
    @PostMapping(value = "/delete")
    public ResponseEntity deleteOrganization(@RequestBody OrgViewRequest orgViewRequest) throws CustomOrganizationException, ParseException {
        Organization organization=organizationServiceImpl.convertToEntity(orgViewRequest);
        organizationServiceImpl.delete(organization);
        return new ResponseEntity<>(new PositiveResponseView(), HttpStatus.OK);
    }
}

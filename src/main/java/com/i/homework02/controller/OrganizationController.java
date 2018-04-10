package com.i.homework02.controller;


import com.i.homework02.entity.Organization;
import com.i.homework02.exeption.CustomOrganizationException;

import com.i.homework02.service.OrganizationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(value = "/api/organization", description = "Операции над организациями")
@RequestMapping(value = "/api/organization")

public class OrganizationController {

    private final Logger logger = LoggerFactory.getLogger(OrganizationController.class);

    @Autowired
    private OrganizationService organizationService;

    /**
     * Поиск организации по нескольким параметрам
     * @param orgListViewRequest - объект содержащий параметры для поиска
     * @return список организаций подходящие критериям поиска
     */
    @PostMapping(value = "/list")
    @ApiOperation(value = "Поиск организации по нескольким параметрам")
    public ResponseEntity searchOrganization(@RequestBody @Valid OrgListViewRequest orgListViewRequest) throws CustomOrganizationException, ParseException {
        List<OrgListViewResponse> listOrganizations = organizationService.search(orgListViewRequest);
        DataView<List<OrgListViewResponse>> dataView = new DataView<>(listOrganizations);
        return new ResponseEntity<>(dataView, HttpStatus.FOUND);
    }

    /**
     * Поиск по Id организации
     * @param id - Id организации
     * @return - Организация найденная по id
     */
    @GetMapping(path = "/{id}")
    @ApiOperation(value = "Поиск организации по нескольким параметрам")
    public ResponseEntity findOrganizationById(@PathVariable Long id) throws CustomOrganizationException {
        OrgViewResponse orgViewResponse = organizationService.findById(id);
        DataView<OrgViewResponse> dataView = new DataView<>(orgViewResponse);
        return new ResponseEntity<>(dataView, HttpStatus.FOUND);
    }

    /**
     * Изменение(обновление) организации
     * @param orgViewRequest - объект содержащий параметры для обновления
     */
    @PostMapping(value = "/update")
    @ApiOperation(value = "Изменение(обновление) организации")
    public ResponseEntity updaterOrganization(@RequestBody @Valid OrgViewRequest orgViewRequest) throws CustomOrganizationException, ParseException {
        organizationService.update(orgViewRequest);
        return new ResponseEntity<>(new PositiveResponseView(), HttpStatus.OK);
    }

    /**
     * Сохранение организации
     * @param orgSaveViewRequest - объект содержащий параметры для сохранения
     */
    @PostMapping(value = "/save")
    @ApiOperation(value = "Сохранение организации")
    public ResponseEntity saveOrganization(@RequestBody @Valid OrgSaveViewRequest orgSaveViewRequest) throws CustomOrganizationException, ParseException {
        organizationService.save(orgSaveViewRequest);
        return new ResponseEntity<>(new PositiveResponseView(), HttpStatus.CREATED);
    }

    /**
     * Удаление организации
     * @param orgViewRequest - объект содержащий id организации
     */
    @PostMapping(value = "/delete")
    @ApiOperation(value = "Удаление организации")
    public ResponseEntity deleteOrganization(@RequestBody OrgViewRequest orgViewRequest) throws CustomOrganizationException, ParseException {
        organizationService.delete(orgViewRequest);
        return new ResponseEntity<>(new PositiveResponseView(), HttpStatus.OK);
    }
}

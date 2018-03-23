package com.i.homework02.controller;


import com.i.homework02.entity.Organization;
import com.i.homework02.exeption.CustomOrganizationException;
import com.i.homework02.servise.serviceImpl.OrganizationServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.i.homework02.view.*;


import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping(value = "/api/organization", produces = MediaType.APPLICATION_JSON_VALUE)
public class OrganizationController {

    private final Logger logger = LoggerFactory.getLogger(OrganizationController.class);

    @Autowired
    private OrganizationServiceImpl organizationServiceImpl;

    //Поиск по officeId, firstName, secondName, middleName, position, docCode, citizenshipCode параметрам
    @PostMapping(value = "/list")
    public ResponseEntity searchOrganization(@RequestBody @Valid OrgListViewIn orgListViewIn) throws CustomOrganizationException {
        List<OrgListViewOut> listOrganizations = organizationServiceImpl.search(orgListViewIn);
        DataView<List<OrgListViewOut>> dataView = new DataView<>(listOrganizations);
        return new ResponseEntity<>(dataView, HttpStatus.FOUND);
    }

    //Поиск Id
    @GetMapping(path = "/{id}")
    public ResponseEntity findOrganizationById(@PathVariable Long id) throws CustomOrganizationException {
        OrgIdViewOut orgIdViewOut = organizationServiceImpl.findById(id);
        DataView<OrgIdViewOut> dataView = new DataView<>(orgIdViewOut);
        return new ResponseEntity<>(dataView, HttpStatus.FOUND);
    }


    //Изменение(обновление)
    @PostMapping(value = "/update")
    public ResponseEntity updaterOrganization(@RequestBody @Valid OrgViewIn orgViewIn) throws CustomOrganizationException {
        organizationServiceImpl.update(orgViewIn);
        return new ResponseEntity<>(new PositiveResponseView(), HttpStatus.OK);

    }


    //Сохранение
    @PostMapping(value = "/save")
    public ResponseEntity saveOrganization(@RequestBody @Valid OrgViewIn orgViewIn) throws CustomOrganizationException {
        organizationServiceImpl.save(orgViewIn);
        return new ResponseEntity<>(new PositiveResponseView(), HttpStatus.CREATED);
    }

    //Удаление
    @PostMapping(value = "/delete")
    public ResponseEntity deleteOrganization(@RequestBody OrgViewIn orgViewIn) throws CustomOrganizationException {
        organizationServiceImpl.delete(orgViewIn);
        return new ResponseEntity<>(new PositiveResponseView(), HttpStatus.OK);
    }
}

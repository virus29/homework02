package com.i.homework02.organization;


import com.fasterxml.jackson.annotation.JsonView;
import com.i.homework02.office.OfficeView;
import com.i.homework02.user.UserView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;



@RestController
@RequestMapping(value = "/api/organization", produces = MediaType.APPLICATION_JSON_VALUE)
public class OrganizationController {

    private final Logger logger = LoggerFactory.getLogger(OrganizationController.class);

    @Autowired
    private OrganizationServiceImpl organizationServiceImpl;

    //Поиск по officeId, firstName, secondName, middleName, position, docCode, citizenshipCode параметрам
    @JsonView()
    @PostMapping(value = "/list")
    @ResponseStatus(HttpStatus.FOUND)
    public @ResponseBody
    List<Organization> searchUser(@Valid Organization organization){
//        logger.info(user.toString());
        return organizationServiceImpl.search(organization.getName(), organization.getInn(), organization.getActive());
    }

    //Поиск Id
    @JsonView(OrganizationView.FindById.class)
    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public @ResponseBody
    Organization findOfficeById(@PathVariable Long id) {
        return organizationServiceImpl.findById(id);
    }

    //Изменение(обновление)
    @PostMapping(value = "/update")
    @ResponseStatus(HttpStatus.UPGRADE_REQUIRED)
    public @ResponseBody
    void updaterUser(Organization organization){
//        logger.info(user.toString());
         organizationServiceImpl.update(organization);
    }

    //Сохранение
    @PostMapping(value = "/save")
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody
    void saveOrganization(@Valid Organization organization){
//        logger.info(user.toString());
         organizationServiceImpl.save(organization);
    }

    //Удаление
    @PostMapping(value = "/delete")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    void updaterser(@Valid Organization organization){
//        logger.info(user.toString());
        organizationServiceImpl.delete(organization);
    }
}

package com.i.homework02.organization;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/organization", produces = MediaType.APPLICATION_JSON_VALUE)
public class OrganizationController {
    final Logger logger = LoggerFactory.getLogger(OrganizationController.class);

    @Autowired
    private OrganizationService organizationService;

    @PostMapping(value = "/list")
    @ResponseStatus(HttpStatus.FOUND)
    public @ResponseBody
    List<Organization> search(
            @RequestParam(required = true) String organizationName,
            @RequestParam(required = false) int organizationInn,
            @RequestParam(required = false) boolean organizationIsactive
    ){
        return organizationService.search(organizationName, organizationInn, organizationIsactive);
    }

    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public @ResponseBody
    Organization findOrganizationById(@PathVariable Long id) {
        return organizationService.findById(id);
    }

    @PostMapping(value = "/save")
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody
    Organization create(@RequestBody Organization organization) {
        organizationService.save(organization);
        return organization;
    }
}
//4. api/organization/list
//        In (фильтр):
//        {
//        “name”:”organizationName”, //обязательный параметр
//        “inn”:”organizationInn”,
//        “isActive”:”organizationIsactive”
//        }
//        Out:
//        [
//        {
//        “id”:””,
//        “name”:””,
//        “isActive”:”true”
//        },
//        ...
//        ]
//
//        5. api/organization/{id:.+}
//        method:GET
//        Out:
//        {
//        “id”:””,
//        “name”:””,
//        “fullName”:””,
//        “inn”:””,
//        “kpp”:””,
//        “address”:””,
//        “phone”,””,
//        “isActive”:”true”
//        }
//
//        6. api/organization/update
//        In:
//        {
//        “id”:””,
//        “name”:””,
//        “fullName”:””,
//        “inn”:””,
//        “kpp”:””,
//        “address”:””,
//        “phone”,””,
//        “isActive”:”true”
//        }
//        Out: {“result”:”success”}
//
//        6. api/organization/save
//        In:
//        {
//        “name”:””,
//        “fullName”:””,
//        “inn”:””,
//        “kpp”:””,
//        “address”:””,
//        “phone”,””,
//        “isActive”:”true”
//        }
//        Out: {“result”:”success”}
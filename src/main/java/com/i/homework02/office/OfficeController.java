package com.i.homework02.office;

import com.fasterxml.jackson.annotation.JsonView;
import com.i.homework02.organization.OrganizationController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Entity;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/office", produces = MediaType.APPLICATION_JSON_VALUE)
public class OfficeController {

    final Logger logger = LoggerFactory.getLogger(OrganizationController.class);

    @Autowired
    private OfficeServiceImpl officeServiceImpl;

    @JsonView(OfficeView.OfficeFindByOrgidOffnameOffPhoneOffisactive.class)
    @PostMapping(value = "/list")
    @ResponseStatus(HttpStatus.FOUND)
    public @ResponseBody
    List<Office> searchOffice(@Valid Office office){
        logger.info(office.toString());
        return officeServiceImpl.searchOffice(office.getOrganization().getId(), office.getName(), office.getPhone(), office.getActive());
    }

    @JsonView(OfficeView.OfficeFindById.class)
    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public @ResponseBody
    Office findOfficeById(@PathVariable Long id) {
        return officeServiceImpl.findById(id);
    }

//    @JsonView(OfficeView.OfficeFindById.class)
//    @PostMapping(value = "/update")
//    public
//    Entity officeUpdate (@RequestBody @Valid Office office) {
//        officeServiceImpl.update(office);
//        return new ResponseEntity<>()@ResponseStatus(HttpStatus.UPGRADE_REQUIRED)
//    }

    @JsonView(OfficeView.OfficeFindById.class)
    @PostMapping(value = "/save")
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody
    Office officeSave (@PathVariable Office office) {
        return officeServiceImpl.save(office);
    }

    @JsonView(OfficeView.OfficeFindById.class)
    @PostMapping(value = "/delete")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    void officeDelete (@PathVariable Office office) {
        officeServiceImpl.delete(office);
    }
}


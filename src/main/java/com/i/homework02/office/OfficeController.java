package com.i.homework02.office;

import com.fasterxml.jackson.annotation.JsonView;
import com.i.homework02.organization.OrganizationController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/office", produces = MediaType.APPLICATION_JSON_VALUE)
public class OfficeController {

    final Logger logger = LoggerFactory.getLogger(OrganizationController.class);

    @Autowired
    private OfficeService officeService;

    @JsonView({OfficeView.OfficeFindByOrgidOffnameOffPhoneOffisactive.class})
    @PostMapping(value = "/list")
    @ResponseStatus(HttpStatus.FOUND)
    public @ResponseBody
    List<Office> searchOffice(@RequestBody @Valid Office office){
        logger.info(office.toString());
        return officeService.searchOffice(office.getOrganization().getId(), office.getOfficeName(), office.getOfficePhone(), office.isOfficeIsactive());
    }

    @JsonView(OfficeView.OfficeFindById.class)
    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public @ResponseBody
    Office findOfficeById(@PathVariable Long id) {
        return officeService.findById(id);
    }
}




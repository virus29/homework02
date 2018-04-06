package com.i.homework02.controller;

import com.i.homework02.repository.CountryRepository;
import com.i.homework02.entity.Country;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(value = "/api/countries", description = "Операции над список названий и кодов стран")
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)

public class CountryController {

    @Autowired
    CountryRepository countryRepository;

    @GetMapping("/countries")
    @ApiOperation(value = "получение списка названий и кодов стран")
    List<Country> countries() {
        return this.countryRepository.findAll();
    }
}

package com.i.homework02.controller;

import com.i.homework02.repository.CountryRepository;
import com.i.homework02.entity.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class CountryController {

    @Autowired
    CountryRepository countryRepository;

    @RequestMapping("/countries")
    List<Country> countries() {
        return this.countryRepository.findAll();
    }
}

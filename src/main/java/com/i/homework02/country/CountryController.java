package com.i.homework02.country;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class CountryController {
    @Autowired
    CountryRepository countryRepository;

    @RequestMapping("/api/countries")
    List<Country> countries() {
        return this.countryRepository.findAll();
    }
}

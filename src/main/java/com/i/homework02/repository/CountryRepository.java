package com.i.homework02.repository;

import com.i.homework02.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Long> {
    Country findCountryByCode(String code);
}

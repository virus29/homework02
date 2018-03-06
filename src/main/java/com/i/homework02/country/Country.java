package com.i.homework02.country;

import com.i.homework02.user.User;

import javax.persistence.*;
import java.util.List;

@Entity(name = "Country")
public class Country {

    /**
     * Служебное поле hibernate
     */
    @Version
    private Integer version;

    /**
     * Код страны
     */
    @Id
    @Basic(optional = false)
    private int countryCode;

    /**
     * Название страны
     */
    @Basic(optional = false)
    private String countryName;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "country", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<User> users;

    public int getCountryCode() {
        return countryCode;
    }

    public String getCountryName() {
        return countryName;
    }
}


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
    private Long countryCode;

    /**
     * Название страны
     */
    @Basic(optional = false)
    private String countryName;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "country", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<User> users;

    public Long getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(Long countryCode) {
        this.countryCode = countryCode;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryName() {
        return countryName;
    }
}


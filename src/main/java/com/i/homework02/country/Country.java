package com.i.homework02.country;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.i.homework02.user.User;
import com.i.homework02.user.UserView;

import javax.persistence.*;
import java.util.List;

@Entity(name = "Country")
public class Country {

    /**
     * Служебное поле hibernate
     */
    @Version
    private Integer version=0;

    /**
     * Код страны
     */
    @Id
    @Basic(optional = false)
    private String code;

    /**
     * Название страны
     */
    @Basic(optional = false)
    private String name;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}


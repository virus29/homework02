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
    @Column(name = "country_code")
    private int code;

    /**
     * Название страны
     */
    @Basic(optional = false)
    @Column(name = "country_name")
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "country", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<User> users;

    public Country() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}


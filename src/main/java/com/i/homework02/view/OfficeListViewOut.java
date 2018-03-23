package com.i.homework02.view;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Version;

public class OfficeListViewOut {

    //Id офиса
    private Long id;

    //Служебное поле hibernate
    @Version
    private Integer version=1;

    // Название офиса
    private String name;

    //Активен ли офис
    private Boolean isActive;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getActive() {
        return isActive;
    }

    @JsonProperty(value = "isActive")
    public void setActive(Boolean active) {
        isActive = active;
    }
}

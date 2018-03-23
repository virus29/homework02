package com.i.homework02.view;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OrgListViewOut {

    //Id организации
    private Long id;

    // Краткое название организации
    private String name;

    //Активная ли организация
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

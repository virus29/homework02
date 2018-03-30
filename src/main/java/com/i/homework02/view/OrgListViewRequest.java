package com.i.homework02.view;

import javax.validation.constraints.NotNull;

public class OrgListViewRequest {

    /**
     * Краткое название организации
     */
    @NotNull
    private String name;

    /**
     * ИНН организации
     */
    private String inn;

    /**
     * Активная ли организация
     */
    private Boolean isActive;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}

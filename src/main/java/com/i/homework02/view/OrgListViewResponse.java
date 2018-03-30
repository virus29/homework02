package com.i.homework02.view;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class OrgListViewResponse {

    /**
     * Id организации
     */
    private Long id;

    /**
     * Краткое название организации
     */
    private String name;

    /**
     * Активная ли организация
     */
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrgListViewResponse that = (OrgListViewResponse) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(isActive, that.isActive);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, isActive);
    }
}

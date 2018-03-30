package com.i.homework02.view;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Version;
import java.util.Objects;

public class OfficeListViewResponse extends OfficeDeleteViewRequest{

    // Название офиса
    private String name;

    //Активен ли офис
    private Boolean isActive;

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
        if (!(o instanceof OfficeListViewResponse)) return false;
        OfficeListViewResponse that = (OfficeListViewResponse) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(isActive, that.isActive);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, isActive);
    }
}

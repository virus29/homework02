package com.i.homework02.view;

import javax.validation.constraints.NotNull;
import java.util.Objects;

public class OrgSaveViewRequest {

    // Краткое название организации
    private String name;

    //Полное название оранизации
    private String fullName;

    //ИНН организации
    private String inn;

    //КПП организации
    private String kpp;

    //Адрес организации
    private String address;

    //Телефон организации
    private String phone;

    //Активная ли организация
    private Boolean isActive;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public String getKpp() {
        return kpp;
    }

    public void setKpp(String kpp) {
        this.kpp = kpp;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrgSaveViewRequest)) return false;
        OrgSaveViewRequest that = (OrgSaveViewRequest) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(fullName, that.fullName) &&
                Objects.equals(inn, that.inn) &&
                Objects.equals(kpp, that.kpp) &&
                Objects.equals(address, that.address) &&
                Objects.equals(phone, that.phone) &&
                Objects.equals(isActive, that.isActive);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, fullName, inn, kpp, address, phone, isActive);
    }
}

package com.i.homework02.view;

import javax.persistence.Version;

public class OfficeSaveViewIn {

    //Id организации, которой принадлежит офис
    private Long orgId;

    // Название офиса
    private String name;

    //Адрес
    private String address;

    //Телефон
    private String phone;

    //Активен ли офис
    private Boolean isActive;

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}

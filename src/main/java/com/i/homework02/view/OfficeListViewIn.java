package com.i.homework02.view;

import javax.persistence.Version;
import javax.validation.constraints.NotNull;

public class OfficeListViewIn {

    @NotNull
    //Id организации
    private Long orgId;

    // Название офиса
    private String name;

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

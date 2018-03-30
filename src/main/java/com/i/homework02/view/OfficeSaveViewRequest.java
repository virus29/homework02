package com.i.homework02.view;

import javax.persistence.Version;

public class OfficeSaveViewRequest extends OfficeListViewRequest{

    /**
     * Адрес
     */
    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

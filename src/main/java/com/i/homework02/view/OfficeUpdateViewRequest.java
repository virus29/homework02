package com.i.homework02.view;


public class OfficeUpdateViewRequest extends OfficeViewResponse{

    private Boolean isActive;

    @Override
    public void setActive(Boolean active) {
        isActive = active;
    }
}

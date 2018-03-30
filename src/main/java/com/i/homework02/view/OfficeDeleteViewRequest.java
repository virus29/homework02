package com.i.homework02.view;

import javax.validation.constraints.NotNull;

public class OfficeDeleteViewRequest {

    /**
     * Id офиса
     */
    @NotNull
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
